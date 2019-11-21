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

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles){
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Condottiere){
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.ROUGE){
                    joueurQuiEffectueAction.ajouterPiece(1);
                }
            }
        }
        detuireQuartierEnemie(joueurQuiEffectueAction,joueurQuiSubitAction );
    }

    public void detuireQuartierEnemie(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction){
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Condottiere && !(joueurQuiSubitAction.getPersonnageACeTour()instanceof Eveque) && joueurQuiEffectueAction!=joueurQuiSubitAction){
            CarteCitadelles quartierADetruire=null;
            int nbPointMax=0;
            for(CarteCitadelles quartier : joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits()){
                if(nbPointMax<quartier.getPoint()){
                    quartierADetruire=quartier;
                    nbPointMax=quartier.getPoint();
                }
            }
            if(quartierADetruire!=null){
                joueurQuiSubitAction.getVilleDuBot().detruireQuartier(quartierADetruire);
                joueurQuiEffectueAction.retirerPiece(nbPointMax-1);
            }
        }

    }
}
