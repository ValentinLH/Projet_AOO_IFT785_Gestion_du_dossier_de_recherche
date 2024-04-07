package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

public class ResponsableLaboratoire extends Ressource{
	
	private String Laboratoire;

	public ResponsableLaboratoire(String Nom, String Prenom, float TH, float HH, LocalDate DC, LocalDate FC, String Laboratoire) {
		super(Nom, Prenom, TH, HH, DC, FC);
		this.Laboratoire = Laboratoire;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString()+"\nResponsableLaboratoire [Laboratoire=" + Laboratoire + "]";
	}

	public String getLaboratoire() {
		return Laboratoire;
	}

	public void setLaboratoire(String laboratoire) {
		Laboratoire = laboratoire;
	}


	
	
}