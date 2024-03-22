package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;

public class LigneBudgetaireTest {
	
	private LigneBudgetaire ligneBudgetaire;

	@Before
	public void setUp() throws Exception {
		ligneBudgetaire = new LigneBudgetaire();
		
	}

	@Test
	public void testGetNom() {
		ligneBudgetaire.setNom("Budget Chocolat");
		assertEquals("Budget Chocolat", ligneBudgetaire.getNom());
	}

	@Test
	public void testGetType() {
		ligneBudgetaire.setType("Chocolat");
		assertEquals("Chocolat", ligneBudgetaire.getType());
	}

	@Test
	public void testGetMontantLigneDeficit() {
		Depense depense1 = new Depense("Chocolat Noir 97%", 100.0f);
		Depense depense2 = new Depense("Petit Ecolier de LU chocolat noir", 200.0f);
		ligneBudgetaire.ajouterDepense(depense1);
		ligneBudgetaire.ajouterDepense(depense2);
		assertEquals(-300.0f, ligneBudgetaire.getMontantLigne(LocalDate.now()), 0.0f);
	}
	
	@Test
	public void testGetMontantLigneContraint() {

		Organisme monFrigo = new Organisme("Mon Frigidaire",0);
		UBR ubr = new UBR(monFrigo,1,true,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1));
		ligneBudgetaire.ajouterUBR(ubr, 100f);
		assertEquals(0.0f, ligneBudgetaire.getMontantLigne(LocalDate.now()), 0.0f);
	}
	
	
	@Test
	public void testToString() {
		ligneBudgetaire.setNom("Budget Chocolat");
		ligneBudgetaire.setType("Budget de recherche");
		assertEquals("LigneBudgetaire [nom=Budget Chocolat, type=Budget de recherche, ubrs=[], depenses=[]]", ligneBudgetaire.toString());
	}
	
	
	
	
	
}
