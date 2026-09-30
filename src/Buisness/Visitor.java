package Buisness;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.Date;

import dao.AppClient;
import dao.AppFormation;
import dao.AppParcours;
import models.Client;
import models.Formation;
import models.Parcours;
import utils.Utils;


public class Visitor {

        /**
     *méthode pour afficher à l'utilisateur les formations sur la console et lui permettre de filtrer selon ses propres critères.
     * @param input scanner instancié
     * @param conn la connexion active à la base de données
     */
    protected static void readFormation(Scanner input, Connection conn){
        System.out.println("Voulez-vous filtrer sur le présentiel, le distanciel ou pas du tout?");
        String resultIsDist = Utils.input(input,new ArrayList <>(Arrays.asList("présentiel", "distanciel", "pas du tout")));
        String name = null;
        String description = null;
        Double price = null;
        Date endDate = null;
        Date beginningDate = null;
        boolean isDist;
        System.out.println(" si vous voulez filtrer sur le nom de la formation tapez true sinon tapez false");
        if(Utils.inputBoolean(input)){
            System.out.println("mettez ce que vous voulez comme début de nom");
            name = input.next();
        }
        System.out.println(" si vous voulez filtrer sur la description de la formation tapez true sinon tapez false");
        if(Utils.inputBoolean(input)){
            System.out.println("mettez ce que vous voulez comme début de description");
            description = input.next();
        }
        System.out.println(" si vous voulez filtrer sur le prix de la formation tapez true sinon tapez false");
        if(Utils.inputBoolean(input)){
            System.out.println("mettez ce que vous voulez comme prix");
            price = Utils.inputDouble(input);
        }
        System.out.println(" si vous voulez filtrer sur la date de fin de la formation");
        if(Utils.inputBoolean(input)){
            System.out.println("mettez ce que vous voulez comme date");
            endDate = Utils.inputDate(input);
        }
        System.out.println(" si vous voulez filtrer sur la date de début de la formation");
        if(Utils.inputBoolean(input)){
            System.out.println("mettez ce que vous voulez comme date");
            beginningDate = Utils.inputDate(input);
        }
        ArrayList <Formation> results;
        if(!resultIsDist.contains("pas du tout")){
            isDist = resultIsDist.contains("distanciel");
            results = AppFormation.requestReadFormation(conn, name,description,isDist, endDate,beginningDate,price);
        }
        else{
            results = AppFormation.requestReadFormation(conn, name,description, endDate,beginningDate,price);
        }
        for(Formation formation: results) System.out.println(formation);
    }

    /**
     *méthode pour afficher à l'utilisateur toutes les formations sur la console.
     * @param conn la connexion active à la base de données
     */
    protected static void readAllFormation(Connection conn){
        ArrayList<Formation> results = AppFormation.requestReadAllFormation(conn);
        for(Formation formation: results) System.out.println(formation);
    }

    /**
     *méthode pour afficher à l'utilisateur touts les parcours sur la console.
     * @param conn la connexion active à la base de données
     */
    protected static void readAllParcours(Connection conn){
        ArrayList<Parcours> results = AppParcours.requestReadAllParcours(conn);
        for(Parcours parcours: results) System.out.println(parcours);
    }

    private static boolean connected(Connection conn , Scanner input){
        System.out.println("Veillez taper votre identifiant");
        String addressemail = input.next();
        System.out.println("Veillez taper votre mot de passe");
        String password = input.next();
        int identifiant = AppClient.connected(conn, addressemail, password);
        if(identifiant < 0) {
            System.out.println("votre adresse mail ou votre mot de passe ou les 2 ont été mal écrit");
            return false;
        }
        Client client = AppClient.readClientConnected(conn, identifiant);
        System.out.println("Bienvenue cher " + client);
        if(AppClient.isDirector(conn, identifiant)){
            Director.interfaceVisitor(input, conn, client);
        }
        else{
            InterfaceClient.interfaceVisitor(input, conn, client);
        }
        return true;

    }

    /**
     * méthode d'interface utilisateur pour appeler de façon plus simple les méthodes de la classe et de celles qui en hériteront.
     * à faire surcharger obligatoirement si on veut rajouter ou dimunier des méthodes dans cette interface.
     * @param input scanner instancié
     * @param conn la connexion active à la base de données
     */
    public static void interfaceVisitor(Scanner input, Connection conn){
        String message = "voulez-vous 1 - voir toutes les formations, 2 voir les formations mais avec vos crirères";
        message = message + ", 3 quitter cette interface, 4 voir tout les parcours, 5 se connecter? choissisez en tapant le numéro";
        System.out.println(message);
        int number = Utils.inputNumber(input);
        while (number != 3) {
            if(number == 1)readAllFormation(conn);
            if(number == 2)readFormation(input, conn);
            if(number == 4) readAllParcours(conn);
            if(number == 5 && connected(conn, input)) break;
            System.out.println(message);
            number = Utils.inputNumber(input);
            
        }
    }
}
