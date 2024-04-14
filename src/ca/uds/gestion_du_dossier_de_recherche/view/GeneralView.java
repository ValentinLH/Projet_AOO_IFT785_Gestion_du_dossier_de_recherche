package ca.uds.gestion_du_dossier_de_recherche.view;

import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;

import java.awt.Button;
import java.net.URL;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GeneralView extends Application {
	
	@FXML
	private ComboBox<StrategieTrie> optionStartegie;
	private Button ajouterProjetButtonClicked;
	
	
	@FXML 
	TableView<Projet> tableViewProjet;
	
	@FXML
	TableColumn<Projet,String> projetNomColumn;
	
	GeneralViewController controller;
	
	public GeneralView() {
		super();
	}
	
	public GeneralView(GeneralViewController _controller){
		controller  = _controller;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_General_View.fxml"));
	    loader.setController(controller);
		Parent root = loader.load();
		primaryStage.setTitle("App de gestion");
		primaryStage.setScene(new Scene(root,651,471));
		primaryStage.show();	
	}
	
	@FXML
    public void initialize() {
		System.out.println("OUIIIIIIIIIIIII");
		updateComboBox();
		updateTableViewProjet();
	}
	
	@FXML
	void updateComboBox() {
		this.optionStartegie.getItems().clear();
		this.optionStartegie.getItems().add(controller.getStrategieDate());
		this.optionStartegie.getItems().add(controller.getStrategieMontant());
	}
	
	@FXML
	public void updateTableViewProjet() {
	}
	
	@FXML
	private void ajouterProjetButtonClicked(ActionEvent event) {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_ajout_projet.fxml"));
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
	
	@FXML
	private void supprimerProjetButtonClicked(ActionEvent event) {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_suppression.fxml"));
            Parent root = loader.load();
            
            // Nouvelle scene et étape pour la scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Supprimer Projet");
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
}
