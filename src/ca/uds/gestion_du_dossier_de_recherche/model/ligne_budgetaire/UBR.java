package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UBR {

	private Organisme organisme;
	private int code;
	private Map<LigneBudgetaire, Float> montantsLignesBudgetaire;

	public UBR() {
		super();
		this.organisme = null;
		this.code = 0;
		this.montantsLignesBudgetaire = new HashMap<>();
	}

	public UBR(Organisme organisme, int code) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = new HashMap<>();
	}

	public UBR(Organisme organisme, int code, Map<LigneBudgetaire, Float> montantsLignesBudgetaire) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = montantsLignesBudgetaire;
	}

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

	// Méthode pour ajouter une ligne budgétaire avec son montant
	public void ajouterLigneBudgetaire(LigneBudgetaire ligneBudgetaire, float montant) {
		montantsLignesBudgetaire.put(ligneBudgetaire, montant);
		if (!ligneBudgetaire.getUbrs().contains(this)) {
			ligneBudgetaire.ajouterUBR(this,montant);
		}
	}

	// Méthode pour supprimer une ligne budgétaire
	public void supprimerLigneBudgetaire(LigneBudgetaire ligneBudgetaire) {
		montantsLignesBudgetaire.remove(ligneBudgetaire);
		if (!ligneBudgetaire.getUbrs().contains(this)) {
			ligneBudgetaire.supprimerUBR(this);
		}
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UBR [organisme=").append(organisme).append(", code=").append(code)
				.append(", montantsLignesBudgetaire={");

		// Parcourir chaque entrée de la map montantsLignesBudgetaire
		for (Entry<LigneBudgetaire, Float> entry : montantsLignesBudgetaire.entrySet()) {
			LigneBudgetaire ligneBudgetaire = entry.getKey();
			float montant = entry.getValue();
			sb.append(ligneBudgetaire.getNom()).append("=").append(montant).append(", ");
		}

		// Supprimer la virgule et l'espace à la fin
		if (!montantsLignesBudgetaire.isEmpty()) {
			sb.setLength(sb.length() - 2);
		}

		sb.append("}]");
		return sb.toString();
	}

}
