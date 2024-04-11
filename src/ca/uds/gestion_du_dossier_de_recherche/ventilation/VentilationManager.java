package ca.uds.gestion_du_dossier_de_recherche.ventilation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;

public class VentilationManager {
	
	LocalDate date;

	
	
    public VentilationManager(LocalDate date) {
		super();
		this.date = date;
	}

	public List<Triplet<Projet, Ressource, Float>> ventilerMontants(List<Projet> projets, List<Ressource> ressources) {
        List<Triplet<Projet, Ressource, Float>> result = new ArrayList<>();

        Map<Ressource,Float> attributions = new HashMap<>();
        
        for (Ressource ressource : ressources) {
        	attributions.put(ressource, 0f);
        }
        
        
        // Parcours de chaque projet
        for (Projet projet : projets) {
            // Calcul du montant à ventiler pour ce projet
        	float montantProjet = projet.getMontantVentilation(date); // Exemple de méthode pour obtenir le montant du projet

            // Parcours de chaque ressource
            for (Ressource ressource : ressources) {
                
            	float montantRestantRessource = ressource.getMontantVentilation(date) - attributions.get(ressource);
            	// Vérifier si le montant du projet peut être attribué à cette ressource
            	
            	
                float montantAttribue = Math.min(montantProjet, montantRestantRessource); // Exemple de méthode pour obtenir le montant restant de la ressource
                montantProjet -= montantAttribue;

                // Ajouter le triplet (projet, ressource, montant) à la liste de résultats si le montant attribué est positif
                if (montantAttribue > 0) {
                	attributions.replace(ressource,  (attributions.get(ressource)+montantAttribue));
                    result.add(new Triplet<>(projet, ressource, montantAttribue));
                }

                // Arrêter la boucle si le montant du projet à ventiler est épuisé
                if (montantProjet <= 0) {
                    break;
                }
            }
        }

        return result;
    }

    // Classe Triplet générique
    public static class Triplet<T, U, V> {
        private final T first;
        private final U second;
        private final V third;

        public Triplet(T first, U second, V third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

        public V getThird() {
            return third;
        }
    }
}
