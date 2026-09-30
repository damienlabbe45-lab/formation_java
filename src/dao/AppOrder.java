package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import models.Client;
import models.Formation;
import models.Order;

public class AppOrder {
    /**
     * Exécute une requête SQL et instancie les commandes du client à partir de toutes les données.
     *
     * @param conn la connexion active à la base de données
     * @param client le client 
     * @return les commandes du client instanciés
     */
    public static Order requestReadClientOrder(Connection conn, Client client){
        Order results = new Order(null, null, null);
        String sql = "SELECT description, price, is_dist, end_date, beginning_date, name_formation, date_order FROM Formation JOIN Be ";
        sql = sql + " ON Formation.formation_id = Be.formation_id JOIN Order_ ON Order.formation_id = Formation.formation_id";
        sql = sql + " WHERE client_id = ?";
        try (PreparedStatement request = conn.prepareStatement(sql);){
            request.setInt(1, client.toInt());
            ResultSet result = request.executeQuery();
            ArrayList<Formation> formations= new ArrayList<Formation>();
            ArrayList<Date> date = new ArrayList<Date>();
           while(result.next()) {
            formations.add(new Formation(result.getNString(1), 
                    result.getDouble(2), 
                    result.getBoolean(3), 
                    result.getDate(4), 
                    result.getDate(5), 
                    result.getNString(6)));
            date.add(result.getDate(7));
           }
           results = new Order(client, formations, date);
           
        }
        catch (SQLException e) {
            System.err.println(e);
        }
       return results;
    }


}
