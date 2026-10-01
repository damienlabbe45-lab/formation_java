package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Parcours;
import models.Formation;

public class AppParcours {


    /**
     * Exécute une requête SQL et instancie une liste d'objets Parcours à partir de toutes les données.
     *
     * @param conn la connexion active à la base de données
      * @return la liste des objets Parcours instanciés
     */
    public static ArrayList<Parcours> requestReadAllParcours(Connection conn){
        ArrayList<Parcours> results = new ArrayList<Parcours>();
        String sql = "SELECT DISTINCT description, price, is_dist, end_date, beginning_date, name_formation, formation_incluant FROM Formation";
        sql = sql + " JOIN Parcours ON Formation_id = formation_incluant JOIN Be  ON formation_incluant = Be.formation_id";
        try (ResultSet result = conn.prepareStatement(sql).executeQuery();){
           while(result.next()) {
            Formation parcours = new Formation(result.getNString(1), 
                    result.getDouble(2), 
                    result.getBoolean(3), 
                    result.getDate(4), 
                    result.getDate(5), 
                    result.getNString(6));
            
            String sql2 = "SELECT description, price, is_dist, end_date, beginning_date, name_formation FROM Formation";
            sql2 = sql2 + " JOIN Parcours ON Formation_id = formation_incluse JOIN Be ON formation_incluse = Be.formation_id";
            sql2 = sql2 + " WHERE formation_incluant = ?";
            
            ArrayList<Formation> resultsFormation = new ArrayList<Formation>();


            try (PreparedStatement requestFormation = conn.prepareStatement(sql2)){

                requestFormation.setInt(1, result.getInt(7));
                ResultSet resultFormation = requestFormation.executeQuery();

                while(resultFormation.next())
                    {

                        resultsFormation.add(new Formation(resultFormation.getNString(1), 
                    resultFormation.getDouble(2),
                     resultFormation.getBoolean(3), 
                    resultFormation.getDate(4), 
                    resultFormation.getDate(5), 
                    resultFormation.getNString(6)));
                    }
            } catch (SQLException e) {
                System.err.println(e);
            }

             results.add(new Parcours(parcours, resultsFormation));
           }
           
        }
        catch (SQLException e) {
            System.err.println(e);
        }
       return results;
    }
    
}
