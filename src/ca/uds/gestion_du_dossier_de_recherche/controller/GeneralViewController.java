package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieDateFinContrat;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieMontant;
import ca.uds.gestion_du_dossier_de_recherche.view.GeneralView;

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeneralViewController {

	private List<Projet> projetList;
	private List<Ressource> ressourceList; 
	private GeneralView view;
	
	public GeneralViewController() {
		super();
		this.projetList = new ArrayList<Projet>();
		this.ressourceList = new ArrayList<Ressource>();
//		view = new GeneralView(this);
	}
	
	public  GeneralViewController(List<Projet> projetList,List<Ressource> ressourceList) {	
		this.projetList = projetList;
		this.ressourceList = ressourceList;
		view = new GeneralView(this);
		
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
  
  @FXML
	public void ajouterProjetButtonClicked(ActionEvent event) {
		
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Protoype_AOO_ajout_projet.fxml"));
            Parent root = loader.load();
            
            // Nouvelle scene et étape pour la scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Nouveau Projet");
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
}