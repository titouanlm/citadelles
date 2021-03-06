package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class BotFairPlayTest {

    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    Bot bot1 = new BotTricheur("Bot 1", "\033[35m");
    Bot bot2 = new BotFairPlay("Bot 2", "\033[34m");
    Bot bot3 = new BotAleatoire("Bot 3", "\033[36m");
    ArrayList<Bot> listeJoueurs = new ArrayList<>();

    @BeforeEach
    void setUp() {
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
    }


    @Test
    public void choixCartesPiocheesCartesPlusHaute(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.VIOLET, "Temple", 1 ));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(4,CouleurCarteCitadelles.VIOLET, "Eglise", 2 ));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(5,CouleurCarteCitadelles.VIOLET, "Eglise", 2 ));
        assertEquals(1 , bot2.getCartesCitadellesEnMain().size());
        assertEquals(2, piocheCartesCitadelles.nbCartesRestantes());

        CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
        CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();
        CarteCitadelles carteChoisie = bot2.choixCartesPiochees(piocheCartesCitadelles, cartePiochee1, cartePiochee2);

        assertEquals(1 , piocheCartesCitadelles.nbCartesRestantes());
        assertEquals("Temple" , carteChoisie.getNom());
    }


    @Test
    public void strategieVoleur() {
        bot1.ajouterPiece(10);
        bot2.ajouterPiece(0);
        bot3.ajouterPiece(40);
        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Voleur());
        bot3.setPersonnageACeTour(new Marchand());
        bot2.strategieVoleur(listeJoueurs, new Eveque());
        if (bot3.getNbPiece() == 0) {
            assertEquals(40, bot2.getNbPiece());
        }
        else if (bot1.getNbPiece()==0){
            assertEquals(10, bot2.getNbPiece());
        }
    }


    @Test
    public void strategieAssassin(){
        bot2.setPersonnageACeTour(new Assassin());
        bot1.setPersonnageACeTour(new Condottiere());
        bot3.setPersonnageACeTour(new Architecte());
        bot2.strategieAssassin(listeJoueurs,new Marchand());
        if (bot1.getPersonnageACeTour()!=null) {
            assertSame("Condottiere", bot1.getPersonnageACeTour().getNom());
        }
        else {
            assertSame("Architecte", bot3.getPersonnageACeTour().getNom());
        }
    }

    @Test
    public void strategieMagicien1(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        bot2.setPersonnageACeTour(new Magicien());
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "H??tel de ville", 5));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universit??", 8));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot2.strategieMagicien(listeJoueurs,piocheCartesCitadelles);
        assertSame("H??tel de ville",bot2.getCartesCitadellesEnMain().get(0).getNom());
        assertSame("Dracopert",bot2.getCartesCitadellesEnMain().get(1).getNom());
        assertSame("Universit??",bot2.getCartesCitadellesEnMain().get(2).getNom());
        assertEquals(0,bot1.getCartesCitadellesEnMain().size());
    }

    @Test
    public void strategieMagicien2(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        bot2.setPersonnageACeTour(new Magicien());
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "H??tel de ville", 5));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot2.strategieMagicien(listeJoueurs,piocheCartesCitadelles);
        assertEquals(2,bot1.getCartesCitadellesEnMain().size());
        assertEquals(0,bot2.getCartesCitadellesEnMain().size());
    }

    @Test
    public void strategieRoi(){
        bot2.setPersonnageACeTour(new Roi());
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(21, CouleurCarteCitadelles.JAUNE, "Ch??teau", 4));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(22, CouleurCarteCitadelles.JAUNE, "Palais", 5));
        bot2.strategieRoi();
        assertEquals(3,bot2.getNbPiece());
    }

    @Test
    public void strategieEveque() {
        bot2.setPersonnageACeTour(new Eveque());
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(7, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(10, CouleurCarteCitadelles.BLEU, "Monast??re", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(11, CouleurCarteCitadelles.BLEU, "Cath??drale", 5));
        bot2.strategieEveque();
        bot1.ajouterPiece(50);
        bot1.setPersonnageACeTour(new Condottiere());
        bot3.setPersonnageACeTour(new Architecte());
        bot1.strategieCondottiere(listeJoueurs, piocheCartesCitadelles);
        assertEquals(3, bot2.getNbPiece());
        assertEquals(3, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    public void strategieMarchand() {
        bot2.setPersonnageACeTour(new Marchand());
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(38, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(39, CouleurCarteCitadelles.VERT, "Port", 4));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.strategieMarchand();
        assertEquals(3, bot2.getNbPiece());
    }

    @Test
    public void strategieArchitecte() {
        bot2.ajouterPiece(30);
        bot2.setPersonnageACeTour(new Architecte());
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cath??drale", 5));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universit??", 8));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(17, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(18, CouleurCarteCitadelles.JAUNE, "Ch??teau", 4));
        bot2.strategieArchitecte();
        assertEquals(2,bot2.villeDuBot.getNbBatimentsConstruits());
    }

    @Test
    public void strategieCondottiere() {
        bot2.setPersonnageACeTour(new Condottiere());
        bot2.ajouterPiece(30);
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(41, CouleurCarteCitadelles.VERT, "Port", 4));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(42, CouleurCarteCitadelles.VERT, "H??tel de ville", 5));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(52, CouleurCarteCitadelles.ROUGE, "Caserne", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.strategieCondottiere(listeJoueurs, piocheCartesCitadelles);
        assertEquals(28, bot2.getNbPiece());
        assertEquals(1,bot1.villeDuBot.getNbBatimentsConstruits());
    }

    @Test
    public void strategieConstruction() {
        bot2.ajouterPiece(30);
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cath??drale", 5));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universit??", 8));
        bot2.strategieConstruction();
        assertEquals(8, bot2.villeDuBot.getNbTotalPoint());
    }

}
