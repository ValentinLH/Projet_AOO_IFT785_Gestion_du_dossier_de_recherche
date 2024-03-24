package ca.uds.gestion_du_dossier_de_recherche.main;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;


public class Main {

	public static void main(String[] args) {

		
		System.out.println("Hey");
		//FabriqueRessource FR = new FabriqueEtudiant();
		//Ressource e = FabriqueEtudiant.fabricateurRessource("Nom", "Prenom", 10.0, "DC", "FC", "cip", 0);
		//e.display();
		
		Projet p = new Projet("Test");
		
		Organisme monFrigo = new Organisme("Mon Frigidaire", 0);
		UBR ubr1 = new UBR(monFrigo, 1, true, LocalDate.now().minusDays(9), LocalDate.now().plusDays(9));
		LigneBudgetaire ligneBudgetaire = new LigneBudgetaire("Ligne Budgetaire de Chocolat", "Chocolat");
		ligneBudgetaire.ajouterUBR(ubr1, 500f);
		
		p.addLigneBudgetaire(ligneBudgetaire);
		
		p.CalculMontant(LocalDate.now());
		
		UBR ubr2 = new UBR(monFrigo, 1, false, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
		LigneBudgetaire ligneBudgetaire2 = new LigneBudgetaire("Ligne Budgetaire de Beurre", "Beurre");
		ligneBudgetaire2.ajouterUBR(ubr2, 1000f);
		
		
		p.addLigneBudgetaire(ligneBudgetaire2);
		p.CalculMontant(LocalDate.now());
		
		LigneBudgetaire ligneBudgetaire3 = new LigneBudgetaire("Ligne Budgetaire de Farine", "Farine");
		ligneBudgetaire3.ajouterUBR(ubr2, 5000f);
		
		p.addLigneBudgetaire(ligneBudgetaire3);
		
		p.removeLigneBudgetaire(ligneBudgetaire2);
		
		p.DateLimiteDepenses();
		
	}

}
