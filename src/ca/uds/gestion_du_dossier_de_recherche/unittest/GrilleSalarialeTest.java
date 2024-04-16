package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.GrilleSalariale;

public class GrilleSalarialeTest {

	private GrilleSalariale grilleSalariale;

	@Before
	public void setUp() {
		grilleSalariale = GrilleSalariale.getInstance();
	}

	@Test
	public void testGetTauxHoraire() {
		assertEquals(32.30, grilleSalariale.getTauxHoraire(1, 3), 0.001);

		assertEquals(37.31, grilleSalariale.getTauxHoraire(2, 6), 0.001);

		assertEquals(33.57, grilleSalariale.getTauxHoraire(3, 1), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTauxHoraireEchelleInexistante() {
		grilleSalariale.getTauxHoraire(4, 1);
		fail("Une exception aurait dû être levée pour une échelle inexistante.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTauxHoraireEchelonInexistant() {
		grilleSalariale.getTauxHoraire(1, 7);
		fail("Une exception aurait dû être levée pour un échelon inexistant.");
	}
}
