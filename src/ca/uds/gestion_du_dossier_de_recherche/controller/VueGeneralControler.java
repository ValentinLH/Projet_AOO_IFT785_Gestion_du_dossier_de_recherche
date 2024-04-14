package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieDateFinContrat;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieMontant;
import ca.uds.gestion_du_dossier_de_recherche.view.VueGenerale;
import javafx.fxml.FXML;

public class VueGeneralControler {
	
	private List<Projet> projetList;
	private List<Ressource> ressourceList; 
	private VueGenerale view;
	
	public VueGeneralControler() {		
		Projet p1 = new Projet("projet 1 ", LocalDate.now().minusDays(2),LocalDate.now().plusDays(2));
		Projet p2 = new Projet("projet 2 ", LocalDate.now().minusDays(5),LocalDate.now().plusDays(10));
		
		Organisme monFrigo;
		UBR ubr1;
		UBR ubr2;
		LigneBudgetaire ligneBudgetaire;
		LigneBudgetaire ligneBudgetaire2;
		
		monFrigo = new Organisme("Mon Frigidaire", 0);
		ubr1 = new UBR(monFrigo, 1, true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));
		ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
		ligneBudgetaire.ajouterUBR(ubr1, 500f);
		p1 = new Projet("Title Test", "Test Description", LocalDate.now().minusDays(30),
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
		
		p1.addLigneBudgetaire(ligneBudgetaire2);
		p1.addLigneBudgetaire(ligneBudgetaire);
		
		p1.addRessourceWithDate(ressource2, dateDebut, dateFin);
		p1.addRessourceWithDate(ressource, dateDebut, dateFin);
		
		
		this.projetList = new ArrayList<Projet>();
		this.ressourceList = new ArrayList<Ressource>();
		
		this.projetList.add(p1);
		this.projetList.add(p2);
		
		view = new VueGenerale(this);
		
	}
	
	public VueGeneralControler(List<Projet> projetList,List<Ressource> ressourceList) {
		view = new VueGenerale(this);
		this.projetList = projetList;
		this.ressourceList = ressourceList;
	}
	
	public List<Projet> getListeProjet() {
		return projetList;
	}
	
	public List<Ressource> getRessourceList() {
		return ressourceList;
	}
	
	public TrieDateFinContrat getStrategieDate() {
		return new TrieDateFinContrat();
	}
	
	public TrieMontant getStrategieMontant() {
		return new TrieMontant();
	}
}
