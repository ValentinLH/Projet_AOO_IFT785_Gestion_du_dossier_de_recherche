package ca.uds.gestion_du_dossier_de_recherche.model.ressource;
import java.time.LocalDate;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
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

	static {
		grilleSalariale = new GrilleSalariale();
	}


	public Ressource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
		this.nom = nom;
		this.prenom = prenom;
		this.echelle = echelle;
		this.echelon = echelon;
		this.heuresHebdo = heuresHebdo;
		this.debutContrat = debutContrat;
		this.finContrat = finContrat;
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
  
  public void travaillePour(Projet P){
  }

  public void estPayePar(Projet P) {
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
		return Fin_contrat;
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

	public double calculerSalaireParJour(Ressource ressource) {
		double tauxHoraire = ressource.getTauxHoraire();
		double heuresParJour = 7;
		return tauxHoraire * heuresParJour;
	}

	public double calculerSalaireBrut(Ressource ressource) {
		int joursOuvrables = UtilitaireDate.calculerJoursOuvrables(ressource.getDebutContrat(), ressource.getFinContrat());
		double salaireParJour = calculerSalaireParJour(ressource);
		return salaireParJour * joursOuvrables;
	}

	public double calculerSalaireEstime(Ressource ressource) {
		double salaireBrut = calculerSalaireBrut(ressource);
		double bonus = salaireBrut * 0.25;
		return salaireBrut + bonus;
	}
  
  @Override
	public String toString() {
		return "Ressource [Nom=" + Nom + ", Prenom=" + Prenom + ", Taux_horaire=" + Taux_horaire + ", Heures_hebdo="
				+ Heures_hebdo + ", Debut_contrat=" + Debut_contrat + ", Fin_contrat=" + Fin_contrat + "]";
	}
    	
