package ca.uds.gestion_du_dossier_de_recherche.controller;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.util.stream.Collectors;

public class LignesAjoutModifController {

	private TreeTableView<LigneBudgetaire> treeTableLignes;
	private LigneBudgetaire ligne;
	private Projet projet;
	private Stage mainStage;
	private Stage stage;

	private boolean estUnAjout = true;

	@FXML
	private ComboBox<UBR> ubrComboBox;

	@FXML
	private TableView<UBR> ubrTableView;

	@FXML
	private TableView<Depense> depenseTableView;

	@FXML
	private TextField nomLigne;

	@FXML
	private TextField typeTextField;

	public LignesAjoutModifController() {
		super();
		// nous devons avoir une ligne pour les ubr
		this.ligne = new LigneBudgetaire();
		
	}

	public LignesAjoutModifController(LigneBudgetaire ligne) {
		super();
		this.ligne = ligne;
		estUnAjout = false;
		update();
	}

	private void update() {
		nomLigne.setText(ligne.getNom());
		typeTextField.setText(ligne.getType());
		setUbrTableView();
		updateUbrComboBox();
		setDepenseTableView();
	}

	
	@FXML
    public void initialize() {
		initUbrTableView();
		setDepenseTableView();
    }
	
	/**
	 * @return the treeTableLignes
	 */
	public TreeTableView<LigneBudgetaire> getTreeTableLignes() {
		return treeTableLignes;
	}

	/**
	 * @param treeTableLignes the treeTableLignes to set
	 */
	public void setTreeTableLignes(TreeTableView<LigneBudgetaire> treeTableLignes) {
		this.treeTableLignes = treeTableLignes;
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
		estUnAjout = false;
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

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void initUbrTableView()
	{

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
			return new SimpleStringProperty(ubr.getMontantTotalUBR(ligne) + "$"); // Remplacer "Montant" par le montant
																					// réel
		});

		TableColumn<UBR, String> montantsMinColumn = new TableColumn<>("Montants Minimum à utiliser");
		montantsMinColumn.setCellValueFactory(cellData -> {
			UBR ubr = cellData.getValue();
			return new SimpleStringProperty(ubr.getMontantaUtilise(ligne) + "$"); // Remplacer "Montant" par le montant
																					// réel
		});

		ubrTableView.getColumns().addAll(idColumn, codeColumn, organismeColumn, contraintesColumn, dateDebutColumn,
				dateFinColumn, montantsTotalColumn, montantsMinColumn);

	}
	

	public void setUbrTableView() {

		ObservableList<UBR> data = FXCollections.observableArrayList(ligne.getUbrs());

		ubrTableView.setItems(data);
	}

	public void setDepenseTableView() {
		// Columns
		TableColumn<Depense, String> nomColumn = new TableColumn<>("Nom");
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<Depense, String> montantsTotalColumn = new TableColumn<>("Montants");
		montantsTotalColumn.setCellValueFactory(cellData -> {
			Depense depense = cellData.getValue();
			return new SimpleStringProperty(depense.getMontant() + "$"); // Remplacer "Montant" par le montant réel
		});

		depenseTableView.getColumns().addAll(nomColumn, montantsTotalColumn);

		ObservableList<Depense> data = FXCollections.observableArrayList(ligne.getDepenses());

		depenseTableView.setItems(data);
	}

	@FXML
	public void delUBR() {
		if (this.ubrTableView.getSelectionModel().isEmpty()) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
			emptyAlert.setTitle("Aucun UBR sélectionné");
			emptyAlert.setHeaderText(null);
			emptyAlert.setContentText("Choisissez un UBR avant de la supprimer");
			emptyAlert.showAndWait();
			return;
		} else {
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setTitle("Confirmation de suppression");
			confirmationAlert.setHeaderText(null);
			confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet UBR ?");

			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				UBR selectedUBR = ubrTableView.getSelectionModel().getSelectedItem();
				selectedUBR.supprimerLigneBudgetaire(ligne);
				ubrTableView.getItems().remove(selectedUBR);
			}
		}

	}

	private boolean checkUbrSelection() {
		if (this.ubrTableView.getSelectionModel().isEmpty()) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
			emptyAlert.setTitle("Aucune ligne selectionné");
			emptyAlert.setHeaderText(null);
			emptyAlert.setContentText("Choisissez une ligne avant de faire une action");
			emptyAlert.showAndWait();
			return false;
		}

		if (this.ubrTableView.getSelectionModel().getSelectedCells().size() > 1) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
			emptyAlert.setTitle("Plusieurs ligne selectionné");
			emptyAlert.setHeaderText(null);
			emptyAlert.setContentText("Choisissez qu'une unique une ligne avant de faire une action");
			emptyAlert.showAndWait();
			return false;
		}

		return true;
	}

	void updateUbrComboBox() {
		this.ubrComboBox.getItems().clear();
		for (UBR ubr : ligne.getUbrs())
			this.ubrComboBox.getItems().add(ubr);
//		this.ubrComboBox.getItems().add(this.controler.getStrategieMontant());
	}

	@FXML
	void addDepense() {

		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("..\\view\\file\\Prototype_AOO_ajout_depense.fxml"));
			Parent root = loader.load();
			CreationDepenseController controllerDepense = loader.getController();
			Stage projectStage = new Stage();
			projectStage.setTitle("Ajouter");
			projectStage.setScene(new Scene(root, 600.0, 183.0));
			projectStage.initModality(Modality.APPLICATION_MODAL);
			projectStage.initOwner(mainStage);
			controllerDepense.setDepenseTableView(this.depenseTableView);
			controllerDepense.setStage(projectStage);
			projectStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//
	}

	@FXML
	void delDepense() {
		if (this.depenseTableView.getSelectionModel().isEmpty()) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
			emptyAlert.setTitle("Aucune dépense sélectionnée");
			emptyAlert.setHeaderText(null);
			emptyAlert.setContentText("Choisissez une dépense avant de la supprimer");
			emptyAlert.showAndWait();
			return;
		} else {
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setTitle("Confirmation de suppression");
			confirmationAlert.setHeaderText(null);
			confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette dépense ?");

			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				Depense selectedDepense = depenseTableView.getSelectionModel().getSelectedItem();

				depenseTableView.getItems().remove(selectedDepense);
			}
		}

	}

	@FXML
	void addUBR() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\view\\file\\Prototype_AOO_ajout_UBR.fxml"));
			Parent root = loader.load();
			CreationModificationUBRController controllerUBR = loader.getController();
			Stage projectStage = new Stage();
			projectStage.setTitle("Ajouter");
//            prefHeight="400.0" prefWidth="429.0"
			projectStage.setScene(new Scene(root, 400.0, 429.0));
			projectStage.initModality(Modality.APPLICATION_MODAL);
			projectStage.initOwner(mainStage);
			controllerUBR.setLigne(this.ligne);
			controllerUBR.setUbrTableView(ubrTableView);
			controllerUBR.setStage(projectStage);
			projectStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void valider() {
		// Récupérer les informations saisies
		String nom = nomLigne.getText();
		String type = typeTextField.getText();

		// Vérifier si les champs sont vides
		if (nom.isEmpty() || type.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Champs vides");
			alert.setHeaderText(null);
			alert.setContentText("Veuillez remplir tous les champs.");
			alert.showAndWait();
			return;
		}

		// Afficher une alerte de confirmation
		Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);

		confirmationAlert.setTitle("Confirmation");
		confirmationAlert.setHeaderText(null);
		if (estUnAjout) {
			confirmationAlert.setContentText("Êtes-vous sûr de vouloir ajouter cette nouvelle ligne budgétaire ?");
		} else
			confirmationAlert.setContentText("Êtes-vous sûr de vouloir modifier cette ligne budgétaire ?");

		Optional<ButtonType> result = confirmationAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {

			ligne.setNom(nom);
			ligne.setType(type);
			ligne.setUbrs(ubrTableView.getItems().stream().collect(Collectors.toSet()));
			ligne.setDepenses(depenseTableView.getItems().stream().collect(Collectors.toSet()));

			projet.addLigneBudgetaire(ligne);

			if (estUnAjout)
			{
		        TreeItem<LigneBudgetaire> newTreeItem = new TreeItem<>(ligne);

		        // Récupérer la racine de la TreeTableView de la deuxième page
		        TreeItem<LigneBudgetaire> rootItem = treeTableLignes.getRoot();

		        // Ajouter le nouvel TreeItem à la liste des enfants de la racine
		        rootItem.getChildren().add(newTreeItem);

		        // Mettre à jour la TreeTableView avec la racine mise à jour
		        treeTableLignes.setRoot(rootItem);

			}
			
			
			
			// partie sensible de l'ajout a la treetable view de la page precedente
			treeTableLignes.refresh();

			// Création des données de modèle observable
			ObservableList<LigneBudgetaire> lignesBudgetaires = FXCollections.observableArrayList();

//			// Lier les données de modèle au TreeTableView
//			treeTableLignes.setRoot(new TreeItem<>());s
//			treeTableLignes.getRoot().getChildren().addAll(
//			    lignesBudgetaires.stream().map(TreeItem::new).collect(Collectors.toList())
//			);
//
//			int index = lignesBudgetaires.indexOf(ligne);
//			if (index != -1) {
//			    // Remplacer l'élément existant par le nouvel élément
//
//			  
//			    
//			} else {
//			    // Ajouter le nouvel élément
//			    lignesBudgetaires.add(ligne);
//		        // Créer un nouvel TreeItem avec la ligne budgétaire
//		        TreeItem<LigneBudgetaire> newTreeItem = new TreeItem<>(ligne);
//
//		        // Récupérer la racine de la TreeTableView de la deuxième page
//		        TreeItem<LigneBudgetaire> rootItem = treeTableLignes.getRoot();
//
//		        // Ajouter le nouvel TreeItem à la liste des enfants de la racine
//		        rootItem.getChildren().add(newTreeItem);
//
//		        // Mettre à jour la TreeTableView avec la racine mise à jour
//		        treeTableLignes.setRoot(rootItem);
//				//			
//
//			}

//			treeTableLignes.getItems().add(ligne);

			// Afficher un message de succès
			Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
			successAlert.setTitle("Nouvelle ligne budgétaire ajoutée");
			successAlert.setHeaderText(null);
			successAlert.setContentText("La nouvelle ligne budgétaire a été ajoutée avec succès.");
			successAlert.showAndWait();

			stage.close();
		}
	}

	@FXML
	void annuler() {

	}

}
