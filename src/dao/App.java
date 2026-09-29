import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe principale gérant les opérations sur la base de données pour les articles du magasin.
 */
public class App {

    /**
     * Lit le contenu du fichier SQL de création de structure.
     *
     * @return le contenu du fichier sous forme de chaîne de caractères, ou une chaîne vide en cas d'erreur
     */
    private static String file() {
        try {
            return new String(Files.readAllBytes(Paths.get("database/formation.sql")));
        } catch (IOException e) {
            System.err.println(e);
            return "";
        }
    }

    /**
     * Exécute les instructions SQL contenues dans le fichier de script.
     *
     * @param conn la connexion active à la base de données
     */
    public static void fileRequest(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String sql = file();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

