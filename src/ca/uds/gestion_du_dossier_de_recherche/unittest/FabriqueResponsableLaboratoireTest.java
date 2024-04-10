package ca.uds.gestion_du_dossier_de_recherche.unittest;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.ResponsableLaboratoire;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;

public class FabriqueResponsableLaboratoireTest {

    @Test
    public void testCreateRessource() {
        FabriqueResponsableLaboratoire fabrique = new FabriqueResponsableLaboratoire();
        Ressource ressource = fabrique.createRessource("Nom", "Prenom", 1, 1, 35.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "LaboratoireTest");
        assertTrue(ressource instanceof ResponsableLaboratoire);
        assertEquals("Nom", ressource.getNom());
        assertEquals("Prenom", ressource.getPrenom());
        assertEquals(1, ((ResponsableLaboratoire)ressource).getEchelle());
        assertEquals(1, ((ResponsableLaboratoire)ressource).getEchelon());
        assertEquals(35.0f, ressource.getHeuresHebdo(), 0.01);
        assertEquals(LocalDate.of(2022, 1, 1), ressource.getDebutContrat());
        assertEquals(LocalDate.of(2022, 12, 31), ressource.getFinContrat());
        assertEquals("LaboratoireTest", ((ResponsableLaboratoire)ressource).getLaboratoire());
    }
}

