package ca.uds.gestion_du_dossier_de_recherche.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ca.uds.gestion_du_dossier_de_recherche.controller.GeneralViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.VueGeneralControler;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VueGenerale extends Application {

	@FXML
	private ComboBox<StrategieTrie> comboBoxStrategie;
	
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
        primaryStage.setTitle("Time Record");
        primaryStage.setScene(new Scene(root, 600, 400));
        mainStage = primaryStage;
        primaryStage.show();
	}
	
	
	@FXML
    public void initialize() {
		this.comboBoxStrategie.getItems().clear();
		this.comboBoxStrategie.getItems().add(this.controler.getStrategieDate());
		this.comboBoxStrategie.getItems().add(this.controler.getStrategieMontant());
    }

}
