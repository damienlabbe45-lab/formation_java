package utils;

import java.util.List;
import java.util.Scanner;

public class Utils {
    public static int inputNumber(Scanner input){
        while(!input.hasNextInt()) input.next();
		return input.nextInt();
    }

    public static Double inputDouble(Scanner input){
        while(!input.hasNextDouble()) input.next();
		return input.nextDouble();
    } 

    public static boolean inputBoolean(Scanner input){
        while(!input.hasNextBoolean()) input.next();
		return input.nextBoolean();
    } 

    public static String input(Scanner input , List<String> words){
        String result = input.next();
        while(!words.contains(result)) result = input.next();
        return result;
    }
}
