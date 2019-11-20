package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bot.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe implémente le pouvoir du Voleur
 * Il annonce quel personnage il vole (il lui prendra toutes ses pièces d'or)
 * Le Voleur ne peut voler ni l'Assassin, ni le personnage que l'Assassin a assassiné.
 */

public class Voleur extends Personnage {

    public Voleur(){
        this.numero =2;
        this.nom = "Voleur";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles) {
        if(!(joueurQuiSubitAction.getPersonnageACeTour()instanceof Assassin) && joueurQuiSubitAction!=joueurQuiEffectueAction && joueurQuiSubitAction.getPersonnageACeTour()!=null){
            joueurQuiEffectueAction.ajouterPiece(joueurQuiSubitAction.getNbPiece());
            joueurQuiSubitAction.retirerPiece(joueurQuiSubitAction.getNbPiece());
        }
    }
}
