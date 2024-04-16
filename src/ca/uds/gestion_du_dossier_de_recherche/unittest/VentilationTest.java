package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilation;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;

public class VentilationTest {

    @Test
    public void testVentiler() {
        // Création de quelques objets ventilables fictifs
        Ventilable ventilable1 = mock(Projet.class);
        Ventilable ventilable2 = mock(Ressource.class);

        // Création d'une liste d'objets ventilables
        List<Ventilable> ventilables = new ArrayList<>();
        ventilables.add(ventilable1);
        ventilables.add(ventilable2);

        // Création d'une stratégie de tri fictive
        StrategieTrie strategie = mock(StrategieTrie.class);

        // Définir le comportement de la stratégie de tri
        when(strategie.ventiler(ventilables)).thenReturn(ventilables);

        // Création d'un objet Ventilation avec la stratégie de tri fictive
        Ventilation ventilation = new Ventilation(strategie);

        // Appel de la méthode à tester
        List<? extends Ventilable> result = ventilation.ventiler(ventilables);

        // Vérifier que la liste résultante est la même que celle fournie en entrée
        assertEquals(ventilables, result);
    }

    @Test
    public void testSetDate() {
        // Création d'une date fictive
        LocalDate date = LocalDate.of(2023, 1, 1);

        // Création d'une stratégie de tri fictive
        StrategieTrie strategie = mock(StrategieTrie.class);

        // Création d'un objet Ventilation avec la stratégie de tri fictive
        Ventilation ventilation = new Ventilation(strategie);

        // Définir une nouvelle date
        ventilation.setDate(date);

        // Vérifier que la date a bien été mise à jour
        assertEquals(date, ventilation.getDate());
    }

    @Test
    public void testSetStrategie() {
        // Création de la stratégie de tri fictive
        StrategieTrie strategie = mock(StrategieTrie.class);

        // Création d'un objet Ventilation
        Ventilation ventilation = new Ventilation(null);

        // Définir une nouvelle stratégie
        ventilation.setStrategie(strategie);

        // Vérifier que la stratégie a bien été mise à jour
        assertEquals(strategie, ventilation.getStrategie());
    }
}
