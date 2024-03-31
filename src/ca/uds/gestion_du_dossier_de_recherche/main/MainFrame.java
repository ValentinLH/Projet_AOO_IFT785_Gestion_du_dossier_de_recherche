package ca.uds.gestion_du_dossier_de_recherche.main;

import javax.swing.*;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.view.AddEntityViewPanel;

import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Add Entity");
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddEntityViewPanel<UBR> panel = new AddEntityViewPanel<>(UBR.class);
        getContentPane().add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
