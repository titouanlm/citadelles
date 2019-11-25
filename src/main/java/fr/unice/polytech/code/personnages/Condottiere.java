package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.Iterator;

/**
 * Cette classe sert à implémenter le pouvoir du Condottiere
 * Il reçoit une pièce d'or par quartier militaire (rouge) dans sa cité
 * À la fin de son tour, il peut attaquer une cité pour y détruire un quartier de son choix.
 * Il peut détruire gratuitement un quartier de coût 1, ou peut détruire un quartier au coût plus élevé en payant le coût de ce quartier - 1
 * Il ne peut pas attaquer une cité déjà terminée, avec ses huit quartiers.
 */

public class Condottiere extends Personnage {

    public Condottiere(){
        this.numero =8;
        this.nom = "Condottiere";
    }

    public void effectuerSpecialiteCondottiere(Bot joueurQuiEffectueAction){
        for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
            if(c.getCouleur()== CouleurCarteCitadelles.ROUGE){
                joueurQuiEffectueAction.ajouterPiece(1);
            }
        }
    }

    public void detruirePlusGrosQuartierEnemie(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction){
        if(!(joueurQuiSubitAction.getPersonnageACeTour()instanceof Eveque) && joueurQuiEffectueAction!=joueurQuiSubitAction
                && joueurQuiSubitAction.getVilleDuBot().getNbBatimentsConstruits()<8){
            CarteCitadelles quartierADetruire=null;
            int nbPointMax=0;
            for(CarteCitadelles quartier : joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits()){
                if(nbPointMax<quartier.getPoint() && joueurQuiEffectueAction.getNbPiece()>= quartier.getPoint()-1){
                    quartierADetruire=quartier;
                    nbPointMax=quartier.getPoint();
                }
            }
            if(quartierADetruire!=null){
                joueurQuiSubitAction.getVilleDuBot().detruireQuartier(quartierADetruire);
                joueurQuiEffectueAction.retirerPiece(quartierADetruire.getPoint()-1);
            }
        }
    }

    public void detruireQuartierAleatoireEnemie(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction){
        if(!(joueurQuiSubitAction.getPersonnageACeTour()instanceof Eveque) && joueurQuiSubitAction.getVilleDuBot().getNbBatimentsConstruits()<8){
            int nbBatConstruits = joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits().size();
            if(nbBatConstruits>0){
                int indiceQuartierADetruire = (int)(Math.random() * nbBatConstruits);
                CarteCitadelles quartierADetruire = joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits().get(indiceQuartierADetruire);
                int coutDestruction = quartierADetruire.getPoint()-1;
                if(coutDestruction<=joueurQuiEffectueAction.getNbPiece()){
                    joueurQuiSubitAction.getVilleDuBot().detruireQuartier(quartierADetruire);
                    joueurQuiEffectueAction.retirerPiece(coutDestruction);
                }
            }
        }
    }

    public void detruirePlusPetitQuartierEnemie(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction){
        if(joueurQuiSubitAction!=null && !(joueurQuiSubitAction.getPersonnageACeTour()instanceof Eveque) && joueurQuiEffectueAction!=joueurQuiSubitAction && joueurQuiSubitAction.getVilleDuBot().getNbBatimentsConstruits()<8){
            CarteCitadelles quartierADetruire=null;
            int nbPointMin=10;
            for(CarteCitadelles quartier : joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits()){
                if(nbPointMin>quartier.getPoint() && joueurQuiEffectueAction.getNbPiece()>= quartier.getPoint()-1){
                    quartierADetruire=quartier;
                    nbPointMin=quartier.getPoint();
                }
            }
            if(quartierADetruire!=null){
                joueurQuiSubitAction.getVilleDuBot().detruireQuartier(quartierADetruire);
                joueurQuiEffectueAction.retirerPiece(quartierADetruire.getPoint()-1);
            }
        }
    }
}