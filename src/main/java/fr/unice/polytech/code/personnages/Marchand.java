package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe permet d'implémenter le pouvoir du Marchand
 * Il reçoit au début de son tour une pièce d'or supplémentaire
 * Il reçoit une pièce d'or par quartier marchand (vert) dans sa cité
 */

public class Marchand extends Personnage {

    public Marchand(Affichage affichage){
        super(affichage);
        this.numero =6;
        this.nom = "Marchand";
    }

    public void effectuerSpecialiteMarchand(Bot joueurQuiEffectueAction) {
        int nbPieceGagnee=0;
        for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
            if(c.getCouleur()== CouleurCarteCitadelles.VERT){
                joueurQuiEffectueAction.ajouterPiece(1);
                nbPieceGagnee++;
            }
        }
        affichage.afficherDetails("Récupère "  + nbPieceGagnee + " pièces bonus grâce aux quartiers marchands.");
    }
}