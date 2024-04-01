package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public abstract class FabriquePersonne {
    public abstract Ressource createRessource(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat);
}
