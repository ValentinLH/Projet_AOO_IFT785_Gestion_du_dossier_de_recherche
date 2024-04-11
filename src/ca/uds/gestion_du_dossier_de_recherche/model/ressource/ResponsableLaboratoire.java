package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class ResponsableLaboratoire extends Ressource {

	private String laboratoire;

	public ResponsableLaboratoire(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat, String laboratoire) {
		super(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat);
		this.laboratoire = laboratoire;
	}
	
	public ResponsableLaboratoire() {
		super();
	}

	@Override
	public String toString() {
		return super.toString() + "\nResponsableLaboratoire [Laboratoire=" + laboratoire + "]";
	}

	public String getLaboratoire() {
		return laboratoire;
	}

	public void setLaboratoire(String laboratoire) {
		this.laboratoire = laboratoire;
	}
}

