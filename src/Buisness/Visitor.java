package Buisness;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import dao.AppFormation;
import models.Formation;
import utils.Utils;
public class Visitor {

    private static void readFormation(Scanner input, Connection conn){
        
    }

    private static void readAllFormation(Connection conn){
        ArrayList<Formation> results = AppFormation.requestReadAllFormation(conn);
        for(Formation formation: results) System.out.println(formation);
    }

    public static void interfaceVisitor(Scanner input, Connection conn){
        String message = "voulez-vous 1 - voir toutes les formations, 2 voir les formations mais avec vos crirères ";
        message = message + ", 3 quitter cette interface? choissisez en tapant le numéro";
        System.out.println(message);
        int number = Utils.inputNumber(input);
        while (number != 3) {
            if(number == 1)readAllFormation(conn);
            if(number == 2)readFormation(input, conn);
            System.out.println(message);
            number = Utils.inputNumber(input);
            
        }
    }
}
