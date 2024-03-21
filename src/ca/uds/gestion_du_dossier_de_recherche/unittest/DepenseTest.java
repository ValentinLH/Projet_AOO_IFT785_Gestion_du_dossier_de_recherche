package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;

public class DepenseTest {
	
	private Depense depense;

	@Before
	public void setUp() throws Exception {
		depense = new Depense();
	}

	@Test
	public void testGetNom() {
		depense.setNom("Fournitures de bureau");
		assertEquals("Fournitures de bureau", depense.getNom());
	}

	@Test
	public void testGetMontant() {
		depense.setMontant(100.0f);
		assertEquals(100.0f, depense.getMontant(), 0.0f);
	}

	@Test
	public void testToString() {
		depense.setNom("Fournitures de bureau");
		depense.setMontant(100.0f);
		assertEquals("Depense [nom=Fournitures de bureau, montant=100.0]", depense.toString());
	}
}
