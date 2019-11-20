package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe permet d'implémenter la spécialité de l'Evèque
 * Il reçoit une pièce d'or par quartier religieux (bleu) dans sa cité
 * Il ne peut pas être attaqué par le Condottiere
 */

public class Eveque extends Personnage {

    public Eveque(){
        this.numero =5;
        this.nom = "Evèque";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles) {
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Eveque){
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.BLEU){
                    joueurQuiEffectueAction.ajouterPiece(1);
                }
            }
        }
    }
}
