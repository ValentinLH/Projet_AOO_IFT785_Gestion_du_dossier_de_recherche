package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.ResponsableLaboratoire;

public class ResponsableLaboratoireTest {
	private ResponsableLaboratoire responsableLaboratoire;

	@Before
	public void setUp() throws Exception {
		responsableLaboratoire = new ResponsableLaboratoire("Nom", "Prenom", 1, 1, 35.0f, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), "domus");
	}

	@Test
	public void testGetLaboratoire() {
		assertEquals("domus", responsableLaboratoire.getLaboratoire());
	}
}