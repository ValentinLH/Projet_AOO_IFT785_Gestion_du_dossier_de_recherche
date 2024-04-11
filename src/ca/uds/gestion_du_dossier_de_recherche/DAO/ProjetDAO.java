package ca.uds.gestion_du_dossier_de_recherche.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.uds.gestion_du_dossier_de_recherche.BDD.BDDConnection;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet.AffectationProjetRessource;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
public class ProjetDAO {
	
	public static boolean persistProjet(Projet persistedProjet) {
		
		persistedProjet.getAffectationsRessources().clear();
		for (Map.Entry<Ressource, List<LocalDate>> entree : persistedProjet.getRessources().entrySet()) {
			persistedProjet.getAffectationsRessources().add(
					new AffectationProjetRessource(persistedProjet, entree.getKey(), entree.getValue().get(0), entree.getValue().get(1))
					);
		}
		
		BDDConnection.em.getTransaction().begin();
		BDDConnection.em.persist(persistedProjet);
		
		for (AffectationProjetRessource affectation : persistedProjet.getAffectationsRessources()) {
			BDDConnection.em.persist(affectation);
		}
		
		BDDConnection.em.getTransaction().commit();
		
		if (BDDConnection.em.contains(persistedProjet)) {
			  return true;
		} else {
			  return false;
		}
	}
	
	
	public static List<Projet> getAllProjet() {
		
		Query queryProjet = BDDConnection.em.createQuery("from Projet");
		List<Projet> projetList = queryProjet.getResultList();
		for (Projet projet : projetList) {
			Query queryAffectation = BDDConnection.em.createQuery("from Projet$AffectationProjetRessource where projet.id = :id");
			queryAffectation.setParameter("id", projet.getId());
			List<AffectationProjetRessource> affectationList = queryAffectation.getResultList();
			
			Map<Ressource, List<LocalDate>> ressources = new HashMap<>();
			for (AffectationProjetRessource affectation : affectationList) {
				Query queryRessource = BDDConnection.em.createQuery("from Ressource where id = :id");
				queryRessource.setParameter("id", affectation.getRessource().getId());
				Ressource ressource = (Ressource) queryRessource.getSingleResult();
				
				ressources.put(ressource, new ArrayList<LocalDate>(Arrays.asList(affectation.getDateDebut(), affectation.getDateFin())));
			}
			
			projet.setRessources(ressources);
			
		}
		
		return projetList;
	}

}
