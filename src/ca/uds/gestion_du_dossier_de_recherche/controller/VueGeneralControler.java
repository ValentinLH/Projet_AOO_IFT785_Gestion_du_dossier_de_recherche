package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.ResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilation;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieDateFinContrat;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieMontant;
import ca.uds.gestion_du_dossier_de_recherche.view.MainView;
import ca.uds.gestion_du_dossier_de_recherche.view.VueGenerale;
import javafx.event.Event;
import javafx.fxml.FXML;

public class VueGeneralControler {
	
	private List<Projet> projetList;
	private List<Ressource> ressourceList; 
	private VueGenerale view;
	
	public VueGeneralControler() {		
		Projet p1 = MainView.stubProjetaSupprimer();
		p1.setDescription("Description du projet 1");
		Projet p2 = new Projet("projet 2 ", LocalDate.now().minusDays(5),LocalDate.now().plusDays(2));
		p2.setDescription("Description du projet 2");

		

		Organisme monFrigo;
		UBR ubr1;
		UBR ubr2;
		LigneBudgetaire ligneBudgetaire;
		LigneBudgetaire ligneBudgetaire2;
		
		monFrigo = new Organisme("Mon Frigidaire", 0);
		ubr1 = new UBR(monFrigo, 1, true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));
		ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
		ligneBudgetaire.ajouterUBR(ubr1, 500f);

		ubr2 = new UBR(monFrigo, 1, false, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
		ligneBudgetaire2 = new LigneBudgetaire("Ligne Budgetaire de Beurre", "Beurre");
		ligneBudgetaire2.ajouterUBR(ubr2, 1000f);
		
		Ressource ressource = new Soutien("Jean", "Bonneau", 1, 1, 40.0f, LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 12, 31));
		
		Ressource ressource2 = new Soutien("Sylvain", "Hébon", 1, 1, 40.0f, LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 10, 25));
		
		Ressource ressource3 = new Etudiant("Frieren", "Himmel", 1, 1, 40.0f, LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 12, 31),"okko2201",Programme.BACCALAUREAT);
		
		Ressource ressource4 = new ResponsableLaboratoire("Fern", "Stark", 1, 1, 40.0f, LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 12, 31),"SouSou no Frieren");

		LocalDate dateDebut = LocalDate.now().minusDays(1);
		LocalDate dateFin = LocalDate.now().plusDays(1);
		
		
		p1.addLigneBudgetaire(ligneBudgetaire2);
		p1.addLigneBudgetaire(ligneBudgetaire);
		
		p1.addRessourceWithDate(ressource2, dateDebut, dateFin);
		p1.addRessourceWithDate(ressource, dateDebut, dateFin);
		p1.addRessourceWithDate(ressource3, dateDebut, dateFin);
		p1.addRessourceWithDate(ressource4, dateDebut, dateFin);
		
		p2.addRessourceWithDate(ressource2, dateDebut, dateFin);
		p2.addRessourceWithDate(ressource, dateDebut, dateFin);
		p2.addRessourceWithDate(ressource3, dateDebut, dateFin);
		p2.addRessourceWithDate(ressource4, dateDebut, dateFin);
	
		
		this.projetList = new ArrayList<Projet>();
		this.ressourceList = new ArrayList<Ressource>();
		
		this.projetList.add(p1);
		this.projetList.add(p2);
		

		this.ressourceList.add(ressource);
		this.ressourceList.add(ressource2);
		this.ressourceList.add(ressource3);
		this.ressourceList.add(ressource4);
		
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
	
	public void setListeProjet(List<Projet> projetList) {
		this.projetList = projetList;
	}
	
	public void setListeRessource(List<Ressource> ressourceList) {
		this.ressourceList = ressourceList;
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
	
	public void appliquerVentilationProjet(StrategieTrie strat,LocalDate date){
		if(strat != null) {
			Ventilation ventilation = new Ventilation(strat);
			
			if(date != null)
				ventilation.setDate(date);
			
			List<Projet> projetListTrie =  (List<Projet>) ventilation.ventiler(getListeProjet());
			setListeProjet(projetListTrie);
		}
	}
	
	public void appliquerVentilationRessource(StrategieTrie strat,LocalDate date){
		if(strat != null) {
			Ventilation ventilation = new Ventilation(strat);
			
			if(date != null)
				ventilation.setDate(date);
			
			List<Ressource> ressourceListTrie = (List<Ressource>) ventilation.ventiler(getRessourceList());
			setListeRessource(ressourceListTrie);
		}
	}
	
	public Projet ProjetAssocie(Ressource ressource) {
		
		for(Projet projet : this.getListeProjet()) {
			if(projet.getRessources().containsKey(ressource))
				return projet;
		}
		
		return null;
		
	}
}
