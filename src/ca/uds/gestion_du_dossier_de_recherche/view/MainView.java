package ca.uds.gestion_du_dossier_de_recherche.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.DAO.ProjetDAO;
import ca.uds.gestion_du_dossier_de_recherche.DAO.ressourceDAO;
import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import javafx.application.Application;

public class MainView {

	public static void main(String[] args) {
					
		Application.launch(GeneralView.class,args);
		
		List<Projet> listProjet = new ArrayList<Projet>();// ProjetDAO.getAllProjet();
		List<Ressource> listRessources = new ArrayList<Ressource>();//ressourceDAO.getAllRessources();
		
		GeneralViewController window = new GeneralViewController(listProjet,listRessources);
		
		
//		listProjet = window.getListeProjet();
//		listRessources = window.getRessourceList();		
	}
}
