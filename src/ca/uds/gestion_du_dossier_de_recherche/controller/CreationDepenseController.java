package ca.uds.gestion_du_dossier_de_recherche.controller;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import javafx.scene.control.TableView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter;

public class CreationDepenseController {

    @FXML
    private TextField textFieldNom;

    @FXML
    private TextField textFieldMontant;

    @FXML
    private Button buttonValider;

    private TableView<Depense> depenseTableView;
    private Stage stage;

    public CreationDepenseController() {
        super();
    }

    public CreationDepenseController(TableView<Depense> depenseTableView) {
        super();
        this.depenseTableView = depenseTableView;
    }

    
    
    /**
	 * @return the depenseTableView
	 */
	public TableView<Depense> getDepenseTableView() {
		return depenseTableView;
	}

	/**
	 * @param depenseTableView the depenseTableView to set
	 */
	public void setDepenseTableView(TableView<Depense> depenseTableView) {
		this.depenseTableView = depenseTableView;
	}

	public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void initialize() {
        // Ajouter une validation pour le textFieldMontant
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (text.matches("-?\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        textFieldMontant.setTextFormatter(formatter);
    }

    @FXML
    void validerDepense() {
        String nom = textFieldNom.getText();
        float montant = Float.parseFloat(textFieldMontant.getText());
        Depense newDepense = new Depense(nom, montant);
        depenseTableView.getItems().add(newDepense);
        clearFields();
        stage.close(); // Fermer la fenêtre après validation
    }

    @FXML
    void annulerDepense() {
        clearFields();
        stage.close(); // Fermer la fenêtre après annulation
    }

    private void clearFields() {
        textFieldNom.clear();
        textFieldMontant.clear();
    }
}
