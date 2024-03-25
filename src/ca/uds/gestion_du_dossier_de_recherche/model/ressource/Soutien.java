package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Etudiant.Programme;

public class Soutien extends Ressource{

	public Soutien(String Nom, String Prenom, float TH, float HH, String DC, String FC) {
		super(Nom, Prenom, TH, HH, DC, FC);
	}

	@Override
	public void setCip(String cip) {
		
	}

	@Override
	public void setProgramme(Programme programme) {
		
	}
	
}