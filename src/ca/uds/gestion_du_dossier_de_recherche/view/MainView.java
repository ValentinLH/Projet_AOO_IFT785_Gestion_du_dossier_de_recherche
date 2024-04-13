package ca.uds.gestion_du_dossier_de_recherche.view;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.DAO.ProjetDAO;
import ca.uds.gestion_du_dossier_de_recherche.DAO.ressourceDAO;
import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.VueGeneralControler;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import javafx.application.Application;

public class MainView {

	public static void main(String[] args) {
		
				
		Application.launch(VueGenerale.class, args);
		

		
		
		
//		listProjet = window.getListeProjet();
//		listRessources = window.getRessourceList();		
	}
}
