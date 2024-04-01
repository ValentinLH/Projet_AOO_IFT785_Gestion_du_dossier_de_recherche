package ca.uds.gestion_du_dossier_de_recherche.main;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hey");
		FabriquePersonne factory = new FabriqueEtudiant();

		Ressource ressource = factory.createRessource("John", "Doe", 1, 1, 40.0f, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));

		if (ressource instanceof Etudiant) {
			Etudiant etudiant = (Etudiant) ressource;
			etudiant.setCip("jj");
			etudiant.setProgramme(Programme.DOCTORAT);
			System.out.println(etudiant.getProgramme());
			System.out.println(etudiant.toString());
			System.out.println(etudiant.getNom());
			System.out.println(etudiant.calculSalaireMensuel());
		} else {
			System.out.println("La ressource créée n'est pas un étudiant.");
		}
	}
}

