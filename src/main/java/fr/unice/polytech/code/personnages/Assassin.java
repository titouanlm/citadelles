package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.Personnage;
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

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles) {
        if(joueurQuiSubitAction!=joueurQuiEffectueAction){
            joueurQuiSubitAction.setPersonnageACeTour(null);
        }
    }
}
