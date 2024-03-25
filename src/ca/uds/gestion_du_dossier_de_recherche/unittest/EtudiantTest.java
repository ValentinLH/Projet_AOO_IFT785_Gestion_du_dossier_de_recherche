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
		etudiant = new Etudiant(null, null, 0, 0, null, null, null, null);
	}
	
//	@Test
//	public void testGetNom() {
//		etudiant.setNom("Nom");
//		assertEquals("Nom", etudiant.getNom());
//	}
//	
//	@Test
//	public void testGetPrenom() {
//		etudiant.setNom("Prenom");
//		assertEquals("Prenom", etudiant.getPrenom());
//	}
	
	@Test
	public void testGetCip() {
		etudiant.setCip("xxxx00");
		assertEquals("xxxx00", etudiant.getCip());
	}
	
	@Test
	public void testGetProgramme() {
		etudiant.setProgramme(Programme.BACCALAUREAT);
		assertEquals(Programme.BACCALAUREAT, etudiant.getProgramme());
	}
	
}
