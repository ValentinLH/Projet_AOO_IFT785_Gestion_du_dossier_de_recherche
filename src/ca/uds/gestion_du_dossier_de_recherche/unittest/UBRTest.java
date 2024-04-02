package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;

public class UBRTest {
	
	private UBR ubr;
	private LigneBudgetaire ligneBudgetaire;

	@Before
	public void setUp() throws Exception {
		ubr = new UBR();
		ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
	}

	@Test
	public void testGetOrganisme() {
		Organisme organisme = new Organisme();
		ubr.setOrganisme(organisme);
		assertEquals(organisme, ubr.getOrganisme());
	}

	@Test
	public void testGetCode() {
		ubr.setCode(123);
		assertEquals(123, ubr.getCode());
	}

	@Test
	public void testGetMontantsLignesBudgetaire() {
//		Map<LigneBudgetaire, UBR.Fond> montantsLignesBudgetaire = new HashMap<>();
//		montantsLignesBudgetaire.put(ligneBudgetaire, new UBR.Fond(100.0f,0.0f));
//		ubr.setMontantsLignesBudgetaire(montantsLignesBudgetaire);
		ubr.ajouterLigneBudgetaire(ligneBudgetaire, 100.0f);
		assertTrue(ubr.getMontantsLignesBudgetaire().containsKey(ligneBudgetaire));
		assertEquals(100.0f, ubr.getMontant(ligneBudgetaire), 0.0f);
	}

	@Test
	public void testGetMontant() {
		ubr.ajouterLigneBudgetaire(ligneBudgetaire, 200.0f);
		assertEquals(200.0f, ubr.getMontant(ligneBudgetaire), 0.0f);
	}

	@Test
	public void testAjouterLigneBudgetaire() {
		ubr.ajouterLigneBudgetaire(ligneBudgetaire, 300.0f);
		assertTrue(ubr.getMontantsLignesBudgetaire().containsKey(ligneBudgetaire));
		assertEquals(300.0f, ubr.getMontantsLignesBudgetaire().get(ligneBudgetaire).getTotal(), 0.0f);
	}

	@Test
	public void testSupprimerLigneBudgetaire() {
		ubr.ajouterLigneBudgetaire(ligneBudgetaire, 400.0f);
		ubr.supprimerLigneBudgetaire(ligneBudgetaire);
		assertTrue(ubr.getMontantsLignesBudgetaire().isEmpty());
	}

	@Test
	public void testToString() {
		ubr.setOrganisme(new Organisme());
		ubr.setCode(123);
		ubr.ajouterLigneBudgetaire(ligneBudgetaire, 500.0f);
		assertEquals("UBR [organisme=[Organisme null, code=0], code=123, montantsLignesBudgetaire={Ligne Budgetaire de Chocolat=500.0}]", ubr.toString());
	}
}
