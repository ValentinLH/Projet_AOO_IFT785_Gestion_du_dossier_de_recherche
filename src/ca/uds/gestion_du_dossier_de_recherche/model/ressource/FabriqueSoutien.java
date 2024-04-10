package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public class FabriqueSoutien extends FabriquePersonne {
    @Override
    public Ressource createRessource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
        return new Soutien(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat);
    }
}
