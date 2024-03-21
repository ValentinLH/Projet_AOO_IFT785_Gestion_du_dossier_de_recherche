package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

public class FabriqueSoutien extends FabriquePersonne{
    @override
    public Ressource fabricateurRessource(){
        return new Soutien();
    }
}