package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Bulletin extends Depense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Ressource ressource;
	
	@ManyToOne(cascade = CascadeType.ALL)
	LigneBudgetaire ligne;
	
	@Column(columnDefinition = "DATE")
	LocalDate date;
	
	public Bulletin()
	{
		super();
	}
	
	

	public Bulletin(String nom, float montant,Ressource ressource, LigneBudgetaire ligne, LocalDate date) {
		super(nom,montant);
		setRessource(ressource);
		setLigne(ligne);
		this.date = date;
	}


	public Bulletin(float montant, Ressource ressource, LigneBudgetaire ligne, LocalDate date) {
		super("Bulletin de salaire - "+ressource.getNom()+" - "+date,montant);
		setRessource(ressource);
		setLigne(ligne);
		this.date = date;
	}

	/**
	 * @return the ressource
	 */
	public Ressource getRessource() {
		return ressource;
	}

	/**
	 * @param ressource the ressource to set
	 */
	public void setRessource(Ressource ressource) {
		if (this.ressource != null)
			this.ressource.supprimerBulletin(this);
		
		if (ressource != null)
			ressource.ajouterBulletin(this);
		this.ressource = ressource;
	}

	/**
	 * @return the ligne
	 */
	public LigneBudgetaire getLigne() {
		return ligne;
	}

	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(LigneBudgetaire ligne) {
		
		if (this.ligne != null)
			this.ligne.supprimerDepense(this);
		
		if (ligne != null)
			ligne.ajouterDepense(this);
		
		this.ligne = ligne;
		
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public void detruit()
	{
		if (this.ligne != null)
			this.ligne.supprimerDepense(this);
		this.ligne = null;
		this.ressource = null;
	}
	
	
	
	
	
	
	
}
