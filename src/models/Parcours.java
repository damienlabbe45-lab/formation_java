package models;

import java.util.ArrayList;


/**
 * classe pour le parcours de plusieurs formations. il est à noter que en vrai, on aurait pu juste créé un hasmap et juste garder
 * ce qu'il avait dans le toString mais bon comme c'est un Dao...
 * Parcours
 */
public class Parcours {


    private Formation parcoursName;
    private  ArrayList<Formation> inclus;

    /**
     * constructeur de la classe Parcours
     * @param parcoursName la formation qui regroupe d'autres formations
     * @param inclus       les formations regroupés en une seule formation
     */
    public Parcours(Formation parcoursName, ArrayList<Formation> inclus) {
        this.parcoursName = parcoursName;
        this.inclus = inclus;
    }


        /**
     * méthode toString de Parcours
     * @return la formation regroupant les autres formations et les noms des autres formations
     */
    @Override
    public String toString() {
        String message = parcoursName.toString() + "\n" ;
        for(Formation formation: inclus) message = message + formation.getNameFormation() + "\n";
        return message;
    }
}
