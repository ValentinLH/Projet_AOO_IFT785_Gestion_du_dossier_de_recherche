package ca.uds.gestion_du_dossier_de_recherche.model.ressource;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet.AffectationProjetRessource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.persistence.Inheritance;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Ressource implements Ventilable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private String nom;
	private String prenom;
	private int echelle;
	private int echelon;
	private float heuresHebdo;
	
	@Column(columnDefinition = "DATE")
	private LocalDate debutContrat;
	
	@Column(columnDefinition = "DATE")
	private LocalDate finContrat;
	
	@Transient
	private static GrilleSalariale grilleSalariale;
	
	@OneToMany(mappedBy = "ressource", cascade = CascadeType.ALL)
	private Set<Bulletin> bulletins;

	

	@OneToMany(mappedBy = "ressource")
    private List<AffectationProjetRessource> affectationsRessource = new ArrayList<>();

	static {
		grilleSalariale = GrilleSalariale.getInstance();
	}


	public Ressource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
		this.nom = nom;
		this.prenom = prenom;
		this.echelle = echelle;
		this.echelon = echelon;
		this.heuresHebdo = heuresHebdo;
		this.debutContrat = debutContrat;
		this.finContrat = finContrat;
		this.bulletins = new HashSet<>();
	}
	
	public Ressource() {

	}

	public double getTauxHoraire() {
		return grilleSalariale.getTauxHoraire(echelon, echelle);
	}

	public void setEchelleEtEchelon(int echelle, int echelon) {
		this.echelle = echelle;
		this.echelon = echelon;
	}

	@Override
	public float getMontantVentilation(LocalDate date) {
		// TODO Auto-generated method stub
		float montantDue = (float) this.calculerSalaireEstime();
		
		
		Set<Bulletin> bulletinPaye = bulletins.stream()
                .filter(bulletin -> bulletin.getDate().isBefore(date) || bulletin.getDate().isEqual(date))
                .collect(Collectors.toSet());
		
		for (Bulletin bulletin : bulletinPaye)
		{
			montantDue -= bulletin.getMontant(); 
		}
		
		return montantDue;
  }


	public double calculSalaireMensuel() {
		double tauxHoraire = getTauxHoraire();
		return (heuresHebdo * tauxHoraire * 4);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getEchelle() {
		return echelle;
	}

	public void setEchelle(int echelle) {
		this.echelle = echelle;
	}

	public int getEchelon() {
		return echelon;
	}

	public void setEchelon(int echelon) {
		this.echelon = echelon;
	}

	public float getHeuresHebdo() {
		return heuresHebdo;
	}

	public void setHeuresHebdo(float heuresHebdo) {
		this.heuresHebdo = heuresHebdo;
	}

	public LocalDate getDebutContrat() {
		return debutContrat;
	}

	public void setDebutContrat(LocalDate debutContrat) {
		this.debutContrat = debutContrat;
	}

	public LocalDate getFinContrat() {
		return finContrat;
  }

  @Override
	public LocalDate getDateFin() {
		return finContrat;
	}



	public void setFinContrat(LocalDate finContrat) {
		this.finContrat = finContrat;
	}

	public static GrilleSalariale getGrilleSalariale() {
		return grilleSalariale;
	}

	public static void setGrilleSalariale(GrilleSalariale grilleSalariale) {
		Ressource.grilleSalariale = grilleSalariale;
	}

	public double calculerSalaireParJour() {
		double tauxHoraire = getTauxHoraire();
		double heuresParJour = 7;
		return tauxHoraire * heuresParJour;
	}

	public double calculerSalaireBrut() {
		int joursOuvrables = UtilitaireDate.calculerJoursOuvrables(getDebutContrat(), getFinContrat());
		double salaireParJour = calculerSalaireParJour();
		return salaireParJour * joursOuvrables;
	}

	public double calculerSalaireEstime() {
		double salaireBrut = calculerSalaireBrut();
		double bonus = salaireBrut * 0.25;
		return salaireBrut + bonus;
	}

	
  /**
	 * @return the bulletins
	 */
	public Set<Bulletin> getBulletins() {
		return bulletins;
	}

	/**
	 * @param bulletins the bulletins to set
	 */
	public void setBulletins(Set<Bulletin> bulletins) {
		this.bulletins = bulletins;
	}

    /**
     * Ajoute un bulletin à l'ensemble des bulletins.
     * @param bulletin le bulletin à ajouter
     */
    public void ajouterBulletin(Bulletin bulletin) {
        this.bulletins.add(bulletin);
    }

    /**
     * Supprime un bulletin de l'ensemble des bulletins.
     * @param bulletin le bulletin à supprimer
     */
    public void supprimerBulletin(Bulletin bulletin) {
    	bulletin.detruit();
        this.bulletins.remove(bulletin);
    }

	
	
  @Override
	public String toString() {
		return "Ressource [Nom=" + nom + ", Prenom=" + prenom  +" Heures_hebdo="+ heuresHebdo + ", Debut_contrat=" + debutContrat + ", Fin_contrat=" + finContrat + "]";
	}




	public List<AffectationProjetRessource> getAffectationsRessource() {
		return affectationsRessource;
	}




	public void setAffectationsRessource(List<AffectationProjetRessource> affectationsRessource) {
		this.affectationsRessource = affectationsRessource;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

}
