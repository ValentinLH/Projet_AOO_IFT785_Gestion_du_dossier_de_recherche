package ca.uds.gestion_du_dossier_de_recherche.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieDateFinContrat;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieMontant;
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

	GeneralViewController controller;
	
	/*======== COMPOSANT FXML ========*/
	
	@FXML
	private ComboBox<StrategieTrie> optionStartegie;
	
	@FXML 
	TableView<Projet> tableViewProjet;
	
	@FXML
	TableColumn<Projet,String> projetNomColumn;
	
	@FXML
	TableColumn<Projet,Double> financementColumn;
	
	@FXML
	TableColumn<Projet,LocalDate> dateDebutColumn;
	
	@FXML
	TableColumn<Projet,LocalDate> dateFinColumn;
	
	@FXML
	TableColumn<Projet,Integer> nbRessourcesColumn;
	
	
	public GeneralView(){
		controller  = new GeneralViewController();
	}
	
	public static void main(String[] args) {
		Application.launch(GeneralView.class,args);
		//new GeneralViewController();
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_General_View.fxml"));
		Parent root = loader.load();
		//loader.setController(this);
		primaryStage.setTitle("App de gestion");
		primaryStage.setScene(new Scene(root,651,471));
		primaryStage.show();
	}
	
	@FXML
    public void initialize() {
		updateComboBox();
		updateTableViewProjet();
		System.out.println("test");
	}
	
	@FXML
	void updateComboBox() {
		this.optionStartegie.getItems().clear();
		this.optionStartegie.getItems().add(new TrieDateFinContrat());
		this.optionStartegie.getItems().add(new TrieMontant());
	}
	
	@FXML
	public void updateTableViewProjet() {
		
		for(Projet projet : controller.getListeProjet()) {
			this.tableViewProjet.getItems().add(projet);
		}
		
	}
}
