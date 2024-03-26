package ca.uds.gestion_du_dossier_de_recherche.model.projet;

import java.util.List;

public interface ProjetRepository {
    void create(Projet projet);
    Projet read(Long id);
    List<Projet> readAll();
    void update(Projet projet);
    void delete(Long id);
}