package ca.uds.gestion_du_dossier_de_recherche.DAO;

import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.BDD.BDDConnection;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
public class OrganismeDAO {

	public static boolean persistOrganisme(Organisme persistedOrganisme) {
		BDDConnection.em.getTransaction().begin();
		BDDConnection.em.persist(persistedOrganisme);
		BDDConnection.em.getTransaction().commit();	
		if (BDDConnection.em.contains(persistedOrganisme)) {
			  return true;
		} else {
			  return false;
		}
	}
	
	public static List<Organisme> getAllOrganisme() {
		Query query = BDDConnection.em.createQuery("from ressource"); 
		List<Organisme> organismeList = query.getResultList();
		return organismeList;
	}
	
	public static Organisme getOrganismeByCode(int code) {
		Query query = BDDConnection.em.createQuery("from organisme where code = :code");
		query.setParameter("code", code);
		return (Organisme) query.getSingleResult();
	}
}
