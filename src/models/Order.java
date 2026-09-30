package models;

import java.util.ArrayList;
import java.sql.Date;

/**
 * la classe pour les commandes des clients
 * Order
 */
public class Order {

    private Client client;
    private ArrayList<Formation> formations;
    private ArrayList<Date> date;

    /**
     * constructeur de la classe Order
     * @param client
     * @param formations
     * @param date la liste des dates ou les formations ont été payé
     */
    public Order(Client client, ArrayList<Formation> formations, ArrayList<Date> date) {
        this.client = client;
        this.formations = formations;
        this.date = date;
    }

    /**    (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String message = "Vous avez payé cher " + client + ": \n";
        int counter = 0;
        while(counter < date.size()){
            message = message + formations.get(counter) + "payé le " + date.get(counter);
            counter++;
        }
        return message;
    }
    
}
