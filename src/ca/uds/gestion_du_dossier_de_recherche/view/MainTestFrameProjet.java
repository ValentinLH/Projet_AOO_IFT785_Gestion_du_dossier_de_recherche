package ca.uds.gestion_du_dossier_de_recherche.view;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.DAO.ProjetDAO;
import ca.uds.gestion_du_dossier_de_recherche.DAO.ressourceDAO;
import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.ProjetViewController;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import javafx.application.Application;

public class MainTestFrameProjet {

	public static void main(String[] args) {

		Application.launch(ProjetView.class, args);

		Projet p = new Projet("Test", LocalDate.now().minusDays(9), LocalDate.now().plusDays(9));

		Organisme monFrigo = new Organisme("Mon Frigidaire", 0);
		UBR ubr1 = new UBR(monFrigo, 1, true, LocalDate.now().minusDays(9), LocalDate.now().plusDays(9));
		LigneBudgetaire ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
		ligneBudgetaire.ajouterUBR(ubr1, 500f);

		p.addLigneBudgetaire(ligneBudgetaire);

		p.calculMontant(LocalDate.now());

		UBR ubr2 = new UBR(monFrigo, 1, false, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
		LigneBudgetaire ligneBudgetaire2 = new LigneBudgetaire("Ligne Budgetaire de Beurre", "Beurre");
		ligneBudgetaire2.ajouterUBR(ubr2, 1000f);

		p.addLigneBudgetaire(ligneBudgetaire2);
		p.calculMontant(LocalDate.now());

		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);

		p.addLigneBudgetaire(ligneBudgetaire3);

		p.removeLigneBudgetaire(ligneBudgetaire2);

		p.dateLimiteDepenses(10);

		ProjetViewController window = new ProjetViewController(p);

//		listProjet = window.getListeProjet();
//		listRessources = window.getRessourceList();		
	}
}
