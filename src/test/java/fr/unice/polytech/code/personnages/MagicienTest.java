package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotFairPlay;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.cartes.Donjon;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MagicienTest {
    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();


    @Test
    void echangerCartesAvecUnPersonnageTest() {
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m");
        Bot bot2 = new BotFairPlay("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Eveque());
        bot2.setPersonnageACeTour(new Magicien());

        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        ArrayList<CarteCitadelles> deckBot1 = new ArrayList<>(bot1.getCartesCitadellesEnMain());

        assertEquals(deckBot1,bot1.getCartesCitadellesEnMain());
        assertNotEquals(deckBot1,bot2.getCartesCitadellesEnMain());

        if(bot2.getPersonnageACeTour() instanceof Magicien){
            ((Magicien)bot2.getPersonnageACeTour()).echangerCartesAvecUnJoueur(bot2,bot1);
        }
        System.out.println(bot1.getCartesCitadellesEnMain().size());
        System.out.println(bot2.getCartesCitadellesEnMain().size());
        assertNotEquals(deckBot1, bot1.getCartesCitadellesEnMain());
        assertEquals(deckBot1,bot2.getCartesCitadellesEnMain());
    }

    @Test
    void echangerCartesAvecPiocheTest() {
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m");

        bot1.setPersonnageACeTour(new Magicien());

        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));

        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        piocheCartesCitadelles.ajouterCarteCitadelles(new Donjon(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 ));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(15, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));

        assertEquals("Taverne",bot1.getCartesCitadellesEnMain().get(0).getNom());
        assertEquals(4,piocheCartesCitadelles.nbCartesRestantes());

        if(bot1.getPersonnageACeTour() instanceof Magicien){
            ((Magicien)bot1.getPersonnageACeTour()).echangerCartesAvecPioche(bot1,piocheCartesCitadelles, bot1.getCartesCitadellesEnMain());
        }

        assertEquals("Cathédrale",bot1.getCartesCitadellesEnMain().get(0).getNom());
        assertEquals("Donjon",bot1.getCartesCitadellesEnMain().get(1).getNom());
        assertEquals(4,piocheCartesCitadelles.nbCartesRestantes());
        assertEquals("Cour des miracles",piocheCartesCitadelles.getPiocheCC().get(3).getNom());
    }

}