package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import java.time.LocalDate;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueEtudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;

public class FabriqueEtudiantTest {

    @Test
    public void testCreateRessource() {
        FabriqueEtudiant fabriqueEtudiant = new FabriqueEtudiant();

        Ressource ressource = fabriqueEtudiant.createRessource("Nom", "Prenom", 1, 1, 35.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));

        assertTrue(ressource instanceof Etudiant);
        assertEquals("Nom", ressource.getNom());
        assertEquals("Prenom", ressource.getPrenom());
        assertEquals(LocalDate.of(2022, 1, 1), ((Etudiant)ressource).getDebutContrat());
        assertEquals(LocalDate.of(2022, 12, 31), ((Etudiant)ressource).getFinContrat());
    }
}
