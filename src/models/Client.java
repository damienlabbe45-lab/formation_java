package models;

/**
 * classe pour les clients
 * Client
 */
public class Client {
    private String nameClient;
    private String firstNameClient;

    /**
     * constructeur de la classe client
     * @param nameClient
     * @param firstNameClient
     */
    public Client(String nameClient, String firstNameClient) {
        this.nameClient = nameClient;
        this.firstNameClient = firstNameClient;
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

    
}
