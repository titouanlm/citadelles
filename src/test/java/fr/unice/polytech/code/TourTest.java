package fr.unice.polytech.code;

import fr.unice.polytech.code.bot.Bot;
import fr.unice.polytech.code.bot.BotSimpliste;
import fr.unice.polytech.code.moteur.Moteur;
import fr.unice.polytech.code.moteur.Tour;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TourTest {
    private Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
    private Bot bot2 = new BotSimpliste("Bot 2","\033[33m");
    private Bot bot3 = new BotSimpliste("Bot 3","\033[35m");


    @Test
    void appelerJoueurDansLOrdreTest(){

    }

    @Test
    void finPartieTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        Tour tour = new Tour(1, moteurJeu.getPiocheCartesCitadelles(), moteurJeu.getPiocheCartesPersonnage(),listeJoueurs);

        tour.setIndiceJoueurPossedantCourrone();
        tour.setJoueurAyantLeRoi(listeJoueurs.get(1));
        assertFalse(tour.finDuTour());
        for(Bot j : listeJoueurs){
            if(j==tour.getJoueurAyantLeRoi()){
                assertTrue(j.possedeCouronne());
            }else{
                assertFalse(j.possedeCouronne());
            }
        }

        listeJoueurs.get(1).getVilleDuBot().setNbBatimentsConstruits(8);
        assertTrue(tour.finDuTour());
    }


    @Test
    void setIndiceJoueurPossedantCourroneTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        Tour tour = new Tour(1, moteurJeu.getPiocheCartesCitadelles(), moteurJeu.getPiocheCartesPersonnage(),listeJoueurs);
        listeJoueurs.get(1).setPossedeCouronne(true);
        tour.setIndiceJoueurPossedantCourrone();
        assertEquals(1,tour.getIndiceJoueurPossedantCouronne());
    }

    @Test
    void defausserCartesPersonnagePourLeTourTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        Moteur moteurJeu = new Moteur(listeJoueurs);
        Tour tour = new Tour(1,  moteurJeu.getPiocheCartesCitadelles(), moteurJeu.getPiocheCartesPersonnage(),listeJoueurs);
        tour.defausserCartesPersonnagePourLeTour();
        assertNotEquals(4, tour.getPersonnageDefausseVisible());
        assertEquals(6,moteurJeu.getPiocheCartesPersonnage().getPiocheCP().size());
    }

    @Test
    void determinerChoixPiocherOuPieceTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        Moteur moteurJeu = new Moteur(listeJoueurs);
        Tour tour = new Tour(1,  moteurJeu.getPiocheCartesCitadelles(), moteurJeu.getPiocheCartesPersonnage(),listeJoueurs);

        for(int i=0; i<65; i++){
            moteurJeu.getPiocheCartesCitadelles().piocher();
        }

        //assertEquals(1,tour.determinerChoixPiocherOuPiece());
    }


    @Test
    void attributionPersonnageAChaqueJoueurTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        Tour tour = new Tour(1,  moteurJeu.getPiocheCartesCitadelles(), moteurJeu.getPiocheCartesPersonnage(),listeJoueurs);

        for(Bot j : listeJoueurs){
            assertNull(j.getPersonnageACeTour());
        }
        tour.attributionPersonnageAChaqueJoueur();
        for(Bot j : listeJoueurs){
            assertNotNull(j.getPersonnageACeTour());
        }
    }

    @Test
    void estJoueurAyantFinisEnPremierTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        Tour tour = new Tour(1,  moteurJeu.getPiocheCartesCitadelles(), moteurJeu.getPiocheCartesPersonnage(),listeJoueurs);

        assertFalse(tour.estJoueurAyantFinisEnPremier(listeJoueurs.get(0)));
        listeJoueurs.get(0).getVilleDuBot().setNbBatimentsConstruits(8);
        listeJoueurs.get(1).getVilleDuBot().setNbBatimentsConstruits(8);
        assertTrue(tour.estJoueurAyantFinisEnPremier(listeJoueurs.get(0)));
        assertFalse(tour.estJoueurAyantFinisEnPremier(listeJoueurs.get(1)));
    }

    @Test
    void verifierFinPartieTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        Tour tour = new Tour(1,  moteurJeu.getPiocheCartesCitadelles(), moteurJeu.getPiocheCartesPersonnage(),listeJoueurs);

        assertFalse(tour.verifierFinPartie());
        listeJoueurs.get(0).getVilleDuBot().setNbBatimentsConstruits(8);
        assertTrue(tour.verifierFinPartie());
    }
}
