package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LigneBudgetaire {

	private String nom;
	private String type;
	private Set<UBR> ubrs;
	private Set<Depense> depenses;
	
	
	public LigneBudgetaire() {
		super();
		this.nom = null;
		this.type = null;
		this.ubrs = new HashSet<>();
		this.depenses = new HashSet<>();
	}
	
	public LigneBudgetaire(String nom, String type) {
		super();
		this.nom = nom;
		this.type = type;
		this.ubrs = new HashSet<>();
		this.depenses = new HashSet<>();
	}
	
	public LigneBudgetaire(String nom, String type, Set<UBR> ubrs, Set<Depense> depenses) {
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
	public Set<UBR> getUbrs() {
		return ubrs;
	}
	/**
	 * @param ubrs the ubrs to set
	 */
	public void setUbrs(Set<UBR> ubrs) {
		this.ubrs = ubrs;
	}
	/**
	 * @return the depenses
	 */
	public Set<Depense> getDepenses() {
		return depenses;
	}
	/**
	 * @param depenses the depenses to set
	 */
	public void setDepenses(Set<Depense> depenses) {
		this.depenses = depenses;
	}
	
	
	/**
	 * @return le montant de la Ligne
	 */
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
	
	

    /**
     * Ajoute une UBR à l'ensemble des UBRs.
     * @param ubr l'UBR à ajouter
     */
    public void ajouterUBR(UBR ubr) {
        this.ubrs.add(ubr);
    }

    /**
     * Supprime une UBR de l'ensemble des UBRs.
     * @param ubr l'UBR à supprimer
     */
    public void supprimerUBR(UBR ubr) {
        this.ubrs.remove(ubr);
    }

    /**
     * Ajoute une dépense à l'ensemble des dépenses.
     * @param depense la dépense à ajouter
     */
    public void ajouterDepense(Depense depense) {
        this.depenses.add(depense);
    }

    /**
     * Supprime une dépense de l'ensemble des dépenses.
     * @param depense la dépense à supprimer
     */
    public void supprimerDepense(Depense depense) {
        this.depenses.remove(depense);
    }

	@Override
	public String toString() {
		return "LigneBudgetaire [nom=" + nom + ", type=" + type + ", ubrs=" + ubrs + ", depenses=" + depenses + "]";
	}
	
    
}
