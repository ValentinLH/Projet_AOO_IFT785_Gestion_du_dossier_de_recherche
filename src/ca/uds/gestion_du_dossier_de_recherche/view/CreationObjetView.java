package ca.uds.gestion_du_dossier_de_recherche.view;

import ca.uds.gestion_du_dossier_de_recherche.controller.CreationObjetController;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.ResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreationObjetView extends Application{
	
	CreationObjetController controller;
	
	public CreationObjetView(){
		controller  = new CreationObjetController();
	}
	
	@FXML
	private ComboBox<String> categorieRessource;
	
	private ComboBox<Integer> echelle;
	private ComboBox<Integer> echelon;
	
	private Stage primaryStage;
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        loadFXML("Prototype_AOO_ajout_ressource.fxml");
        updateComboBox();
    }

    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("file/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, 651, 471);
            primaryStage.setMinWidth(799);
            primaryStage.setMinHeight(416);
            primaryStage.setScene(scene);

                        
            this.categorieRessource = (ComboBox<String>) scene.lookup("#categorieRessource");
            this.echelle = (ComboBox<Integer>) scene.lookup("#echelle_combo");
            this.echelon = (ComboBox<Integer>) scene.lookup("#echelon_combo");
            
            // ETUDIANT
            
            Text cip = (Text) scene.lookup("#cip_text");
            TextField cip_input = (TextField) scene.lookup("#cip_field");
            Text programme_text = (Text) scene.lookup("#programme_text");
            ComboBox<String> programme_combo = (ComboBox<String>) scene.lookup("#programme_combo");
            
            // LABORATOIRE
            
            Text labo_text = (Text) scene.lookup("#labo_text");
            ComboBox<String> labo_combo = (ComboBox<String>) scene.lookup("#labo_combo");
            Button nouveau_labo_bouton = (Button) scene.lookup("#nouveau_labo_bouton");
            Button annuler_nouveau_labo = (Button) scene.lookup("#annuler_new_labo");
            
            
            if (nouveau_labo_bouton != null) {
            	nouveau_labo_bouton.setOnAction(event -> {
                    loadFXML("Prototype_AOO_ajout_laboratoire.fxml"); 
                });
            }
            
            if (annuler_nouveau_labo != null) {
            	annuler_nouveau_labo.setOnAction(event -> {
                	primaryStage.close();
                });
            }
            
            
            categorieRessource.setOnAction(event -> {
            	String value = categorieRessource.getValue();
            	if (value == "Etudiant") {
            		show_etudiant(cip, cip_input, programme_text, programme_combo);
            		hide_labo(labo_text, labo_combo, nouveau_labo_bouton);
            	}
            	if (value == "Responsable Laboratoire") {
            		hide_etudiant(cip, cip_input, programme_text, programme_combo);
            		show_labo(labo_text, labo_combo, nouveau_labo_bouton);
            	}
            	
            	if (value == "Soutien") {
            		hide_etudiant(cip, cip_input, programme_text, programme_combo);
            		hide_labo(labo_text, labo_combo, nouveau_labo_bouton);
            	}
            });

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
	
	@FXML
    public void initialize() {
		updateComboBox();
	}
	
	@FXML
	void updateComboBox() {
		this.categorieRessource.getItems().clear();
		this.categorieRessource.getItems().add("Soutien");
		this.categorieRessource.getItems().add("Etudiant");
		this.categorieRessource.getItems().add("Responsable Laboratoire");
		
		this.echelle.getItems().clear();
		this.echelle.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13);
		
		this.echelon.getItems().clear();
		this.echelon.getItems().addAll(0,1,2,3,4,5,6,7,8,9);
		
	}
	
	public void show_etudiant(Text cip, TextField cip_input, Text programme_text, ComboBox<String> programme_combo) {
		cip.setVisible(true);
		cip_input.setVisible(true);
		programme_text.setVisible(true);
		programme_combo.setVisible(true);
	}
	
	public void hide_etudiant(Text cip, TextField cip_input, Text programme_text, ComboBox<String> programme_combo) {
		cip.setVisible(false);
		cip_input.setVisible(false);
		programme_text.setVisible(false);
		programme_combo.setVisible(false);
	}
	
	public void show_labo(Text labo_text, ComboBox<String> labo_combo, Button nouveau_labo_bouton ) {
		labo_text.setVisible(true);
		labo_combo.setVisible(true);
		nouveau_labo_bouton.setVisible(true);
	}
	
	public void hide_labo(Text labo_text, ComboBox<String> labo_combo, Button nouveau_labo_bouton ) {
		labo_text.setVisible(false);
		labo_combo.setVisible(false);
		nouveau_labo_bouton.setVisible(false);
	}

}
