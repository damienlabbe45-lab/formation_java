package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import models.Formation;

public class AppFormation{

/**
     * Exécute une requête SQL et instancie une liste d'objets Formation à partir des données.
     *
     * @param conn          la connexion active à la base de données
     * @param nameFormation le nom de la formation
     * @param description   la description de la formation
     * @param endDate       la date de fin de la formation
     * @param beginningDate la date de début de la formation
     * @param price         le prix de la formation
     * @return la liste des objets Formation instanciés
     */
    public static ArrayList<Formation> requestReadFormation(Connection conn, String nameFormation,
        String description, Date endDate, Date beginningDate, Double price
    ){
        ArrayList<Formation> results = new ArrayList<Formation>();
        String sql = "SELECT description, price, is_dist, end_date, beginning_date, name_formation FROM Formation JOIN Be ";
        sql = sql + " ON Formation.formation_id = Be.formation_id";
		try (PreparedStatement request = conn.prepareStatement(sql);) {
            if(!description.equals("") || !nameFormation.equals("") 
                || price != null 
            || endDate != null 
            || beginningDate != null){
                int count = 0;
                sql = sql + " WHERE";
                if(!description.equals("")){
                sql = sql + " OR description LIKE ?";
                request.setString(count, "%" + description);
                count++;
            }
            if(!nameFormation.equals("")){
                sql = sql + " OR name_formation LIKE ?";
                request.setString(count, "%" + nameFormation);
                count++;
            }
            if(price != null){
                sql = sql + " OR price <= ?";
                request.setDouble(count, price);
                count++;
            }
            if(endDate != null){
                sql = sql + " OR end_date == ?";
                request.setDate(count, endDate);
                count++;
            }
            if(beginningDate != null){
                sql =sql + " OR beginning_date == ?";
                request.setDate(count, beginningDate);
                count++;
            }
            }
            
            ResultSet result = request.executeQuery();

            if (result != null) 
                {while(result.next()) results.add(new Formation(result.getNString(1), 
                    result.getDouble(2), result.getBoolean(3), 
                    result.getDate(4), result.getDate(5), result.getNString(6)));
                }
            else
            {
                System.out.println("il y a aucun résultat. \n");
                results = AppFormation.requestReadAllFormation(conn);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        System.out.println("\n");
        return  results;

    }

    	/**
     * Exécute une requête SQL et instancie une liste d'objets Formation à partir des données.
     *
     * @param conn          la connexion active à la base de données
     * @param nameFormation le nom de la formation
     * @param description   la description de la formation
     * @param isDist        si c'est en distanciel ou présentiel
     * @param endDate       la date de fin de la formation
     * @param beginningDate la date de début de la formation
     * @param price         le prix de la formation
     * @return la liste des objets Formation instanciés
     */
    public static ArrayList<Formation> requestReadFormation(Connection conn, String nameFormation,
        String description, boolean isDist, Date endDate, Date beginningDate, Double price
    ){
        ArrayList<Formation> results = new ArrayList<Formation>();
        String sql = "SELECT description, price, is_dist, end_date, beginning_date, name_formation FROM Formation JOIN Be ";
        sql = sql + " ON Formation.formation_id = Be.formation_id WHERE";
		try (PreparedStatement request = conn.prepareStatement(sql);) {
            int count = 1;
            sql = sql + " is_dist = ?";
            request.setBoolean(count, isDist);
            count++;
            if(!description.equals("")){
                sql = sql + " OR description LIKE ?";
                request.setString(count, "%" + description);
                count++;
            }
            if(!nameFormation.equals("")){
                sql = sql + " OR name_formation LIKE ?";
                request.setString(count, "%" + nameFormation);
                count++;
            }
            if(price != null){
                sql = sql + " OR price <= ?";
                request.setDouble(count, price);
                count++;
            }
            if(endDate != null){
                sql = sql + " OR end_date == ?";
                request.setDate(count, endDate);
                count++;
            }
            if(beginningDate != null){
                sql =sql + " OR beginning_date == ?";
                request.setDate(count, beginningDate);
                count++;
            }
            
            
            ResultSet result = request.executeQuery();

            if (result != null) 
                {while(result.next()) results.add(new Formation(result.getNString(1), 
                    result.getDouble(2), result.getBoolean(3), 
                    result.getDate(4), result.getDate(5), result.getNString(6)));
                }
            else
            {
                System.out.println("il y a aucun résultat. \n");
                results = AppFormation.requestReadAllFormation(conn);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        System.out.println("\n");
        return  results;

    }
    /**
     * Exécute une requête SQL et instancie une liste d'objets Formation à partir des données.
     *
     * @param conn la connexion active à la base de données
      * @return la liste des objets Formation instanciés
     */
    public static ArrayList<Formation> requestReadAllFormation(Connection conn){
        ArrayList<Formation> results = new ArrayList<Formation>();
        String sql = "SELECT description, price, is_dist, end_date, beginning_date, name_formation FROM Formation JOIN Be ";
        sql = sql + " ON Formation.formation_id = Be.formation_id";
        try (ResultSet result = conn.prepareStatement(sql).executeQuery();){
           while(result.next()) {
             results.add(new Formation(result.getNString(1), 
                    result.getDouble(2), 
                    result.getBoolean(3), 
                    result.getDate(4), 
                    result.getDate(5), 
                    result.getNString(6)));
           }
           
        }
        catch (SQLException e) {
            System.err.println(e);
        }
       return results;
    }
		
}
