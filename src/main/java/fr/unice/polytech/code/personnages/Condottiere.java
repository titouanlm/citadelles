package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.CouleurCarteCitadelles;
import fr.unice.polytech.code.Personnage;
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
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles ){
        //System.out.println("Le condottière effectue sa spécialité ! \n");
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Condottiere){
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.ROUGE){
                    joueurQuiEffectueAction.ajouterPiece(1);
                }
            }
        }
        for (Iterator<CarteCitadelles> carteIterator = joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits().iterator(); carteIterator.hasNext();){
            CarteCitadelles carte = carteIterator.next();
            if(joueurQuiSubitAction.getVilleDuBot().getNbBatimentsConstruits()!=8) {
                if (joueurQuiEffectueAction.getNbPiece() > carte.getPoint() && carte.getPoint() != 1) {
                    carteIterator.remove();
                    joueurQuiEffectueAction.retirerPiece(carte.getPoint() - 1);
                }
                if (carte.getPoint() == 1) {
                    carteIterator.remove();
                }
            }
        }
    }

}
