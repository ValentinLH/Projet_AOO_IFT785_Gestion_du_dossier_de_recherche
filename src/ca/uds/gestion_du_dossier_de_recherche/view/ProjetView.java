package ca.uds.gestion_du_dossier_de_recherche.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ca.uds.gestion_du_dossier_de_recherche.controller.ProjetViewController;
import ca.uds.gestion_du_dossier_de_recherche.controller.VueGeneralControler;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;


public class ProjetView extends Application {

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
     // Obtenir la taille de l'écran et definir la scene en fonction de cette taille
     // Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
     // primaryStage.setScene(new Scene(root, screenBounds.getWidth(), screenBounds.getHeight()));
        primaryStage.setScene(new Scene(root, 925.0, 740.0));
        primaryStage.show();
        
        
    }
    


    
//    @FXML
//    public void initialize() {
//        ObservableList<LigneBudgetaire> lignesBudgetaires = controller.getLignesBudgetaires();
//
//        TreeItem<String> rootItem = new TreeItem<>();
//        rootItem.setExpanded(true);
//
//        for (LigneBudgetaire ligneBudgetaire : lignesBudgetaires) {
//            TreeItem<String> item = new TreeItem<>(ligneBudgetaire.getNom());
//
//            // Ajouter les sous-nœuds pour représenter les ubrs et les dépenses de la ligne budgétaire
//            for (UBR ubr : ligneBudgetaire.getUbrs()) {
//                TreeItem<String> ubrNode = new TreeItem<>("UBR de "+ubr.getOrganisme().getNom() +", Code : "+ ubr.getCode()+", Montant :"+ubr.getMontant(ligneBudgetaire)+"$, De "+ubr.getDateDebut()+" à "+ubr.getDateFin()); // Créer un nœud pour UBR
//                item.getChildren().add(ubrNode); // Ajouter le nœud UBR comme enfant du nœud de la ligne budgétaire
//            }
//
//            for (Depense depense : ligneBudgetaire.getDepenses()) {
//                TreeItem<String> depenseNode = new TreeItem<>("Dépense: " + depense.getNom()+ ", Montant : "+depense.getMontant()+"$"); // Créer un nœud pour la dépense
//                item.getChildren().add(depenseNode); // Ajouter le nœud dépense comme enfant du nœud de la ligne budgétaire
//            }
//
//            rootItem.getChildren().add(item); // Ajouter le nœud de la ligne budgétaire à la racine
//        }
//
//        treeViewLignes.setRoot(rootItem);
//    }

}
