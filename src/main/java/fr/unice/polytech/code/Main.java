package fr.unice.polytech.code;

import java.util.ArrayList;

public class Main {

    public static void main(String... args) {
        Bot bot1 = new Bot("Bot1");
        Bot bot2 = new Bot("Bot2");
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);listeJoueurs.add(bot2);

        Moteur moteurJeu = new Moteur();
        moteurJeu.lancerUnePartie(listeJoueurs);

        Arbitre arbitre = new Arbitre();
        arbitre.compteLesPoints(listeJoueurs);
        System.out.println(arbitre); //Affichage du vainqueur
    }
}
