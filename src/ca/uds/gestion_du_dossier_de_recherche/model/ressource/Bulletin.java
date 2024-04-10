package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;

public class Bulletin extends Depense {

	
	Ressource ressource;
	LigneBudgetaire ligne;
	LocalDate date;
	
	public Bulletin()
	{
		super();
	}

	/**
	 * @return the ressource
	 */
	public Ressource getRessource() {
		return ressource;
	}

	/**
	 * @param ressource the ressource to set
	 */
	public void setRessource(Ressource ressource) {
		if (this.ressource != null)
			this.ressource.supprimerBulletin(this);
		
		if (ressource != null)
			ressource.ajouterBulletin(this);
		this.ressource = ressource;
	}

	/**
	 * @return the ligne
	 */
	public LigneBudgetaire getLigne() {
		return ligne;
	}

	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(LigneBudgetaire ligne) {
		
		if (this.ligne != null)
			this.ligne.supprimerDepense(this);
		
		if (ligne != null)
			ligne.ajouterDepense(this);
		
		this.ligne = ligne;
		
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public void detruit()
	{
		if (this.ligne != null)
			this.ligne.supprimerDepense(this);
		this.ligne = null;
		this.ressource = null;
	}
	
	
	
	
	
	
	
}
