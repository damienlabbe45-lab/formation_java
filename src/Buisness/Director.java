package Buisness;

import java.sql.Connection;
import java.util.Scanner;

import utils.Utils;
import models.Client;

public class Director extends InterfaceClient{
    
    
    /**
     * méthode d'interface utilisateur pour appeler de façon plus simple les méthodes de la classe et de celles qui en hériteront.
     * à faire surcharger obligatoirement si on veut rajouter ou dimunier des méthodes dans cette interface.
     * @param input scanner instancié
     * @param conn la connexion active à la base de données
     * @param client le nom et prénom de l'utilisateur
     */
    public static void interfaceVisitor(Scanner input, Connection conn, Client client){
        String message = "voulez-vous 1 - voir toutes les formations, 2 voir les formations mais avec vos crirères";
        message = message + ", 3 quitter cette interface, 4 voir tout les parcours 5 voir vos commandes, 6 voir votre profil?";
        message = message + " choissisez en tapant le numéro";
        int number = -345666567;
        while (number != 3) {
            if(number == 1) readAllFormation(conn);
            if(number == 2) readFormation(input, conn);
            if(number == 4) readAllParcours(conn);
            if(number == 5) readClientOrders(conn, client);
            if(number == 6) readProfilClient(conn, client);
            System.out.println(message);
            number = Utils.inputNumber(input);
            
        }
    }
}