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
     * Exécute une requête SQL et instancie une liste d'objets Formation à partir des données filtrés selon les critères de l'utilisateur.
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
        String description, Boolean isDist, Date endDate, Date beginningDate, Double price
    ){
        ArrayList<Formation> results = new ArrayList<Formation>();
        String sql = "SELECT description, price, is_dist, end_date, beginning_date, name_formation FROM Formation JOIN Be ";
        sql = sql + " ON Formation.formation_id = Be.formation_id";

        sql = utils.Utils.requestPersonifyFormation(sql, nameFormation, description,isDist, endDate, beginningDate, price);

		try (PreparedStatement request = conn.prepareStatement(sql);) {
            if(sql.contains("?")){
                int count = 1;
            if( isDist != null)request.setBoolean(count++, isDist);

            if(description != null)request.setString(count++, "%" + description);
            
            if(nameFormation != null)request.setString(count++, "%" + nameFormation);
                
            if(price != null)request.setDouble(count++, price);
            
            if(endDate != null)request.setDate(count++, endDate);
                
            if(beginningDate != null)request.setDate(count++, beginningDate);
                
            }
            
            ResultSet result = request.executeQuery();

           if (!result.next()) {
    System.out.println("Il n'y a aucun résultat.\n");
    results = AppFormation.requestReadAllFormation(conn);
            }
            else{do {
        results.add(new Formation(
            result.getNString(1), 
            result.getDouble(2), 
            result.getBoolean(3), 
            result.getDate(4), 
            result.getDate(5), 
            result.getNString(6)
        ));
    } while (result.next());
}

        } catch (SQLException e) {
            System.err.println(e);
        }
        System.out.println("\n");
        return  results;

    }
    /**
     * Exécute une requête SQL et instancie une liste d'objets Formation à partir de toutes les données.
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
