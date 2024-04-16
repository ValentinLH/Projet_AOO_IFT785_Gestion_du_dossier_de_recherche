package ca.uds.gestion_du_dossier_de_recherche.unittest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Bulletin;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueEtudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;

public class BulletinTest {

    private Bulletin bulletin;
    private Ressource ressource;
    private LigneBudgetaire ligne;
    private LocalDate date;

    @Before
    public void setUp() throws Exception {
        bulletin = new Bulletin();
        ressource = new RessourceConcret("Jean", "Dupont", 6, 7, 35, LocalDate.of(2023, 04, 1), LocalDate.of(2024, 12, 31));
        ligne = new LigneBudgetaire();
        date = LocalDate.now();
    }

    @After
    public void tearDown() throws Exception {
        bulletin = null;
        ressource = null;
        ligne = null;
        date = null;
    }

    @Test
    public void testSetAndGetRessource() {
        assertNull(bulletin.getRessource());
        
        bulletin.setRessource(ressource);
        assertNotNull(bulletin.getRessource());
        assertEquals(ressource, bulletin.getRessource());
    }

    @Test
    public void testSetAndgetLigne() {
        assertNull(bulletin.getLigne());
        
        bulletin.setLigne(ligne);
        assertNotNull(bulletin.getLigne());
        assertEquals(ligne, bulletin.getLigne());
    }

    @Test
    public void testSetAndGetDate() {
        assertNull(bulletin.getDate());
        
        bulletin.setDate(date);
        assertNotNull(bulletin.getDate());
        assertEquals(date, bulletin.getDate());
    }

    @Test
    public void testDetruit() {
        bulletin.setLigne(ligne);
        bulletin.setRessource(ressource);
        
        bulletin.detruit();
        
        assertNull(bulletin.getLigne());
        assertNull(bulletin.getRessource());
    }
    
    private static class RessourceConcret extends Ressource {
        public RessourceConcret(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
            super(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat);
        }
    }
}
