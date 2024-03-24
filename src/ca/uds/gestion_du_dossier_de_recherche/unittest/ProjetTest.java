package ca.uds.gestion_du_dossier_de_recherche.unittest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.*;

public class ProjetTest {

	Projet projet;
	Organisme monFrigo;
	UBR ubr1;
	UBR ubr2;
	LigneBudgetaire ligneBudgetaire;
	LigneBudgetaire ligneBudgetaire2;
	
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
	
	@Before
	public void setUp() throws Exception {
		monFrigo = new Organisme("Mon Frigidaire", 0);
		ubr1 = new UBR(monFrigo, 1, true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));
		ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
		ligneBudgetaire.ajouterUBR(ubr1, 500f);
		projet = new Projet("OK");
		
		ubr2 = new UBR(monFrigo, 1, false, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
		ligneBudgetaire2 = new LigneBudgetaire("Ligne Budgetaire de Beurre", "Beurre");
		ligneBudgetaire2.ajouterUBR(ubr2, 1000f);
		
		
		System.setOut(new PrintStream(outContent));
		
	}
	
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
	
	
	@Test
	public void CalculMontantTest(){
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.CalculMontant(LocalDate.now());
		assertEquals(0f,projet.getFinancement(),0f); // UBR avec contrainte
		
		projet.addLigneBudgetaire(ligneBudgetaire2);
		projet.CalculMontant(LocalDate.now());
		assertEquals(1000f,projet.getFinancement(),0f); // UBR sans contrainte
	}
	
	@Test
	public void AddLigneBudgetaireTest() {
		assertEquals(0,projet.getAllLigneBudgetaires().size()); // car init avec aucune ligne
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.addLigneBudgetaire(ligneBudgetaire2);
		assertEquals(2,projet.getAllLigneBudgetaires().size());
		assertEquals(1000f,projet.getFinancement(),0f);


		
		List<LigneBudgetaire> lignesList =  new ArrayList<LigneBudgetaire>();
		lignesList.add(ligneBudgetaire);
		lignesList.add(ligneBudgetaire2);
		
		Projet projet2 = new Projet("Miam", "Projet qui fait l'inventaire de mon Frigo", LocalDate.now().minusDays(50), LocalDate.now().plusDays(50),lignesList);
		assertEquals(2,projet2.getAllLigneBudgetaires().size());
		
		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);
		assertEquals(1000f,projet2.getFinancement(),0f);
		
		projet2.addLigneBudgetaire(ligneBudgetaire3);
		assertEquals(3,projet2.getAllLigneBudgetaires().size());
		assertEquals(6000f,projet2.getFinancement(),0f);
		
		
	}
	
	@Test
	public void RemoveLigneBudgetaire() {
		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);
				
		List<LigneBudgetaire> lignesList =  new ArrayList<LigneBudgetaire>();
		lignesList.add(ligneBudgetaire);
		lignesList.add(ligneBudgetaire2);
		lignesList.add(ligneBudgetaire3);
		
		Projet projet2 = new Projet("Miam", "Projet qui fait l'inventaire de mon Frigo", LocalDate.now().minusDays(50), LocalDate.now().plusDays(50),lignesList);
		
		projet2.removeLigneBudgetaire(ligneBudgetaire2);
		assertEquals(2,projet2.getAllLigneBudgetaires().size());
		assertEquals(5000f,projet2.getFinancement(),0f);
		
		projet2.removeLigneBudgetaire(null); // ne change rien au projet
		assertEquals(5000f,projet2.getFinancement(),0f);
		assertEquals(2,projet2.getAllLigneBudgetaires().size());

		projet2.removeLigneBudgetaire(ligneBudgetaire2); //n'existe plus dans la liste de ce projet donc fait rien
		assertEquals(5000f,projet2.getFinancement(),0f);
		assertEquals(2,projet2.getAllLigneBudgetaires().size());
		
	}
	
	@Test 
	public void TestDateLimiteDepense() {
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.addLigneBudgetaire(ligneBudgetaire2);
		projet.DateLimiteDepenses();
		assertEquals("Le montant founit par l'UBR Mon Frigidaire d'un motant total de 1000.0 dollars expire bientôt"
				, outContent.toString().trim());
		
	}
}
