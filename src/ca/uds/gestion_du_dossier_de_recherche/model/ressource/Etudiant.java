package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.util.Objects;

public class Etudiant extends Ressource {

    public enum Programme {
        BACCALAUREAT, MAITRISE, DOCTORAT, POST_DOCTORAT, STAGE;
    }

    private String cip;
    private Programme programme;

    public Etudiant(String nom, String prenom, float tauxHeure, float heuresHebdo, String dateCreation, String dateDeFin, String cip, Programme programme) {
        super(nom, prenom, tauxHeure, heuresHebdo, dateCreation, dateDeFin);
        this.cip = cip;
        this.programme = Objects.requireNonNull(programme);
    }

	@Override
	public String toString() {
    	
		return super.toString()+ "/n Etudiant [cip=" + cip + ", programme=" + programme + "]";
	}

    // getters and setters
    
    @Override
    public String getCip() {
        return cip;
    }
    
    @Override
    public void setCip(String cip) {
        this.cip = cip;
    }

    @Override
    public Programme getProgramme() {
        return programme;
    }

    @Override
    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

}