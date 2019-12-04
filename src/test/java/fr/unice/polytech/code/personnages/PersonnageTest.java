package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonnageTest {
    Affichage affichage  = new Affichage(1);

    @Test
    void botQuiPossedeTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m", null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m", null);
        Bot bot3 = new BotAleatoire("Bot 3","\033[33m", null);

        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        bot1.setPersonnageACeTour(new Roi(affichage));
        bot2.setPersonnageACeTour(new Marchand(affichage));
        bot3.setPersonnageACeTour(new Eveque(affichage));

        assertEquals(bot1, bot1.getPersonnageACeTour().botQuiPossede(new Roi(affichage) , listeJoueurs));
        assertEquals(bot2, bot1.getPersonnageACeTour().botQuiPossede(new Marchand(affichage) , listeJoueurs));
        assertEquals(bot3, bot1.getPersonnageACeTour().botQuiPossede(new Eveque(affichage) , listeJoueurs));
        assertNull(bot1.getPersonnageACeTour().botQuiPossede(null, listeJoueurs));

    }

}