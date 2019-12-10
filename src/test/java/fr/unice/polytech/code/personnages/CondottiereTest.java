package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.cartes.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CondottiereTest {

    Affichage affichage  = new Affichage(1);
    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    Bot bot1 = new BotAleatoire("Bot 1", "\033[32m", affichage);
    Bot bot2 = new BotAleatoire("Bot 2","\033[33m", affichage);
    ArrayList<Bot> listeJoueurs = new ArrayList<>();

    @BeforeEach
    void setUp() {
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
    }

    @Test
    void effectuerSpecialiteCondottiereTest(){

        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot2.setPersonnageACeTour(new Condottiere(affichage));

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).effectuerSpecialiteCondottiere(bot1);
        }
        if(bot2.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot2.getPersonnageACeTour()).effectuerSpecialiteCondottiere(bot2);
        }

        assertEquals(1,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }


    @Test
    void detruirePlusGrosQuartierEnemieTest(){
        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot2.setPersonnageACeTour(new Marchand(affichage));

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 2 ));
        bot2.getVilleDuBot().construireBatiment(new Donjon(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 ));

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(5,bot1.getNbPiece());
        assertEquals(4, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2, piocheCartesCitadelles, listeJoueurs);
        }

        assertFalse(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(2,bot1.getNbPiece());
        assertEquals(3, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2, piocheCartesCitadelles, listeJoueurs);
        }

        assertFalse(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 2 )));
        assertEquals(1,bot1.getNbPiece());
        assertEquals(2, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2, piocheCartesCitadelles, listeJoueurs);
        }
        assertEquals(1,bot1.getNbPiece());
        assertEquals(1, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2, piocheCartesCitadelles, listeJoueurs);
        }
        assertEquals(1,bot1.getNbPiece());
        assertEquals(1, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    void detruirePlusGrosQuartierEnemieEvequeTest(){
        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot2.setPersonnageACeTour(new Eveque(affichage));

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(5,bot1.getNbPiece());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2, piocheCartesCitadelles, listeJoueurs);
        }

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(5,bot1.getNbPiece());
    }

    @Test
    void detruireQuartierAleatoireEnemieTest(){
        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot2.setPersonnageACeTour(new Marchand(affichage));

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        assertEquals(3, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruireQuartierAleatoireEnemie(bot1, bot2, piocheCartesCitadelles, listeJoueurs);
        }

        assertEquals(2, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    void detruirePlusPetitQuartierEnemieTest(){
        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot2.setPersonnageACeTour(new Marchand(affichage));

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 )));
        assertEquals(5,bot1.getNbPiece());
        assertEquals(3, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusPetitQuartierEnemie(bot1, bot2, piocheCartesCitadelles, listeJoueurs);
        }

        assertFalse(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 )));
        assertEquals(5,bot1.getNbPiece());
        assertEquals(2, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    void recupereQuartierDetruitTest(){
        bot1.ajouterPiece(2);
        bot2.ajouterPiece(2);
        bot1.getVilleDuBot().construireBatiment(new Cimitiere(61,CouleurCarteCitadelles.VIOLET, "Cimetière", 5 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.setPersonnageACeTour(new Eveque(affichage));
        bot2.setPersonnageACeTour(new Marchand(affichage));
        Condottiere c = new Condottiere(affichage);
        CarteCitadelles quartierDetruit = new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 );

        assertTrue(c.recupereQuartierDetruit(listeJoueurs, quartierDetruit));
        assertEquals(1, bot1.getCartesCitadellesEnMain().size());
        assertEquals(1, bot1.getNbPiece());
        assertEquals(0, bot2.getCartesCitadellesEnMain().size());
        assertEquals(2, bot2.getNbPiece());
    }

}
