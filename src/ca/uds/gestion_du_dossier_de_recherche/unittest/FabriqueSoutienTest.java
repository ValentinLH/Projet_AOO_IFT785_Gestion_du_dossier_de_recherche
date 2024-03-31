package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueSoutien;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;

public class FabriqueSoutienTest {

    @Test
    public void testCreateRessource() {
        FabriqueSoutien fs = new FabriqueSoutien();

        Ressource r = fs.createRessource("Nom", "Prenom", 20.5f, 40.0f, "2022-01-01", "2022-12-31");

        assertTrue(r instanceof Soutien); // check that the created object is an instance of Soutien
        assertEquals("Nom", r.getNom());
        assertEquals("Prenom", r.getPrenom());
        assertEquals(20.5f, r.getTaux_horaire(), 0.01); // use a delta to account for floating point precision
        assertEquals(40.0f, r.getHeures_hebdo(), 0.01);
        assertEquals("2022-01-01", r.getDebut_contrat());
        assertEquals("2022-12-31", r.getFin_contrat());
        assertEquals(4 * 40 * 20.5, r.calcul_salaire_mensuel(), 0.01);
    }

}