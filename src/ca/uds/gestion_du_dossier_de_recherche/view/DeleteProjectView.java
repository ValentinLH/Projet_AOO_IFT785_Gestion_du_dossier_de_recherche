package ca.uds.gestion_du_dossier_de_recherche.view;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeleteProjectView extends Application {
	@FXML
    private ResourceBundle resources;

    @FXML
    void initialize() {

    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_suppression.fxml"));
		Parent root = loader.load();
		loader.setController(this);
		primaryStage.setTitle("Supprimer Projet");
		primaryStage.setScene(new Scene(root,651,471));
		primaryStage.show();
		
	}

}

