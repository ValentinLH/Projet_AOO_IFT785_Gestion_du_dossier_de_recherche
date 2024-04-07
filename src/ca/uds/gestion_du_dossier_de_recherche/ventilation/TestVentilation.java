package ca.uds.gestion_du_dossier_de_recherche.ventilation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Depense;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.Organisme;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;

public class TestVentilation {
	
	public static void main(String[] args) {
	
		Projet p1 = new Projet("projet 1 ", LocalDate.now().minusDays(2),LocalDate.now().plusDays(2));
		Projet p2 = new Projet("projet 2 ", LocalDate.now().minusDays(5),LocalDate.now().plusDays(10));
		Projet p3 = new Projet("projet 3 ", LocalDate.now().minusDays(10),LocalDate.now().plusDays(10));
		

		p1.addLigneBudgetaire(get1());

		p2.addLigneBudgetaire(get2());
		

		p3.addLigneBudgetaire(get1());
		p3.addLigneBudgetaire(get2());
		
		
		List<Projet> projets = new ArrayList<>();
		
		projets.add(p1);
		projets.add(p2);
		projets.add(p3);
		
		
		/** 
		 *  Ventillation par montant
		 */
		
		LocalDate date = LocalDate.now();
		
		Collections.sort(projets, (projet1, projet2) -> {
		    float montant1 = projet1.calculMontant(date);
		    float montant2 = projet2.calculMontant(date);
		    return Double.compare(montant2,montant1); // Trie par ordre décroissant
		});

		
		for(Projet p : projets)
			System.out.println("> "+p.getTitre()+" - "+p.calculMontant(date)+"$");
		
		System.out.println("  ----------------");
		
		
		Collections.sort(projets, (projet1, projet2) -> {
		    LocalDate date1 = projet1.getDateFin();
		    LocalDate date2 = projet2.getDateFin();
		    return date1.compareTo(date2); // Trie par ordre décroissant
		});
		
		for(Projet p : projets)
			System.out.println("> "+p.getTitre()+" - "+p.calculMontant(date)+"$"+" - "+p.getDateFin());
		
	}
	
	
	public static LigneBudgetaire get1()
	{
		Organisme monFrigo = new Organisme("Mon Frigidaire",0);
		UBR ubr1 = new UBR(monFrigo,1,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1));
		UBR ubr2 = new UBR(monFrigo,2,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(3));
		
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
		
		return ligne1;
	}
	
	public static LigneBudgetaire get2()
	{
		Organisme monFrigo = new Organisme("Mon Voyage",0);
		UBR ubr1 = new UBR(monFrigo,1,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1));
		UBR ubr2 = new UBR(monFrigo,2,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(3));
		
		LigneBudgetaire ligne1 = new LigneBudgetaire("Ligne Budgetaire de Voyage","Voyage");
		
		Depense depense1 = new Depense("Cologne",299f);
		Depense depense2 = new Depense("Edimbourg",103f);
		Depense depense3 = new Depense("Vérone",312f);
		
		ligne1.ajouterDepense(depense1);
		ligne1.ajouterDepense(depense2);
		ligne1.ajouterDepense(depense3);
		
		
		ubr1.ajouterLigneBudgetaire(ligne1, 300);
		//ubr2.ajouterLigneBudgetaire(ligne1, 50);
		ligne1.ajouterUBR(ubr2, 500.0f);
		
		return ligne1;
	}

}
