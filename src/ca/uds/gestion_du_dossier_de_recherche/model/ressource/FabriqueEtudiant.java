package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

public class FabriqueEtudiant extends FabriquePersonne{
    @override
    public Ressource fabricateurRessource(String Nom, String Prenom, float TH, String DC, String FC, String cip, programme p){
        return new Etudiant(Nom, Prenom, TH, DC, FC, cip, p);
    }
}