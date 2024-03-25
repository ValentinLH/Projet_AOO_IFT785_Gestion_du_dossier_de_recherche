package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.ResponsableLaboratoire;

public class ResponsableLaboratoireTest {
private ResponsableLaboratoire ResponsableLaboratoire;
	
	@Before
	public void setUp() throws Exception {
		ResponsableLaboratoire = new ResponsableLaboratoire("", "", 0, 0, "", "", "");
	}
	
	
	@Test
	public void testgetLaboratoire() {
		ResponsableLaboratoire.setLaboratoire("domus");;
		assertEquals("domus", ResponsableLaboratoire.getLaboratoire());
	}
	

	
}
