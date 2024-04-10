package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueEtudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;

public class FabriqueEtudiantTest {

    @Test
    public void testCreateRessource() {
        FabriqueEtudiant fe = new FabriqueEtudiant();

        Ressource r = fe.createRessource("Nom", "Prenom", 0.0f, 0.0f, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));

        
        
        assertTrue(r instanceof Etudiant); // check that the created object is an instance of Etudiant
        assertEquals("Nom", r.getNom());
        assertEquals("Prenom", r.getPrenom());
        assertEquals(0.0f, r.getTaux_horaire(), 0.01); // use a delta to account for floating point precision
        assertEquals(0.0f, r.getHeures_hebdo(), 0.01);
        assertEquals(LocalDate.now().minusDays(10), r.getDebut_contrat());
        assertEquals(LocalDate.now().plusDays(10), r.getFin_contrat());
        // assertEquals("null", r.getLaboratoire()); // this field is not applicable for Etudiant objects
        // assertEquals(Programme.BACCALAUREAT, r.getProgramme()); // this field is not applicable for Etudiant objects
        // assertEquals(0.0f, r.calcul_salaire_mensuel(), 0.01); // this method is not applicable for Etudiant objects
    }

}