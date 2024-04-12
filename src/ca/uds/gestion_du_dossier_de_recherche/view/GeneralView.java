package ca.uds.gestion_du_dossier_de_recherche.view;

import java.util.ArrayList;
import java.util.List;

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
import javafx.stage.Stage;

public class GeneralView extends Application {

	List<Projet> projetList = new ArrayList<Projet>();
	
	@FXML
	private ComboBox<StrategieTrie> optionStartegie;
	
	public static void main(String[] args) {
		Application.launch(GeneralView.class,args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_General_View.fxml"));
		Parent root = loader.load();
		loader.setController(this);
		primaryStage.setTitle("App de gestion");
		primaryStage.setScene(new Scene(root,651,471));
		primaryStage.show();
	}
	
	@FXML
    public void initialize() {
		
	}
	
	@FXML
	void updateComboBox() {
		this.optionStartegie.getItems().clear();
		this.optionStartegie.getItems().add(new TrieDateFinContrat());
		this.optionStartegie.getItems().add(new TrieMontant());
		
	}
}
