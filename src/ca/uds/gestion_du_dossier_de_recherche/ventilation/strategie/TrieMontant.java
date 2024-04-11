package ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;

public class TrieMontant extends StrategieTrie {

	@Override
	public List<Ventilable> ventiler(List<? extends Ventilable> ventilables) {

		List<Ventilable> res = new ArrayList<>();
		res.addAll(ventilables);

		LocalDate date = getVentilation().getDate();
		
		
		Collections.sort(res, (ventilable1, ventilable2) -> {
			float montant1 = ventilable1.getMontantVentilation(date);
			float montant2 = ventilable2.getMontantVentilation(date);
			return Double.compare(montant2, montant1);// Trie par ordre décroissant
		});

		return res;
	}

}
