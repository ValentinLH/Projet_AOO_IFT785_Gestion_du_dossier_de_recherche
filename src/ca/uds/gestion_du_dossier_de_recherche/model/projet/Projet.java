package ca.uds.gestion_du_dossier_de_recherche.model.projet;

import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.LigneBudgetaire;
import ca.uds.gestion_du_dossier_de_recherche.model.ligne_budgetaire.UBR;
import ca.uds.gestion_du_dossier_de_recherche.model.ressource.Ressource;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Projet implements Ventilable {

    /* ====================
        Attributs de base
     ===================== */
    private Long id;
    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<LigneBudgetaire> lignesBudgetaires;
    private Map<Ressource, List<LocalDate>> ressources;


/* ====================
      	  Constructeur
 	===================== */
    
    public Projet(String Titre,LocalDate dateDebut,LocalDate dateFin) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = "";
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.lignesBudgetaires = new ArrayList<LigneBudgetaire>();
    	this.ressources = new HashMap<Ressource, List<LocalDate>>();
    }
    
    public Projet(String Titre,String description,LocalDate dateDebut,LocalDate dateFin) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = description;
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.lignesBudgetaires = new ArrayList<LigneBudgetaire>();
    	this.ressources = new HashMap<Ressource, List<LocalDate>>();
    }
    
    public Projet(String Titre,String description,LocalDate dateDebut,LocalDate dateFin,List<LigneBudgetaire> lignes) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = description;
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.lignesBudgetaires = lignes;
    	this.calculMontant(LocalDate.now());
    }
    
    public Projet(String Titre,String description,LocalDate dateDebut,LocalDate dateFin, Map<Ressource, List<LocalDate>> ressources) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = description;
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.lignesBudgetaires = new ArrayList<LigneBudgetaire>();
    	this.ressources = ressources;
    }
    
    public Projet(String Titre,String description,LocalDate dateDebut,LocalDate dateFin,List<LigneBudgetaire> lignes,Map<Ressource, List<LocalDate>> ressources) {
    	this.id = 0l;
    	this.titre = Titre;
    	this.description = description;
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.lignesBudgetaires = lignes;
    	this.ressources = ressources;
    	this.calculMontant(LocalDate.now());
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

    @Override
    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
    public List<LigneBudgetaire> getAllLigneBudgetaires(){
    	return this.lignesBudgetaires;
    }
    
    public LigneBudgetaire getLigneBudgetaireIndices(int indices){
    	return this.lignesBudgetaires.get(indices);
    }

    public Map<Ressource, List<LocalDate>> getRessources() { 
    	return ressources;
    }

    public List<LocalDate> getDateForOneRessources(Ressource ressource) {
    	return this.ressources.get(ressource);
    }
    
    public void setRessources(Map<Ressource, List<LocalDate>> ressources) { 
    	this.ressources = ressources; 
    }

    /* ====================
       Methodes optionnelles
     ===================== */

    //Methode :

    public void addLigneBudgetaire(LigneBudgetaire lignes) {
    	if(lignes != null) {
    		this.lignesBudgetaires.add(lignes);
    		this.calculMontant(LocalDate.now());
    	}
    }
    
    public void removeLigneBudgetaire(LigneBudgetaire lignes) {
    	if(lignes != null && this.lignesBudgetaires.contains(lignes) == true) {
    		this.lignesBudgetaires.remove(lignes);
    		this.calculMontant(LocalDate.now());
    	}
    }
        
    public void addRessourceWithDate(Ressource ressource, LocalDate dateDebut, LocalDate dateFin) {
        
    	LocalDate affectationDebut = dateDebut;
    	LocalDate affectationFin = dateFin;
    	
    	if(dateDebut!= null && dateDebut.isBefore(getDateDebut())) {
    		affectationDebut = getDateDebut();
    	}
    	
    	if(dateFin != null && dateFin.isAfter(getDateFin())) {
    		affectationFin = getDateFin();
    	}
    	
    	List<LocalDate> dates = Arrays.asList(affectationDebut, affectationFin);
        this.ressources.put(ressource, dates);
    }
    
    public void removeRessouces(Ressource ressource) {
    	if(ressource != null && this.ressources.containsKey(ressource)== true)
    		this.ressources.remove(ressource);
    }
    
    @Override
	public float getMontantVentilation(LocalDate date) {
		return calculMontant(date);
	}
    
    public float calculMontant(LocalDate date) {
    	float financement =0.0f;
    	for(LigneBudgetaire lignes : lignesBudgetaires) {
    		financement += lignes.getMontantLigne(date);
    	}
    	return financement;
    }

    public void dateLimiteDepenses(int nbJour) {
        //Pour chaque ligne budgetaire
        //  Comparé les date de fin imposé par l'UBR avec celle d'ojd
        //  SI < à un certain palier
        //      Afficher une alerte
        //  FIN SI
        //FIN POUR
    	
    	for (LigneBudgetaire ligne : this.lignesBudgetaires) {
    		for(UBR ubr : ligne.getUbrs()) {
    			if(ubr.getDateFin().minusDays(nbJour).isBefore(LocalDate.now())) {
    				//afficher une alerte
    				System.out.println("Le montant founit par l'UBR " + ubr.getOrganisme().getNom() + " d'un motant total de " + ubr.getMontant(ligne) + " dollars expire bientôt");
    				//actuellement sur console mais doit être sur la vue plus tard
    			}
    		}
    	}
    	
    }

    public void updateRessource() {
        //Supprime les ressource qui ne travaille plus sur le projet et qui n'ont pas besoin d'être payé
    	//POUR chaque ressources dans la liste ou map :
    	// 		on compare sa date de fin à la date d'aujourd'hui
    	//		SI la date de fin est antérieur à aujourd'hui
    	//			On supprime la ressource de la map ou liste
    	//		FIN SI
    	//FIN POUR

        //Supprime les ressources qui ne travaillent plus sur le projet et qui n'ont pas besoin d'être payées
    
    	Set<Ressource> keys = ressources.keySet();
    	Set<Ressource> del = new HashSet<>();
    	
    	for(Ressource ressource : keys)
    	{
	    	List<LocalDate> listDate = ressources.get(ressource);
	    	if(listDate.get(1).isBefore(LocalDate.now()) == true) {
	    		del.add(ressource);
	    	}
    	}
    	
    	for (Ressource ressource : del)
    		ressources.remove(ressource);
        
    }


    public Map<Ressource, Double> calculSalaireRessources() {
        Map<Ressource, Double> salaires = new HashMap<>();

        for (Map.Entry<Ressource, List<LocalDate>> entry : this.ressources.entrySet()) {
            Ressource ressource = entry.getKey();
            List<LocalDate> periodes = entry.getValue();

            // Vérifier et ajuster la période d'affectation selon la durée du contrat
            LocalDate dateDebutAffectation = periodes.get(0).isBefore(ressource.getDebut_contrat()) ? ressource.getDebut_contrat() : periodes.get(0);
            LocalDate dateFinAffectation = periodes.get(1).isAfter(ressource.getFin_contrat()) ? ressource.getFin_contrat() : periodes.get(1);

            if (dateDebutAffectation.isBefore(dateFinAffectation.plusDays(1))) { // Date de début est avant la date de fin
                long joursEffectifs = ChronoUnit.DAYS.between(dateDebutAffectation, dateFinAffectation.plusDays(1)); // Inclut la date de fin

                // Convertir les jours en heures de travail effectives
                double heuresEffectives = joursEffectifs * (ressource.getHeures_hebdo() / 7.0);
                // Calculer le salaire en fonction des heures effectives
                double salaire = heuresEffectives * ressource.getTaux_horaire();

                salaires.put(ressource, salaire);
            }
        }

        return salaires;
    }




    @java.lang.Override
    public java.lang.String toString() {
        return "Projet{" +
                "id=" + id +
                ", titre=" + titre +
                ", description=" + description +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", ressources=" + ressources +
                '}';
    }

	
}