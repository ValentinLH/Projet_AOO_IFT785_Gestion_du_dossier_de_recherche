package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public class FabriqueResponsableLaboratoire extends FabriquePersonne {

	private String laboratoire;
	
	public FabriqueResponsableLaboratoire(String laboratoire) {
		this.laboratoire = laboratoire;
	}
	
	@Override
    public Ressource createRessource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
        return new ResponsableLaboratoire(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat, laboratoire);
    }
}

