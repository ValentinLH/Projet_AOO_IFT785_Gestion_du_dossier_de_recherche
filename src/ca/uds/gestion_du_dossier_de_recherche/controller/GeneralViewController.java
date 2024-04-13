package ca.uds.gestion_du_dossier_de_recherche.controller;

import ca.uds.gestion_du_dossier_de_recherche.view.GeneralView;
import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeneralViewController {
	
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
