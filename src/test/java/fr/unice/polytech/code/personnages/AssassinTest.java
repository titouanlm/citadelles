package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.bots.BotFairPlay;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AssassinTest {

    @Test
    void effectuerSpecialiteAssassin() {
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        Affichage affichage  = new Affichage(1);
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m", affichage);
        Bot bot2 = new BotFairPlay("Bot 2","\033[33m", affichage);
        Bot bot3 = new BotFairPlay("Bot 3","\033[33m", affichage);

        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        bot1.setPersonnageACeTour(new Assassin(affichage));
        bot2.setPersonnageACeTour(new Magicien(affichage));
        bot3.setPersonnageACeTour(new Voleur(affichage));

        assertNotNull(bot1.getPersonnageACeTour());
        assertNotNull(bot2.getPersonnageACeTour());
        assertNotNull(bot3.getPersonnageACeTour());

        if(bot1.getPersonnageACeTour() instanceof Assassin){
            ((Assassin) bot1.getPersonnageACeTour()).effectuerSpecialiteAssassin(bot1.getPersonnageACeTour(), listeJoueurs);
            ((Assassin) bot1.getPersonnageACeTour()).effectuerSpecialiteAssassin(new Magicien(affichage), listeJoueurs);
            ((Assassin) bot1.getPersonnageACeTour()).effectuerSpecialiteAssassin(null, listeJoueurs);
        }

        assertNotNull(bot1.getPersonnageACeTour());
        assertNull(bot2.getPersonnageACeTour());
        assertNotNull(bot3.getPersonnageACeTour());
    }
}