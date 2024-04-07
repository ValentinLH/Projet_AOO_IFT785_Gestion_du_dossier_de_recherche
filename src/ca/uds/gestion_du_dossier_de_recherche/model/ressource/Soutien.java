package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public class Soutien extends Ressource{

	public Soutien(String Nom, String Prenom, float TH, float HH, LocalDate DC, LocalDate FC) {
		super(Nom, Prenom, TH, HH, DC, FC);
	}

	
}