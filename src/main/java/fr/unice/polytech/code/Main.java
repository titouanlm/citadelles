package fr.unice.polytech.code;

import java.util.*;

public class Main {

    public static void main(String... args) {

        HashMap<String,Integer> listeJoueursGagnants = new HashMap<>();
        listeJoueursGagnants.put("Bot 1",0);
        listeJoueursGagnants.put("Bot 2",0);
        listeJoueursGagnants.put("Bot 3",0);
        listeJoueursGagnants.put("Bot 4",0);

        for(int i=0 ; i<1000; i++){
            Bot bot1 = new Bot("Bot 1", "\033[36m");
            Bot bot2 = new Bot("Bot 2", "\033[35m");
            Bot bot3 = new Bot("Bot 3", "\033[33m");
            Bot bot4 = new Bot("Bot 4", "\033[34m");

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
            listeJoueursGagnants.put(arbitre.getJoueurGagnant().getNom(), listeJoueursGagnants.get(arbitre.getJoueurGagnant().getNom())+1);
        }

        Set<Map.Entry<String, Integer>> set = listeJoueursGagnants.entrySet();
        for (Map.Entry<String, Integer> e : set) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }

    }
}
