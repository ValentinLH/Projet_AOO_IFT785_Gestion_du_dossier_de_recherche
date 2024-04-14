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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProjetViewController {

	
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

	/*=============================*/
	private Projet projet;
	private ProjetView view;
	
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
		
		this.dateFinColumn.setCellValueFactory(
			    new PropertyValueFactory<>("finContrat"));
		
		this.dateDebutColumn.setCellValueFactory(
			    new PropertyValueFactory<>("debutContrat"));
		
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
	
	
}
