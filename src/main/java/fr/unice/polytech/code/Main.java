package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.moteur.Moteur;

import java.util.*;

public class Main {

    public static void main(String... args) {
        System.out.println("Meilleur bot (Bot 1) contre 4 versions du second (Bot 2, Bot 3, Bot 4, Bot 5) :");
        Affichage affichage1 = new Affichage(1000); 
        for(int i=0 ; i<affichage1.getNbParties(); i++){
            Bot bot1 = new BotFairPlay("Bot 1", "\033[35m");
            Bot bot2 = new BotAleatoire("Bot 2", "\033[34m");
            Bot bot3 = new BotAleatoire("Bot 3", "\033[36m");
            Bot bot4 = new BotAleatoire("Bot 4", "\033[33m");
            Bot bot5 = new BotAleatoire("Bot 5", "\033[32m");

            ArrayList<Bot> listeJoueurs = new ArrayList<>();
            listeJoueurs.add(bot1);
            listeJoueurs.add(bot2);
            listeJoueurs.add(bot3);
            listeJoueurs.add(bot4);
            listeJoueurs.add(bot5);

            Collections.shuffle(listeJoueurs);
            Moteur moteurJeu = new Moteur(listeJoueurs);
            moteurJeu.lancerUnePartie();

            Arbitre arbitre = new Arbitre();
            arbitre.compteLesPoints(listeJoueurs);
            arbitre.determineJoueurGagnant(listeJoueurs);
            affichage1.incrementerNbPointsDesBotsEnMoyenne(listeJoueurs);
            affichage1.incrementerNbVictoireDuBot(arbitre.getJoueurGagnant());
        }
        affichage1.afficherResultats();

        System.out.println("\n\nMeilleur bot (BotFairPlay) contre 4 versions de lui même : ");
        Affichage affichage2 = new Affichage(1000);
        for(int i=0 ; i<affichage2.getNbParties(); i++){
            Bot bot1 = new BotFairPlay("Bot 1", "\033[35m");
            Bot bot2 = new BotFairPlay("Bot 2", "\033[34m");
            Bot bot3 = new BotFairPlay("Bot 3", "\033[36m");
            Bot bot4 = new BotFairPlay("Bot 4", "\033[33m");
            Bot bot5 = new BotFairPlay("Bot 5", "\033[32m");

            ArrayList<Bot> listeJoueurs = new ArrayList<>();
            listeJoueurs.add(bot1);
            listeJoueurs.add(bot2);
            listeJoueurs.add(bot3);
            listeJoueurs.add(bot4);
            listeJoueurs.add(bot5);

            Collections.shuffle(listeJoueurs);
            Moteur moteurJeu = new Moteur(listeJoueurs);
            moteurJeu.lancerUnePartie();

            Arbitre arbitre = new Arbitre();
            arbitre.compteLesPoints(listeJoueurs);
            arbitre.determineJoueurGagnant(listeJoueurs);
            affichage2.incrementerNbPointsDesBotsEnMoyenne(listeJoueurs);
            affichage2.incrementerNbVictoireDuBot(arbitre.getJoueurGagnant());
        }
        affichage2.afficherResultats();

    }
}
