package models;

import java.util.ArrayList;

/**
 * la classe pour les commandes des clients, en fonction de ce qu'on veut, on aurait pu faire un simple hasmap.
 * Order
 */
public class Order {

    private Client client;
    private ArrayList<Formation> formations;

    /**
     * constructeur de la classe Order
     * @param client
     * @param formations
     */
    public Order(Client client, ArrayList<Formation> formations) {
        this.client = client;
        this.formations = formations;
    }

    /**    (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String message = "Vous avez payé cher " + client + ": \n";
        for(Formation formation:formations)message = message + formation;
        return message;
    }
    
}
