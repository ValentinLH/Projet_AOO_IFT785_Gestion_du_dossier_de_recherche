package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

public class FabriqueSoutien extends FabriquePersonne{
    @Override
    public Ressource createRessource(String Nom, String Prenom, float Taux_horaire, float Heures_hebdo, String Debut_contrat, String Fin_contrat){
        return new Soutien(Nom, Prenom, Taux_horaire, Heures_hebdo,Debut_contrat, Fin_contrat);
    }
}