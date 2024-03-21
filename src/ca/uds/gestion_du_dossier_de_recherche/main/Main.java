package ca.uds.gestion_du_dossier_de_recherche.main;
package ca.uds.gestion_du_dossier_de_recherche.model.ressource;


public class Main {

	public static void main(String[] args) {

		
		System.out.println("Hey");
		FabriqueRessource FR = new FabriqueEtudiant();
		Ressource e = FabriqueEtudiant.fabricateurRessource("Nom", "Prenom", 10.0, "DC", "FC", "cip", 0);
		e.display();

	}

}
