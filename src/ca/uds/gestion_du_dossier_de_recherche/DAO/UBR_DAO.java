package ca.uds.gestion_du_dossier_de_recherche.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.uds.gestion_du_dossier_de_recherche.BDD.BDDConnection;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR.AffectationLigneUbr;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR.Fond;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
public class UBR_DAO {

	public static boolean persistUBR(UBR persistedUBR) {
		
		persistedUBR.getAffectationsLignes().clear();
		for(Map.Entry<LigneBudgetaire, Fond> entree : persistedUBR.getMontantsLignesBudgetaire().entrySet()) {
			persistedUBR.getAffectationsLignes().add(new AffectationLigneUbr(persistedUBR, entree.getKey(), entree.getValue()));
		}
		
		BDDConnection.em.getTransaction().begin();
				
		if(OrganismeDAO.getOrganismeByCode(persistedUBR.getOrganisme().getCode()) != null) {
			persistedUBR.setOrganisme(BDDConnection.em.merge(persistedUBR.getOrganisme()));
		} else {
			BDDConnection.em.persist(persistedUBR.getOrganisme());
		}
		
		//BDDConnection.em.persist(persistedUBR.getOrganisme());
		BDDConnection.em.persist(persistedUBR);
		
		for (AffectationLigneUbr affectation : persistedUBR.getAffectationsLignes()) {
		    	BDDConnection.em.persist(affectation);
		    }
		
		BDDConnection.em.getTransaction().commit();
		
		if (BDDConnection.em.contains(persistedUBR)) {
			  return true;
		} else {
			  return false;
		}
	}
	
	public static List<UBR> getAllUBR() {
		
		Query queryUBR = BDDConnection.em.createQuery("from UBR"); 
		List<UBR> UBRList = queryUBR.getResultList();
		for (UBR ubr : UBRList) {
			Query queryAffectation = BDDConnection.em.createQuery("from UBR$AffectationLigneUbr where ubr.id = :id");
			queryAffectation.setParameter("id", ubr.getId());
			List<AffectationLigneUbr> affectationList = queryAffectation.getResultList();
			
			Map<LigneBudgetaire, Fond> montantsLignesBudgetaire = new HashMap<>();
			
			for (AffectationLigneUbr affectation : affectationList)
			{
				Query queryFond = BDDConnection.em.createQuery("from UBR$Fond where id = :id");
				queryFond.setParameter("id", affectation.getFond().getId());
				Fond fond = (Fond) queryFond.getSingleResult();
				
				Query queryLigne = BDDConnection.em.createQuery("from LigneBudgetaire where id = :id");
				queryLigne.setParameter("id", affectation.getLigneBudgetaire().getId());
				LigneBudgetaire ligne = (LigneBudgetaire) queryLigne.getSingleResult();
				
				montantsLignesBudgetaire.put(ligne, fond);
			}
			
			ubr.setMontantsLignesBudgetaire(montantsLignesBudgetaire);
		}
		
		return UBRList;
	}
}
