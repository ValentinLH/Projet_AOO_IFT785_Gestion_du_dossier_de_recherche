package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;
import java.time.LocalDate;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.GrilleSalariale;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.UtilitaireDate;

public class RessourceTest {

    private Ressource ressource;

    @Before
    public void setUp() {
        ressource = new RessourceConcret("Jean", "Dupont", 6, 7, 35, LocalDate.of(2023, 04, 1), LocalDate.of(2024, 12, 31));
    }

    @Test
    public void testCalculerSalaireParJour() {
        double expected = ressource.getTauxHoraire() * 7; // 7 heures par jour
        double result = ressource.calculerSalaireParJour(ressource);
        System.out.println("Salaire par jour attendu: " + expected + ", obtenu: " + result);
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculerSalaireBrut() {
        double salaireParJour = ressource.calculerSalaireParJour(ressource);
        int joursOuvrables = UtilitaireDate.calculerJoursOuvrables(ressource.getDebutContrat(), ressource.getFinContrat());
        double expected = salaireParJour * joursOuvrables;
        double result = ressource.calculerSalaireBrut(ressource);
        System.out.println("Salaire brut attendu: " + expected + ", obtenu: " + result);
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculerSalaireEstime() {
        double salaireBrut = ressource.calculerSalaireBrut(ressource);
        double expected = salaireBrut + (salaireBrut * 0.25); // Bonus de 25%
        double result = ressource.calculerSalaireEstime(ressource);
        System.out.println("Salaire estimé attendu: " + expected + ", obtenu: " + result);
        assertEquals(expected, result, 0.001);

    private static class RessourceConcret extends Ressource {
        public RessourceConcret(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
            super(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat);
        }
    }
}
