package ca.uds.gestion_du_dossier_de_recherche.main;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

public class Main {

	public static void main(String[] args) {

		
		System.out.println("Hey");
	    FabriquePersonne factory = new FabriqueEtudiant();
	    Ressource ressource = factory.createRessource("John", "Doe", 20.0f, 40.0f, "2023-01-01", "2023-12-31");
	    ressource.setCip("jj");
	    ressource.setProgramme(Programme.DOCTORAT);
	    ressource.toString();
	    //System.out.println(ressource.getNom());
	    //System.out.println(ressource.calcul_salaire_mensuel());
	}

}
