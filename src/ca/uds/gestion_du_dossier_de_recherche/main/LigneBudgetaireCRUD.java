package ca.uds.gestion_du_dossier_de_recherche.main;

import javax.swing.*;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LigneBudgetaireCRUD extends JFrame {

    private List<LigneBudgetaire> lignesBudgetaires;
    private JList<LigneBudgetaire> ligneBudgetaireJList;
    private DefaultListModel<LigneBudgetaire> listModel;

    public LigneBudgetaireCRUD() {
        lignesBudgetaires = new ArrayList<>();
        listModel = new DefaultListModel<>();

        // Création de l'interface utilisateur
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestion des lignes budgétaires");
        setSize(400, 300);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        ligneBudgetaireJList = new JList<>(listModel);
        panel.add(new JScrollPane(ligneBudgetaireJList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterLigneBudgetaire();
            }
        });
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Modifier");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierLigneBudgetaire();
            }
        });
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerLigneBudgetaire();
            }
        });
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void ajouterLigneBudgetaire() {
        String nom = JOptionPane.showInputDialog(this, "Nom de la ligne budgétaire :");
        String type = JOptionPane.showInputDialog(this, "Type de la ligne budgétaire :");
        LigneBudgetaire nouvelleLigne = new LigneBudgetaire(nom, type);
        lignesBudgetaires.add(nouvelleLigne);
        listModel.addElement(nouvelleLigne);
    }

    private void modifierLigneBudgetaire() {
        LigneBudgetaire ligneSelectionnee = ligneBudgetaireJList.getSelectedValue();
        if (ligneSelectionnee != null) {
            String nouveauNom = JOptionPane.showInputDialog(this, "Nouveau nom de la ligne budgétaire :", ligneSelectionnee.getNom());
            String nouveauType = JOptionPane.showInputDialog(this, "Nouveau type de la ligne budgétaire :", ligneSelectionnee.getType());
            ligneSelectionnee.setNom(nouveauNom);
            ligneSelectionnee.setType(nouveauType);
            ligneBudgetaireJList.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne budgétaire à modifier.");
        }
    }

    private void supprimerLigneBudgetaire() {
        int selectedIndex = ligneBudgetaireJList.getSelectedIndex();
        if (selectedIndex != -1) {
            LigneBudgetaire ligneASupprimer = lignesBudgetaires.get(selectedIndex);
            listModel.remove(selectedIndex);
            lignesBudgetaires.remove(ligneASupprimer);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne budgétaire à supprimer.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LigneBudgetaireCRUD app = new LigneBudgetaireCRUD();
                app.setVisible(true);
            }
        });
    }
}
