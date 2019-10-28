package fr.unice.polytech.code;

import java.util.ArrayList;

public class Main {

    public static void main(String... args) {
        Bot bot1 = new Bot("Bot 1");
        Bot bot2 = new Bot("Bot 2");
        Bot bot3 = new Bot("Bot 3");
        Bot bot4 = new Bot("Bot 4");

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
        arbitre.compteLespointsFinal(listeJoueurs);
        System.out.println(arbitre); //Affichage du vainqueur
    }
}
