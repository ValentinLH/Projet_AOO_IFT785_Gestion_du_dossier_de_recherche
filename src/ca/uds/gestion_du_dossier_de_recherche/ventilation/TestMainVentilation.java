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
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Bulletin;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.FabriqueEtudiant;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieDateFinContrat;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.TrieMontant;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.*;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.VentilationManager.Triplet;

public class TestMainVentilation {
	
	public static void main(String[] args) {
	
		Projet p1 = new Projet("projet 1 ", LocalDate.now().minusDays(2),LocalDate.now().plusDays(2));
		Projet p2 = new Projet("projet 2 ", LocalDate.now().minusDays(5),LocalDate.now().plusDays(10));
		Projet p3 = new Projet("projet 3 ", LocalDate.now().minusDays(10),LocalDate.now().plusDays(10));
		
		Projet p4 = new Projet("projet 4x4 ", LocalDate.now().minusDays(10),LocalDate.now().plusDays(1000));
		
		p1.addLigneBudgetaire(get1());
		p2.addLigneBudgetaire(get2());
		
		p3.addLigneBudgetaire(get1());
		p3.addLigneBudgetaire(get2());
		
		p4.addLigneBudgetaire(get3());
		
		List<Projet> projets = new ArrayList<>();
		
		projets.add(p1);
		projets.add(p2);
		projets.add(p3);
		projets.add(p4);
		
		/** 
		 *  Ventillation par montant
		 */
		
//		LocalDate date = LocalDate.now();
//		
//		Collections.sort(projets, (projet1, projet2) -> {
//		    float montant1 = projet1.calculMontant(date);
//		    float montant2 = projet2.calculMontant(date);
//		    return Double.compare(montant2,montant1); // Trie par ordre décroissant
//		});
//
//		
//		for(Projet p : projets)
//			System.out.println("> "+p.getTitre()+" - "+p.calculMontant(date)+"$");
//		
//		System.out.println("  ----------------");
//		
//		
//		Collections.sort(projets, (projet1, projet2) -> {
//		    LocalDate date1 = projet1.getDateFin();
//		    LocalDate date2 = projet2.getDateFin();
//		    return date1.compareTo(date2); // Trie par ordre décroissant
//		});
//		
//		for(Projet p : projets)
//			System.out.println("> "+p.getTitre()+" - "+p.calculMontant(date)+"$"+" - "+p.getDateFin());
//		
//		
		
		System.out.println(" Ressource ----------------");
		
		FabriqueEtudiant fe = new FabriqueEtudiant();

        Ressource r1 = fe.createRessource("Athos", "ESN", 1,3, 35.0f, LocalDate.now().minusDays(10), LocalDate.now().plusDays(100));
        Ressource r2 = fe.createRessource("Portos", "Vin", 1,1, 9.0f*5, LocalDate.now().minusDays(10), LocalDate.now().plusDays(1));
        Ressource r3 = fe.createRessource("Aramis", "Alcool", 7,2, 10.0f*5, LocalDate.now().minusDays(10), LocalDate.now().plusDays(50));
        Ressource r4 = fe.createRessource("D'Artagnan", "epee",3,2, 24.0f*5, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));

        
        List<Ressource> ressources = new ArrayList<>();
		
        ressources.add(r1);
        ressources.add(r2);
        ressources.add(r3);
        ressources.add(r4);
		
        LigneBudgetaire corneDAbondance = getSalaire();
        Bulletin b1 = new Bulletin("bulletin 1", 1300, r1, corneDAbondance,LocalDate.now());
//        r1.ajouterBulletin(b1);
        Bulletin b2 = new Bulletin("bulletin 2", 250, r2, corneDAbondance,LocalDate.now());
        Bulletin b3 = new Bulletin("bulletin 3", 1392, r3, corneDAbondance,LocalDate.now());
        Bulletin b4 = new Bulletin("bulletin 4", 10000, r4, corneDAbondance,LocalDate.now());
        
        
        
        
        for(Ressource r : ressources)
			System.out.println("> "+r.getNom()+" - "+r.calculerSalaireEstime()+"$"+" - "+r.getFinContrat());
        
        		
        System.out.println(" ##################################################################");
        System.out.println(" TrieDateFinContrat  : ");
        
        Ventilation ventilateur = new Ventilation(new TrieDateFinContrat());   
        List<Projet> res = (List<Projet>) ventilateur.ventiler(projets);
        
        for(Projet p : res)
			System.out.println("> "+p.getTitre()+" - "+p.getDateFin());
		
        System.out.println(" ~~~~~~~~~~~~~~");
        
        List<Ressource> res2 = (List<Ressource>) ventilateur.ventiler(ressources);
		
        for(Ressource r : res2)
			System.out.println("> "+r.getNom()+" - "+r.getFinContrat());
		
        
        System.out.println(" ##################################################################");
        System.out.println(" TrieMontant  : ");
        
        ventilateur.setStrategie(new TrieMontant());
        
        res = (List<Projet>) ventilateur.ventiler(projets);
        
        for(Projet p : res)
			System.out.println("> "+p.getTitre()+" - "+p.getMontantVentilation(ventilateur.getDate())+"$"+" - "+p.getDateFin());
		
        
        System.out.println(" ~~~~~~~~~~~~~~");
        
        res2 = (List<Ressource>) ventilateur.ventiler(ressources);
        for(Ressource r : res2)
			System.out.println("> "+r.getNom()+" - "+r.getMontantVentilation(ventilateur.getDate())+"$"+" - "+r.getFinContrat());
		
        System.out.println(" ~~~~~~~~~~~~~~");
        
        VentilationManager ventilationManager = new VentilationManager(ventilateur.getDate());
        List<Triplet<Projet, Ressource, Float>> tricount =  ventilationManager.ventilerMontants(res, res2);
        
        for(Triplet<Projet, Ressource, Float> t : tricount)
			System.out.println("> "+t.getFirst().getTitre()+" - "+t.getSecond().getNom() +" - "+t.getThird()+"$");
		
        
        
        
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
		
		
		ligne1.ajouterUBR(ubr1, 50.0f);
		
		return ligne1;
	}
	
	
	public static LigneBudgetaire get3()
	{
		Organisme monFrigo = new Organisme("Mon Concessionnaire",0);
		UBR ubr1 = new UBR(monFrigo,1,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1));
		UBR ubr2 = new UBR(monFrigo,2,false,LocalDate.now().minusDays(1),LocalDate.now().plusDays(3));
		
		LigneBudgetaire ligne1 = new LigneBudgetaire("Ligne Budgetaire des Vroom Vroom","Vehicule");
		
		Depense depense1 = new Depense("Alfa Romeo Spider 68'",29900f);
		Depense depense2 = new Depense("Mustang",10300f);
		Depense depense3 = new Depense("Alpine",312500f);
		
		ligne1.ajouterDepense(depense1);
		ligne1.ajouterDepense(depense2);
		ligne1.ajouterDepense(depense3);
		
		
		ubr1.ajouterLigneBudgetaire(ligne1, 2000000);
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
	
	public static LigneBudgetaire getSalaire()
	{
		Organisme corne = new Organisme("Corne d'abondance",0);
		UBR ubr1 = new UBR(corne,1,false,LocalDate.MIN,LocalDate.MAX);
		
		LigneBudgetaire ligne1 = new LigneBudgetaire("Argent Infini","Salaire");

		
		ubr1.ajouterLigneBudgetaire(ligne1, Float.MAX_VALUE);
		//ubr2.ajouterLigneBudgetaire(ligne1, 50);
//		ligne1.ajouterUBR(ubr2, 500.0f);
		
		return ligne1;
	}

}
