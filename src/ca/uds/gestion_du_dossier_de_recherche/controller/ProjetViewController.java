package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.view.GeneralView;
import ca.uds.gestion_du_dossier_de_recherche.view.ProjetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class ProjetViewController {


    @FXML
    private TreeTableView<LigneBudgetaire> treeTableLignes;

    private Projet projet;
    private ProjetView view;
    
    public ProjetViewController() {
        super();
        this.projet = null;
        
    }
    public ProjetViewController(Projet projet) {
        super();
        this.projet = projet;
        view = new ProjetView(this);
        
    }
    
    public void update() {
    	treeTableUpdate();
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
    	                .map(ubr -> "UBR de " + ubr.getOrganisme().getNom() + ", Code : " + ubr.getCode() + ", Montant :" + ubr.getMontant(ligneBudgetaire) + "$, De " + ubr.getDateDebut() + " à " + ubr.getDateFin())
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


    

}


