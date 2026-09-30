import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Buisness.Visitor;

public class Main {
    public static void main(String[] args) {
        if( args.length > 0) throw new IllegalArgumentException(" pas d'arguments");
        Scanner input = new Scanner(System.in, System.getProperty("sun.stdin.encoding","CP850"));
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Formation?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true",
                "Formation", "K05lust-CQO6mogq")) {
            Visitor.interfaceVisitor(input, conn);
        } catch (SQLException e) {
            System.err.println("une erreur est survenue. \n");
            /**il serait possible le println(e) par un envoi dans un fichier de log .log*/
            System.err.println(e);
        }
        input.close();
        System.out.println("Au plaisir de vous revoir");
    }
}
