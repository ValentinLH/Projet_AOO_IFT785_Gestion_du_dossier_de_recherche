package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public class FabriqueEtudiant extends FabriquePersonne {
    @Override
    public Ressource createRessource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
        String cip = genererCip(nom, prenom);
        return new Etudiant(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat, cip, Etudiant.Programme.BACCALAUREAT);
    }

    private String genererCip(String nom, String prenom) {
        return nom.substring(0, 1).toLowerCase() + prenom.substring(0, 1).toLowerCase() + "123";
    }

}
