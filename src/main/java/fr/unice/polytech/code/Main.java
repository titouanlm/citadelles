package fr.unice.polytech.code;

import java.util.*;

public class Main {

    public static void main(String... args) {
        Affichage affichage = new Affichage();

        for(int i=0 ; i<1000; i++){
            Bot bot1 = new BotSimpliste("Bot 1", "\033[36m");
            Bot bot2 = new BotSimpliste("Bot 2", "\033[35m");
            Bot bot3 = new BotSimpliste("Bot 3", "\033[33m");
            Bot bot4 = new BotIntelligent("Bot 4", "\033[34m");

            ArrayList<Bot> listeJoueurs = new ArrayList<>();
            listeJoueurs.add(bot1);
            listeJoueurs.add(bot2);
            listeJoueurs.add(bot3);
            listeJoueurs.add(bot4);

            Moteur moteurJeu = new Moteur(listeJoueurs);
            moteurJeu.lancerUnePartie();

            Arbitre arbitre = new Arbitre();
            arbitre.compteLesPoints(listeJoueurs);
            arbitre.determineJoueurGagnant(listeJoueurs);
            //System.out.println(arbitre); //Affichage du vainqueur
            affichage.incrementerNbVictoireDuBot(arbitre.getJoueurGagnant());
            affichage.NbDePointDesBotsEnMoyenne(listeJoueurs);
        }


        affichage.afficherResultats();

    }
}
