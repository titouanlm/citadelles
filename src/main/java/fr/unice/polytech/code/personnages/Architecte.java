package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe permet d'implémenter la spécialité de l'architecte ==> piocher deux cartes Quartiers supplémentaires au début de son tour
 * Il peut donc prendre en tout trois cartes ou deux cartes et deux pièces d'or
 * Il peut construire jusqu'à trois quartiers d'un coup
 */

public class Architecte extends Personnage {

    public Architecte(Affichage affichage){
        super(affichage);
        this.numero = 7;
        this.nom = "Architecte";
    }

    public void effectuerSpecialiteArchitecte(Bot joueurPossedantArchitecte, PiocheCartesCitadelles piocheCartesCitadelles) {
        for(int i=0; i<2; i++){
            joueurPossedantArchitecte.strategieConstruction(piocheCartesCitadelles);
        }
    }
}