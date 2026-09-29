package Buisness;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.Date;

import dao.AppFormation;
import models.Formation;
import utils.Utils;
public class Visitor {

    private static void readFormation(Scanner input, Connection conn){
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
