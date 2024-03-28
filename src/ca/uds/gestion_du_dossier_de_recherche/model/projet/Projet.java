package ca.uds.gestion_du_dossier_de_recherche.model.projet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.*;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;

public class Projet {

    /* ====================
        Attributs de base
     ===================== */
    private Long id;
    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<Ressource> equipe;
    private List<LigneBudgetaire> lignesBudgetaires;
    private double financement;
    private Map<Ressource, List<LocalDate>> ressources;


/* ====================
      	  Constructeur
 	===================== */
    
    public Projet(String Titre) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = "";
    	this.dateDebut = null;
    	this.dateFin = null;
    	this.equipe = new ArrayList<Ressource>();
    	this.lignesBudgetaires = new ArrayList<LigneBudgetaire>();
    	this.financement = 0d;
    }
    
    public Projet(String Titre,String description) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = description;
    	this.dateDebut = null;
    	this.dateFin = null;
    	this.equipe = new ArrayList<Ressource>();
    	this.lignesBudgetaires = new ArrayList<LigneBudgetaire>();
    	this.financement = 0d;
    }
    
    public Projet(String Titre,String description,LocalDate dateDebut,LocalDate dateFin) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = description;
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.equipe = new ArrayList<Ressource>();
    	this.lignesBudgetaires = new ArrayList<LigneBudgetaire>();
    	this.financement = 0d;
    }
    
    public Projet(String Titre,String description,LocalDate dateDebut,LocalDate dateFin,List<LigneBudgetaire> lignes) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = description;
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.equipe = new ArrayList<Ressource>();
    	this.lignesBudgetaires = lignes;
    	this.financement = 0d;
    	this.CalculMontant(LocalDate.now());
    }
    
    /* ====================
        Getters & Setters
    ===================== */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<Ressource> getEquipe() {
        return equipe;
    }
    
    public Ressource getOneRessources(int indice) {
    	return this.equipe.get(indice);
    }

    public void setEquipe(List<Ressource> equipe) {
        this.equipe = equipe;
    }

    public double getFinancement() {
        return financement;
    }

    private void setFinancement(double financement) {
        this.financement = financement;
    }
    
    public List<LigneBudgetaire> getAllLigneBudgetaires(){
    	return this.lignesBudgetaires;
    }
    
    public LigneBudgetaire getLigneBudgetaireIndices(int indices){
    	return this.lignesBudgetaires.get(indices);
    }

    public Map<Ressource, List<LocalDate>> getRessources() { return ressources; }

    public void setRessources(Map<Ressource, List<LocalDate>> ressources) { this.ressources = ressources; }

    /* ====================
       Methodes optionnelles
     ===================== */

    //Methode :

    public void addLigneBudgetaire(LigneBudgetaire lignes) {
    	if(lignes != null) {
    		this.lignesBudgetaires.add(lignes);
    		this.CalculMontant(LocalDate.now());
    	}
    }
    
    public void removeLigneBudgetaire(LigneBudgetaire lignes) {
    	if(lignes != null && this.lignesBudgetaires.contains(lignes) == true) {
    		this.lignesBudgetaires.remove(lignes);
    		this.CalculMontant(LocalDate.now());
    	}
    }
    
    public void AddRessources(Ressource ressource) {
    	if(ressource!= null)
    		this.equipe.add(ressource);
    }
    
    public void RemoveRessouces(Ressource ressource) {
    	if(ressource != null && this.equipe.contains(ressource) == true)
    		this.equipe.remove(ressource);
    }
    
    public void CalculMontant(LocalDate date) {
    	this.setFinancement(0.0);
    	for(LigneBudgetaire lignes : lignesBudgetaires) {
    		this.financement += lignes.getMontantLigne(date);
    	}
    }

    public void DateLimiteDepenses() {
        //Pour chaque ligne budgetaire
        //  Comparé les date de fin imposé par l'UBR avec celle d'ojd
        //  SI < à un certain palier
        //      Afficher une alerte
        //  FIN SI
        //FIN POUR
    	
    	for (LigneBudgetaire ligne : this.lignesBudgetaires) {
    		for(UBR ubr : ligne.getUbrs()) {
    			if(ubr.getDateFin().minusDays(10).isBefore(LocalDate.now())) {
    				//afficher une alerte
    				System.out.println("Le montant founit par l'UBR " + ubr.getOrganisme().getNom() + " d'un motant total de " + ubr.getMontant(ligne) + " dollars expire bientôt");
    				//actuellement sur console mais doit être sur la vue plus tard
    			}
    		}
    	}
    	
    }

    public void Updateressource() {
        //Supprime les ressource qui ne travaille plus sur le rojet et qui n'ont pas besoin d'être payé
    	//POUR chaque ressources dans la liste ou map :
    	// 		on compare ça date de fin à la date d'aujourd'hui
    	//		SI la date de fin est antérieur à aujourd'hui
    	//			On supprime la ressource de la map ou liste
    	//		FIN SI
    	//FIN POUR

        //Supprime les ressources qui ne travaillent plus sur le projet et qui n'ont pas besoin d'être payées
        this.equipe.removeIf(ressource -> ressource.getDateFin() != null && ressource.getDateFin().isBefore(LocalDate.now()));
    }

    public void addRessourceWithDate(Ressource ressource, LocalDate dateDebut, LocalDate dateFin) {
        List<LocalDate> dates = Arrays.asList(dateDebut, dateFin);
        this.ressources.put(ressource, dates);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Projet{" +
                "id=" + id +
                ", titre=" + titre +
                ", description=" + description +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", equipe=" + equipe +
                ", financement=" + financement +
                '}';
    }
}