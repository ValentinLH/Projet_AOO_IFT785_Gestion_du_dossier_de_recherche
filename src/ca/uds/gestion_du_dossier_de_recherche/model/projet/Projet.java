public class Projet {

    /* ====================
        Attributs de base
     ===================== */
    private Long id;
    private string titre;
    private string description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<Ressources> equipe;
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

    public string getTitre() {
        return titre;
    }

    public void setTitre(string titre) {
        this.titre = titre;
    }

    public string getDescription() {
        return description;
    }

    public void setDescription(string description) {
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

    public List<Ressources> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Ressources> equipe) {
        this.equipe = equipe;
    }

    public double getFinancement() {
        return financement;
    }

    public void setFinancement(double financement) {
        this.financement = financement;
    }

    /* ====================
       Methodes optionnelles
     ===================== */

    //Methode :
    void CalculMontant() {
        //POUR chaque ligne budgetaire
        //  Prendre le montant de cette ligne
        //  Ajouter au montant total
        //FIN POUR
    }

    void DateLimiteDepenses() {
        //Pour chaque ligne budgetaire
        //  Comparé les date de fin imposé par l'UBR avec celle d'ojd
        //  SI < à un certain palier
        //      Afficher une alerte
        //  FIN SI
        //FIN POUR
    }

    void Updateressource() {
        //Supprime les ressource qui ne travaille plus sur le rojet et qui n'ont pas besoin d'être payé
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