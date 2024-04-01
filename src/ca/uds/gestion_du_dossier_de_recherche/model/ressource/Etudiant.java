package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;
import java.util.Objects;

public class Etudiant extends Ressource {

    public enum Programme {
        BACCALAUREAT, MAITRISE, DOCTORAT, POST_DOCTORAT, STAGE;
    }

    private String cip;
    private Programme programme;

    public Etudiant(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat, String cip, Programme programme) {
        super(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat);
        this.cip = cip;
        this.programme = Objects.requireNonNull(programme);
    }

    @Override
    public String toString() {
        return super.toString() + "\n Etudiant [cip=" + cip + ", programme=" + programme + "]";
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

    public double getTauxHoraire() {
        return super.getTauxHoraire();
    }
}
