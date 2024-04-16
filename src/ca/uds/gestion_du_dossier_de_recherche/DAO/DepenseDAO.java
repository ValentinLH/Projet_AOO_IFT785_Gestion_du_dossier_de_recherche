package ca.uds.gestion_du_dossier_de_recherche.DAO;

import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.BDD.BDDConnection;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Bulletin;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
public class DepenseDAO {
	
	public static boolean persistDepense(Depense persistedDepense) {
		BDDConnection.em.getTransaction().begin();
		BDDConnection.em.merge(persistedDepense);
		BDDConnection.em.getTransaction().commit();	
		if (BDDConnection.em.contains(persistedDepense)) {
			  return true;
		} else {
			  return false;
		}
	}
	
	public static List<Depense> getAllDepense() {
		Query query = BDDConnection.em.createQuery("from Depense"); 
		List<Depense> depenseList = query.getResultList();
		return depenseList;
	}
	
	public static List<Bulletin> getAllBulletin() {
		Query query = BDDConnection.em.createQuery("from Bulletin"); 
		List<Bulletin> bulletinList = query.getResultList();
		return bulletinList;
	}
}
