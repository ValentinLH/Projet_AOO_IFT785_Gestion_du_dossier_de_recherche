package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.util.HashMap;
import java.util.Map;

public class GrilleSalariale {

	private Map<Integer, Map<Integer, Double>> grille;
	private static GrilleSalariale instance;

	private GrilleSalariale() {
		grille = new HashMap<>();

		initialiserGrille();

	}

	public static synchronized GrilleSalariale getInstance() {
		if (instance == null) {
			instance = new GrilleSalariale();
		}
		return instance;
	}

	private void initialiserGrille() {
		// Echellon 1
		Map<Integer, Double> echelle1 = new HashMap<>();
		echelle1.put(1, 30.03);
		echelle1.put(2, 31.18);
		echelle1.put(3, 32.30);
		echelle1.put(4, 33.43);
		echelle1.put(5, 34.63);
		echelle1.put(6, 35.95);
		grille.put(1, echelle1);

		// Echellon 2
		Map<Integer, Double> echelle2 = new HashMap<>();
		echelle2.put(1, 31.17);
		echelle2.put(2, 32.35);
		echelle2.put(3, 33.52);
		echelle2.put(4, 34.694);
		echelle2.put(5, 35.94);
		echelle2.put(6, 37.31);
		grille.put(2, echelle2);

		// Echellon 3
		Map<Integer, Double> echelle3 = new HashMap<>();
		echelle3.put(1, 33.57);
		echelle3.put(2, 34.85);
		echelle3.put(3, 36.10);
		echelle3.put(4, 32.30);
		echelle3.put(5, 32.30);
		echelle3.put(6, 32.30);
		grille.put(3, echelle3);

		// Echellon 4 -> 16 (A faire)
		Map<Integer, Double> echelle6 = new HashMap<>();
		echelle6.put(7, 44.91);
		grille.put(6, echelle6);

		// Echellon 7
		Map<Integer, Double> echelle7 = new HashMap<>();
		echelle7.put(1, 33.57);
		echelle7.put(2, 34.85);
		echelle7.put(3, 36.10);
		echelle7.put(4, 32.30);
		echelle7.put(5, 32.30);
		echelle7.put(6, 44.91);
		grille.put(7, echelle7);
	}

	public double getTauxHoraire(int echelle, int echelon) {

		Map<Integer, Double> echelleMap = grille.get(echelle);
		if (echelleMap == null) {
			throw new IllegalArgumentException("L'échelle spécifiée n'existe pas dans la grille salariale.");
		}
		Double tauxHoraire = echelleMap.get(echelon);
		if (tauxHoraire == null) {
			throw new IllegalArgumentException(
					"L'échelon spécifié pour cette échelle n'existe pas dans la grille salariale.");
		}

		return tauxHoraire;
	}

}
