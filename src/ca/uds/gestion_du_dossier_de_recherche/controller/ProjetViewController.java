package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

public class ProjetViewController {

	VueGeneralControler vueGeneralControler = new VueGeneralControler();
	List<Projet> projets = vueGeneralControler.getListeProjet();
	List<Ressource> ressources = vueGeneralControler.getRessourceList();
	
	@FXML
	private TreeTableView<LigneBudgetaire> treeTableLignes;

	@FXML
	private TextArea text_projet ;
		
	@FXML
	private TextArea lignes_budg ;
	
    @FXML
    private Text nb_ressource;
	
    @FXML
    private Text montant_total;

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
	// Onglet Ligne Budgtaire //
	// ---------------------- //
	public void update() {
		treeTableUpdate();
		Afficheinfos();
	}
	
	@FXML
	public void addLigne() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_ajout_budget.fxml"));
            Parent root = loader.load();
            LignesAjoutModifController controllerLigne = loader.getController();
            Stage projectStage = new Stage();
            projectStage.setTitle("Modifier Ligne");
            projectStage.setScene(new Scene(root, 925, 740));
            projectStage.initModality(Modality.APPLICATION_MODAL);
            projectStage.initOwner(mainStage);
            controllerLigne.setLigne(new LigneBudgetaire());
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
			System.out.println("Hey");
	}

	@FXML
	public void Afficheinfos() {
		 
		
		Projet projet = projets.get(0);
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

//    	    ubrColumn.setCellValueFactory(param -> {
//    	        LigneBudgetaire ligneBudgetaire = param.getValue().getValue();
//
//    	        TreeItem<String> ubrRootItem = new TreeItem<>("UBRs");
//    	        ubrRootItem.setExpanded(true);
//
//    	        // Ajouter chaque UBR comme un noeud enfant
//    	        for (UBR ubr : ligneBudgetaire.getUbrs()) {
//    	            String ubrText = "UBR de " + ubr.getOrganisme().getNom() + ", Code : " + ubr.getCode() + ", Montant :" + ubr.getMontant(ligneBudgetaire) + "$, De " + ubr.getDateDebut() + " à " + ubr.getDateFin();
//    	            TreeItem<String> ubrItem = new TreeItem<>(ubrText);
//    	            ubrRootItem.getChildren().add(ubrItem);
//    	        }
//
//    	        return new SimpleObjectProperty<>(ubrRootItem);
//    	    });

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

//        ObservableList<LigneBudgetaire> lignesBudgetaires = getLignesBudgetaires();
//
//        // Créer les colonnes pour la TreeTableView
//        TreeTableColumn<LigneBudgetaire, String> ligneColumn = new TreeTableColumn<>("Ligne Budgétaire");
//        TreeTableColumn<LigneBudgetaire, String> ubrColumn = new TreeTableColumn<>("UBR");
//        TreeTableColumn<LigneBudgetaire, String> depenseColumn = new TreeTableColumn<>("Dépense");
//        
//        // Associer les données à chaque colonne
//        ligneColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
//
//        // Créer une cell factory personnalisée pour la colonne des UBR
//        ubrColumn.setCellFactory(column -> new TreeTableCell<LigneBudgetaire, String>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item == null || empty) {
//                    setText(null);
//                    setStyle("");
//                } else {
//                    setText(item);
//
//                    // Mettre le texte en vert
//                    setStyle("-fx-text-fill: green;");
//                }
//            }
//        });
//
//        // Créer une cell factory personnalisée pour la colonne des dépenses
//        depenseColumn.setCellFactory(column -> new TreeTableCell<LigneBudgetaire, String>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item == null || empty) {
//                    setText(null);
//                    setStyle("");
//                } else {
//                    setText(item);
//
//                    // Mettre le texte en rouge
//                    setStyle("-fx-text-fill: red;");
//                }
//            }
//        });
//
//        treeTableLignes.getColumns().addAll(ligneColumn, ubrColumn, depenseColumn);
//
//        // Créer les items pour la TreeTableView
//        TreeItem<LigneBudgetaire> rootItem = new TreeItem<>();
//        rootItem.setExpanded(true);
//
//        for (LigneBudgetaire ligneBudgetaire : lignesBudgetaires) {
//            TreeItem<LigneBudgetaire> item = new TreeItem<>(ligneBudgetaire);
//            rootItem.getChildren().add(item);
//        }
//
//        treeTableLignes.setRoot(rootItem);
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
            emptyAlert.showAndWait();
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
