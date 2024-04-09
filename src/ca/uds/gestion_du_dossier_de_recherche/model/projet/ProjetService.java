package ca.uds.gestion_du_dossier_de_recherche.model.projet;

import java.util.List;

/**
 * @apiNote A utiliser potentiellement avec la BDD
 **/
public class ProjetService {

    private ProjetRepository projetRepository = new ProjetRepositoryImpl();

    /*
     * Pour ajouter un projet
     */
    public void ajouterProjet(Projet projet) {
        projetRepository.create(projet);
    }

    /*
     * Pour lire un projet par son ID
     */
    public Projet lireProjet(Long id) {
        return projetRepository.read(id);
    }

    /*
     * Pour lire tous les projets
     */
    public List<Projet> lireTousLesProjets() {
        return projetRepository.readAll();
    }

    /*
     * Pour mettre à jour un projet
     */
    public void mettreAJourProjet(Projet projet) {
        projetRepository.update(projet);
    }

    /*
     * Pour supprimer un projet par son ID
     */
    public void supprimerProjet(Long id) {
        projetRepository.delete(id);
    }


}