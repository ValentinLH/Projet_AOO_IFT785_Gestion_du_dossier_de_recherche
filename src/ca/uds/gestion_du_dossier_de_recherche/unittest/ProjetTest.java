package ca.uds.gestion_du_dossier_de_recherche.unittest;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import org.junit.Before;
import org.junit.Test;

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

import static org.junit.Assert.*;

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
		projet = new Projet("Title Test","Test Description",LocalDate.now().minusDays(30), LocalDate.now().plusDays(30));

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
	public void CalculMontantTest() {
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.CalculMontant(LocalDate.now());
		assertEquals(0f, projet.getFinancement(), 0f); // UBR avec contrainte

		projet.addLigneBudgetaire(ligneBudgetaire2);
		projet.CalculMontant(LocalDate.now());
		assertEquals(1000f, projet.getFinancement(), 0f); // UBR sans contrainte
	}

	@Test
	public void AddLigneBudgetaireTest() {
		assertEquals(0, projet.getAllLigneBudgetaires().size()); // car init avec aucune ligne
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.addLigneBudgetaire(ligneBudgetaire2);
		assertEquals(2, projet.getAllLigneBudgetaires().size());
		assertEquals(1000f, projet.getFinancement(), 0f);

		List<LigneBudgetaire> lignesList = new ArrayList<LigneBudgetaire>();
		lignesList.add(ligneBudgetaire);
		lignesList.add(ligneBudgetaire2);

		Projet projet2 = new Projet("Miam", "Projet qui fait l'inventaire de mon Frigo", LocalDate.now().minusDays(50),
				LocalDate.now().plusDays(50), lignesList);
		assertEquals(2, projet2.getAllLigneBudgetaires().size());

		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);
		assertEquals(1000f, projet2.getFinancement(), 0f);

		projet2.addLigneBudgetaire(ligneBudgetaire3);
		assertEquals(3, projet2.getAllLigneBudgetaires().size());
		assertEquals(6000f, projet2.getFinancement(), 0f);

	}

	@Test
	public void RemoveLigneBudgetaire() {
		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);

		List<LigneBudgetaire> lignesList = new ArrayList<LigneBudgetaire>();
		lignesList.add(ligneBudgetaire);
		lignesList.add(ligneBudgetaire2);
		lignesList.add(ligneBudgetaire3);

		Projet projet2 = new Projet("Miam", "Projet qui fait l'inventaire de mon Frigo", LocalDate.now().minusDays(50),
				LocalDate.now().plusDays(50), lignesList);

		projet2.removeLigneBudgetaire(ligneBudgetaire2);
		assertEquals(2, projet2.getAllLigneBudgetaires().size());
		assertEquals(5000f, projet2.getFinancement(), 0f);

		projet2.removeLigneBudgetaire(null); // ne change rien au projet
		assertEquals(5000f, projet2.getFinancement(), 0f);
		assertEquals(2, projet2.getAllLigneBudgetaires().size());

		projet2.removeLigneBudgetaire(ligneBudgetaire2); // n'existe plus dans la liste de ce projet donc fait rien
		assertEquals(5000f, projet2.getFinancement(), 0f);
		assertEquals(2, projet2.getAllLigneBudgetaires().size());

	}

	@Test
	public void TestDateLimiteDepense() {
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.addLigneBudgetaire(ligneBudgetaire2);
		projet.DateLimiteDepenses();
		assertEquals("Le montant founit par l'UBR Mon Frigidaire d'un motant total de 1000.0 dollars expire bientôt",
				outContent.toString().trim());

	}

	@Test
	public void testUpdateressource() {

		Ressource ressource1 = new Ressource("Nom", "Prenom", 25f, 40f, LocalDate.now().minusMonths(1),
				LocalDate.now().minusMonths(5));
		Ressource ressource2 = new Ressource("Nom", "Prenom", 25f, 40f, LocalDate.now().minusMonths(1),
				LocalDate.now().plusMonths(1));
		LocalDate dateDebut = LocalDate.now().minusMonths(10);
		LocalDate dateFin = LocalDate.now().plusMonths(1);
		LocalDate dateFinExpire = LocalDate.now().minusMonths(5);

		projet.addRessourceWithDate(ressource1, dateDebut, dateFin);
		projet.addRessourceWithDate(ressource2, dateDebut, dateFinExpire);

		assertEquals("Avant la mise à jour, l'équipe doit contenir 2 ressources.", 2, projet.getRessources().size());

		projet.Updateressource();

		assertEquals("Après la mise à jour, l'équipe doit contenir 1 ressource active.", 1,
				projet.getRessources().size());
		assertTrue("L'équipe doit contenir la ressource active dont la date de fin est après aujourd'hui.",
				projet.getRessources().get(ressource1).get(1).isAfter(LocalDate.now()));
	}

	@Test
	public void testAddRessourceWithDate() {

		Ressource ressource = new Ressource("Amin", "Dev", 20f, 40f, LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(1));
		Ressource ressource2 = new Ressource("Maxime", "Dev", 25f, 30f, LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(2));
		
		LocalDate dateDebut = LocalDate.now().minusDays(1);
		LocalDate dateFin = LocalDate.now().plusDays(1);

		projet.addRessourceWithDate(ressource, dateDebut, dateFin);
		projet.addRessourceWithDate(ressource2, dateDebut.minusMonths(5), dateFin.plusMonths(5));

		assertTrue("La map doit contenir la ressource ajoutée.", projet.getRessources().containsKey(ressource));
		List<LocalDate> datesR1 = projet.getRessources().get(ressource);
		List<LocalDate> datesR2 = projet.getRessources().get(ressource2);
		assertNotNull("La liste des dates ne doit pas être null.", datesR1);
		assertNotNull("La liste des dates ne doit pas être null.", datesR2);
		assertEquals("La date de début doit correspondre à celle fournie.", dateDebut, datesR1.get(0));
		assertEquals("La date de fin doit correspondre à celle fournie.", dateFin, datesR1.get(1));
		assertEquals("La date de début doit correspondre à celle du projet.", projet.getDateDebut(), datesR2.get(0));
		assertEquals("La date de fin doit correspondre à celle du projet.", projet.getDateFin(), datesR2.get(1));
	}

}
