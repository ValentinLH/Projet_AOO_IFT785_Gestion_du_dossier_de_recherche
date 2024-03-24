package ca.uds.gestion_du_dossier_de_recherche.BDD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * G�re les entit�s du package de la base de donn�es
 * La classe utilise JPA (Java Persistence API) pour traiter la connexion avec la base de donn�es
 */
public class BDDConnection {

	private static EntityManagerFactory emf;
	public static EntityManager em;
	
	/**
	 * Initialise la connexion avec l'unit� de persistence par default, "gestionEtudiant"
	 */
	public static void init() {
		emf = Persistence.createEntityManagerFactory("gestionEtudiant");
		em = emf.createEntityManager();
		
	}
	
	/**
	 * Initialise la connexion avec l'unit� de persistence pass�e en param�tre
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