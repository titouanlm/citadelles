package fr.unice.polytech.code.moteur;


import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.moteur.Moteur;
import fr.unice.polytech.code.moteur.Tour;
import fr.unice.polytech.code.personnages.Architecte;
import fr.unice.polytech.code.personnages.Assassin;
import fr.unice.polytech.code.personnages.Roi;
import fr.unice.polytech.code.personnages.Voleur;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TourTest {
    private Bot bot1 = new BotTricheur("Bot 1", "\033[32m");
    private Bot bot2 = new BotFairPlay("Bot 2","\033[33m");
    private Bot bot3 = new BotAleatoire("Bot 3","\033[35m");


    @Test
    public void appelerJoueurDansLOrdreTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
        piocheCartesCitadelles.implementerCartesCitadelles();
        PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();
        bot1.setPersonnageACeTour(piocheCartesPersonnage.prendre("Assassin"));
        bot2.setPersonnageACeTour(piocheCartesPersonnage.prendre("Voleur"));
        bot2.setNbPoint(20);
        Tour tour = new Tour(2,piocheCartesCitadelles, piocheCartesPersonnage, listeJoueurs);
        tour.appelerJoueursDansLOrdre();
        assertNull(bot2.getPersonnageACeTour());
        assertEquals(0,bot1.getCartesCitadellesEnMain().size());
        assertEquals(0,bot1.getNbPiece());
    }

    @Test
    public void finPartieTest(){
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

    @Test
    public void finDuTourTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
        piocheCartesCitadelles.implementerCartesCitadelles();
        PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();
        bot1.setPersonnageACeTour(piocheCartesPersonnage.prendre("Assassin"));
        Tour tour = new Tour(2,piocheCartesCitadelles, piocheCartesPersonnage, listeJoueurs);
        piocheCartesCitadelles.piocher();
        tour.finDuTour();
        assertTrue(piocheCartesPersonnage.contient("Assassin"));
    }

    @Test
    public void strategieEffectuerSpecialiteTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        bot3.setNbPoint(20);
        bot2.ajouterPiece(15);
        bot3.ajouterPiece(15);
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(52,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(54, CouleurCarteCitadelles.ROUGE, "Forteresse", 5 ));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(49,CouleurCarteCitadelles.ROUGE, "Prison", 2 ));
        PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
        piocheCartesCitadelles.implementerCartesCitadelles();
        PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();
        Tour tour = new Tour(2,piocheCartesCitadelles, piocheCartesPersonnage, listeJoueurs);
        bot1.setPersonnageACeTour(new Assassin());
        piocheCartesPersonnage.prendre("Assassin");
        bot2.setPersonnageACeTour(new Architecte());
        piocheCartesPersonnage.prendre("Architecte");
        bot3.setPersonnageACeTour(new Roi());
        piocheCartesPersonnage.prendre("Roi");
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(17,CouleurCarteCitadelles.JAUNE, "Manoir", 3 ));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(21,CouleurCarteCitadelles.JAUNE, "Château", 4 ));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(22,CouleurCarteCitadelles.JAUNE, "Palais", 5 ));
        bot3.strategieConstruction();
        bot3.strategieConstruction();
        bot3.strategieConstruction();
        tour.strategieEffectuerSpecialite(bot1);
        tour.strategieEffectuerSpecialite(bot2);
        tour.strategieEffectuerSpecialite(bot3);
        assertNull(bot3.getPersonnageACeTour());
        assertEquals(1,bot2.getCartesCitadellesEnMain().size());
        assertEquals(3,bot3.getNbPiece());
        }

    }

