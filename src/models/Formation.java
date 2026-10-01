package models;

import java.sql.Date;

/**
 * classe pour les formations.
 * Formation
 */
public class Formation {
    private String description;
    private Double price;
    private boolean isDist;
    private Date endDate;
    private Date beginningDate;
    private String nameFormation;


    /**
     * Constructeur de la classe Formation.
     *
     * @param description   la description de la formation
     * @param price         le prix unitaire de la formation
     * @param isDist        si c'est en présentiel ou distanciel
     * @param endDate       la date de fin de la formation
     * @param beginningDate la date de début de la formation
     * @param nameFormation le nom de la formation
     */
   public Formation(String description, Double price, boolean isDist, Date endDate, Date beginningDate,
        String nameFormation) {
    this.description = description;
    this.price = price;
    this.isDist = isDist;
    this.endDate = endDate;
    this.beginningDate = beginningDate;
    this.nameFormation = nameFormation;
}

/**
     * Retourne une représentation textuelle de la formation.
     *
     * @return une chaîne décrivant la formation
     */
    @Override    
    public String toString(){
        String message = "La formation " + nameFormation + " commence le " + beginningDate + " et finit le " + endDate + ". Elle est ";
        if(isDist)  message = message + "en distanciel."; else message = message + "en présentielle." ;
        message = message + " Elle coûte " + price + " euros";
        return message + ". " + description + "\n";
        
    }

    /**
     * Retourne le prix de la formation.
     *
     * @return le prix sous forme de Double
     */
    public Double toDouble(){
        return price;
    }

    public String getNameFormation() {
        return nameFormation;
    }

    
    
}
