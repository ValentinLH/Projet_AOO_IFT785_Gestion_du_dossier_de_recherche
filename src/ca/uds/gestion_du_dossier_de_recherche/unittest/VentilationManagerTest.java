package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.uds.gestion_du_dossier_de_recherche.model.projet.Projet;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.VentilationManager;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.VentilationManager.Triplet;

public class VentilationManagerTest {

	@Test
	public void testVentilerMontants() {
		// Création de quelques projets fictifs
		Projet projet1 = mock(Projet.class);
		Projet projet2 = mock(Projet.class);

		// Création de quelques ressources fictives
		Ressource ressource1 = mock(Ressource.class);
		Ressource ressource2 = mock(Ressource.class);

		// Date fictive pour les tests
		LocalDate date = LocalDate.now();

		// Définir le comportement des méthodes de montant de projet et de ressource
		when(projet1.getMontantVentilation(date)).thenReturn(1000.0f);
		when(projet2.getMontantVentilation(date)).thenReturn(2000.0f);
		when(ressource1.getMontantVentilation(date)).thenReturn(500.0f);
		when(ressource2.getMontantVentilation(date)).thenReturn(1500.0f);

		// Création d'un gestionnaire de ventilation
		VentilationManager manager = new VentilationManager(date);

		// Création de la liste de projets et de ressources
		List<Projet> projets = new ArrayList<>();
		projets.add(projet1);
		projets.add(projet2);

		List<Ressource> ressources = new ArrayList<>();
		ressources.add(ressource1);
		ressources.add(ressource2);

		// Appel de la méthode à tester
		List<Triplet<Projet, Ressource, Float>> result = manager.ventilerMontants(projets, ressources);

		// Vérifier le résultat
		assertEquals(3, result.size()); // Il devrait y avoir deux triplets dans le résultat

		// Vérifier que les montants attribués sont corrects
		assertEquals(500.0, result.get(0).getThird(), 0.01); // Le montant attribué au premier projet pour la première
																// ressource
		assertEquals(500.0, result.get(1).getThird(), 0.01); // Le montant attribué au deuxième projet pour la deuxième
																// ressource
		assertEquals(1000.0, result.get(2).getThird(), 0.01); // Le montant attribué au deuxième projet pour la deuxième
																// ressource

	}

	@Test
	public void testVentilerMontants_AucunProjet() {
		LocalDate date = LocalDate.now();
		VentilationManager manager = new VentilationManager(date);
		List<Projet> projets = new ArrayList<>();
		List<Ressource> ressources = new ArrayList<>();
		List<Triplet<Projet, Ressource, Float>> result = manager.ventilerMontants(projets, ressources);
		assertEquals(0, result.size()); // Aucun triplet ne devrait être généré car il n'y a pas de projet
	}

	@Test
	public void testVentilerMontants_AucuneRessource() {
		LocalDate date = LocalDate.now();
		VentilationManager manager = new VentilationManager(date);
		Projet projet1 = mock(Projet.class);
		List<Projet> projets = new ArrayList<>();
		projets.add(projet1);
		List<Ressource> ressources = new ArrayList<>();
		List<Triplet<Projet, Ressource, Float>> result = manager.ventilerMontants(projets, ressources);
		assertEquals(0, result.size()); // Aucun triplet ne devrait être généré car il n'y a pas de ressource
	}

	@Test
	public void testVentilerMontants_AucunMontantVentilation() {
		LocalDate date = LocalDate.now();
		VentilationManager manager = new VentilationManager(date);
		Projet projet1 = mock(Projet.class);
		Ressource ressource1 = mock(Ressource.class);
		List<Projet> projets = new ArrayList<>();
		projets.add(projet1);
		List<Ressource> ressources = new ArrayList<>();
		ressources.add(ressource1);
		List<Triplet<Projet, Ressource, Float>> result = manager.ventilerMontants(projets, ressources);
		assertEquals(0, result.size()); // Aucun triplet ne devrait être généré car il n'y a pas de montant de
										// ventilation
	}

}
