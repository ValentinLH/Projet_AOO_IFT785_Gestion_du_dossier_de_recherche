package ca.uds.gestion_du_dossier_de_recherche.main;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
import javafx.application.Application;

public class Main {

	public static void main(String[] args) {

		
		//FabriqueRessource FR = new FabriqueEtudiant();
		//Ressource e = FabriqueEtudiant.fabricateurRessource("Nom", "Prenom", 10.0, "DC", "FC", "cip", 0);
		//e.display();
		
		
		Projet p = new Projet("Test", LocalDate.now().minusDays(9), LocalDate.now().plusDays(9));
		
		Organisme monFrigo = new Organisme("Mon Frigidaire", 0);
		UBR ubr1 = new UBR(monFrigo, 1, true, LocalDate.now().minusDays(9), LocalDate.now().plusDays(9));
		LigneBudgetaire ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
		ligneBudgetaire.ajouterUBR(ubr1, 500f);
		
		p.addLigneBudgetaire(ligneBudgetaire);
		
		p.calculMontant(LocalDate.now());
		
		UBR ubr2 = new UBR(monFrigo, 1, false, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
		LigneBudgetaire ligneBudgetaire2 = new LigneBudgetaire("Ligne Budgetaire de Beurre", "Beurre");
		ligneBudgetaire2.ajouterUBR(ubr2, 1000f);
		
		
		p.addLigneBudgetaire(ligneBudgetaire2);
		p.calculMontant(LocalDate.now());
		
		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);
		
		p.addLigneBudgetaire(ligneBudgetaire3);
		
		p.removeLigneBudgetaire(ligneBudgetaire2);
		
		/*
		p.dateLimiteDepenses();
		
	    
		FabriquePersonne factory = new FabriqueEtudiant();
	    Ressource ressource = factory.createRessource("John", "Doe", 20.0f, 40.0f,   LocalDate.now().minusDays(5),  LocalDate.now().plusDays(5));
	    ressource.setCip("jj");
	    ressource.setProgramme(Programme.DOCTORAT);
	    System.out.println(ressource.getProgramme());
	    System.out.println(ressource.toString());
	    */
	    
	    
	    //System.out.println(ressource.getNom());
	    //System.out.println(ressource.calcul_salaire_mensuel());
		Application.launch();
		
	}

}
