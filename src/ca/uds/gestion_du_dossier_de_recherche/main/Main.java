package ca.uds.gestion_du_dossier_de_recherche.main;

import java.lang.reflect.Field;

import javax.swing.SwingUtilities;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;

import ca.uds.gestion_du_dossier_de_recherche.view.AddEntityView;
	
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;





public class Main {

    public static void main(String[] args) {
        AddEntityView<LigneBudgetaire> view = new AddEntityView<>(LigneBudgetaire.class);
        view.getOkButton().addActionListener(e -> {
        	LigneBudgetaire ligneBudgetaire = new LigneBudgetaire();
            for (Field field : LigneBudgetaire.class.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object value = view.getValueForField(field);
                    field.set(ligneBudgetaire, value);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
            // Faire quelque chose avec l'employé, par exemple l'ajouter à une liste, l'envoyer à une méthode, etc.
        });
    }
	
	public static void main_2(String[] args) {
        AddEntityView<UBR> view = new AddEntityView<>(UBR.class);
        view.getOkButton().addActionListener(e -> {
        	UBR ligneBudgetaire = new UBR();
            for (Field field : UBR.class.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object value = view.getValueForField(field);
                    field.set(ligneBudgetaire, value);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
            // Faire quelque chose avec l'employé, par exemple l'ajouter à une liste, l'envoyer à une méthode, etc.
        });
    }

    
	public static void main_test_factory(String[] args) {

		
		System.out.println("Hey");
	    FabriquePersonne factory = new FabriqueEtudiant();
	    Ressource ressource = factory.createRessource("John", "Doe", 20.0f, 40.0f, "2023-01-01", "2023-12-31");
	    ressource.setCip("jj");
	    ressource.setProgramme(Programme.DOCTORAT);
	    System.out.println(ressource.getProgramme());
	    System.out.println(ressource.toString());
	    //System.out.println(ressource.getNom());
	    //System.out.println(ressource.calcul_salaire_mensuel());
	}
}
	
//	    public static void main(String[] args) {
//	        SwingUtilities.invokeLater(() -> {
//	            // Créer une instance de AddEntityView pour la classe Entity
//	            AddEntityView<UBR> addEntityView = new AddEntityView<>(UBR.class);
//	            // Écouter l'action du bouton OK
//	            addEntityView.getOkButton().addActionListener(e -> {
//	                // Récupérer les valeurs des champs et les afficher
//	                for (Field field : UBR.class.getDeclaredFields()) {
//	                    Object value = addEntityView.getValueForField(field);
//	                    System.out.println(field.getName() + ": " + value);
//	                }
//	            });
//	        });
//	    }
