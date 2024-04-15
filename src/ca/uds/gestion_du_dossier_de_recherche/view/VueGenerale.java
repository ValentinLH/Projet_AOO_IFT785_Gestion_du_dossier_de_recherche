package ca.uds.gestion_du_dossier_de_recherche.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import ca.uds.gestion_du_dossier_de_recherche.controller.AjoutProjetController;
import ca.uds.gestion_du_dossier_de_recherche.controller.ProjetViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.VueGeneralControler;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;


public class VueGenerale extends Application {

	@FXML
	private ComboBox<StrategieTrie> comboBoxStrategie;
	
	@FXML 
	TableView<Projet> tableViewProjet;
	
	@FXML
	TableColumn<Projet,String> projetNomColumn;
	
	@FXML
	TableColumn<Projet,Float> financementColumn;
	
	@FXML
	TableColumn<Projet,LocalDate> dateDebutColumn;
	
	@FXML
	TableColumn<Projet,LocalDate> dateFinColumn;
	
	@FXML
	TableColumn<Projet,Integer> nbRessourcesColumn;
	
	private VueGeneralControler controler;
	
	private Stage mainStage;
	
	public VueGenerale(VueGeneralControler _controler) {
		super();
		this.controler = _controler;
	}
	
    public VueGenerale() {
        super();
        this.controler = new VueGeneralControler();
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("file\\Prototype_AOO_General_View.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        primaryStage.setTitle("Gestion des Ressources");
        primaryStage.setScene(new Scene(root, 800, 600));
        mainStage = primaryStage;
        primaryStage.show();
        
      
        
	}
	
	
	@FXML
	public void showProject() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("file\\Prototype_AOO_projet_view.fxml"));
            Parent root = loader.load();
            ProjetViewController controllerProject = loader.getController();
            Stage projectStage = new Stage();
            projectStage.setTitle("Projet");
            projectStage.setScene(new Scene(root, 925, 740));
            projectStage.initModality(Modality.APPLICATION_MODAL);
            projectStage.initOwner(mainStage);
            controllerProject.setProjet(this.controler.getListeProjet().get(0));
            projectStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
    public void initialize() {
		this.comboBoxStrategie.getItems().clear();
		this.comboBoxStrategie.getItems().add(this.controler.getStrategieDate());
		this.comboBoxStrategie.getItems().add(this.controler.getStrategieMontant());
		updatetableView();
    }
	
	public void updatetableView(){
		this.tableViewProjet.getItems().clear();
		
		this.projetNomColumn.setCellValueFactory(
			    new PropertyValueFactory<>("titre"));
		
		this.dateFinColumn.setCellValueFactory(
			    new PropertyValueFactory<>("dateFin"));
		
		this.dateDebutColumn.setCellValueFactory(
			    new PropertyValueFactory<>("dateDebut"));
		
		this.financementColumn.setCellValueFactory(cellData -> {
		    Projet projet = cellData.getValue();
		    float total = projet.calculMontant(LocalDate.now());
		    return new SimpleObjectProperty<>(total);
		});
		
		this.nbRessourcesColumn.setCellValueFactory(cellData -> {
		    Projet projet = cellData.getValue();
		    int total = projet.getRessources().size();
		    return new SimpleObjectProperty<>(total);
		});
		
		this.tableViewProjet.getItems().addAll(controler.getListeProjet());
	}
	
	@FXML
	public void addProject() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("file\\Prototype_AOO_ajout_projet.fxml"));
            Parent root = loader.load();
            AjoutProjetController controllerProject = loader.getController();
            Stage projectStage = new Stage();
            projectStage.setTitle("Ajouter un nouveau projet");
            projectStage.setScene(new Scene(root, 800, 650));
            projectStage.initModality(Modality.APPLICATION_MODAL);
            projectStage.initOwner(mainStage);
            controllerProject.setControler(this.controler);
            controllerProject.updateComponents();
            controllerProject.setMainStage(this.mainStage);
            projectStage.showAndWait();
            this.updatetableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void supprimerProjet()
	{
		if (this.tableViewProjet.getSelectionModel().getSelectedItem() == null) 
		{
			
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
	        emptyAlert.setTitle("Aucun projet selectionne");
	        emptyAlert.setHeaderText(null);
	        emptyAlert.setContentText("Selectionnez un projet avant de le supprimer");
	        emptyAlert.showAndWait();
	        return;
		}
		
		
		Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Etes vous sur?");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText("Vous etes sur le point de supprimer un projet, vous ne pourrez pas revenir en arriere, continuer ?");
        
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        	this.controler.getListeProjet().remove(this.tableViewProjet.getSelectionModel().getSelectedItem());
			this.updatetableView();
        }
		
		
	}
	

}
