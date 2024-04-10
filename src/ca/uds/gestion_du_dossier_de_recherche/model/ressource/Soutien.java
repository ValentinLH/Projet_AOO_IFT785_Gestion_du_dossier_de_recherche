package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Soutien extends Ressource {

	public Soutien(String nom, String prenom, int echelle, int echelon, float heuresHebdo, LocalDate debutContrat, LocalDate finContrat) {
		super(nom, prenom, echelle, echelon, heuresHebdo, debutContrat, finContrat);
	}
	
	public Soutien() {
		super();
	}

}
