package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;

public class OrganismeTest {
	
	private Organisme organisme;

	@Before
	public void setUp() throws Exception {
		organisme = new Organisme();
	}

	@Test
	public void testGetNom() {
		organisme.setNom("Organisme Test");
		assertEquals("Organisme Test", organisme.getNom());
	}

	@Test
	public void testGetCode() {
		organisme.setCode(123);
		assertEquals(123, organisme.getCode());
	}

	@Test
	public void testToString() {
		organisme.setNom("Organisme Test");
		organisme.setCode(123);
		assertEquals("[Organisme Organisme Test, code=123]", organisme.toString());
	}
}

