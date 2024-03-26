package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public class Etudiant extends Ressource{

    String cip;
    programme p;

    public enum programme {
        Baccalaureat, Maitrise, Doctorat, Post_Doctorat, Stage
    };


    public Etudiant(String Nom, String Prenom, float TH, float HH, LocalDate DC, LocalDate FC, String cip, programme p){
        super(Nom, Prenom, TH, HH, DC, FC);
        this.cip = cip;
        this.p = p;
    }

    public void display(){
        System.out.println("etudiant"+this.cip+"  "+this.p);
    }


}