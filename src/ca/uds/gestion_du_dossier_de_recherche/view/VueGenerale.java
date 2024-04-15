package ca.uds.gestion_du_dossier_de_recherche.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import ca.uds.gestion_du_dossier_de_recherche.controller.AjoutProjetController;
import ca.uds.gestion_du_dossier_de_recherche.controller.ProjetViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.VueGeneralControler;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilation;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;


public class VueGenerale extends Application {

	@FXML
	private ComboBox<StrategieTrie> comboBoxStrategie;
	
	@FXML
	ComboBox<StrategieTrie> comboBoxStrategieRessource;
	
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
		
	@FXML
	DatePicker dateVentilation;
	
	@FXML 
	TableView<Ressource> tableViewRessource;
	
	@FXML
	TableColumn<Ressource,String> ressourceNomColumn;
	
	@FXML
	TableColumn<Ressource,Float> SalaireRestantColumn;
	
	@FXML
	TableColumn<Ressource,LocalDate> ressourceDateDebutColumn;
	
	@FXML
	TableColumn<Ressource,LocalDate> ressourceDateFinColumn;
	
	@FXML
	TableColumn<Ressource,String> ressourceProjetAssocieColumn;
	
	@FXML
	DatePicker dateVentilationRessource;
	
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
		if(this.tableViewProjet.getSelectionModel().getSelectedItem() == null)
		{
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
	        emptyAlert.setTitle("Pas de projet selectionne");
	        emptyAlert.setHeaderText(null);
	        emptyAlert.setContentText("Selectionnez un projet avant de le visionner");
	        emptyAlert.showAndWait();
	        return;
		}
		
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("file\\Prototype_AOO_projet_view.fxml"));
            Parent root = loader.load();
            ProjetViewController controllerProject = loader.getController();
            Stage projectStage = new Stage();
            projectStage.setTitle("Projet");
            projectStage.setScene(new Scene(root, 925, 740));
            projectStage.initModality(Modality.APPLICATION_MODAL);
            projectStage.initOwner(mainStage);
            controllerProject.setProjet(this.tableViewProjet.getSelectionModel().getSelectedItem());
            controllerProject.setRessourceList(this.controler.getRessourceList());
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
		
		this.comboBoxStrategieRessource.getItems().clear();
		this.comboBoxStrategieRessource.getItems().add(this.controler.getStrategieDate());
		this.comboBoxStrategieRessource.getItems().add(this.controler.getStrategieMontant());
		
		updatetableViewProjet();
		updateTableViewRessource();
    }
	
  public void updatetableViewProjet(){

		this.tableViewProjet.getItems().clear();
		
		this.projetNomColumn.setCellValueFactory(
			    new PropertyValueFactory<>("titre"));
		
		this.dateFinColumn.setCellValueFactory(
			    new PropertyValueFactory<>("dateFin"));
		
		this.dateDebutColumn.setCellValueFactory(
			    new PropertyValueFactory<>("dateDebut"));
		
		this.financementColumn.setCellValueFactory(cellData -> {
		    Projet projet = cellData.getValue();
		    LocalDate date = dateVentilation.getValue();
		    
		    if(date == null) {
		    	date = LocalDate.now();
		    }
		    float total = projet.calculMontant(date);
		    
		    return new SimpleObjectProperty<>(total);
		});
		
		this.nbRessourcesColumn.setCellValueFactory(cellData -> {
		    Projet projet = cellData.getValue();
		    int total = projet.getRessources().size();
		    return new SimpleObjectProperty<>(total);
		});
		
		this.tableViewProjet.getItems().addAll(controler.getListeProjet());
	}
	
	public StrategieTrie getStrategieProjet() {
		return comboBoxStrategie.getValue();
	}
	
	public StrategieTrie getStrategieRessource() {
		return comboBoxStrategieRessource.getValue();
	}
	
	@FXML
	void appliquerVentilation(Event e){
		StrategieTrie strat = getStrategieProjet();
		LocalDate date = dateVentilation.getValue();
		controler.appliquerVentilationProjet(strat, date); 
		updatetableViewProjet();
	}
	
	@FXML
	void appliquerVentilationRessource(Event e){
		StrategieTrie strat = getStrategieRessource();
		LocalDate date = dateVentilation.getValue();	
		controler.appliquerVentilationRessource(strat, date);
		updateTableViewRessource();
	}
	
	public void updateTableViewRessource() {
		this.tableViewRessource.getItems().clear();
		
		this.ressourceNomColumn.setCellValueFactory(
			    new PropertyValueFactory<>("nom"));
		
		this.ressourceDateDebutColumn.setCellValueFactory(
			    new PropertyValueFactory<>("debutContrat"));
		
		this.ressourceDateFinColumn.setCellValueFactory(
			    new PropertyValueFactory<>("finContrat"));
		
		this.SalaireRestantColumn.setCellValueFactory(cellData -> {
		    Ressource ressource = cellData.getValue();
		    LocalDate date = dateVentilationRessource.getValue();
		    
		    if(date == null) {
		    	date = LocalDate.now();
		    }
		    return new SimpleObjectProperty<>(ressource.getMontantVentilation(date));
		});
		
		this.ressourceProjetAssocieColumn.setCellValueFactory(cellData -> {
		    Ressource ressource = cellData.getValue();
		    
		    Projet projetAssocie = controler.ProjetAssocie(ressource); 
		    
		    if(projetAssocie == null)
		    	return new SimpleObjectProperty<>("Aucun projet est associé à cette ressource");
		    else
		    	return new SimpleObjectProperty<>(projetAssocie.getTitre());
		});
		
		this.tableViewRessource.getItems().addAll(controler.getRessourceList());
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
            this.updatetableViewProjet();
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
			    this.updatetableViewProjet();
        }	
	}
}
