package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

import java.util.Map;

public class UBR {

	private Organisme organisme;
	private int code;
	private Map<LigneBudgetaire, Float> montantsLignesBudgetaire;
	
	
	/**
	 * @return the organisme
	 */
	public Organisme getOrganisme() {
		return organisme;
	}
	/**
	 * @param organisme the organisme to set
	 */
	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the lignesBudgetaire
	 */
	public Map<LigneBudgetaire, Float> getMontantsLignesBudgetaire() {
		return montantsLignesBudgetaire;
	}
	/**
	 * @param lignesBudgetaire the lignesBudgetaire to set
	 */
	public void setMontantsLignesBudgetaire(Map<LigneBudgetaire, Float> lignesBudgetaire) {
		this.montantsLignesBudgetaire = lignesBudgetaire;
	}
	public float getMontant(LigneBudgetaire ligneBudgetaire) {
		return montantsLignesBudgetaire.get(ligneBudgetaire);
	}
	
	

}
