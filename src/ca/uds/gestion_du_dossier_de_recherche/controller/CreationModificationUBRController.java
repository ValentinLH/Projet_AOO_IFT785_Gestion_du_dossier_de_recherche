package ca.uds.gestion_du_dossier_de_recherche.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;

public class CreationModificationUBRController {
	
	private LigneBudgetaire ligne;
	private TableView<UBR> ubrTableView;

    @FXML
    private TextField textFieldOrganisme;

    @FXML
    private TextField textFieldCodeUBR;

    @FXML
    private TextField textFieldMontantTotal;

    @FXML
    private TextField textFieldMontantMinimum;

    @FXML
    private CheckBox checkBoxContraintes;

    @FXML
    private DatePicker datePickerDateDebut;

    @FXML
    private DatePicker datePickerDateFin;

    @FXML
    private Button buttonValider;

    @FXML
    private Button buttonAnnuler;

    private Stage stage;

    private UBR ubr;

    public void setStage(Stage stage) {
        this.stage = stage;
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
	}



	/**
	 * @return the ubrTableView
	 */
	public TableView<UBR> getUbrTableView() {
		return ubrTableView;
	}



	/**
	 * @param ubrTableView the ubrTableView to set
	 */
	public void setUbrTableView(TableView<UBR> ubrTableView) {
		this.ubrTableView = ubrTableView;
	}



	public void setUBR(UBR ubr) {
        this.ubr = ubr;
        if (ubr != null) {
            // Pré-remplir les champs si un UBR est fourni pour modification
            textFieldOrganisme.setText(ubr.getOrganisme().getNom());
            textFieldCodeUBR.setText(String.valueOf(ubr.getCode()));
            textFieldMontantTotal.setText(String.valueOf(ubr.getMontantTotalUBR(ligne)));
            textFieldMontantMinimum.setText(String.valueOf(ubr.getMontantaUtilise(ligne)));
            checkBoxContraintes.setSelected(ubr.isContraintes());
            datePickerDateDebut.setValue(ubr.getDateDebut());
            datePickerDateFin.setValue(ubr.getDateFin());
        }
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
        TextFormatter<String> formatter2 = new TextFormatter<>(filter);
        textFieldMontantTotal.setTextFormatter(formatter);
        textFieldMontantMinimum.setTextFormatter(formatter2);


        UnaryOperator<TextFormatter.Change> filter1 = change -> {
            String text = change.getControlNewText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        };

        // Appliquer le filtre au champ de texte
        TextFormatter<String> formatter3 = new TextFormatter<>(filter1);
        textFieldCodeUBR.setTextFormatter(formatter3);
        
    }
    
    
    
    @FXML
    void validerUBR() {
        // Récupérer les valeurs des champs
        String nomOrganisme = textFieldOrganisme.getText();
        int codeUBR = Integer.parseInt(textFieldCodeUBR.getText());
        float montantTotal = Float.parseFloat(textFieldMontantTotal.getText());
        float montantMinimum = Float.parseFloat(textFieldMontantMinimum.getText());
        boolean contraintes = checkBoxContraintes.isSelected();
        LocalDate dateDebut = datePickerDateDebut.getValue();
        LocalDate dateFin = datePickerDateFin.getValue();

        // Créer un nouvel UBR avec les valeurs
        UBR newUBR = new UBR();
        newUBR.setOrganisme(new Organisme(nomOrganisme,0)); // Supposons qu'Organisme ait un constructeur prenant le nom
        newUBR.setCode(codeUBR);
        newUBR.ajouterLigneBudgetaire(ligne, montantTotal,montantMinimum);
        newUBR.setContraintes(contraintes);
        newUBR.setDateDebut(dateDebut);
        newUBR.setDateFin(dateFin);

        ubrTableView.getItems().add(newUBR);
        
        
        stage.close();
    }

    @FXML
    void annulerUBR() {
        // Fermer la fenêtre sans effectuer de modification
        stage.close();
    }
}
