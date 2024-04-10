package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public class FabriqueResponsableLaboratoire {

	public Ressource createRessource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat, String laboratoire) {
		return new ResponsableLaboratoire(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat, laboratoire);
	}
}

