package models;

/**
 * classe pour les clients
 * Client
 */
public class Client {
    private String nameClient;
    private String firstNameClient;
    private int identifiant;

    /**
     * constructeur de la classe client
     * @param nameClient
     * @param firstNameClient
     */
    public Client(String nameClient, String firstNameClient, int identifiant) {
        this.nameClient = nameClient;
        this.firstNameClient = firstNameClient;
        this.identifiant = identifiant;
    }

    /**    (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return nameClient + " " + firstNameClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public int toInt() {
        return identifiant;
    }
}
