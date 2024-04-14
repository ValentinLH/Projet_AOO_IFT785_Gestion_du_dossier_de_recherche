package ca.uds.gestion_du_dossier_de_recherche.view;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.DAO.ProjetDAO;
import ca.uds.gestion_du_dossier_de_recherche.DAO.ressourceDAO;
import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.VueGeneralControler;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import javafx.application.Application;

public class MainView {

	public static void main(String[] args) {
						
		Application.launch(VueGenerale.class, args);

//		listProjet = window.getListeProjet();
//		listRessources = window.getRessourceList();		
	}
	
	
	public static Projet stubProjetaSupprimer()
	{
		
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

		
		LigneBudgetaire ligneBudgetaire4 = get2();
		
		p.addLigneBudgetaire(ligneBudgetaire3);
		p.addLigneBudgetaire(ligneBudgetaire4);


		p.dateLimiteDepenses(10);
		
		return p;
	}
	
	public static LigneBudgetaire get2()
	{
		Organisme monFrigo = new Organisme("Mon Voyage",0);
		UBR ubr1 = new UBR(monFrigo,1,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1));
		UBR ubr2 = new UBR(monFrigo,2,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(3));
		
		LigneBudgetaire ligne1 = new LigneBudgetaire("Ligne Budgetaire de Voyage","Voyage");
		
		Depense depense1 = new Depense("Cologne",299f);
		Depense depense2 = new Depense("Edimbourg",103f);
		Depense depense3 = new Depense("Vérone",312f);
		
		ligne1.ajouterDepense(depense1);
		ligne1.ajouterDepense(depense2);
		ligne1.ajouterDepense(depense3);
		
		
		ubr1.ajouterLigneBudgetaire(ligne1, 300);
		//ubr2.ajouterLigneBudgetaire(ligne1, 50);
		ligne1.ajouterUBR(ubr2, 500.0f);
		
		return ligne1;
	}

	
}
