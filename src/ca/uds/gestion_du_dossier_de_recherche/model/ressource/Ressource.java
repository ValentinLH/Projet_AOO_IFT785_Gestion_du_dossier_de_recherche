package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Inheritance;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Ressource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
    private String Nom;
    private String Prenom;
    private float Taux_horaire;
    private float Heures_hebdo;
    private LocalDate Debut_contrat;
    private LocalDate Fin_contrat;


    public Ressource(String Nom, String Prenom, float TH, float HH, LocalDate DC, LocalDate FC){
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Taux_horaire = TH;
        this.Heures_hebdo = HH;
        this.Debut_contrat = DC;
        this.Fin_contrat = FC;
    }


 
    
    public void travaillePour(Projet P){
    }

    public void estPayePar(Projet P) {
    }

    public String getNom() {
		return Nom;
	}


	public void setNom(String nom) {
		Nom = nom;
	}


	public String getPrenom() {
		return Prenom;
	}


	public void setPrenom(String prenom) {
		Prenom = prenom;
	}


	public float getTaux_horaire() {
		return Taux_horaire;
	}


	public void setTaux_horaire(float taux_horaire) {
		Taux_horaire = taux_horaire;
	}


	public float getHeures_hebdo() {
		return Heures_hebdo;
	}


	public void setHeures_hebdo(float heures_hebdo) {
		Heures_hebdo = heures_hebdo;
	}


	public void setDebut_contrat(LocalDate debut_contrat) {
		Debut_contrat = debut_contrat;
	}


//	public LocalDate getFin_contrat() {
//		return Fin_contrat;
//	}


	public void setFin_contrat(LocalDate fin_contrat) {
		Fin_contrat = fin_contrat;
	}

    public LocalDate getFin_contrat() {
        return Fin_contrat;
    }

    public LocalDate getDebut_contrat() {
        return Debut_contrat;
    }


	public float calcul_salaire_mensuel(){
        return(this.Heures_hebdo*this.Taux_horaire*4); // voir cas contrat termine avant fin du mois
    }


	public void setProgramme(Programme programme) {
		// TODO Auto-generated method stub
		
	}


	public Programme getProgramme() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getCip() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setCip(String cip) {
		// TODO Auto-generated method stub
		
	}

    @Override
	public String toString() {
		return "Ressource [Nom=" + Nom + ", Prenom=" + Prenom + ", Taux_horaire=" + Taux_horaire + ", Heures_hebdo="
				+ Heures_hebdo + ", Debut_contrat=" + Debut_contrat + ", Fin_contrat=" + Fin_contrat + "]";
	}
    	
}