package ca.uds.gestion_du_dossier_de_recherche.DAO;

import jakarta.transaction.Transactional;

import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.BDD.BDDConnection;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import jakarta.persistence.Query;

@Transactional
public class ressourceDAO {
	
	public static boolean persistRessource(Ressource persistedRessource) {
		BDDConnection.em.getTransaction().begin();
		BDDConnection.em.persist(persistedRessource);
		BDDConnection.em.getTransaction().commit();	
		if (BDDConnection.em.contains(persistedRessource)) {
			  return true;
		} else {
			  return false;
		}
	}
	
	public static List<Ressource> getAllRessources() {
		Query query = BDDConnection.em.createQuery("from ressource"); 
		List<Ressource> ressourceList = query.getResultList();
		return ressourceList;
	}
	
	public static List<Etudiant> getAllEtuidants() {
		Query query = BDDConnection.em.createQuery("from etudiant"); 
		List<Etudiant> etudiantList = query.getResultList();
		return etudiantList;
	}
	
	public static List<Soutien> getAllSoutien() {
		Query query = BDDConnection.em.createQuery("from soutien"); 
		List<Soutien> soutienList = query.getResultList();
		return soutienList;
	}
	
	public static List<ResponsableLaboratoire> getAllResponsable() {
		Query query = BDDConnection.em.createQuery("from responsablelaboratoire"); 
		List<ResponsableLaboratoire> responsableList = query.getResultList();
		return responsableList;
	}

}
