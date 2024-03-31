package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.ResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;

public class FabriqueResponsableLaboratoireTest {

    @Test
    public void testCreateRessource() {
        FabriqueResponsableLaboratoire fll = new FabriqueResponsableLaboratoire();

        Ressource r = fll.createRessource("Nom", "Prenom", 20.5f, 40.0f, "2022-01-01", "2022-12-31");

        assertTrue(r instanceof ResponsableLaboratoire); // check that the created object is an instance of ResponsableLaboratoire
        assertEquals("Nom", r.getNom());
        assertEquals("Prenom", r.getPrenom());
        assertEquals(20.5f, r.getTaux_horaire(), 0.01); // use a delta to account for floating point precision
        assertEquals(40.0f, r.getHeures_hebdo(), 0.01);
        assertEquals("2022-01-01", r.getDebut_contrat());
        assertEquals("2022-12-31", r.getFin_contrat());
        assertEquals("null", ((ResponsableLaboratoire) r).getLaboratoire());
        assertEquals(4 * 40 * 20.5, r.calcul_salaire_mensuel(), 0.01);
    }

}