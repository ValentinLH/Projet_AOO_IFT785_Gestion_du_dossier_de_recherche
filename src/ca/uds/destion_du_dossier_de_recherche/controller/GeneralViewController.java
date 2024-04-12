package ca.uds.destion_du_dossier_de_recherche.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;

public class GeneralViewController {

	private List<Projet> projetList;
	
	public GeneralViewController() {
		Projet projet;
		Organisme monFrigo;
		UBR ubr1;
		UBR ubr2;
		LigneBudgetaire ligneBudgetaire;
		LigneBudgetaire ligneBudgetaire2;
		
		monFrigo = new Organisme("Mon Frigidaire", 0);
		ubr1 = new UBR(monFrigo, 1, true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));
		ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
		ligneBudgetaire.ajouterUBR(ubr1, 500f);
		projet = new Projet("Title Test", "Test Description", LocalDate.now().minusDays(30),
				LocalDate.now().plusDays(30));

		ubr2 = new UBR(monFrigo, 1, false, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
		ligneBudgetaire2 = new LigneBudgetaire("Ligne Budgetaire de Beurre", "Beurre");
		ligneBudgetaire2.ajouterUBR(ubr2, 1000f);
		
		Ressource ressource = new Soutien("Amin", "Dev", 1, 1, 40.0f, LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 12, 31));
		Ressource ressource2 = new Soutien("Maxime", "Dev", 1, 1, 40.0f, LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 12, 31));

		LocalDate dateDebut = LocalDate.now().minusDays(1);
		LocalDate dateFin = LocalDate.now().plusDays(1);
		
		projet.addLigneBudgetaire(ligneBudgetaire2);
		projet.addLigneBudgetaire(ligneBudgetaire);
		
		projet.addRessourceWithDate(ressource2, dateDebut, dateFin);
		projet.addRessourceWithDate(ressource, dateDebut, dateFin);
		
		projetList = new ArrayList<Projet>();
		projetList.add(projet);
		
		
	}
	
	public List<Projet> getListeProjet() {
		return projetList;
	}
}
