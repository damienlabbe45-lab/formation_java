package test;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.AppFormation;
import models.Formation;

import java.sql.Connection;


/**
 * Classe d'exécution contenant les scénarios de test pour la gestion des articles.
 */
public class TestFormation {
    /**
	 * la liste de tout les tests de requêtes sql;
	 * d'abord une insertion puis un update
	 * @param conn la connexion à la base de donnée
	 */
	public static void testRequest(Connection conn){
        AppFormation formation = new AppFormation(conn);
		formation.fileRequest();
        AppFormation.request(conn, "INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( ? ,? ,?)",
        "disque dur externe 890 To", "SATA", 34.0);
        
		AppFormation.requestNoDao(conn, 
			"SELECT concat('l''article ', Description,' a comme marque ', Brand,' et coûte ', UnitaryPrice ,' euros.') FROM T_articles");
	
		AppFormation.request(conn, "UPDATE T_Articles SET UnitaryPrice = ? WHERE Description = ? AND Brand = ? AND UnitaryPrice = ?",
		30.0, "disque dur externe 890 To", "SATA", 34.0);


		AppFormation.requestNoDao(conn, 
			"SELECT concat('l''article ', Description,' a comme marque ', Brand,' et coûte ', UnitaryPrice ,' euros.') FROM T_articles");
	
		AppFormation.request(conn, "DELETE FROM T_articles WHERE Description = ? AND Brand = ? AND UnitaryPrice = ?", 
		"disque dur externe 890 To", "SATA", 30.0);

		AppFormation.requestNoDao(conn, 
			"SELECT concat('l''article ', Description,' a comme marque ', Brand,' et coûte ', UnitaryPrice ,' euros.') FROM T_articles");
        ArrayList<Formation> articles= AppFormation.requestDao(conn, "SELECT Description, Brand, UnitaryPrice FROM T_articles");
        System.out.println("voici la liste des Articles instanciés:");

        for(Formation article:articles)System.out.println(article);
        }

	
    /**
     * Point d'entrée principal du programme.
     * Établit la connexion à MariaDB, exécute le script SQL de structure puis appelle testRequest.
     *
     * @param args les arguments transmis en ligne de commande (non utilisés)
     * @throws Exception en cas d'erreur de chargement de classe ou d'exécution
     */

        public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:8080/Formation?allowMultiQueries=true",
                "Formation", "hvD!yuCrq2ZMZZi/")) {
            System.out.println("Connexion réussie !");
            testRequest(conn);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

    