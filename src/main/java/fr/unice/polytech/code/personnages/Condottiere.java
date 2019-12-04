package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.cartes.Donjon;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.Iterator;

public class Condottiere extends Personnage {

    public Condottiere(Affichage affichage){
        super(affichage);
        this.numero =8;
        this.nom = "Condottiere";
    }

    public void effectuerSpecialiteCondottiere(Bot joueurQuiEffectueAction){
        int nbPieceGagnee=0;
        for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
            if(c.getCouleur()== CouleurCarteCitadelles.ROUGE){
                joueurQuiEffectueAction.ajouterPiece(1);
                nbPieceGagnee++;
            }
        }
        affichage.afficherDetails("Récupère " + nbPieceGagnee + " pièces bonus grâce aux quartiers militaires.");

    }

    public void detruirePlusGrosQuartierEnemie(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction){
        if(joueurQuiSubitAction!=null && !(joueurQuiSubitAction.getPersonnageACeTour()instanceof Eveque) && joueurQuiEffectueAction!=joueurQuiSubitAction
                && joueurQuiSubitAction.getVilleDuBot().getNbBatimentsConstruits()<8){
            CarteCitadelles quartierADetruire=null;
            int nbPointMax=0;
            for(CarteCitadelles quartier : joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits()){
                if(nbPointMax<quartier.getPoint() && joueurQuiEffectueAction.getNbPiece()>= quartier.getPoint()-1 && !(quartier instanceof Donjon)){
                    quartierADetruire=quartier;
                    nbPointMax=quartier.getPoint();
                }
            }
            if(quartierADetruire!=null){
                int coutDestruction = quartierADetruire.getPoint()-1;
                joueurQuiSubitAction.getVilleDuBot().detruireQuartier(quartierADetruire);
                joueurQuiEffectueAction.retirerPiece(coutDestruction);
                affichage.afficherDetails("Détruit le quartier " + quartierADetruire.getNom() + " de " + joueurQuiSubitAction.getNom() + " pour " + coutDestruction + " pièces.");
                if (joueurQuiSubitAction.contientDansSaMain("Cimitière") && !(joueurQuiSubitAction.getPersonnageACeTour() instanceof Condottiere) ){
                    joueurQuiSubitAction.retirerPiece(1);
                    joueurQuiSubitAction.ajouterCartesCitadellesDansMain(quartierADetruire);
                }
            }
        }
    }

    public void detruireQuartierAleatoireEnemie(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction){
        if(joueurQuiSubitAction!=null && !(joueurQuiSubitAction.getPersonnageACeTour()instanceof Eveque) && joueurQuiSubitAction.getVilleDuBot().getNbBatimentsConstruits()<8){
            int nbBatConstruits = joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits().size();
            if(nbBatConstruits>0){
                int indiceQuartierADetruire = (int)(Math.random() * nbBatConstruits);
                CarteCitadelles quartierADetruire = joueurQuiSubitAction.getVilleDuBot().getBatimentsConstruits().get(indiceQuartierADetruire);
                int coutDestruction = quartierADetruire.getPoint()-1;
                if(coutDestruction<=joueurQuiEffectueAction.getNbPiece() && !(quartierADetruire instanceof Donjon)){
                    joueurQuiSubitAction.getVilleDuBot().detruireQuartier(quartierADetruire);
                    joueurQuiEffectueAction.retirerPiece(coutDestruction);
                    affichage.afficherDetails("Détruit le quartier " + quartierADetruire.getNom() + " de " + joueurQuiSubitAction.getNom() + " pour " + coutDestruction + " pièces.");

                }
                if (quartierADetruire!=null){
                    if (joueurQuiSubitAction.contientDansSaMain("Cimitière") && !(joueurQuiSubitAction.getPersonnageACeTour() instanceof Condottiere) ){
                        joueurQuiSubitAction.retirerPiece(1);
                        joueurQuiSubitAction.ajouterCartesCitadellesDansMain(quartierADetruire);
                    }
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
            if(quartierADetruire!=null && !(quartierADetruire instanceof Donjon)){
                int coutDestruction =quartierADetruire.getPoint()-1;
                joueurQuiSubitAction.getVilleDuBot().detruireQuartier(quartierADetruire);
                joueurQuiEffectueAction.retirerPiece(coutDestruction);
                affichage.afficherDetails("Détruit le quartier " + quartierADetruire.getNom() + " de " + joueurQuiSubitAction.getNom() + " pour " + coutDestruction + " pièces.");
            }
            if (quartierADetruire!=null){
                if (joueurQuiSubitAction.contientDansSaMain("Cimitière") && !(joueurQuiSubitAction.getPersonnageACeTour() instanceof Condottiere) ){
                    joueurQuiSubitAction.retirerPiece(1);
                    joueurQuiSubitAction.ajouterCartesCitadellesDansMain(quartierADetruire);
                }
            }
        }
    }
}