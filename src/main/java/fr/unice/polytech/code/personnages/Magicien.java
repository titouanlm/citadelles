package fr.unice.polytech.code.personnages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

/**
 * Cette classe sert à implémenter le pouvoir du Magicien
 * Il peut:
 * ==> soit échanger tout son jeu de cartes contre le jeu d'un autre joueur de son choix (même s'il n'a pas de cartes en main: il prend alors les cartes de l'autre joueur)
 * ==> soit échanger un certain nombre de cartes de sa main contre le même nombre de cartes de la pioche. Les cartes défaussées sont mises sous la pioche
 */

public class Magicien extends Personnage {

    public Magicien(){
        this.numero = 3;
        this.nom = "Magicien";
    }

    public void echangerCartesAvecUnPersonnage(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction){
        if(joueurQuiEffectueAction.getPersonnageACeTour() instanceof Magicien && joueurQuiSubitAction!=joueurQuiEffectueAction){
            //Ajoute les cartes de la victime dans une liste temporaire
            ArrayList<CarteCitadelles> carteEnMainDeVictime = new ArrayList<>(joueurQuiSubitAction.getCartesCitadellesEnMain());
            //Supprime toutes les cartes dans la main de la victime
            joueurQuiSubitAction.getCartesCitadellesEnMain().clear();
            //Ajoute les cartes du bot ayant le magicien dans le deck du bot victime
            joueurQuiSubitAction.getCartesCitadellesEnMain().addAll(joueurQuiEffectueAction.getCartesCitadellesEnMain());
            //Supprime les cartes dans la main du bot ayant le magicien
            joueurQuiEffectueAction.getCartesCitadellesEnMain().clear();
            //Ajoute les cartes du bot victime dans le deck du bot ayant le magicien
            joueurQuiEffectueAction.getCartesCitadellesEnMain().addAll(carteEnMainDeVictime);
        }
    }

    public void echangerCartesAvecPioche(Bot joueurQuiEffectueAction, PiocheCartesCitadelles piocheCartesCitadelles, ArrayList<CarteCitadelles> cartesAEchanger) {
        int nbCartesAPiocher = cartesAEchanger.size();
        if(joueurQuiEffectueAction.getPersonnageACeTour() instanceof Magicien && piocheCartesCitadelles.nbCartesRestantes()>= nbCartesAPiocher) {
            //On rajoute en fin de pioche les cartes qu'on a retiré
            for(CarteCitadelles c : cartesAEchanger){
                piocheCartesCitadelles.ajouterCarteCitadelles(c);
            }
            //Supprime les cartes à échanger avec la pioche du deck du bot
            joueurQuiEffectueAction.getCartesCitadellesEnMain().removeAll(cartesAEchanger);
            //Pioche le nombre de carte que l'on veut échanger et les ajoute dans la main du bot
            for (int i = 0; i < nbCartesAPiocher; i++) {
                joueurQuiEffectueAction.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
            }
        }
    }
}