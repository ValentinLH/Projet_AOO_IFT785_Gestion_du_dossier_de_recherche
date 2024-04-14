package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

public class FabriqueEtudiant extends FabriquePersonne {
	
	private String cip;
	private Programme programme;
	
    @Override
    public Ressource createRessource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
        return new Etudiant(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat, this.cip, this.programme);
    }


    private String genererCip(String nom, String prenom) {
        return nom.substring(0, 1).toLowerCase() + prenom.substring(0, 1).toLowerCase() + "123";
    }


	public String getCip() {
		return cip;
	}


	public void setCip(String cip) {
		this.cip = cip;
	}


	public Programme getProgramme() {
		return programme;
	}


	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

}
