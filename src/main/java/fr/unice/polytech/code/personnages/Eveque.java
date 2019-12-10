package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Eveque extends Personnage {

    public Eveque(){
        this.numero =5;
        this.nom = "Evèque";
    }

    public void effectuerSpecialiteEveque(Bot joueurQuiEffectueAction) {
        int nbPieceGagnee=0;
        for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
            if(c.getCouleur()== CouleurCarteCitadelles.BLEU){
                joueurQuiEffectueAction.ajouterPiece(1);
                nbPieceGagnee++;
            }
        }
    }
}