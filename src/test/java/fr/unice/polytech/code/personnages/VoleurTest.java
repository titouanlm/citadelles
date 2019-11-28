package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotFairPlay;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VoleurTest {
    Affichage affichage  = new Affichage(1);
    @Test
    void effectuerSpecialiteVolerUnAssassin() {
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", null);
        Bot bot2 = new BotFairPlay("Bot 2","\033[33m", null);

        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Voleur());

        bot1.ajouterPiece(2);
        bot2.ajouterPiece(0);

        assertNotNull(bot1.getPersonnageACeTour());
        assertNotNull(bot2.getPersonnageACeTour());

        if(bot2.getPersonnageACeTour() instanceof Voleur){
            ((Voleur) bot2.getPersonnageACeTour()).effectuerSpecialiteVoleur(bot2, bot1.getPersonnageACeTour(), listeJoueurs);
            ((Voleur) bot2.getPersonnageACeTour()).effectuerSpecialiteVoleur(bot2, new Assassin(), listeJoueurs);
        }

        assertEquals(0, bot2.getNbPiece());
        assertEquals(2,bot1.getNbPiece() );
    }

    @Test
    void effectuerSpecialiteVolerUnPersonnageAssassine() {
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", null);
        Bot bot2 = new BotFairPlay("Bot 2","\033[33m", null);

        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        bot2.setPersonnageACeTour(new Voleur());

        bot1.ajouterPiece(2);
        bot2.ajouterPiece(0);

        assertNull(bot1.getPersonnageACeTour());
        assertNotNull(bot2.getPersonnageACeTour());

        if(bot2.getPersonnageACeTour() instanceof Voleur){
            ((Voleur) bot2.getPersonnageACeTour()).effectuerSpecialiteVoleur(bot2, bot1.getPersonnageACeTour(), listeJoueurs);
            ((Voleur) bot2.getPersonnageACeTour()).effectuerSpecialiteVoleur(bot2, null, listeJoueurs);
        }

        assertEquals(0, bot2.getNbPiece());
        assertEquals(2, bot1.getNbPiece() );
    }

    @Test
    void effectuerSpecialiteVolerUnPersonnage() {
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", null);
        Bot bot2 = new BotFairPlay("Bot 2","\033[33m", null);

        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        bot1.setPersonnageACeTour(new Voleur());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.ajouterPiece(1);
        bot2.ajouterPiece(3);

        assertNotNull(bot1.getPersonnageACeTour());
        assertNotNull(bot2.getPersonnageACeTour());

        if(bot1.getPersonnageACeTour() instanceof Voleur){
            ((Voleur) bot1.getPersonnageACeTour()).effectuerSpecialiteVoleur(bot1, new Marchand(), listeJoueurs);
        }

        assertEquals(4, bot1.getNbPiece());
        assertEquals(0, bot2.getNbPiece() );
    }
}