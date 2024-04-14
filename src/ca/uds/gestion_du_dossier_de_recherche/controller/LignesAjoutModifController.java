package ca.uds.gestion_du_dossier_de_recherche.controller;


import java.time.LocalDate;
import java.util.Map;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR.Fond;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LignesAjoutModifController {

	private LigneBudgetaire ligne;
	private Projet projet;

	@FXML 
	private TableView<UBR> ubrTableView;
	
	@FXML
	private TextField nomLigne;
	
	@FXML
	private TextField typeTextField;
	
	public LignesAjoutModifController() {
		super();
		this.ligne = null;
	}

	
	public LignesAjoutModifController(LigneBudgetaire ligne) {
		super();
		this.ligne = ligne;
		update();
	}

	private void update()
	{
		nomLigne.setText(ligne.getNom());
		typeTextField.setText(ligne.getType());
		setUbrTableView();
	}
	

	/**
	 * @return the ligne
	 */
	public LigneBudgetaire getLigne() {
		return ligne;
	}

	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(LigneBudgetaire ligne) {
		this.ligne = ligne;
		update();
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
	}

	public void setUbrTableView()
	{
//		TableView<UBR> ubrTableView = new TableView<>();

        // Columns
        TableColumn<UBR, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<UBR, Number> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<UBR, Organisme> organismeColumn = new TableColumn<>("Organisme");
        organismeColumn.setCellValueFactory(new PropertyValueFactory<>("organisme"));

        TableColumn<UBR, Boolean> contraintesColumn = new TableColumn<>("Contraintes");
        contraintesColumn.setCellValueFactory(cellData -> {
            UBR ubr = cellData.getValue();
            return new SimpleBooleanProperty(ubr.isContraintes());
        });

        TableColumn<UBR, LocalDate> dateDebutColumn = new TableColumn<>("Date de Début");
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        TableColumn<UBR, LocalDate> dateFinColumn = new TableColumn<>("Date de Fin");
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        TableColumn<UBR, String> montantsTotalColumn = new TableColumn<>("Montants Total");
        montantsTotalColumn.setCellValueFactory(cellData -> {
            UBR ubr = cellData.getValue();            
            return new SimpleStringProperty(ubr.getMontantTotalUBR(ligne)+"$"); // Remplacer "Montant" par le montant réel
        });

        TableColumn<UBR, String> montantsMinColumn = new TableColumn<>("Montants Minimum à utiliser");
        montantsMinColumn.setCellValueFactory(cellData -> {
            UBR ubr = cellData.getValue();            
            return new SimpleStringProperty(ubr.getMontantaUtilise(ligne)+"$"); // Remplacer "Montant" par le montant réel
        });
        
        ubrTableView.getColumns().addAll(idColumn, codeColumn, organismeColumn, contraintesColumn, dateDebutColumn, dateFinColumn,montantsTotalColumn, montantsMinColumn);

        // Sample data
        ObservableList<UBR> data = FXCollections.observableArrayList(ligne.getUbrs());
        // Ajoutez des objets UBR à la liste data ici...

        ubrTableView.setItems(data);
	}
	
	
}
