package utils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Utils {

    /**
     * renvoie un nombre entier via le scanner de l'utilisateur
     * @param input scanner instancié
     * @return int 
     */
    public static int inputNumber(Scanner input){
        while(!input.hasNextInt()) input.next();
		return input.nextInt();
    }

    /**
     * renvoie un nombre réel via le scanner de l'utilisateur
     * @param input scanner instancié
     * @return Double 
     */
    public static Double inputDouble(Scanner input){
        while(!input.hasNextDouble()) input.next();
		return input.nextDouble();
    } 

    /**
     * renvoie un boolean via le scanner de l'utilisateur
     * @param input scanner instancié
     * @return boolean
     */
    public static boolean inputBoolean(Scanner input){
        while(!input.hasNextBoolean()) input.next();
		return input.nextBoolean();
    } 

        /**
     * renvoie du texte qui fait parti des mots sélectionnés dans  via le scanner de l'utilisateur
     * @param input scanner instancié
     * @param words une liste de mots ou de plusieurs mots
     * @return String
     */
    public static String input(Scanner input , List<String> words){
        String result = input.next();
        while(!words.contains(result)) result = input.next();
        return result;
    }

    /**
     * renvoie une date à partir des int de l'utilisateur via inputNumber via le scanner de l'utilisateur
     * @param input scanner instancié
     * @return Date
     */
    public static Date inputDate(Scanner input){
        int year = 23456789;
        System.out.println("Veillez saisir l'année entre 1000 et 9999");
        while(year > 10000 || year < 999) year = inputNumber(input);
        System.out.println("Veillez saisir le mois en nombre");
        int mouth = 16;
        while(mouth > 0 || mouth > 12) mouth = inputNumber(input);
        int dayBorn = 31;
        if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) dayBorn = 29;
        else{
            if(mouth== 2){
                dayBorn = 28;
            }
            else{ 
                ArrayList<Integer> mouths = new ArrayList<>(Arrays.asList(1,3, 5, 7, 8, 10, 12));
                if(!mouths.contains(mouth) ){
                    dayBorn = 30;
                }
            }
        }
        int day = 34;
        System.out.println("Veillez saisir le jour en nombre du mois");
        while(day > 0 && day > dayBorn) day = inputNumber(input);
        return Date.valueOf(String.format("%04d-%02d-%02d", year, mouth, day));
    }

    public static String requestPersonifyFormation(String sql, String nameFormation,
        String description, Date endDate, Date beginningDate, Double price){
        if(description != null)sql = sql + " OR description LIKE ?";

        if(nameFormation != null)sql = sql + " OR name_formation LIKE ?";
        
        if(price != null)sql = sql + " OR price <= ?";

        if(endDate != null) sql = sql + " OR end_date == ?";

        if(beginningDate != null) sql =sql + " OR beginning_date == ?";
        
        return sql;
    }
    
}
