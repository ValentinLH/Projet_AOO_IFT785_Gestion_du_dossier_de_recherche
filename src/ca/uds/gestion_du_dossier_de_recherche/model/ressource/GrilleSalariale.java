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
		
		Map<Integer, Double> echelle4 = new HashMap<>();
		echelle4.put(1, 33.57);
		echelle4.put(2, 34.85);
		echelle4.put(3, 36.10);
		echelle4.put(4, 37.36);
		echelle4.put(5, 38.71);
		echelle4.put(6, 40.18);
		grille.put(4, echelle4);
		
		Map<Integer, Double> echelle5 = new HashMap<>();
		echelle5.put(1, 34.84);
		echelle5.put(2, 36.16);
		echelle5.put(3, 37.47);
		echelle5.put(4, 38.78);
		echelle5.put(5, 40.17);
		echelle5.put(6, 41.70);
		grille.put(5, echelle5);
		
		Map<Integer, Double> echelle6 = new HashMap<>();
		echelle6.put(1, 36.16);
		echelle6.put(2, 37.53);
		echelle6.put(3, 58.88);
		echelle6.put(4, 40.24);
		echelle6.put(5, 41.69);
		echelle6.put(6, 43.27);
		grille.put(6, echelle6);
		
		Map<Integer, Double> echelle7 = new HashMap<>();
		echelle7.put(1, 37.52);
		echelle7.put(2, 38.95);
		echelle7.put(3, 40.35);
		echelle7.put(4, 41.76);
		echelle7.put(5, 43.27);
		echelle7.put(6, 44.91);
		grille.put(7, echelle7);
		
		Map<Integer, Double> echelle8 = new HashMap<>();
		echelle8.put(1, 38.94);
		echelle8.put(2, 40.42);
		echelle8.put(3, 41.88);
		echelle8.put(4, 43.34);
		echelle8.put(5, 44.90);
		echelle8.put(6, 46.61);
		grille.put(8, echelle8);
		
		Map<Integer, Double> echelle9 = new HashMap<>();
		echelle8.put(1, 40.41);
		echelle8.put(2, 41.95);
		echelle8.put(3, 43.46);
		echelle8.put(4, 44.98);
		echelle8.put(5, 46.60);
		echelle8.put(6, 48.37);
		grille.put(9, echelle9);
		
		Map<Integer, Double> echelle10 = new HashMap<>();
		echelle10.put(1, 41.94);
		echelle10.put(2, 43.53);
		echelle10.put(3, 45.10);
		echelle10.put(4, 46.68);
		echelle10.put(5, 48.36);
		echelle10.put(6, 50.20);
		grille.put(10, echelle10);
		
		Map<Integer, Double> echelle11 = new HashMap<>();
		echelle11.put(1, 43.53);
		echelle11.put(2, 45.18);
		echelle11.put(3, 46.81);
		echelle11.put(4, 48.44);
		echelle11.put(5, 50.19);
		echelle11.put(6, 52.10);
		grille.put(11, echelle11);
		
		Map<Integer, Double> echelle12 = new HashMap<>();
		echelle12.put(1, 45.17);
		echelle12.put(2, 46.89);
		echelle12.put(3, 48.58);
		echelle12.put(4, 50.28);
		echelle12.put(5, 52.09);
		echelle12.put(6, 54.06);
		grille.put(12, echelle12);
		
		Map<Integer, Double> echelle13 = new HashMap<>();
		echelle13.put(1, 46.88);
		echelle13.put(2, 48.66);
		echelle13.put(3, 50.41);
		echelle13.put(4, 52.18);
		echelle13.put(5, 54.05);
		echelle13.put(6, 56.11);
		grille.put(13, echelle13);
		
		Map<Integer, Double> echelle14 = new HashMap<>();
		echelle14.put(1, 48.69);
		echelle14.put(2, 50.45);
		echelle14.put(3, 52.28);
		echelle14.put(4, 54.18);
		echelle14.put(5, 56.15);
		echelle14.put(6, 58.18);
		grille.put(14, echelle14);


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
