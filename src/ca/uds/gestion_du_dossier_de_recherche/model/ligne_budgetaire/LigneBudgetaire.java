package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

import java.util.ArrayList;
import java.util.List;


public class LigneBudgetaire {

	private String nom;
	private String type;
	private List<UBR> ubrs;
	private List<Depense> depenses;
	
	
	public LigneBudgetaire() {
		super();
		this.nom = null;
		this.type = null;
		this.ubrs = new ArrayList<>();
		this.depenses = new ArrayList<>();
	}
	
	public LigneBudgetaire(String nom, String type, List<UBR> ubrs, List<Depense> depenses) {
		super();
		this.nom = nom;
		this.type = type;
		this.ubrs = ubrs;
		this.depenses = depenses;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the ubrs
	 */
	public List<UBR> getUbrs() {
		return ubrs;
	}
	/**
	 * @param ubrs the ubrs to set
	 */
	public void setUbrs(List<UBR> ubrs) {
		this.ubrs = ubrs;
	}
	/**
	 * @return the depenses
	 */
	public List<Depense> getDepenses() {
		return depenses;
	}
	/**
	 * @param depenses the depenses to set
	 */
	public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	}
	
	
	
	public float getMontantLigne()
	{
		float somme = 0.0f;
		for(Depense d : depenses)
		{
			somme -= d.getMontant();
		}
		for(UBR u : ubrs)
		{
			somme += u.getMontant(this);
		}
		return somme;
	}
	
}
