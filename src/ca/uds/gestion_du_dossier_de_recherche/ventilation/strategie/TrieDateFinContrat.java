package ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;

public class TrieDateFinContrat extends StrategieTrie {

	@Override
	public List<Ventilable> ventiler(List<? extends Ventilable> ventilables) {
		
		List<Ventilable> res = new ArrayList<>();
		res.addAll(ventilables);
	
		
		Collections.sort(res, (ventilable1, ventilable2) -> {
		    LocalDate date1 = ventilable1.getDateFin();
		    LocalDate date2 = ventilable2.getDateFin();
		    return date1.compareTo(date2); // Trie par ordre décroissant
		});
		return res;
	}
	
	@Override
	public String toString() {
		return TrieDateFinContrat.class.getSimpleName();
	}

}
