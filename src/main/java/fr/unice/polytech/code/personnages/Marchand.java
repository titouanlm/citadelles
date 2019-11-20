package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe permet d'implémenter le pouvoir du Marchand
 * Il reçoit au début de son tour une pièce d'or supplémentaire
 * Il reçoit une pièce d'or par quartier marchand (vert) dans sa cité
 */

public class Marchand extends Personnage {

    public Marchand(){
        this.numero =6;
        this.nom = "Marchand";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction , PiocheCartesCitadelles piocheCartesCitadelles) {
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Marchand){
            int nbPieceRecoltee=0;
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.VERT){
                    joueurQuiEffectueAction.ajouterPiece(1);
                    nbPieceRecoltee+=1;
                }
            }
            System.out.println("Récupère " + nbPieceRecoltee + " pièces bonus grâce aux quartier(s) marchand(s) qu'il possède.");
        }
    }
}
