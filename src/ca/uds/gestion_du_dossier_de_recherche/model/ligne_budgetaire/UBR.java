package ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
public class UBR {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int code;

	@OneToOne
	private Organisme organisme;

	@OneToMany(mappedBy = "ubr")
    private List<AffectationLigneUbr> affectationsLignes = new ArrayList<>();


	@Transient
	private Map<LigneBudgetaire, Fond> montantsLignesBudgetaire;
	
	private boolean contraintes;

	@Column(columnDefinition = "DATE")
	private LocalDate dateDebut;

	@Column(columnDefinition = "DATE")
	private LocalDate dateFin;

	public UBR() {
		super();
		this.organisme = null;
		this.code = 0;
		this.montantsLignesBudgetaire = new HashMap<>();
		this.contraintes = false;
		this.dateDebut = null;
		this.dateFin = null;
	}

	public UBR(Organisme organisme, int code) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = new HashMap<>();
		this.contraintes = false;
		this.dateDebut = null;
		this.dateFin = null;
	}

	public UBR(Organisme organisme, int code, Map<LigneBudgetaire, Fond> montantsLignesBudgetaire) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = montantsLignesBudgetaire;
		this.contraintes = false;
		this.dateDebut = null;
		this.dateFin = null;
	}

	public UBR(Organisme organisme, int code, boolean contraintes) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = new HashMap<>();
		this.contraintes = contraintes;
		this.dateDebut = null;
		this.dateFin = null;
	}

	public UBR(Organisme organisme, int code, Map<LigneBudgetaire, Fond> montantsLignesBudgetaire,
			boolean contraintes) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = montantsLignesBudgetaire;
		this.contraintes = contraintes;
		this.dateDebut = null;
		this.dateFin = null;
	}

	public UBR(Organisme organisme, int code, boolean contraintes, LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = new HashMap<>();
		this.contraintes = contraintes;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public UBR(Organisme organisme, int code, Map<LigneBudgetaire, Fond> montantsLignesBudgetaire, boolean contraintes,
			LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = montantsLignesBudgetaire;
		this.contraintes = contraintes;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
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
	public Map<LigneBudgetaire, Fond> getMontantsLignesBudgetaire() {
		return montantsLignesBudgetaire;
	}

	/**
	 * @param lignesBudgetaire the lignesBudgetaire to set
	 */
	public void setMontantsLignesBudgetaire(Map<LigneBudgetaire, Fond> lignesBudgetaire) {
		this.montantsLignesBudgetaire = lignesBudgetaire;
	}

	public float getMontant(LigneBudgetaire ligneBudgetaire) {
		Fond f = montantsLignesBudgetaire.get(ligneBudgetaire);
		return f.getTotal()-f.getMinUtilise();
	}

	public float getMontantaUtilise(LigneBudgetaire ligneBudgetaire) {
		Fond f = montantsLignesBudgetaire.get(ligneBudgetaire);
		return f.getMinUtilise();
	}

	public float getMontantTotalUBR(LigneBudgetaire ligneBudgetaire) {
		Fond f = montantsLignesBudgetaire.get(ligneBudgetaire);
		return f.getTotal();
	}

	// Méthode pour ajouter une ligne budgétaire avec son montant
	public void ajouterLigneBudgetaire(LigneBudgetaire ligneBudgetaire, float montant) {
		Fond f = new Fond(montant,0.0f);
		montantsLignesBudgetaire.put(ligneBudgetaire, f);
		if (!ligneBudgetaire.getUbrs().contains(this)) {
			ligneBudgetaire.ajouterUBR(this, montant);
		}
	}

	// Méthode pour ajouter une ligne budgétaire avec son montant
	public void ajouterLigneBudgetaire(LigneBudgetaire ligneBudgetaire, float montant, float min) {
		Fond f = new Fond(montant,min);
		montantsLignesBudgetaire.put(ligneBudgetaire, f);
		if (!ligneBudgetaire.getUbrs().contains(this)) {
			ligneBudgetaire.ajouterUBR(this, montant);
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
		for (Entry<LigneBudgetaire, Fond> entry : montantsLignesBudgetaire.entrySet()) {
			LigneBudgetaire ligneBudgetaire = entry.getKey();
			Fond montant = entry.getValue();
			sb.append(ligneBudgetaire.getNom()).append("=").append(montant).append(", ");
		}

		// Supprimer la virgule et l'espace à la fin
		if (!montantsLignesBudgetaire.isEmpty()) {
			sb.setLength(sb.length() - 2);
		}

		sb.append("}]");
		return sb.toString();
	}

	public boolean isContraintes() {
		return contraintes;
	}

	public void setContraintes(boolean contraintes) {
		this.contraintes = contraintes;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
		// Vérifie si la date de fin est définie et si elle est inférieure à la date de
		// début
		if (dateFin != null && dateFin.isBefore(dateDebut)) {
			this.dateFin = dateDebut;
		}
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		// Vérifie si la date de fin est inférieure à la date de début
		if (dateDebut != null && dateFin.isBefore(dateDebut)) {
			throw new IllegalArgumentException("La date de fin doit être supérieure ou égale à la date de début.");
		}
		this.dateFin = dateFin;
	}

	
	@Entity
	@Table(name = "fond")
	public static class Fond {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		private float total;
		private float minUtilise;
		
		public Fond() {

		}


		public Fond(Float total, Float minUtilise) {
			super();
			this.total = total;
			this.minUtilise = minUtilise;
		}
		
		public long getId() {
			return this.id;
		}
		
		public void setId(long id) {
			this.id = id;
		}

		/**
		 * @return the total
		 */
		public Float getTotal() {
			return total;
		}

		/**
		 * @param total the total to set
		 */
		public void setTotal(Float total) {
			this.total = total;
		}

		/**
		 * @return the minUtilise
		 */
		public Float getMinUtilise() {
			return minUtilise;
		}

		/**
		 * @param minUtilise the minUtilise to set
		 */
		public void setMinUtilise(Float minUtilise) {
			this.minUtilise = minUtilise;
		}

	}

	
	@Entity
	@Table(name = "affectation_ligneubr")
	public static class AffectationLigneUbr {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "ubr_id")
		private UBR ubr;

		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "lignebudgetaire_id")
		private LigneBudgetaire ligne;

		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "fond_id")
		private Fond fond;
		
		public AffectationLigneUbr() {
			
		}
		
		public AffectationLigneUbr(UBR ubr, LigneBudgetaire ligne, Fond fond) {
			super();
			this.ubr = ubr;
			this.ligne = ligne;
			this.fond = fond;
		}

		public long getId() {
			return this.id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public UBR getUbr() {
			return ubr;
		}

		public void setUbr(UBR ubr) {
			this.ubr = ubr;
		}

		public LigneBudgetaire getLigneBudgetaire() {
			return ligne;
		}

		public void setLigneBudgetaire(LigneBudgetaire ligne) {
			this.ligne = ligne;
		}

		public Fond getFond() {
			return fond;
		}

		public void setFond(Fond fond) {
			this.fond = fond;
		}
	}

	public List<AffectationLigneUbr> getAffectationsLignes() {
		return affectationsLignes;
	}

	public void setAffectationsLignes(List<AffectationLigneUbr> affectationsLignes) {
		this.affectationsLignes = affectationsLignes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
