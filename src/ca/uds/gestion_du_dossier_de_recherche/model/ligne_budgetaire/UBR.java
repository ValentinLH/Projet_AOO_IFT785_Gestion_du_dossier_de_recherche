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
	private Map<LigneBudgetaire, Float> montantsLignesBudgetaire;
	
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

	public UBR(Organisme organisme, int code, Map<LigneBudgetaire, Float> montantsLignesBudgetaire) {
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
	
	public UBR(Organisme organisme, int code, Map<LigneBudgetaire, Float> montantsLignesBudgetaire,
			boolean contraintes) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = montantsLignesBudgetaire;
		this.contraintes = contraintes;
		this.dateDebut = null;
        this.dateFin = null;
	}

	public UBR(Organisme organisme, int code,  boolean contraintes,
			LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.organisme = organisme;
		this.code = code;
		this.montantsLignesBudgetaire = new HashMap<>();
		this.contraintes = contraintes;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	
	public UBR(Organisme organisme, int code, Map<LigneBudgetaire, Float> montantsLignesBudgetaire, boolean contraintes,
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
        // Vérifie si la date de fin est définie et si elle est inférieure à la date de début
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
    @Table(name = "affectation_ligneubr")
    public static class AffectationLigneUbr {
    	
    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
    	private long id;

    	@ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ubr_id")
        private UBR ubr;

    	@ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "plignebudgetaire_id")
        private LigneBudgetaire ligne;

        private Float montant;
        
        public AffectationLigneUbr(UBR ubr, LigneBudgetaire ligne, Float montant) {
        	super();
        	this.ubr = ubr;
        	this.ligne = ligne;
        	this.montant = montant;
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
        
        public Float getMontant() {
        	return montant;
        }
        
        public void setMontant(Float montant) {
        	this.montant = montant;
        }
    }

	public List<AffectationLigneUbr> getAffectationsLignes() {
		return affectationsLignes;
	}

	public void setAffectationsLignes(List<AffectationLigneUbr> affectationsLignes) {
		this.affectationsLignes = affectationsLignes;
	}

}
