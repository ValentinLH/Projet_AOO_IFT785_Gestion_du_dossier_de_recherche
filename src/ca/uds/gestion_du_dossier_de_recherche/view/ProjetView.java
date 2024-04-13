package ca.uds.gestion_du_dossier_de_recherche.view;

import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.ProjetViewController;
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

public class ProjetView extends Application {
	
	@FXML
	private ComboBox<StrategieTrie> optionStartegie;
	
	@FXML 
	TableView<Projet> tableViewProjet;
	
	@FXML
	TableColumn<Projet,String> projetNomColumn;
	
	ProjetViewController controller;
	
	public ProjetView() {
		super();
	}
	
	public ProjetView(ProjetViewController _controller){
		controller  = _controller;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_projet_view.fxml"));
	    loader.setController(controller);
		Parent root = loader.load();
		primaryStage.setTitle("App de gestion d' projet");
		primaryStage.setScene(new Scene(root,925.0,740.0));
		primaryStage.show();	
	}
	
	
	@FXML
    public void initialize() {
		System.out.println("OUIIIIIIIIIIIII");
//		updateComboBox();
//		updateTableViewProjet();
	}
	
//	prefHeight="" prefWidth="925.0"

}
