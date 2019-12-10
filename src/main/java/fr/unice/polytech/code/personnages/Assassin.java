package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;

/**
 * Cette classe consiste à implémenter la spécialité de l'assassin
 * Il assassine un personnage et ce dernier ne jouera pas durant ce tour
 */

public class Assassin extends Personnage {

    public Assassin(){
        this.numero =1;
        this.nom = "Assassin";
    }

    public void effectuerSpecialiteAssassin(Personnage personnageAAssassiner, ArrayList<Bot> listeJoueurs) {
        if(!(personnageAAssassiner instanceof Assassin) && personnageAAssassiner!=null){
            Bot joueurAAssassiner= this.botQuiPossede(personnageAAssassiner, listeJoueurs);
            if(joueurAAssassiner!=null) {
                joueurAAssassiner.setPersonnageACeTour(null);
            }
        }
    }
}