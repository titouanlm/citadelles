package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.moteur.Moteur;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.*;

public class Main {

    public static void main(String... args) {
        Affichage affichage = new Affichage(10000);

        for(int i=0 ; i<affichage.getNbParties(); i++){
            Bot bot1 = new BotTricheur("Bot 1", "\033[35m");
            Bot bot2 = new BotFairPlay("Bot 2", "\033[34m");
            Bot bot3 = new BotFairPlay("Bot 3", "\033[36m");
            Bot bot4 = new BotAleatoire("Bot 4", "\033[33m");

            ArrayList<Bot> listeJoueurs = new ArrayList<>();
            listeJoueurs.add(bot1);
            listeJoueurs.add(bot2);
            listeJoueurs.add(bot3);
            listeJoueurs.add(bot4);

            Collections.shuffle(listeJoueurs);
            Moteur moteurJeu = new Moteur(listeJoueurs);
            moteurJeu.lancerUnePartie();

            Arbitre arbitre = new Arbitre();
            arbitre.compteLesPoints(listeJoueurs);
            arbitre.determineJoueurGagnant(listeJoueurs);
            affichage.incrementerNbPointsDesBotsEnMoyenne(listeJoueurs);
            affichage.incrementerNbVictoireDuBot(arbitre.getJoueurGagnant());
        }

        affichage.afficherResultats();
    }
}
