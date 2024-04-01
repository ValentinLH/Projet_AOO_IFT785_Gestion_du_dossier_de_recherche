package ca.uds.gestion_du_dossier_de_recherche.model.ressource;
import java.time.LocalDate;


public abstract class Ressource {
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


	//abstract travaillePour(Projet P);
    //abstract estPayéPar(Projet P);
}