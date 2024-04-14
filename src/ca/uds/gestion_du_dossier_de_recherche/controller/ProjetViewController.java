package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.view.GeneralView;
import ca.uds.gestion_du_dossier_de_recherche.view.ProjetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjetViewController {

    private Projet projet;
    private ProjetView view;
    
    public ProjetViewController() {
        super();
        this.projet = null;
        
    }
    public ProjetViewController(Projet projet) {
        super();
        this.projet = projet;
        view = new ProjetView(this);
        
    }
  

    public ObservableList<LigneBudgetaire> getLignesBudgetaires() {
        // Récupérer les lignes budgétaires associées au projet
        return FXCollections.observableArrayList(projet.getAllLigneBudgetaires());
    }
	/**
	 * @return the projet
	 */
	public Projet getProjet() {
		return projet;
	}
	/**
	 * @param projet the projet to set
	 */
	public void setProjet(Projet projet) {
		this.projet = projet;
	}


    

}
