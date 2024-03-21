package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

public class FabriqueEtudiant extends FabriquePersonne {
    @Override
    public Ressource createRessource(String Nom, String Prenom, float Taux_horaire, float Heures_hebdo, String Debut_contrat, String Fin_contrat) {
        // Add any additional logic or validation here if needed
        return new Etudiant(Nom, Prenom, Taux_horaire, Debut_contrat, Fin_contrat, "", Programme.BACCALAUREAT);
    }
}