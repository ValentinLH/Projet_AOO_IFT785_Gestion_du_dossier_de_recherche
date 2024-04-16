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
		Projet p2 = MainView.getStubProjet2();
		
		this.projetList = new ArrayList<Projet>();
		this.ressourceList = new ArrayList<Ressource>();
		
		this.projetList.add(p1);
		this.projetList.add(p2);
		
		for (Projet p : projetList)
			for (Ressource r : p.getRessources().keySet())
				this.ressourceList.add(r);
		
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
