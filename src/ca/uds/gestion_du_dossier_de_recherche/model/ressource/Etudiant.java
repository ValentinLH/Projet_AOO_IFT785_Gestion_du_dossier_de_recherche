package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

public class Etudiant extends Ressource{

    String cip;
    programme p;

    public enum programme {
        Baccalaureat, Maitrise, Doctorat, Post-Doctorat, Stage
    };


    public Etudiant(String Nom, String Prenom, float TH, String DC, String FC, String cip, programme p){
        super(Nom, Prenom, TH, DC, FC);
        this.cip = cip;
        this.programme = p;
    }

    public void display(){
        System.out.println("etudiant"+this.cip+"  "+this.p);
    }


}