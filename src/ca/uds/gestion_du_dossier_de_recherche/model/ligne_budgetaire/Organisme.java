package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

public class Organisme {

	private String nom;
	private int code;
	
	
	
	public Organisme() {
		super();
		this.nom = null;
		this.code = 0;
	}
	
	public Organisme(String nom, int code) {
		super();
		this.nom = nom;
		this.code = code;
	}
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
}
