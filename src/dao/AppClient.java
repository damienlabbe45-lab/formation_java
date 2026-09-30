package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import models.Client;

public class AppClient {

    /**
     * Exécute une requête SQL et renvoie l'identifiant de l'utilisateur 
     *
     * @param conn la connexion active à la base de données
     * @return l'identifiant de la base de donnée de l'utilisateur
     */
    public static int connected(Connection conn, String identifiant, String password){
        int result;
        String sql = "SELECT user_id FROM User WHERE addressemail = ? and password = ?";
        try (PreparedStatement request = conn.prepareStatement(sql)){
            request.setNString(1, identifiant);
            request.setNString(2, password);
            result = request.executeQuery().getInt(1);
        }
        catch(SQLException e){
            result = -10;
        }
        return result;
    } 


     /**
     * Exécute une requête SQL et renvoie la classe Client de l'Utilisateur à partir de l'identifiant de la base de donnée de l'utilisateur 
     * elle a été obtenue par la méthode connected ci au dessus
     *
     * @param conn la connexion active à la base de données
     * 
     * @return la classe client de l'utilisateur
     */
    public static Client readClientConnected(Connection conn, int identifiantClient){
        Client client = new Client(null, null);
        String sql = "SELECT name_client, firstname_client FROM Client WHERE client_id = ?";
        try (PreparedStatement request = conn.prepareStatement(sql)){
            request.setInt(1, identifiantClient);
            ResultSet results = request.executeQuery();
            client = new Client(results.getNString(1), results.getNString(2));
        }
        catch(SQLException e){
            System.err.println(e);
        }
        return client;
    }
    
}
