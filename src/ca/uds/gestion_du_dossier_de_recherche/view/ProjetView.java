package ca.uds.gestion_du_dossier_de_recherche.view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import ca.uds.gestion_du_dossier_de_recherche.controller.ProjetViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.VueGeneralControler;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ProjetView extends Application {

    @FXML
    private TreeView<LigneBudgetaire> treeViewLignes;

    private ProjetViewController controller;

    public ProjetView() {
        super();
        this.controller = new ProjetViewController();
    }

    public ProjetView(ProjetViewController _controller) {
        controller = _controller;
    }


    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_projet_view.fxml"));
        loader.setController(this);
        
        // supprimer cette méthode lorsque l'on aura des fenetre modal
        controller.setProjet(MainTestFrameProjet.stubProjetaSupprimer());
 
        
        
        Parent root = loader.load();
        primaryStage.setTitle("App de gestion d' projet");
        primaryStage.setScene(new Scene(root, 925.0, 740.0));
        primaryStage.show();
        
        
        
        
    }

    @FXML
    public void initialize() {
        
    
    	ObservableList<LigneBudgetaire> lignesBudgetaires = controller.getLignesBudgetaires();

        TreeItem<LigneBudgetaire> rootItem = new TreeItem<>();
        rootItem.setExpanded(true);

        for (LigneBudgetaire ligneBudgetaire : lignesBudgetaires) {
            TreeItem<LigneBudgetaire> item = new TreeItem<>(ligneBudgetaire);
            rootItem.getChildren().add(item);
        }

        treeViewLignes.setRoot(rootItem);
    }
}

//public class ProjetView extends Application {
//	
//	
////	@FXML 
////	TableView<Projet> tableViewProjet;
////	
//	
//	@FXML 
//	TreeView<Projet> treeViewProjet;
//	
//	
//	ProjetViewController controller;
//	
//	public ProjetView() {
//		super();
//	}
//	
//	public ProjetView(ProjetViewController _controller){
//		controller  = _controller;
//	}
//	
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("file/Prototype_AOO_projet_view.fxml"));
//	    loader.setController(controller);
//		Parent root = loader.load();
//		primaryStage.setTitle("App de gestion d' projet");
//		primaryStage.setScene(new Scene(root,925.0,740.0));
//		primaryStage.show();	
//	}
//	
//	
//	@FXML
//    public void initialize() {
//		System.out.println("OUIIIIIIIIIIIII");
////		updateComboBox();
////		updateTableViewProjet();
//	}
//	
////	prefHeight="" prefWidth="925.0"
//
//}
