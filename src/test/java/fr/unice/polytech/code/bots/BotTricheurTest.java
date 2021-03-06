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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BotTricheurTest {

    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    Bot bot1 = new BotTricheur("Bot 1", "\033[35m");
    Bot bot2 = new BotFairPlay("Bot 2", "\033[34m");
    Bot bot3 = new BotAleatoire("Bot 3", "\033[36m");
    Bot bot4 = mock(BotTricheur.class);
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
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(5,CouleurCarteCitadelles.VIOLET, "Eglise", 2 ));
        assertEquals(1 , bot1.getCartesCitadellesEnMain().size());
        assertEquals(2, piocheCartesCitadelles.nbCartesRestantes());

        CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
        CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();
        CarteCitadelles carteChoisie = bot1.choixCartesPiochees(piocheCartesCitadelles, cartePiochee1, cartePiochee2);

        assertEquals(1, piocheCartesCitadelles.nbCartesRestantes());
        assertEquals("Temple", carteChoisie.getNom());
    }

    @Test
    public void strategieVoleur(){
        listeJoueurs.add(bot4);
        bot1.ajouterPiece(10);
        bot2.ajouterPiece(30);
        bot3.ajouterPiece(40);
        bot1.setPersonnageACeTour(new Voleur());
        bot2.setPersonnageACeTour(new Condottiere());
        bot3.setPersonnageACeTour(new Marchand());
        when(bot4.getPersonnageACeTour()).thenReturn(new Assassin());
        bot1.strategieVoleur(listeJoueurs,null);
        assertEquals(50, bot1.getNbPiece());
        assertEquals(30, bot2.getNbPiece());
        assertEquals(0, bot3.getNbPiece());
    }

    @Test
    public void strategieAssassin(){
        bot1.setNbPoint(10);
        bot2.setNbPoint(20);
        bot3.setNbPoint(30);
        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Condottiere());
        bot3.setPersonnageACeTour(new Architecte());
        bot1.strategieAssassin(listeJoueurs,null);
        assertEquals(bot3.getPersonnageACeTour(),null);
        assertSame("Condottiere", bot2.getPersonnageACeTour().getNom());
    }

    @Test
    public void strategieMagicien(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        bot1.setPersonnageACeTour(new Magicien());
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "H??tel de ville", 5));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universit??", 8));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot1.strategieMagicien(listeJoueurs,piocheCartesCitadelles);
        assertSame("H??tel de ville",bot1.getCartesCitadellesEnMain().get(0).getNom());
        assertSame("Dracopert",bot1.getCartesCitadellesEnMain().get(1).getNom());
        assertSame("Universit??",bot1.getCartesCitadellesEnMain().get(2).getNom());
        assertNotEquals(null,bot1.getCartesCitadellesEnMain());
    }

    @Test
    public void strategieRoi(){
        bot1.setPersonnageACeTour(new Roi());
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(21, CouleurCarteCitadelles.JAUNE, "Ch??teau", 4));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(22, CouleurCarteCitadelles.JAUNE, "Palais", 5));
        bot1.strategieRoi();
        assertEquals(3,bot1.getNbPiece());
    }

    @Test
    public void strategieEveque() {
        bot1.setPersonnageACeTour(new Eveque());
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(7, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(10, CouleurCarteCitadelles.BLEU, "Monast??re", 3));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(11, CouleurCarteCitadelles.BLEU, "Cath??drale", 5));
        bot1.strategieEveque();
        bot2.ajouterPiece(50);
        bot2.setPersonnageACeTour(new Condottiere());
        bot3.setPersonnageACeTour(new Architecte());
        bot2.strategieCondottiere(listeJoueurs, piocheCartesCitadelles);
        assertEquals(3, bot1.getNbPiece());
        assertEquals(3, bot1.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    public void strategieMarchand() {
        bot1.setPersonnageACeTour(new Marchand());
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(38, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(39, CouleurCarteCitadelles.VERT, "Port", 4));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.strategieMarchand();
        assertEquals(3, bot1.getNbPiece());
    }

    @Test
    public void strategieArchitecte() {
        bot1.ajouterPiece(30);
        bot1.setPersonnageACeTour(new Architecte());
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cath??drale", 5));
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universit??", 8));
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(17, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(18, CouleurCarteCitadelles.JAUNE, "Ch??teau", 4));
        bot1.strategieArchitecte();
        assertEquals(2,bot1.villeDuBot.getNbBatimentsConstruits());
    }

    @Test
    public void strategieCondottiere() {
        bot1.setPersonnageACeTour(new Condottiere());
        bot1.ajouterPiece(30);
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(41, CouleurCarteCitadelles.VERT, "Port", 4));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(42, CouleurCarteCitadelles.VERT, "H??tel de ville", 5));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(52, CouleurCarteCitadelles.ROUGE, "Caserne", 3));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.strategieCondottiere(listeJoueurs, piocheCartesCitadelles);
        assertEquals(28, bot1.getNbPiece());
        assertEquals(1,bot2.villeDuBot.getNbBatimentsConstruits());
    }

    @Test
    public void strategieConstruction() {
        bot1.ajouterPiece(30);
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cath??drale", 5));
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "??cole de magie", 6));
        bot1.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universit??", 8));
        bot1.strategieConstruction();
        assertEquals(8, bot1.villeDuBot.getNbTotalPoint());
    }

}
