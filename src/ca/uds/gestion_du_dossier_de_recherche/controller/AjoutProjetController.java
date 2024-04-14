package ca.uds.gestion_du_dossier_de_recherche.controller;

import static org.mockito.ArgumentMatchers.anyList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AjoutProjetController {
	
	@FXML
	private ComboBox<Ressource> comboBoxRessource;
	
	@FXML
	private ListView<Ressource> ressourceListView;
	
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
		this.comboBoxRessource.getItems().addAll(controler.getRessourceList());
	}
	
	
	@FXML
	public void choisirRessource() {
		if (this.comboBoxRessource.getSelectionModel().isEmpty()) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
            emptyAlert.setTitle("Aucune ressource selectionne");
            emptyAlert.setHeaderText(null);
            emptyAlert.setContentText("Choisissez une ressource avant de la selectionner");
            emptyAlert.showAndWait();
			return;
		}
		
		if (this.ressourceListView.getItems().contains(this.comboBoxRessource.getSelectionModel().getSelectedItem())) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
            emptyAlert.setTitle("Ressource deja selectionne");
            emptyAlert.setHeaderText(null);
            emptyAlert.setContentText("Cette ressource est deja choisi pour le projet");
            emptyAlert.showAndWait();
			return;
		}
		
		this.ressourceListView.getItems().add(this.comboBoxRessource.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	public void ajoutRessource() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_ajout_ressource.fxml"));
            Parent root = loader.load();
            CreationObjetController controllerRessource = loader.getController();
            Stage ressourceStagectStage = new Stage();
            ressourceStagectStage.setTitle("Ajouter une nouvelle ressource");
            ressourceStagectStage.setScene(new Scene(root, 800, 775));
            ressourceStagectStage.initModality(Modality.APPLICATION_MODAL);
            ressourceStagectStage.initOwner(mainStage);
            //controllerProject.setControler(this.controler);
            //controllerProject.updateComponents();
            ressourceStagectStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

}
