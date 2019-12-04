package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.moteur.Moteur;

import java.util.*;

public class Main {

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("(a) Afficher 1 partie ou (b) Afficher des statistiques sur 1000 parties ?");
        String reponse = sc.nextLine();
        Affichage affichage;

        if(reponse.equals("a")){
            affichage  = new Affichage(1);
            affichage.setModeDetails(true);
        }else{
            affichage = new Affichage(1000);
        }

        for(int i=0 ; i<affichage.getNbParties(); i++){
            Bot bot1 = new BotTricheur("Bot 1", "\033[35m",affichage);
            Bot bot2 = new BotFairPlay("Bot 2", "\033[34m",affichage);
            Bot bot3 = new BotAleatoire("Bot 3", "\033[36m",affichage);
            Bot bot4 = new BotAleatoire("Bot 4", "\033[33m",affichage);
            Bot bot5 = new BotAleatoire("Bot 5", "\033[32m",affichage);

            ArrayList<Bot> listeJoueurs = new ArrayList<>();
            listeJoueurs.add(bot1);
            listeJoueurs.add(bot2);
            listeJoueurs.add(bot3);
            listeJoueurs.add(bot4);
            listeJoueurs.add(bot5);

            Collections.shuffle(listeJoueurs);
            Moteur moteurJeu = new Moteur(listeJoueurs,affichage);
            moteurJeu.lancerUnePartie();

            Arbitre arbitre = new Arbitre(affichage);
            arbitre.compteLesPoints(listeJoueurs);
            arbitre.determineJoueurGagnant(listeJoueurs);
            affichage.incrementerNbPointsDesBotsEnMoyenne(listeJoueurs);
            affichage.incrementerNbVictoireDuBot(arbitre.getJoueurGagnant());
        }

        affichage.afficherResultats();
    }
}
