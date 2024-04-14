package ca.uds.gestion_du_dossier_de_recherche.controller;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueEtudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueSoutien;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.GrilleSalariale;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreationObjetController {
	
	@FXML
	private ComboBox<String> comboBoxCategorie;
	
	@FXML
	private ComboBox<Integer> comboBoxEchelle;
	
	@FXML
	private ComboBox<Integer> comboBoxEchelon;
	
	@FXML
	private ComboBox<Programme> programmeComboBox;
	
	@FXML
	private TextField salaireTextField;
	
	@FXML
	private TextField cipTextField;
	
	@FXML
	private TextField laboTextField;
	
	@FXML
	private TextField nomTextField;
	
	@FXML
	private TextField prenomTextField;

	@FXML
	private Text cipLabel;
	
	@FXML
	private Text programmeLabel;
	
	@FXML
	private Text laboLabel;
	
	@FXML
	private DatePicker dateDebut;
	
	@FXML
	private DatePicker dateFin;
		
	private VueGeneralControler controller;
	private Stage mainStage;
	
	private FabriqueEtudiant fabriqueEtu;
	private FabriqueResponsableLaboratoire fabriqueRespo;
	private FabriqueSoutien fabriqueSoutien;
	
	public CreationObjetController() {
		this.fabriqueEtu = new FabriqueEtudiant();
		this.fabriqueRespo = new FabriqueResponsableLaboratoire();
		this.fabriqueSoutien = new FabriqueSoutien();
	}
	
	public void updateComponents() {
		this.comboBoxCategorie.getItems().addAll("Etudiant", "Soutien", "Responsable de laboratoire");
		this.comboBoxEchelle.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
		this.comboBoxEchelon.getItems().addAll(1, 2, 3, 4, 5, 6);
		this.programmeComboBox.getItems().addAll(Programme.values());
		this.cipLabel.setVisible(false);
		this.programmeLabel.setVisible(false);
		this.laboLabel.setVisible(false);
		this.cipTextField.setVisible(false);
		this.programmeComboBox.setVisible(false);
		this.laboTextField.setVisible(false);
	}
	
	public void updateSalaire() {
		Integer selectedEchelle = comboBoxEchelle.getSelectionModel().getSelectedItem();
        Integer selectedEchelon = comboBoxEchelon.getSelectionModel().getSelectedItem();
        
        if (selectedEchelle != null && selectedEchelon != null) {
            this.salaireTextField.setText(String.valueOf(GrilleSalariale.getInstance().getTauxHoraire(selectedEchelle, selectedEchelon)));
        }
	}
	
	@FXML
	public void changeEchelle() {
		this.updateSalaire();
	}
	
	@FXML
	public void changeEchelon() {
		this.updateSalaire();
	}
	
	@FXML
	public void changeCategorie() {
		String categorieSelected = this.comboBoxCategorie.getSelectionModel().getSelectedItem();
		
		if (categorieSelected.equals("Etudiant")) {
			this.cipLabel.setVisible(true);
			this.cipTextField.clear();
			this.cipTextField.setVisible(true);
			this.programmeLabel.setVisible(true);
			this.programmeComboBox.setVisible(true);
			this.laboLabel.setVisible(false);
			this.laboTextField.setVisible(false);
		}
		
		if (categorieSelected.equals("Responsable de laboratoire")) {
			this.laboLabel.setVisible(true);
			this.laboTextField.clear();
			this.laboTextField.setVisible(true);
			this.cipLabel.setVisible(false);
			this.cipTextField.setVisible(false);
			this.programmeLabel.setVisible(false);
			this.programmeComboBox.setVisible(false);
		}
		
		if (categorieSelected.equals("Soutien")) {
			this.laboLabel.setVisible(false);
			this.laboTextField.setVisible(false);
			this.cipLabel.setVisible(false);
			this.cipTextField.setVisible(false);
			this.programmeLabel.setVisible(false);
			this.programmeComboBox.setVisible(false);
		}
	}
	
	
	@FXML
	public void validerButton() {
		if (this.comboBoxCategorie.getSelectionModel().getSelectedItem() == null)
		{
			this.showAlert("Pas de categorie selectionne", "Selectionnez une categorie avant de creer un projet");
			return;
		}
		
		if (this.nomTextField.getText().isEmpty())
		{
			this.showAlert("Pas de nom selectionne", "Selectionnez un nom avant de creer un projet");
			return;
		}
		
		if (this.prenomTextField.getText().isEmpty())
		{
			this.showAlert("Pas de prenom selectionne", "Selectionnez un prenom avant de creer un projet");
			return;
		}
		
		if (this.comboBoxEchelle.getSelectionModel().getSelectedItem() == null)
		{
			this.showAlert("Pas d'echelle selectionne", "Selectionnez une echelle avant de creer un projet");
			return;
		}
		
		if (this.comboBoxEchelon.getSelectionModel().getSelectedItem() == null)
		{
			this.showAlert("Pas d'echelon selectionne", "Selectionnez un echelon avant de creer un projet");
			return;
		}
		
		if (this.salaireTextField.getText().isEmpty()) {
			this.showAlert("Pas de salaire selectionne", "Selectionnez un salaire avant de creer un projet");
			return;
		}
		
		if (this.dateDebut.getValue() == null)
		{
			this.showAlert("Pas de date de debut selectionne", "Selectionnez une date de debut avant de creer un projet");
			return;
		}
		
		if (this.dateFin.getValue() == null)
		{
			this.showAlert("Pas de date de fin selectionne", "Selectionnez une date de fin avant de creer un projet");
			return;
		}
		
		if (this.comboBoxCategorie.getSelectionModel().getSelectedItem().equals("Etudiant") && 
				(this.programmeComboBox.getSelectionModel().getSelectedItem() == null))
		{
			this.showAlert("Pas de programme selectionne", "Selectionnez un programme d'etude avant de creer un projet");
			return;
		}
		
		if (this.comboBoxCategorie.getSelectionModel().getSelectedItem().equals("Etudiant") && 
				this.cipTextField.getText().isEmpty())
		{
			this.showAlert("Pas de cip selectionne", "Selectionnez un cip d'etude avant de creer un projet");
			return;
		}
		
		if (this.comboBoxCategorie.getSelectionModel().getSelectedItem().equals("Responsable de laboratoire") && 
				this.laboTextField.getText().isEmpty())
		{
			this.showAlert("Pas de programme selectionne", "Selectionnez un programme d'etude avant de creer un projet");
			return;
		}
		
		
		if (this.comboBoxCategorie.getSelectionModel().getSelectedItem().equals("Etudiant"))
		{
			Ressource ressource = this.fabriqueEtu.createRessource(
					this.nomTextField.getText(), 
					this.prenomTextField.getText(),
					this.comboBoxEchelle.getSelectionModel().getSelectedItem(),
					this.comboBoxEchelon.getSelectionModel().getSelectedItem(),
					Float.parseFloat(this.salaireTextField.getText()),
					this.dateDebut.getValue(), 
					this.dateFin.getValue(),
					this.cipTextField.getText(),
					this.programmeComboBox.getSelectionModel().getSelectedItem());
			
			this.controller.getRessourceList().add(ressource);
			this.showAlert("Ressource cree", "La ressource a bien ete cree");
			Stage stage = (Stage) this.comboBoxCategorie.getScene().getWindow();
			stage.close();
		}
		
		
	}
	
	@FXML
	public void annulerButton() {
		Stage stage = (Stage) this.comboBoxCategorie.getScene().getWindow();
		stage.close();
	}
	
	public void showAlert(String titre, String texte) {
		Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
        emptyAlert.setTitle(titre);
        emptyAlert.setHeaderText(null);
        emptyAlert.setContentText(texte);
        emptyAlert.showAndWait();
	}

	public VueGeneralControler getController() {
		return controller;
	}

	public void setController(VueGeneralControler controller) {
		this.controller = controller;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

}
