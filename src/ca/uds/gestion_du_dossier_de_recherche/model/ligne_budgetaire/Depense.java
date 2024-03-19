package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

public class Depense {
	
	private String nom;
	private float montant;
	
	
	
	public Depense() {
		super();
		this.nom = null;
		this.montant = 0.0f;
	}
	
	public Depense(String nom, float montant) {
		super();
		this.nom = nom;
		this.montant = montant;
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
	 * @return the montant
	 */
	public float getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(float montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "Depense [nom=" + nom + ", montant=" + montant + "]";
	}

	
	
	
}
