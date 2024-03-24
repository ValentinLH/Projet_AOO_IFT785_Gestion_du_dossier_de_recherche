package ca.uds.gestion_du_dossier_de_recherche.BDD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Gère les entités du package de la base de données
 * La classe utilise JPA (Java Persistence API) pour traiter la connexion avec la base de données
 */
public class BDDConnection {

	private static EntityManagerFactory emf;
	public static EntityManager em;
	
	/**
	 * Initialise la connexion avec l'unité de persistence par default, "gestionEtudiant"
	 */
	public static void init() {
		emf = Persistence.createEntityManagerFactory("gestionEtudiant");
		em = emf.createEntityManager();
		
	}
	
	/**
	 * Initialise la connexion avec l'unité de persistence passée en paramètre
	 * 
	 * @param name
	 */
	public static void init(String name) {
		emf = Persistence.createEntityManagerFactory(name);
		em = emf.createEntityManager();
		
	}	
	
	/**
	 * Ferme les connexions
	 */
	public static void close() {
		em.close();
		emf.close();		
	}
	
}