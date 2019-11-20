package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bot.Bot;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe sert à implémenter le pouvoir du Roi
 * Il reçoit une pièce d'or par quartier noble (jaune) dans sa cité
 */

public class Roi extends Personnage {

    public Roi(){
        this.numero =4;
        this.nom = "Roi";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction , PiocheCartesCitadelles piocheCartesCitadelles) {
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Roi){
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.JAUNE){
                    joueurQuiEffectueAction.ajouterPiece(1);
                }
            }
        }

    }
}
