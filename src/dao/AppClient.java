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
            ResultSet results = request.executeQuery();
            results.next();
            result = results.getInt(1);
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
     * @param l'identifiant de l'utilisateur
     * 
     * @return la classe client de l'utilisateur
     */
    public static Client readClientConnected(Connection conn, int identifiantClient){
        Client client = new Client(null, null, identifiantClient);
        String sql = "SELECT name_client, firstname_client FROM Client WHERE client_id = ?";
        try (PreparedStatement request = conn.prepareStatement(sql)){
            request.setInt(1, identifiantClient);
            ResultSet results = request.executeQuery();
            results.next();
            client = new Client(results.getNString(1), results.getNString(2), identifiantClient);
        }
        catch(SQLException e){
            System.err.println(e);
        }
        return client;
    }
    
    /**
     * Exécute une requête SQL et renvoie si c'est un utilisateur directeur ou juste un simple client avec un booleen
     *
     * @param conn la connexion active à la base de données
     * @param l'identifiant de l'utilisateur
     * 
     * @return si c'est true, directeur , si c'est false, Client.
     */
    public static boolean isDirector(Connection conn, int identifiantClient){
        boolean director = false;
        String sql = "SELECT is_director FROM USER WHERE user_id = ?";
        try (PreparedStatement request = conn.prepareStatement(sql)){
            request.setInt(1, identifiantClient);
            ResultSet result = request.executeQuery();
            result.next();
            director = result.getBoolean(1);
        }
        catch(SQLException e){
            System.err.println(e);
        }
        return director;
    }

    public static String readProfilClient(Connection conn, Client client){
        String result = "";
        String sql = "SELECT concat('votre numéro est ', phonenumber, '.\n Votre addresse mail est ', adressemail, ' .\n votre adresse est '";
        sql = sql + ", city, ' ', number, ' ', road, ' ', codepostal, '.\n')";
        sql = sql + "FROM Client JOIN User client_id = user_id JOIN Adress Adress.adress_id = Client.adress_id WHERE client_id = ?";
        try (PreparedStatement request = conn.prepareStatement(sql)){
            request.setInt(1, client.toInt());
            ResultSet results = request.executeQuery();
            results.next();
            result = results.getNString(1);
        }
        catch(SQLException e){
            System.err.println(e);
        }
        return result;
    }
}
