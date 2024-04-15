package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class AffectationRessourceController {
	
	@FXML
	private ComboBox<Ressource> ressourceComboBox;
	
	@FXML
	private DatePicker dateDebut;
	
	@FXML
	private DatePicker dateFin;
	
	private List<Ressource> ressourceList;
	boolean modifer;
	private Ressource selectedRessource;
	private Projet projet;
	
	public AffectationRessourceController() {
		this.ressourceList = new ArrayList<Ressource>();
		this.modifer = false;
	}
	
	public void updateComponents() {
		this.ressourceComboBox.getItems().addAll(this.ressourceList);
		if (this.modifer) {
			this.ressourceComboBox.setValue(this.selectedRessource);
			this.ressourceComboBox.setDisable(true);
		}
	}
	
	public void updatedate(LocalDate debut, LocalDate fin) {
		this.dateDebut.setValue(debut);
		this.dateFin.setValue(fin);
	}
	
	@FXML
	private void validerButton () {
		if (this.ressourceComboBox.getSelectionModel().getSelectedItem() == null) {
			this.showAlert("Pas de ressource selectionne", "Selectionnez une ressource avant de l'associer au projet");
			return;
		}
		
		if (this.dateDebut.getValue() == null) {
			this.showAlert("Pas de date de debut selectionne", "Selectionnez une date de debut avant de l'associer au projet");
			return;
		}
		
		if (this.dateFin.getValue() == null) {
			this.showAlert("Pas de date de fin selectionne", "Selectionnez une date de fin avant de l'associer au projet");
			return;
		}
		
		if (this.modifer) {
			this.projet.getRessources().put(this.selectedRessource, 
					new ArrayList<LocalDate>(Arrays.asList(this.dateDebut.getValue(), this.dateFin.getValue())));
			this.showAlert("Ressurce modifie", "La ressource a bien ete modifie");
			Stage stage = (Stage) this.ressourceComboBox.getScene().getWindow();
			stage.close();
			return;
		}
		
		this.projet.addRessourceWithDate(this.ressourceComboBox.getSelectionModel().getSelectedItem(),
				this.dateDebut.getValue(), this.dateFin.getValue());
		this.showAlert("Ressurce affecte", "La ressource a bien ete affecte");
		Stage stage = (Stage) this.ressourceComboBox.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void annulerButton() {
		Stage stage = (Stage) this.ressourceComboBox.getScene().getWindow();
		stage.close();
	}
	
	public void showAlert(String titre, String texte) {
		Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
        emptyAlert.setTitle(titre);
        emptyAlert.setHeaderText(null);
        emptyAlert.setContentText(texte);
        emptyAlert.showAndWait();
	}

	public List<Ressource> getRessourceList() {
		return ressourceList;
	}

	public void setRessourceList(List<Ressource> ressourceList) {
		this.ressourceList = ressourceList;
	}

	public boolean isModifer() {
		return modifer;
	}

	public void setModifer(boolean modifer) {
		this.modifer = modifer;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Ressource getSelectedRessource() {
		return selectedRessource;
	}

	public void setSelectedRessource(Ressource selectedRessource) {
		this.selectedRessource = selectedRessource;
	}

}
