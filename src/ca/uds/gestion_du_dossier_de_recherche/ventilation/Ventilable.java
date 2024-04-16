package ca.uds.gestion_du_dossier_de_recherche.ventilation;

import java.time.LocalDate;

public interface Ventilable {

	public LocalDate getDateFin();

	public float getMontantVentilation(LocalDate date);

}
