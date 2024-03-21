package ca.uds.gestion_du_dossier_de_recherche.main;

import java.lang.reflect.Field;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.view.AddEntityView;

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
}
