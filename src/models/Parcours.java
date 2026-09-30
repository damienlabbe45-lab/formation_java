package models;

import java.util.ArrayList;



public class Parcours {


    private Formation parcours;
    private  ArrayList<Formation> inclus;


    public Parcours(Formation parcours, ArrayList<Formation> inclus) {
        this.parcours = parcours;
        this.inclus = inclus;
    }

    @Override
    public String toString() {
        String message = parcours.toString() ;
        for(Formation formation: inclus) message = message + formation.getNameFormation() + "\n";
        return message;
    }
}
