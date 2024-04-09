package ca.uds.gestion_du_dossier_de_recherche.unittest;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Soutien;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		assertEquals(0f, projet.calculMontant(LocalDate.now()), 0f); // UBR avec contrainte

		projet.addLigneBudgetaire(ligneBudgetaire2);
		assertEquals(1000f, projet.calculMontant(LocalDate.now()), 0f); // UBR sans contrainte
	}

	@Test
	public void AddLigneBudgetaireTest() {
		assertEquals(0, projet.getAllLigneBudgetaires().size()); // car init avec aucune ligne
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.addLigneBudgetaire(ligneBudgetaire2);
		assertEquals(2, projet.getAllLigneBudgetaires().size());
		assertEquals(1000f, projet.calculMontant(LocalDate.now()), 0f);

		List<LigneBudgetaire> lignesList = new ArrayList<LigneBudgetaire>();
		lignesList.add(ligneBudgetaire);
		lignesList.add(ligneBudgetaire2);

		Projet projet2 = new Projet("Miam", "Projet qui fait l'inventaire de mon Frigo", LocalDate.now().minusDays(50),
				LocalDate.now().plusDays(50), lignesList);
		assertEquals(2, projet2.getAllLigneBudgetaires().size());

		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);
		assertEquals(1000f, projet2.calculMontant(LocalDate.now()), 0f);

		projet2.addLigneBudgetaire(ligneBudgetaire3);
		assertEquals(3, projet2.getAllLigneBudgetaires().size());
		assertEquals(6000f, projet2.calculMontant(LocalDate.now()), 0f);

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
		assertEquals(5000f, projet2.calculMontant(LocalDate.now()), 0f);

		projet2.removeLigneBudgetaire(null); // ne change rien au projet
		assertEquals(5000f, projet2.calculMontant(LocalDate.now()), 0f);
		assertEquals(2, projet2.getAllLigneBudgetaires().size());

		projet2.removeLigneBudgetaire(ligneBudgetaire2); // n'existe plus dans la liste de ce projet donc fait rien
		assertEquals(5000f, projet2.calculMontant(LocalDate.now()), 0f);
		assertEquals(2, projet2.getAllLigneBudgetaires().size());

	}

	@Test
	public void TestDateLimiteDepense() {
		projet.addLigneBudgetaire(ligneBudgetaire);
		projet.addLigneBudgetaire(ligneBudgetaire2);
		projet.dateLimiteDepenses(10);
		assertEquals("Le montant founit par l'UBR Mon Frigidaire d'un motant total de 1000.0 dollars expire bientôt",
				outContent.toString().trim());

	}

	@Test
	public void testUpdateressource() {

		Ressource ressource1 = new Soutien("Nom", "Prenom", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
		Ressource ressource2 = new Soutien("Nom", "Prenom", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
		LocalDate dateDebut = LocalDate.now().minusMonths(10);
		LocalDate dateFin = LocalDate.now().plusMonths(1);
		LocalDate dateFinExpire = LocalDate.now().minusMonths(5);

		projet.addRessourceWithDate(ressource1, dateDebut, dateFin);
		projet.addRessourceWithDate(ressource2, dateDebut, dateFinExpire);

		assertEquals("Avant la mise à jour, l'équipe doit contenir 2 ressources.", 2, projet.getRessources().size());

		projet.updateRessource();

		assertEquals("Après la mise à jour, l'équipe doit contenir 1 ressource active.", 1,
				projet.getRessources().size());
		assertTrue("L'équipe doit contenir la ressource active dont la date de fin est après aujourd'hui.",
				projet.getRessources().get(ressource1).get(1).isAfter(LocalDate.now()));
	}

	@Test
	public void testAddRessourceWithDate() {

		Ressource ressource = new Soutien("Amin", "Dev", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
		Ressource ressource2 = new Soutien("Maxime", "Dev", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
		
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


	@Test
	public void testCalculSalaireRessources() {

		Ressource ressource1 = new Soutien("Amin", "Dev",1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
		Ressource ressource2 = new Soutien("Maxime", "Dev", 1, 1, 40.0f, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));


		// Affectation commence il y a 30 jours
		LocalDate dateDebutProjet = LocalDate.now().minusDays(30);
		// Affectation se termine dans 30 jours
		LocalDate dateFinProjet = LocalDate.now().plusDays(30);
		projet.addRessourceWithDate(ressource1, dateDebutProjet, dateFinProjet);
		projet.addRessourceWithDate(ressource2, dateDebutProjet, dateFinProjet);
		
		Map<Ressource, Double> salaires = projet.calculSalaireRessources();

		assertNotNull("La map des salaires ne doit pas être null", salaires);

		double salaireAttenduRessource1 = ressource1.calculSalaireMensuel();
		double salaireAttenduRessource2 = ressource2.calculSalaireMensuel();

		assertEquals("Le salaire calculé pour la ressource 1 doit correspondre au salaire attendu",
				salaireAttenduRessource1, salaires.get(ressource1), 0.01);
		assertEquals("Le salaire calculé pour la ressource 2 doit correspondre au salaire attendu",
				salaireAttenduRessource2, salaires.get(ressource2), 0.01);
	}



}
