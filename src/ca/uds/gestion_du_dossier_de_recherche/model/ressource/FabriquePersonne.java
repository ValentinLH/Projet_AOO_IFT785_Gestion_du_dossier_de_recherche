package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

public abstract class FabriquePersonne {
    public abstract Ressource createRessource(String Nom, String Prenom, float Taux_horaire, float Heures_hebdo, String Debut_contrat, String Fin_contrat);
}