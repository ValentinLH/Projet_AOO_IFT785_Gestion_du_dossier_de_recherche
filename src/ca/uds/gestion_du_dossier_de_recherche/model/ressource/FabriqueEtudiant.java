package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

public class FabriqueEtudiant implements FabriquePersonne {

    @Override
    public Ressource fabricateurRessource() {
        return new Etudiant("Nom", "Prenom", 15.0f, "2022-01-01", "2023-01-01", "123456", Programme.MAITRISE);
    }
}