package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

public class EtudiantTest {
	private Etudiant etudiant;
	
	@Before
	public void setUp() throws Exception {
		etudiant = new Etudiant("", "", 0, 0,"", "", "", Programme.BACCALAUREAT);
	}
	
	@Test
	public void testGetNom() {
		etudiant.setNom("Nom");
		assertEquals("Nom", etudiant.getNom());
	}
	
	@Test
	public void testGetPrenom() {
		etudiant.setPrenom("Prenom");
		assertEquals("Prenom", etudiant.getPrenom());
	}
	
	@Test
	public void testGetTH() {
		etudiant.setTaux_horaire(4.5f);
		assertEquals(4.5f, etudiant.getTaux_horaire(),0.00001f);
	}
	
	@Test
	public void testGetHH() {
		etudiant.setHeures_hebdo(17.0f);
		assertEquals(17.0f, etudiant.getHeures_hebdo(),0.00001f);
	}
	
	@Test
	public void testGetDebutContrat() {
		etudiant.setDebut_contrat("1234");
		assertEquals("1234", etudiant.getDebut_contrat());
	}
	
	@Test
	public void testGetFinContrat() {
		etudiant.setFin_contrat("5678");
		assertEquals("5678", etudiant.getFin_contrat());
	}
	
	@Test
	public void testSalaireMensuel() {
		etudiant.setTaux_horaire(4.0f);
		etudiant.setHeures_hebdo(10.0f);
		assertEquals(160.0f, etudiant.calcul_salaire_mensuel(),0.00001f);
	}
	
	@Test
	public void testGetCip() {
		etudiant.setCip("xxxx00");
		assertEquals("xxxx00", etudiant.getCip());
	}
	
	@Test
	public void testGetProgramme() {
		etudiant.setProgramme(Programme.DOCTORAT);
		assertEquals(Programme.DOCTORAT, etudiant.getProgramme());
	}
	
}
