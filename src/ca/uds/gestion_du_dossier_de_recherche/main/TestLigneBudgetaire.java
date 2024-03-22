package ca.uds.gestion_du_dossier_de_recherche.main;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;

public class TestLigneBudgetaire {

	public static void main(String[] args) {

		
		System.out.println("Hey");

		Organisme monFrigo = new Organisme("Mon Frigidaire",0);
		UBR ubr1 = new UBR(monFrigo,1);
		UBR ubr2 = new UBR(monFrigo,2);
		
		LigneBudgetaire ligne1 = new LigneBudgetaire("Ligne Budgetaire de Chocolat","Chocolat");
		
		Depense depense1 = new Depense("Chocolat Noir 97%",2.99f);
		Depense depense2 = new Depense("Sarments du Médoc",10.30f);
		Depense depense3 = new Depense("Guinettes Bordelaises",31.25f);
		
		ligne1.ajouterDepense(depense1);
		ligne1.ajouterDepense(depense2);
		ligne1.ajouterDepense(depense3);
		
		
		//ubr1.ajouterLigneBudgetaire(ligne1, 20);
		//ubr2.ajouterLigneBudgetaire(ligne1, 50);
		ligne1.ajouterUBR(ubr1, 50.0f);
		
		System.out.println(ligne1.toString());
		System.out.println("Le montant restant de la ligne : "+ligne1.getMontantLigne()+"$");
		
		
	}

}
