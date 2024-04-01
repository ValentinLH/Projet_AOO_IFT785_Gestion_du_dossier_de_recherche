package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import org.junit.Test;
import java.time.LocalDate;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueSoutien;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;

public class FabriqueSoutienTest {

    @Test
    public void testCreateRessource() {
        FabriqueSoutien fs = new FabriqueSoutien();
        Ressource r = fs.createRessource("Nom", "Prenom", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));

        assertTrue(r instanceof Soutien); // vérifie que l'objet créé est une instance de Soutien
        assertEquals("Nom", r.getNom());
        assertEquals("Prenom", r.getPrenom());
        assertEquals(40.0f, r.getHeuresHebdo(), 0.01);
        assertEquals(LocalDate.of(2022, 1, 1), r.getDebutContrat());
        assertEquals(LocalDate.of(2022, 12, 31), r.getFinContrat());
    }
}
