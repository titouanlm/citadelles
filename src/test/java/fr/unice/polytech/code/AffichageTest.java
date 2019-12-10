package fr.unice.polytech.code;


import fr.unice.polytech.code.bots.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AffichageTest {

    @Test
    void incrementerNbPointsDesBotsEnMoyenneTest(){

        Affichage affichage=new Affichage(1);

        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");
        Bot bot3 = new BotAleatoire("Bot 3","\033[35m");
        Bot bot4 = new BotAleatoire("Bot 4","\033[35m");
        Bot bot5 = new BotAleatoire("Bot 5","\033[35m");

        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        listeJoueurs.add(bot4);
        listeJoueurs.add(bot5);

        bot1.setNbPoint(10);
        bot2.setNbPoint(12);
        bot3.setNbPoint(5);
        bot4.setNbPoint(6);
        bot5.setNbPoint(3);

        affichage.incrementerNbPointsDesBotsEnMoyenne(listeJoueurs);
        assertEquals(5,affichage.getMapJoueurs().size());

        double NombreDePointsMoyensParPartieBot1= affichage.getMapJoueurs().get(listeJoueurs.get(0).getNom())[1];
        double NombreDePointsMoyensParPartieBot2= affichage.getMapJoueurs().get(listeJoueurs.get(1).getNom())[1];
        double NombreDePointsMoyensParPartieBot3=affichage.getMapJoueurs().get(listeJoueurs.get(2).getNom())[1];
        double NombreDePointsMoyensParPartieBot4=affichage.getMapJoueurs().get(listeJoueurs.get(3).getNom())[1];


        assertTrue(NombreDePointsMoyensParPartieBot2 > NombreDePointsMoyensParPartieBot3);
        assertTrue(NombreDePointsMoyensParPartieBot3 < NombreDePointsMoyensParPartieBot1);
        assertFalse(NombreDePointsMoyensParPartieBot4 > NombreDePointsMoyensParPartieBot1);

    }

    @Test
    void incrementerNbVictoireDuBot(){

        Affichage affichage=new Affichage(1);

        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");
        Bot bot3 = new BotAleatoire("Bot 3","\033[35m");
        Bot bot4 = new BotAleatoire("Bot 4","\033[35m");

        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        listeJoueurs.add(bot4);

        bot1.setNbPoint(10);
        bot2.setNbPoint(12);
        bot3.setNbPoint(5);
        bot4.setNbPoint(6);

        Arbitre arbitre= new Arbitre();
        arbitre.determineJoueurGagnant(listeJoueurs);
        arbitre.determineJoueurGagnant(listeJoueurs);
        affichage.incrementerNbVictoireDuBot(arbitre.getJoueurGagnant());

        int NombreDeVictoireBot2=affichage.getMapJoueurs().get(listeJoueurs.get(1).getNom())[0];

        assertEquals(1, NombreDeVictoireBot2);
    }


}
