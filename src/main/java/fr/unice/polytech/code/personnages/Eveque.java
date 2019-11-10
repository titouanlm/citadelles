package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.CouleurCarteCitadelles;
import fr.unice.polytech.code.Personnage;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Eveque extends Personnage {

    public Eveque(){
        this.numero =5;
        this.nom = "Evèque";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles) {
        //System.out.println("L'évèque effectue sa spécialité ! \n");
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Eveque){
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.BLEU){
                    joueurQuiEffectueAction.ajouterPiece(1);
                }
            }
        }
    }
}
