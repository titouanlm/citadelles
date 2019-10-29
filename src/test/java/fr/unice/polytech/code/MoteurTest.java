package fr.unice.polytech.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MoteurTest {
    private Bot bot1 = new Bot("Bot 1", "\033[32m");
    private Bot bot2 = new Bot("Bot 2","\033[33m");
    private Bot bot3 = new Bot("Bot 3","\033[35m");

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
        //A faire
    }

    @Test
    void obtenirIndiceJoueurPossedantCourroneTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        listeJoueurs.get(1).setPossedeCouronne(true);
        assertEquals(1,moteurJeu.obtenirIndiceJoueurPossedantCourrone());
    }

    @Test
    void defausserCartesPersonnagePourLeTourTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        Moteur moteurJeu = new Moteur(listeJoueurs);

        assertNotEquals(4, moteurJeu.defausserCartesPersonnagePourLeTour().getNumero());
        assertEquals(6,moteurJeu.getPiocheCartesPersonnage().getPiocheCP().size());
    }

    @Test
    void determinerChoixPiocherOuPieceTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
        Moteur moteurJeu = new Moteur(listeJoueurs);
        for(int i=0; i<65; i++){
            moteurJeu.getPiocheCartesCitadelles().piocher();
        }
        assertEquals(1,moteurJeu.determinerChoixPiocherOuPiece());
    }

    @Test
    void attributionPersonnageAChaqueJoueurTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        Moteur moteurJeu = new Moteur(listeJoueurs);

        assertNull(listeJoueurs.get(0).getPersonnageACeTour());
        assertNull(listeJoueurs.get(1).getPersonnageACeTour());
        assertNull(listeJoueurs.get(2).getPersonnageACeTour());
        moteurJeu.attributionPersonnageAChaqueJoueur(2);
        assertNotNull(listeJoueurs.get(0).getPersonnageACeTour());
        assertNotNull(listeJoueurs.get(1).getPersonnageACeTour());
        assertNotNull(listeJoueurs.get(2).getPersonnageACeTour());
    }

    @Test
    void estJoueurAyantFinisEnPremierTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        Moteur moteurJeu = new Moteur(listeJoueurs);

        assertFalse(moteurJeu.estJoueurAyantFinisEnPremier(listeJoueurs.get(0)));
        listeJoueurs.get(0).getVilleDuBot().setNbBatimentsConstruits(8);
        listeJoueurs.get(1).getVilleDuBot().setNbBatimentsConstruits(8);
        assertTrue(moteurJeu.estJoueurAyantFinisEnPremier(listeJoueurs.get(0)));
        assertFalse(moteurJeu.estJoueurAyantFinisEnPremier(listeJoueurs.get(1)));
    }

    @Test
    void verifierFinPartieTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        Moteur moteurJeu = new Moteur(listeJoueurs);
        assertFalse(moteurJeu.verifierFinPartie());
        listeJoueurs.get(0).getVilleDuBot().setNbBatimentsConstruits(8);
        assertTrue(moteurJeu.verifierFinPartie());
    }

}

