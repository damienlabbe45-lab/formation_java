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

    /**
     * lis le profil du client sous forme de string
     * @param conn la connexion à la base de donnée
     * @param client le client
     * @return le profil sous forme de texte
     */
    public static String readProfilClient(Connection conn, Client client){
        String result = "";
        String sql = "SELECT concat('votre numéro est ', phonenumber, '.\n Votre addresse mail est ', addressemail, ' .\n votre adresse est '";
        sql = sql + ", city, ' ', number_, ' ', road, ' ', codepostal, '.\n')";
        sql = sql + "FROM Client JOIN User ON client_id = user_id JOIN Adress ON Adress.adress_id = Client.adress_id WHERE client_id = ?";
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

    /**
     * créé un client dans la base de donnée et renvoit ce client sous fore d'instance java
     * @param conn la connexion à la base de donnée
     * @param name le prénom du futur client
     * @param firstname le nom du futur client
     * @param phonenumber le numéro de télé^hone du futur client
     * @param adressmail l'adresse mail du futur client
     * @param password le futur mot de passe du client
     * @param city le nom de la ville du futur client
     * @param number le numéro de rue du futur client
     * @param road la rue ou boulevard de la ville du futur client
     * @param codepostal le codepostal de la ville du futur client
     * @return le nouveau client instancié
     */
    public static Client CreateClient(Connection conn,String name, String firstname, String phonenumber, String adressmail, String password, 
        String city, int number, String road, String codepostal){
            Client result = new Client(null, null, -5);
            String sql = "INSERT INTO User(addressemail, password, is_director) SELECT ?, ? ,false FROM User; SET @user = LAST_INSERT_ID()";
            sql = sql + "INSERT INTO Adress(city, number_, road, codepostal) VALUES (?, ? , ?); SET @adress = LAST_INSERT_ID()";
            sql = sql + "INSERT INTO Client(client_id, name_client, firstname_client, phonenumber, adress_id)";
            sql = sql + " VALUES (user_id, ?, ? , ?) adress_id RETURNING user_id; ";
            try(PreparedStatement request = conn.prepareStatement(sql)){
                request.setNString(1, adressmail);
                request.setNString(2, password);
                request.setNString(3, city);
                request.setNString(5, road);
                request.setNString(6, codepostal);
                request.setNString(7, name);
                request.setNString(8, firstname);
                request.setNString(9, phonenumber);
                request.setInt(4, number);
                ResultSet results = request.executeQuery();
                results.next();
                result = new Client(name,firstname, results.getInt(1));
            }
            catch(SQLException e){
            System.err.println(e);
            }
            return result;
        }

    /**
     * vérifie si une addresse mail est déja présente dans la base de donnée
     * @param conn la connexion à la base de donnée
     * @param adressmail l'adresse mail à tester 
     * @return
     */
    public static boolean isExistAdressMail(Connection conn, String adressmail){
        boolean result = false;
        String sql ="SELECT EXISTS(SELECT 1 FROM User WHERE addressemail = ?);";
        try(PreparedStatement request = conn.prepareStatement(sql)){
                request.setNString(1, adressmail);
                ResultSet results = request.executeQuery();
                results.next();
                result = results.getBoolean(1);
        }
        catch(SQLException e){
            System.err.println(e);
        }
        return result;
    }

}
