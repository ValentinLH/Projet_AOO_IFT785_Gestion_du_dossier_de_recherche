package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import org.junit.Test;
import java.time.LocalDate;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.GrilleSalariale;

public class SoutienTest {

    @Test
    public void testSoutien() {
        GrilleSalariale grilleSalariale = new GrilleSalariale();
        Ressource.setGrilleSalariale(grilleSalariale);

        Soutien s = new Soutien("Nom", "Prenom", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));

        assertEquals("Nom", s.getNom());
        assertEquals("Prenom", s.getPrenom());
        assertTrue(s.getTauxHoraire() > 0);
        assertEquals(40.0f, s.getHeuresHebdo(), 0.01);
        assertEquals(LocalDate.of(2022, 1, 1), s.getDebutContrat());
        assertEquals(LocalDate.of(2022, 12, 31), s.getFinContrat());
    }

    @Test
    public void testCalculSalaireMensuel() {
        GrilleSalariale grilleSalariale = new GrilleSalariale();

        Soutien s = new Soutien("Nom", "Prenom", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
        assertTrue(s.calculSalaireMensuel() > 0);
    }

}
