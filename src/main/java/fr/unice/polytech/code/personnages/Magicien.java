package fr.unice.polytech.code.personnages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.Personnage;
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

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles) {
        //System.out.println("Le magicien effectue sa spécialité ! \n");
        Random rand = new Random();
        int value = rand.nextInt(2);
        ArrayList<CarteCitadelles> cartesCitadellesIntermedieaire = new ArrayList<>();
        if (value == 1) {
            for (Iterator<CarteCitadelles> carteIterator = joueurQuiEffectueAction.getCartesCitadellesEnMain().iterator(); carteIterator.hasNext(); ) {
                CarteCitadelles carte = carteIterator.next();
                cartesCitadellesIntermedieaire.add(carte);
                carteIterator.remove();
            }
            for (Iterator<CarteCitadelles> carteIterator = joueurQuiSubitAction.getCartesCitadellesEnMain().iterator(); carteIterator.hasNext(); ) {
                CarteCitadelles carte = carteIterator.next();
                joueurQuiEffectueAction.ajouterCartesCitadellesDansMain(carte);
                carteIterator.remove();
            }
            for (CarteCitadelles carte : cartesCitadellesIntermedieaire) {
                joueurQuiSubitAction.ajouterCartesCitadellesDansMain(carte);
            }
        } else {
            System.out.println(joueurQuiEffectueAction.getCartesCitadellesEnMain().size());
            for (Iterator<CarteCitadelles> carteIterator = joueurQuiEffectueAction.getCartesCitadellesEnMain().iterator(); carteIterator.hasNext(); ) {
                carteIterator.remove();
                break;

            }
            joueurQuiEffectueAction.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
            System.out.println(joueurQuiEffectueAction.getCartesCitadellesEnMain().size());


        }
    }



}

