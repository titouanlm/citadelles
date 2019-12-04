package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe sert à implémenter le pouvoir du Roi
 * Il reçoit une pièce d'or par quartier noble (jaune) dans sa cité
 */

public class Roi extends Personnage {

    public Roi(Affichage affichage){
        super(affichage);
        this.numero =4;
        this.nom = "Roi";
    }

    public void effectuerSpecialiteRoi(Bot joueurQuiEffectueAction) {
        int nbPieceGagnee=0;
        for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
            if(c.getCouleur()== CouleurCarteCitadelles.JAUNE){
                joueurQuiEffectueAction.ajouterPiece(1);
                nbPieceGagnee++;
            }
        }
        affichage.afficherDetails("Récupère " + nbPieceGagnee + " pièces bonus grâce aux quartiers nobles.");
    }
}