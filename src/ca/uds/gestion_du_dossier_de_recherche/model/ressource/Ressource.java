package ca.uds.gestion_du_dossier_de_recherche.model.ressource;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import java.time.LocalDate;

public class Ressource {
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

    public LocalDate getDateFin() {
        return Fin_contrat;
    }

    public LocalDate getDateDebut() {
        return Debut_contrat;
    }

    public float calcul_salaire_mensuel(){
        return(this.Heures_hebdo*this.Taux_horaire*4); // voir cas contrat termine avant fin du mois
    }
    public void travaillePour(Projet P){
    }

    public void estPayéPar(Projet P) {
    }
}