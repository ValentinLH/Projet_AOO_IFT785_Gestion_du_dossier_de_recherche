package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
	public float getMontantLigne(LocalDate date)
	{
		float somme = 0.0f;
		// tout les ubr ou la date choisi est inclue
		Set<UBR> ubrsCompris= ubrs.stream()
                .filter(ubr -> ubr.getDateDebut().isBefore(date) || ubr.getDateDebut().isEqual(date))
                .filter(ubr -> ubr.getDateFin().isAfter(date) || ubr.getDateFin().isEqual(date))
                .collect(Collectors.toSet());

		for(Depense d : depenses)
		{
			somme -= d.getMontant();
		}
		
		
		
		for(UBR u : ubrsCompris)
		{
			somme += u.getMontantaUtilise(this);
		}
		

		Set<UBR> ubrsAvecContraintes = ubrsCompris.stream()
                .filter(ubr -> ubr.isContraintes())
                .collect(Collectors.toSet());
		
		for(UBR u : ubrsAvecContraintes)
		{
			somme += u.getMontant(this);
		}
		
		if (somme > 0)
		{
			somme = 0;
		}
		
		Set<UBR> ubrsSansContraintes = ubrsCompris.stream()
		                .filter(ubr -> !ubr.isContraintes())
		                .collect(Collectors.toSet());
				
		for(UBR u : ubrsSansContraintes)
		{
			somme += u.getMontant(this);
		}
		return somme;
	}
	
	

    /**
     * Ajoute une UBR à l'ensemble des UBRs.
     * @param ubr l'UBR à ajouter
     */
    public void ajouterUBR(UBR ubr, Float montant) {
        if (!ubr.getMontantsLignesBudgetaire().containsKey(this))
        {
        	ubr.ajouterLigneBudgetaire(this, montant);
        }
        this.ubrs.add(ubr);	
    }

    /**
     * Supprime une UBR de l'ensemble des UBRs.
     * @param ubr l'UBR à supprimer
     */
    public void supprimerUBR(UBR ubr) {
        this.ubrs.remove(ubr);
        if (ubr.getMontantsLignesBudgetaire().containsKey(this))
        {
        	ubr.supprimerLigneBudgetaire(this);	
        }
        
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
