package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public abstract class FabriquePersonne {
    public abstract Ressource createRessource(String Nom, String Prenom, float Taux_horaire, float Heures_hebdo, LocalDate Debut_contrat, LocalDate Fin_contrat);

}