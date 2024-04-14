package ca.uds.gestion_du_dossier_de_recherche.view;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddProjectView extends Application {
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    
    @Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("file/Protoype_AOO_ajout_projet.fxml"));
		Parent root = loader.load();
		loader.setController(this);
		primaryStage.setTitle("Nouveau Projet");
		primaryStage.setScene(new Scene(root,651,471));
		primaryStage.show();
	}

    void initialize() {

    }

}

