package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;

public class SoutienTest {

    @Test
    public void testSoutien() {
        Soutien s = new Soutien("Nom", "Prenom", 20.5f, 40.0f, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));

        assertEquals("Nom", s.getNom());
        assertEquals("Prenom", s.getPrenom());
        assertEquals(20.5f, s.getTaux_horaire(), 0.01); // use a delta to account for floating point precision
        assertEquals(40.0f, s.getHeures_hebdo(), 0.01);
        assertEquals(LocalDate.now().minusDays(10), s.getDebut_contrat());
        assertEquals(LocalDate.now().plusDays(10), s.getFin_contrat());
        assertEquals(4 * 40 * 20.5, s.calcul_salaire_mensuel(), 0.01);
    }

    @Test
    public void testCalculSalaireMensuel() {
        Soutien s = new Soutien("Nom", "Prenom", 20.5f, 40.0f, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));

        assertEquals(4 * 40 * 20.5, s.calcul_salaire_mensuel(), 0.01);
    }

}

