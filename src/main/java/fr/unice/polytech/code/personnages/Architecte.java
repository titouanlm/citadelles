package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe permet d'implémenter la spécialité de l'architecte ==> piocher deux cartes Quartiers supplémentaires au début de son tour
 * Il peut donc prendre en tout trois cartes ou deux cartes et deux pièces d'or
 * Il peut construire jusqu'à trois quartiers d'un coup
 */

public class Architecte extends Personnage {

    public Architecte(){
        this.numero = 7;
        this.nom = "Architecte";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction,PiocheCartesCitadelles piocheCartesCitadelles) {

    }
}
