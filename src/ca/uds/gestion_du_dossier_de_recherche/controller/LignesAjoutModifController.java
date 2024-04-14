package ca.uds.gestion_du_dossier_de_recherche.controller;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;

public class LignesAjoutModifController {

	private LigneBudgetaire ligne;

	
	public LignesAjoutModifController() {
		super();
		this.ligne = new LigneBudgetaire();
	}

	
	public LignesAjoutModifController(LigneBudgetaire ligne) {
		super();
		this.ligne = ligne;
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
		this.ligne = ligne;
	}
	
	
	
	
}
