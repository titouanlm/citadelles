package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.moteur.Moteur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MoteurTest {
    private Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
    private Bot bot2 = new BotSimpliste("Bot 2","\033[33m");
    private Bot bot3 = new BotSimpliste("Bot 3","\033[35m");

    @Test
    void initialiserPartieTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        for (Bot joueur : listeJoueurs) {
            assertEquals(0,joueur.getNbPiece());
            assertEquals(0,joueur.getCartesCitadellesEnMain().size());
        }
        moteurJeu.initialiserPartie();
        for (Bot joueur : listeJoueurs) {
            assertEquals(2,joueur.getNbPiece());
            assertEquals(4,joueur.getCartesCitadellesEnMain().size());
        }
    }

    @Test
    void commencerPartieTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        assertEquals(0,moteurJeu.getListeTours().size());
        moteurJeu.commencerPartie();
        assertTrue(moteurJeu.getListeTours().size()>0);
    }


}

