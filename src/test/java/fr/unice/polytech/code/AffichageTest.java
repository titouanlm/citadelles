package fr.unice.polytech.code;


import fr.unice.polytech.code.bots.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AffichageTest {

    @Test
    void incrementerNbPointsDesBotsEnMoyenneTest(){

        Affichage affichage=new Affichage(3);

        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m",null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m",null);
        Bot bot3 = new BotAleatoire("Bot 3","\033[35m",null);
        Bot bot4 = new BotAleatoire("Bot 4","\033[35m",null);

        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        listeJoueurs.add(bot4);

        bot1.setNbPoint(10);
        bot2.setNbPoint(12);
        bot3.setNbPoint(5);
        bot4.setNbPoint(6);

        affichage.incrementerNbPointsDesBotsEnMoyenne(listeJoueurs);
        assertEquals(4,affichage.getMapJoueurs().size());

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

        Affichage affichage=new Affichage(3);

        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m",null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m",null);
        Bot bot3 = new BotAleatoire("Bot 3","\033[35m",null);
        Bot bot4 = new BotAleatoire("Bot 4","\033[35m",null);

        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        listeJoueurs.add(bot4);

        bot1.setNbPoint(10);
        bot2.setNbPoint(12);
        bot3.setNbPoint(5);
        bot4.setNbPoint(6);

        Arbitre arbitre= new Arbitre(null);
        arbitre.determineJoueurGagnant(listeJoueurs);
        arbitre.determineJoueurGagnant(listeJoueurs);
        affichage.incrementerNbVictoireDuBot(arbitre.getJoueurGagnant());

        int NombreDeVictoireBot2=affichage.getMapJoueurs().get(listeJoueurs.get(1).getNom())[0];

        assertEquals(1, NombreDeVictoireBot2);
    }


}
