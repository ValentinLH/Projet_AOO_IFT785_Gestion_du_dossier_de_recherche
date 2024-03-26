package ca.uds.gestion_du_dossier_de_recherche.model.projet;

import ca.uds.gestion_du_dossier_de_recherche.BDD.EntityClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.*;
import java.util.List;

public class ProjetRepositoryImpl implements ProjetRepository {

    public static EntityManager em = EntityClass.em;
    @Override
    public void create(Projet projet) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(projet);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Projet read(Long id) {
        return em.find(Projet.class, id);
    }

    @Override
    public List<Projet> readAll() {
        EntityManager em = EntityClass.em;
        return em.createQuery("'SELECT p FROM Projet p'", Projet.class).getResultList();
    }

    @Override
    public void update(Projet updatedProjet) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Projet projet = em.find(Projet.class, updatedProjet.getId());
            if (projet != null) {
                projet.setTitre(updatedProjet.getTitre());
                projet.setDescription(updatedProjet.getDescription());
                projet.setDateDebut(updatedProjet.getDateDebut());
                projet.setDateFin(updatedProjet.getDateFin());
                //projet.setFinancement(updatedProjet.getFinancement());
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = EntityClass.em;
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Projet projet = em.find(Projet.class, id);
            if (projet != null) {
                em.remove(projet);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

}