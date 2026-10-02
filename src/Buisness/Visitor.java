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

    protected Connection conn;
    protected Scanner input;

    

        public Visitor(Connection conn, Scanner input) {
        this.conn = conn;
        this.input = input;
    }

        /**
     *méthode pour afficher à l'utilisateur les formations sur la console et lui permettre de filtrer selon ses propres critères.
     */
    protected void readFormation(){
        System.out.println("Voulez-vous filtrer sur le présentiel, le distanciel ou pas du tout?");
        String resultIsDist = Utils.input(input,new ArrayList <>(Arrays.asList("présentiel", "distanciel", "pas du tout")));
        String name = null;
        String description = null;
        Double price = null;
        Date endDate = null;
        Date beginningDate = null;
        Boolean isDist = null;
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
        System.out.println(" si vous voulez filtrer sur la date de fin de la formation tapez true sinon tapez false");
        if(Utils.inputBoolean(input)){
            System.out.println("mettez ce que vous voulez comme date");
            endDate = Utils.inputDate(input);
        }
        System.out.println(" si vous voulez filtrer sur la date de début de la formation tapez true sinon tapez false");
        if(Utils.inputBoolean(input)){
            System.out.println("mettez ce que vous voulez comme date");
            beginningDate = Utils.inputDate(input);
        }
        if(!resultIsDist.contains("pas du tout"))isDist = resultIsDist.contains("distanciel");
        ArrayList <Formation> results = AppFormation.requestReadFormation(conn, name,description,isDist, endDate,beginningDate,price);
        for(Formation formation: results) System.out.println(formation);
    }

    /**
     *méthode pour afficher à l'utilisateur toutes les formations sur la console.
     */
    protected void readAllFormation(){
        ArrayList<Formation> results = AppFormation.requestReadAllFormation(conn);
        for(Formation formation: results) System.out.println(formation);
    }

    /**
     *méthode pour afficher à l'utilisateur touts les parcours sur la console.
     */
    protected void readAllParcours(){
        ArrayList<Parcours> results = AppParcours.requestReadAllParcours(conn);
        for(Parcours parcours: results) System.out.println(parcours);
    }

     /**
     *méthode pour se connecter en tant que Client ou directeur et ne plus être un simple client.
     * @param input scanner instancié
     * @param conn la connexion active à la base de données
     */
    private boolean connected(){
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
        InterfaceClient client1;
        if(AppClient.isDirector(conn, identifiant)){
            client1 = new Director(conn, input, client);
        }
        else{
        client1 = new InterfaceClient(conn, input, client);
        }
        client1.interfaceVisitor();
        return true;

    }

    /**
     * méthode d'interface utilisateur pour appeler de façon plus simple les méthodes de la classe et de celles qui en hériteront.
     * à faire surcharger obligatoirement si on veut rajouter ou dimunier des méthodes dans cette interface.
     * @param input scanner instancié
     * @param conn la connexion active à la base de données
     */
    public void interfaceVisitor(){
        String message = "voulez-vous 1 - voir toutes les formations, 2 voir les formations mais avec vos crirères";
        message = message + ", 3 quitter cette interface, 4 voir tout les parcours, 5 se connecter? choissisez en tapant le numéro";
        int number = -345666567;
        while (number != 3) {
            if(number == 1)readAllFormation();
            if(number == 2)readFormation();
            if(number == 4) readAllParcours();
            System.out.println(message);
            number = Utils.inputNumber(input);
            if(number == 5 && connected()) number = 3;
        }
    }
}
