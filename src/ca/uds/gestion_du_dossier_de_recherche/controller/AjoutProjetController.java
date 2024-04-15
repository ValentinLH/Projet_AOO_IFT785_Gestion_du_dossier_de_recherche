package ca.uds.gestion_du_dossier_de_recherche.controller;

import static org.mockito.ArgumentMatchers.anyList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet.AffectationProjetRessource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AjoutProjetController {
	
	@FXML
	private ComboBox<Ressource> comboBoxRessource;
	
	@FXML
	private ListView<AffectationProjetRessource> ressourceListView;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextArea descriptionTextArea;
	
	@FXML
	private DatePicker debutAffectation;

	@FXML
	private DatePicker finAffectation;
	
	@FXML
	private DatePicker dateDebut;
	
	@FXML
	private DatePicker dateFin;
	
	private Stage mainStage;

	
	private VueGeneralControler controler;
	private List<Ressource> ressourceSelectionne = new ArrayList<Ressource>();
	
	public VueGeneralControler getControler() {
		return controler;
	}

	public void setControler(VueGeneralControler controler) {
		this.controler = controler;
	}
	
	public void updateComponents() {
		this.comboBoxRessource.getItems().clear();
		this.comboBoxRessource.getItems().addAll(controler.getRessourceList());
	}
	
	
	@FXML
	public void choisirRessource() {
		if (this.comboBoxRessource.getSelectionModel().isEmpty()) {
			this.showAlert("Aucune ressource selectionne", "Choisissez une ressource avant de la selectionner");
			return;
		}
		
		if (this.ressourceSelectionne.contains(this.comboBoxRessource.getSelectionModel().getSelectedItem())) {
			this.showAlert("Ressource deja selectionne", "Cette ressource est deja choisi pour le projet");
			return;
		}
		
		if (this.debutAffectation.getValue() == null)
		{
			this.showAlert("Pas de date de debut d'affectation selectionne", "Selectionnez une date de debut d'affectation"
					+ " avant d'ajouter une ressource");
			return;
		}
		
		if (this.finAffectation.getValue() == null)
		{
			this.showAlert("Pas de date de fin d'affectation selectionne", "Selectionnez une date de fin d'affectation"
					+ " avant d'ajouter une ressource");
			return;
		}
		this.ressourceSelectionne.add(this.comboBoxRessource.getSelectionModel().getSelectedItem());
		AffectationProjetRessource affectation = new AffectationProjetRessource(
				this.comboBoxRessource.getSelectionModel().getSelectedItem(),
				this.debutAffectation.getValue(), this.finAffectation.getValue());
		this.ressourceListView.getItems().add(affectation);
	}
	
	@FXML
	public void ajoutRessource() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_ajout_ressource.fxml"));
            Parent root = loader.load();
            CreationObjetController controllerRessource = loader.getController();
            Stage ressourceStagectStage = new Stage();
            ressourceStagectStage.setTitle("Ajouter une nouvelle ressource");
            ressourceStagectStage.setScene(new Scene(root, 800, 425));
            ressourceStagectStage.initModality(Modality.APPLICATION_MODAL);
            ressourceStagectStage.initOwner(mainStage);
            controllerRessource.setController(this.controler);
            controllerRessource.setMainStage(this.mainStage);
            controllerRessource.updateComponents();
            ressourceStagectStage.showAndWait();
            this.updateComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void annulerButton() {
		Stage stage = (Stage) this.comboBoxRessource.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void validerButton() {
		if (this.nameTextField.getText().isEmpty()) {
			this.showAlert("Pas de titre selectionne", "Selectionnez un titre avant de creer un projet");
			return;
		}
		
		if (this.descriptionTextArea.getText().isEmpty()) {
			this.showAlert("Pas de description selectionne", "Selectionnez une description avant de creer un projet");
			return;
		}
		
		if (this.dateDebut.getValue() == null) {
			this.showAlert("Pas de date de debut selectionne", "Selectionnez une date de debut avant de creer un projet");
			return;
		}
		
		if (this.dateFin.getValue() == null) {
			this.showAlert("Pas de date de fin selectionne", "Selectionnez une date de fin avant de creer un projet");
			return;
		}
				
		Projet projet = new Projet(this.nameTextField.getText(),
				this.descriptionTextArea.getText(),
				this.dateDebut.getValue(),
				this.dateFin.getValue());
				
		ObservableList<AffectationProjetRessource> affectationList = this.ressourceListView.getItems();
		
		for (AffectationProjetRessource affectation : this.ressourceListView.getItems())
		{
			projet.getRessources().put(affectation.getRessource(),
					new ArrayList<LocalDate>(Arrays.asList(affectation.getDateDebut(), affectation.getDateFin())));
		}
		
		this.controler.getListeProjet().add(projet);
		this.showAlert("Projet cree", "Le projet a bien ete cree");
		Stage stage = (Stage) this.comboBoxRessource.getScene().getWindow();
		stage.close();
	}
	
	public void showAlert(String titre, String texte) {
		Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
        emptyAlert.setTitle(titre);
        emptyAlert.setHeaderText(null);
        emptyAlert.setContentText(texte);
        emptyAlert.showAndWait();
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

}
