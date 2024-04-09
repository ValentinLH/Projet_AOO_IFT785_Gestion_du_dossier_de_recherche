package ca.uds.gestion_du_dossier_de_recherche.unittest;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
import java.time.LocalDate;
import static org.junit.Assert.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.GrilleSalariale;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.UtilitaireDate;
import org.junit.Before;
import org.junit.Test;

public class EtudiantTest {
	private Etudiant etudiant;

	@Before
	public void setUp() {
		GrilleSalariale grilleSalariale = new GrilleSalariale();
		Ressource.setGrilleSalariale(grilleSalariale);
		etudiant = new Etudiant("Jean", "Dupont", 6, 7, 35, LocalDate.of(2023, 04, 1), LocalDate.of(2024, 12, 31), "testCIP", Etudiant.Programme.BACCALAUREAT);
	}


	@Test
	public void testTauxHoraire() {
		assertEquals(44.91, etudiant.getTauxHoraire(), 0.001);
	}

	@Test
	public void testGetNom() {
		assertEquals("Jean", etudiant.getNom());
	}

	@Test
	public void testGetPrenom() {
		assertEquals("Dupont", etudiant.getPrenom());
	}

	@Test
	public void testGetEchelle() {
		assertEquals(6, etudiant.getEchelle());
	}

	@Test
	public void testGetEchelon() {
		assertEquals(7, etudiant.getEchelon());
	}

	@Test
	public void testGetHeuresHebdo() {
		assertEquals(35.0f, etudiant.getHeuresHebdo(), 0.00001f);
	}

	@Test
	public void testGetDebutContrat() {
		assertEquals(LocalDate.of(2023, 04, 1), etudiant.getDebutContrat());
	}

	@Test
	public void testGetFinContrat() {
		assertEquals(LocalDate.of(2024, 12, 31), etudiant.getFinContrat());
	}

	@Test
	public void testGetCip() {
		assertEquals("testCIP", etudiant.getCip());
	}

	@Test
	public void testGetProgramme() {
		assertEquals(Programme.BACCALAUREAT, etudiant.getProgramme());
	}

	@Test
	public void testCalculerSalaireParJour() {
		double expected = etudiant.getTauxHoraire() * 7; // 7 heures par jour
		double result = etudiant.calculerSalaireParJour(etudiant);
		System.out.println("Salaire par jour attendu: " + expected + ", obtenu: " + result);
		assertEquals(expected, result, 0.001);
	}

	@Test
	public void testCalculerSalaireBrut() {
		double salaireParJour = etudiant.calculerSalaireParJour(etudiant);
		int joursOuvrables = UtilitaireDate.calculerJoursOuvrables(etudiant.getDebutContrat(), etudiant.getFinContrat());
		double expected = salaireParJour * joursOuvrables;
		double result = etudiant.calculerSalaireBrut(etudiant);
		System.out.println("Salaire brut attendu: " + expected + ", obtenu: " + result);
		assertEquals(expected, result, 0.001);
	}

	@Test
	public void testCalculerSalaireEstime() {
		double salaireBrut = etudiant.calculerSalaireBrut(etudiant);
		double expected = salaireBrut + (salaireBrut * 0.25); // Bonus de 25%
		double result = etudiant.calculerSalaireEstime(etudiant);
		System.out.println("Salaire estimé attendu: " + expected + ", obtenu: " + result);
		assertEquals(expected, result, 0.001);
	}


}
