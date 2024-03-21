import java.time.LocalDate;
import java.util.List;
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

    public void setFinancement(double financement) {
        this.financement = financement;
    }
    
    public List<LigneBudgetaire> getAllLigneBudgetaires(){
    	return this.lignesBudgetaires;
    }
    
    public LigneBudgetaire getLigneBudgetaireIndices(int indices){
    	return this.lignesBudgetaires.get(indices);
    }
    
    

    /* ====================
       Methodes optionnelles
     ===================== */

    //Methode :

    public void addLigneBudgetaire(LigneBudgetaire lignes) {
    	if(lignes != null) {
    		this.lignesBudgetaires.add(lignes);
    		this.CalculMontant();
    	}
    }
    
    public void removeLigneBudgetaire(LigneBudgetaire lignes) {
    	if(lignes != null && this.lignesBudgetaires.contains(lignes) == true) {
    		this.lignesBudgetaires.remove(lignes);
    		this.CalculMontant();
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
    
    public void CalculMontant() {    	
    	for(LigneBudgetaire lignes : lignesBudgetaires) {
    		this.financement += lignes.getMontantLigne();
    	}
    }

    public void DateLimiteDepenses() {
        //Pour chaque ligne budgetaire
        //  Comparé les date de fin imposé par l'UBR avec celle d'ojd
        //  SI < à un certain palier
        //      Afficher une alerte
        //  FIN SI
        //FIN POUR
    }

    public void Updateressource() {
        //Supprime les ressource qui ne travaille plus sur le rojet et qui n'ont pas besoin d'être payé
    	//POUR chaque ressources dans la liste ou map : 
    	// 		on compare ça date de fin à la date d'aujourd'hui
    	//		SI la date de fin est antérieur à aujourd'hui
    	//			On supprime la ressource de la map ou liste
    	//		FIN SI
    	//FIN POUR
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