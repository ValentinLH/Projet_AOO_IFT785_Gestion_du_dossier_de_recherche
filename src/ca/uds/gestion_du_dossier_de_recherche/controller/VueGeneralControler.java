package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieDateFinContrat;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieMontant;
import ca.uds.gestion_du_dossier_de_recherche.view.MainView;
import ca.uds.gestion_du_dossier_de_recherche.view.VueGenerale;

public class VueGeneralControler {
	
	private List<Projet> projetList;
	private List<Ressource> ressourceList; 
	private VueGenerale view;
	
	public VueGeneralControler() {		
		Projet p1 = MainView.stubProjetaSupprimer();
		Projet p2 = new Projet("projet 2 ", LocalDate.now().minusDays(5),LocalDate.now().plusDays(10));
		
		
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
