package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.ResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.view.ProjetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProjetViewController {

	VueGeneralControler vueGeneralControler = new VueGeneralControler();
	List<Projet> projets = vueGeneralControler.getListeProjet();
	List<Ressource> ressources = vueGeneralControler.getRessourceList();

	
	/*======= COMPOSANT FXML =======*/
	
	@FXML
	private TreeTableView<LigneBudgetaire> treeTableLignes;
	
	@FXML 
	TableView<Ressource> tableViewRessource;
	
	@FXML
	TableColumn<Ressource,String> ressourceCategorieColumn;
	
	@FXML
	TableColumn<Ressource,String> ressourceNomColumn;
	
	@FXML
	TableColumn<Ressource,String> ressourcePrenomColumn;
	
	@FXML
	TableColumn<Ressource,Integer> ressourceEchelleColumn;
	
	@FXML
	TableColumn<Ressource,Integer> ressourceEchellonColumn;
	
	@FXML
	TableColumn<Ressource,Integer> ressourceHeureHebdoColumn;
	
	@FXML
	TableColumn<Ressource,LocalDate> dateDebutColumn;
	
	@FXML
	TableColumn<Ressource,LocalDate> dateFinColumn;
	
	@FXML
	TableColumn<Etudiant,String> ressourceCIPColumn;
	
	@FXML
	TableColumn<ResponsableLaboratoire,String> ressourceLaboratoireColumn;

	@FXML
	private TextArea text_projet ;
		
	@FXML
	private TextArea lignes_budg ;
	
  @FXML
  private Text nb_ressource;

  @FXML
  private Text montant_total;
	/*=============================*/
  
	private Projet projet;
	private ProjetView view;
	private List<Ressource> ressourceList;
	
	private Stage mainStage;
	
	public ProjetViewController() {
		super();
		this.projet = null;

	}

	
	public ProjetViewController(Projet projet) {
		super();
		this.projet = projet;
		view = new ProjetView(this);

	}

	
	// ---------------------- //
	//    Onglet Ressource    //
	// ---------------------- //
	public void TableViewEmployeeUpdate() {
		this.tableViewRessource.getItems().clear();
		
		ressourceCategorieColumn.setCellValueFactory(cellData -> {
		    Ressource ressource = cellData.getValue();
		    return new SimpleObjectProperty<>(ressource.getClass().getSimpleName());
		});
		
		this.ressourceNomColumn.setCellValueFactory(
			    new PropertyValueFactory<>("nom"));
		
		this.ressourcePrenomColumn.setCellValueFactory(
			    new PropertyValueFactory<>("prenom"));
		
		this.ressourceEchelleColumn.setCellValueFactory(
			    new PropertyValueFactory<>("echelle"));
		
		this.ressourceEchellonColumn.setCellValueFactory(
			    new PropertyValueFactory<>("echelon"));
		
		this.ressourceHeureHebdoColumn.setCellValueFactory(
			    new PropertyValueFactory<>("heuresHebdo"));
		
		this.dateFinColumn.setCellValueFactory(cellData -> {
		    Ressource ressource = cellData.getValue();
		    
		    return new SimpleObjectProperty<>(this.projet.getRessources().get(ressource).get(1));
		});
		
		this.dateDebutColumn.setCellValueFactory(cellData -> {
		    Ressource ressource = cellData.getValue();
		    
		    return new SimpleObjectProperty<>(this.projet.getRessources().get(ressource).get(0));
		});
		
		this.ressourceCIPColumn.setCellValueFactory(cellData -> {
		    Ressource ressource = cellData.getValue();
		    
		    if(ressource.getClass() == Etudiant.class){
		    	Etudiant e = (Etudiant)ressource;
		    	return new SimpleObjectProperty<>(e.getCip());
		    }
		    return new SimpleObjectProperty<>("/");
		});
		
		this.ressourceLaboratoireColumn.setCellValueFactory(cellData -> {
			Ressource ressource = cellData.getValue();
		    
		    if(ressource.getClass() == ResponsableLaboratoire.class){
		    	ResponsableLaboratoire rl = (ResponsableLaboratoire)ressource;
		    	return new SimpleObjectProperty<>(rl.getLaboratoire());
		    }
		    return new SimpleObjectProperty<>("/");
		});
		
		this.tableViewRessource.getItems().addAll(this.projet.getRessources().keySet());
	}
	
	// ---------------------- //
	// Onglet Ligne Budgtaire //
	// ---------------------- //
	public void update() {
		treeTableUpdate();
		TableViewEmployeeUpdate();
		Afficheinfos();
	}
	
	@FXML
	public void addLigne() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_ajout_budget.fxml"));
            Parent root = loader.load();
            LignesAjoutModifController controllerLigne = loader.getController();
            Stage projectStage = new Stage();
            projectStage.setTitle("Ajouter Ligne");
            projectStage.setScene(new Scene(root, 925, 740));
            projectStage.initModality(Modality.APPLICATION_MODAL);
            
            projectStage.initOwner(mainStage);
            controllerLigne.setProjet(projet);
            controllerLigne.setStage(projectStage);
            controllerLigne.setTreeTableLignes(treeTableLignes);
            projectStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	@FXML
	public void modifierLigne() {
		if (checkSelection())
		{
			
	    	try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_ajout_budget.fxml"));
	            Parent root = loader.load();
	            LignesAjoutModifController controllerLigne = loader.getController();
	            Stage projectStage = new Stage();
	            projectStage.setTitle("Modifier Ligne");
	            projectStage.setScene(new Scene(root, 925, 740));
	            projectStage.initModality(Modality.APPLICATION_MODAL);
	            projectStage.initOwner(mainStage);
	            controllerLigne.setProjet(projet);
	            controllerLigne.setTreeTableLignes(treeTableLignes);
	            controllerLigne.setStage(projectStage);
	            controllerLigne.setLigne(this.treeTableLignes.getSelectionModel().getSelectedCells().get(0).getTreeItem().getValue());
	            projectStage.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
		}
	}



	
	@FXML
	public void supprimerLigne() {
		if (checkSelection())
		{
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setTitle("Confirmation de suppression");
			confirmationAlert.setHeaderText(null);
			confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette Ligne ?");
      
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				TreeItem<LigneBudgetaire> selectedTreeItem = treeTableLignes.getSelectionModel().getSelectedItem();
	            if (selectedTreeItem != null) {
	                LigneBudgetaire ligne = selectedTreeItem.getValue();
	                projet.removeLigneBudgetaire(ligne);
	                selectedTreeItem.getParent().getChildren().remove(selectedTreeItem);
	            }
				
			}
		}
	}

	@FXML
	public void Afficheinfos() {
        
		this.lignes_budg.clear();
        
		String titre=projet.getTitre();
		String desc=projet.getDescription();
		int nb_res=projet.GetRessourceNumber();
		String nb_res_string= "Il y a "+nb_res+ " Ressources dans le projet";
		float montant=projet.calculMontant(LocalDate.now());
		String montant_string="Montant total : "+ montant +"€";
		
		List<LigneBudgetaire> lignes=projet.getAllLigneBudgetaires();
		List<Float> montants = new ArrayList<>();
		List<String> noms = new ArrayList<>();

		for (LigneBudgetaire ligne : lignes) {
		    montants.add(ligne.getMontantLigne(LocalDate.now()));
		    noms.add(ligne.getNom());
		}

		String result = "";
		for (int i = 0; i < noms.size(); i++) {
		    result += "Il reste à " + noms.get(i) + " " + montants.get(i) + " euros. " +"\n";
		}
		
		
		text_projet.setText("Nom du projet : " +"\n"+ titre +"\n"+ "\n"+ "Description du projet : " +"\n"+ desc );
        montant_total.setText(montant_string);
        nb_ressource.setText(nb_res_string);
        lignes_budg.setText(result);

		    
	}
	
	public void treeTableUpdate() {
		{
			ObservableList<LigneBudgetaire> lignesBudgetaires = getLignesBudgetaires();

			// Créer les colonnes pour la TreeTableView
			TreeTableColumn<LigneBudgetaire, String> ligneColumn = new TreeTableColumn<>("Ligne Budgétaire");
			TreeTableColumn<LigneBudgetaire, String> ubrColumn = new TreeTableColumn<>("UBR");
			TreeTableColumn<LigneBudgetaire, String> depenseColumn = new TreeTableColumn<>("Dépense");

			// Associer les données à chaque colonne
			ligneColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));

			// Créer une cell factory personnalisée pour la colonne des UBR
			ubrColumn.setCellValueFactory(param -> {
				LigneBudgetaire ligneBudgetaire = param.getValue().getValue();
				String ubrsText = ligneBudgetaire.getUbrs().stream()
						.map(ubr -> "UBR de " + ubr.getOrganisme().getNom() + ", Code : " + ubr.getCode()
								+ ", Montant :" + ubr.getMontant(ligneBudgetaire) + "$, De " + ubr.getDateDebut()
								+ " à " + ubr.getDateFin())
						.collect(Collectors.joining("\n"));
				return new SimpleStringProperty(ubrsText);
			});

			// Créer une cell factory personnalisée pour la colonne des dépenses
			depenseColumn.setCellValueFactory(param -> {
				LigneBudgetaire ligneBudgetaire = param.getValue().getValue();
				String depensesText = ligneBudgetaire.getDepenses().stream()
						.map(depense -> "Dépense: " + depense.getNom() + ", Montant : " + depense.getMontant() + "$")
						.collect(Collectors.joining("\n"));
				return new SimpleStringProperty(depensesText);
			});

			treeTableLignes.getColumns().addAll(ligneColumn, ubrColumn, depenseColumn);

			// Créer les items pour la TreeTableView
			TreeItem<LigneBudgetaire> rootItem = new TreeItem<>();
			rootItem.setExpanded(true);

			for (LigneBudgetaire ligneBudgetaire : lignesBudgetaires) {
				TreeItem<LigneBudgetaire> item = new TreeItem<>(ligneBudgetaire);
				rootItem.getChildren().add(item);
			}

			treeTableLignes.setRoot(rootItem);
		}

	}


	public ObservableList<LigneBudgetaire> getLignesBudgetaires() {
		// Récupérer les lignes budgétaires associées au projet
		return FXCollections.observableArrayList(projet.getAllLigneBudgetaires());
	}

	/**
	 * @return the projet
	 */
	public Projet getProjet() {
		return projet;
	}

	/**
	 * @param projet the projet to set
	 */
	public void setProjet(Projet projet) {
		this.projet = projet;
		update();
	}

	
	
	private boolean checkSelection()
	{
		if (this.treeTableLignes.getSelectionModel().isEmpty()) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
            emptyAlert.setTitle("Aucune ligne selectionné");
            emptyAlert.setHeaderText(null);
            emptyAlert.setContentText("Choisissez une ligne avant de faire une action");
			return false;
		}
		
		if (this.treeTableLignes.getSelectionModel().getSelectedCells().size()>1) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
            emptyAlert.setTitle("Plusieurs ligne selectionné");
            emptyAlert.setHeaderText(null);
            emptyAlert.setContentText("Choisissez qu'une unique une ligne avant de faire une action");
            emptyAlert.showAndWait();
			return false;
		}
		
		return true;
	}
	
	@FXML
	public void ajouterRessource() {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_affectation_ressource.fxml"));
        Parent root = loader.load();
        AffectationRessourceController controllerRessource = loader.getController();
        Stage ressourceStagectStage = new Stage();
        ressourceStagectStage.setTitle("Ajouter une nouvelle ressource");
        ressourceStagectStage.setScene(new Scene(root, 800, 425));
        ressourceStagectStage.initModality(Modality.APPLICATION_MODAL);
        ressourceStagectStage.initOwner(mainStage);
        controllerRessource.setRessourceList(this.ressourceList);
        controllerRessource.setProjet(this.projet);
        controllerRessource.updateComponents();
        ressourceStagectStage.showAndWait();
        this.TableViewEmployeeUpdate();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}

	
	@FXML
	public void modifierRessource() {
		if(this.tableViewRessource.getSelectionModel().getSelectedItem() == null)
		{
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
	        emptyAlert.setTitle("Pas de ressource selectionne");
	        emptyAlert.setHeaderText(null);
	        emptyAlert.setContentText("Selectionnez une ressource avant de le modifier");
	        emptyAlert.showAndWait();
	        return;
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_affectation_ressource.fxml"));
	        Parent root = loader.load();
	        AffectationRessourceController controllerRessource = loader.getController();
	        Stage ressourceStagectStage = new Stage();
	        ressourceStagectStage.setTitle("Ajouter une nouvelle ressource");
	        ressourceStagectStage.setScene(new Scene(root, 800, 425));
	        ressourceStagectStage.initModality(Modality.APPLICATION_MODAL);
	        ressourceStagectStage.initOwner(mainStage);
	        controllerRessource.setRessourceList(this.ressourceList);
	        controllerRessource.setProjet(this.projet);
	        controllerRessource.setModifer(true);
	        controllerRessource.setSelectedRessource(this.tableViewRessource.getSelectionModel().getSelectedItem());
	        controllerRessource.updateComponents();
	        controllerRessource.updatedate(this.dateDebutColumn.getCellData(this.tableViewRessource.getSelectionModel().getSelectedItem()),
	        		this.dateFinColumn.getCellData(this.tableViewRessource.getSelectionModel().getSelectedItem()));
	        this.setProjet(controllerRessource.getProjet());
	        ressourceStagectStage.showAndWait();
	        this.TableViewEmployeeUpdate();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void supprimerRessource() {
		if (this.tableViewRessource.getSelectionModel().getSelectedItem() == null) 
		{
			
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
	        emptyAlert.setTitle("Aucune ressource selectionne");
	        emptyAlert.setHeaderText(null);
	        emptyAlert.setContentText("Selectionnez une ressource avant de le supprimer");
	        emptyAlert.showAndWait();
	        return;
		}
		
		
		Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Etes vous sur?");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText("Vous etes sur le point de d�saffecter la ressource du projet, continuer ?");
        
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        	this.projet.getRessources().remove(this.tableViewRessource.getSelectionModel().getSelectedItem());
        	this.TableViewEmployeeUpdate();
        }
	}

	public List<Ressource> getRessourceList() {
		return ressourceList;
	}


	public void setRessourceList(List<Ressource> ressourceList) {
		this.ressourceList = ressourceList;
	}
	
	
}
