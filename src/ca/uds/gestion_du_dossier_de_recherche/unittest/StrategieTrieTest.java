package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilation;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;

public class StrategieTrieTest {

    @Test
    public void testSetContext() {
        // Création d'un objet de la classe abstraite StrategieTrie
        StrategieTrie strategie = new StrategieTrie() {
            @Override
            public List<Ventilable> ventiler(List<? extends Ventilable> ventilables) {
                return null; // Pas besoin de cette méthode dans le test
            }
        };

        // Création d'un objet de la classe Ventilation fictif
        Ventilation ventilation = mock(Ventilation.class);

        // Appel de la méthode setContext avec l'objet ventilation
        strategie.setContext(ventilation);

        // Vérification que l'attribut ventilation a été correctement initialisé
        assertEquals(ventilation, strategie.getVentilation());
    }
}
