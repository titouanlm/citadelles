package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe consiste à implémenter la spécialité de l'assassin
 * Il assassine un personnage et ce dernier ne jouera pas durant ce tour
 */

public class Assassin extends Personnage {

    public Assassin(){
        this.numero =1;
        this.nom = "Assassin";
    }

    public void effectuerSpecialiteAssassin(Personnage personnageAAssassiner) {
        if(!(personnageAAssassiner instanceof Assassin)){
            Bot joueurAssassine= null; // A faire
            if(joueurAssassine!=null){
                joueurAssassine.setPersonnageACeTour(null);
            }
        }
    }
}