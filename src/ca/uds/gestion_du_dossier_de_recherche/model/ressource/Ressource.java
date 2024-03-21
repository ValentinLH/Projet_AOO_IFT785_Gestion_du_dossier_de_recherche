package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

public abstract class Ressource {
    private String Nom;
    private String Prenom;
    private float Taux_horaire;
    private float Heures_hebdo;
    private String Debut_contrat;
    private String Fin_contrat;


    public Ressource(String Nom, String Prenom, float TH, float HH, String DC, String FC){
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Taux_horaire = TH;
        this.Heures_hebdo = HH;
        this.Debut_contrat = DC;
        this.Fin_contrat = FC;
    }


    public float calcul_salaire_mensuel(){
        return(this.Heures_hebdo*this.Taux_horaire*4); // voir cas contrat termine avant fin du mois
    }
    
    //abstract travaillePour(Projet P);
    //abstract estPayéPar(Projet P);
}