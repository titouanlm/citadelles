package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotFairPlay;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AssassinTest {

    @Test
    void effectuerSpecialiteAssassin() {
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m");
        Bot bot2 = new BotFairPlay("Bot 2","\033[33m");
        Bot bot3 = new BotFairPlay("Bot 3","\033[33m");

        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Magicien());
        bot3.setPersonnageACeTour(new Voleur());

        assertNotNull(bot1.getPersonnageACeTour());
        assertNotNull(bot2.getPersonnageACeTour());
        assertNotNull(bot3.getPersonnageACeTour());

        if(bot1.getPersonnageACeTour() instanceof Assassin){
            ((Assassin) bot1.getPersonnageACeTour()).effectuerSpecialiteAssassin(bot1.getPersonnageACeTour(), listeJoueurs);
            ((Assassin) bot1.getPersonnageACeTour()).effectuerSpecialiteAssassin(new Magicien(), listeJoueurs);
            ((Assassin) bot1.getPersonnageACeTour()).effectuerSpecialiteAssassin(null, listeJoueurs);
        }

        assertNotNull(bot1.getPersonnageACeTour());
        assertNull(bot2.getPersonnageACeTour());
        assertNotNull(bot3.getPersonnageACeTour());
    }
}