package ca.uds.gestion_du_dossier_de_recherche.model.ressource;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;

public abstract class Ressource implements Ventilable {
	private String nom;
	private String prenom;
	private int echelle;
	private int echelon;
	private float heuresHebdo;
	private LocalDate debutContrat;
	private LocalDate finContrat;
	private static GrilleSalariale grilleSalariale;
	private Set<Bulletin> bulletins;

	

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

	public double getTauxHoraire() {
		return grilleSalariale.getTauxHoraire(echelle, echelon);
	}

	public void setEchelleEtEchelon(int echelle, int echelon) {
		this.echelle = echelle;
		this.echelon = echelon;
	}

	@Override
	public float getMontantVentilation(LocalDate date) {
		// TODO Auto-generated method stub
		return 0;
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
		return "Ressource [Nom=" + nom + ", Prenom=" + prenom  +"Heures_hebdo="+ heuresHebdo + ", Debut_contrat=" + debutContrat + ", Fin_contrat=" + finContrat + "]";
	}
}    	
