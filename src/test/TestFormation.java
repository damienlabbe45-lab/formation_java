package test;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.App;
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
        App.fileRequest(conn);
        ArrayList<Formation> results = AppFormation.requestReadAllFormation(conn);
        for(Formation formation: results) System.out.println(formation);
    }
	
    /**
     * Point d'entrée principal du programme.
     * Établit la connexion à MariaDB, exécute le script SQL de structure puis appelle testRequest.
     *
     * @param args les arguments transmis en ligne de commande (non utilisés)
     * @throws Exception en cas d'erreur de chargement de classe ou d'exécution
     */

        public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Formation?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true",
                "Formation", "K05lust-CQO6mogq")) {
            System.out.println("Connexion réussie !");
            testRequest(conn);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

    