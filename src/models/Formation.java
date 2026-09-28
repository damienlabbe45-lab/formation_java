package models;
public class Formation {
    private String description;
    private String brand;
    private Double price;


    /**
     * Constructeur de la classe Formation.
     *
     * @param description la description de la formation
     * @param brand       la marque de l'article
     * @param price       le prix unitaire de la formation
     */
    public Formation(String description, String brand, Double price) {
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

/**
     * Retourne une représentation textuelle de la formation.
     *
     * @return une chaîne décrivant la formation
     */
    @Override    
    public String toString(){
        return "l'article " + description + " a comme marque " + brand + " et coûte " + price + " euros.";
    }

    /**
     * Retourne le prix de la formation.
     *
     * @return le prix sous forme de Double
     */
    public Double toDouble(){
        return price;
    }
    
}
