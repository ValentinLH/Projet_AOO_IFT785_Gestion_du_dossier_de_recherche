package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.util.EnumSet;
import java.util.Objects;

public class Etudiant extends Ressource {

    public enum Programme {
        BACCALAUREAT, MAITRISE, DOCTORAT, POST_DOCTORAT, STAGE;
    }

    private String cip;
    private Programme programme;

    public Etudiant(String nom, String prenom, float tauxHeure, String dateCreation, String dateDeFin, String cip, Programme programme) {
        super(nom, prenom, tauxHeure, tauxHeure, dateCreation, dateDeFin);
        this.cip = cip;
        this.programme = Objects.requireNonNull(programme);
    }

    public void display() {
        System.out.println("Étudiant [CIP: " + this.cip + ", Programme: " + this.programme + ']');
    }

    // getters and setters

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