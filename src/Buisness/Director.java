package Buisness;

import java.sql.Connection;
import java.util.Scanner;

import utils.Utils;
import models.Client;

public class Director extends InterfaceClient{
    
    
    public Director(Connection conn, Scanner input, Client client) {
        super(conn, input, client);
    }

    /**
     * méthode d'interface utilisateur pour appeler de façon plus simple les méthodes de la classe et de celles qui en hériteront.
     * à faire surcharger obligatoirement si on veut rajouter ou dimunier des méthodes dans cette interface.
     */
    @Override 
    public void interfaceVisitor(){
        String message = "voulez-vous 1 - voir toutes les formations, 2 voir les formations mais avec vos crirères";
        message = message + ", 3 quitter cette interface, 4 voir tout les parcours 5 voir vos commandes, 6 voir votre profil?";
        message = message + " choissisez en tapant le numéro";
        int number = -345666567;
        while (number != 3) {
            if(number == 1) readAllFormation();
            if(number == 2) readFormation();
            if(number == 4) readAllParcours();
            if(number == 5) readClientOrders();
            if(number == 6) readProfilClient();
            System.out.println(message);
            number = Utils.inputNumber(input);
            
        }
    }
}