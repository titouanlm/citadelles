package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BotAleatoireTest {

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
    public void choixCartesPiochees(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1 ));
        assertEquals(0 , bot3.getCartesCitadellesEnMain().size());
        assertEquals(1 , piocheCartesCitadelles.nbCartesRestantes());

        CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
        CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();
        CarteCitadelles carteCitadelles = bot3.choixCartesPiochees(piocheCartesCitadelles, cartePiochee1, cartePiochee2);

        assertEquals("Temple" , carteCitadelles.getNom());
    }


    @Test
    public void strategieVoleur() {
        bot1.ajouterPiece(10);
        bot2.ajouterPiece(30);
        bot3.ajouterPiece(40);
        bot3.setPersonnageACeTour(new Voleur());
        bot2.setPersonnageACeTour(new Condottiere());
        bot1.setPersonnageACeTour(new Marchand());
        bot3.strategieVoleur(listeJoueurs, null);
        if (bot1.getNbPiece() != 10 | bot2.getNbPiece() != 30) {
            assertNotEquals(40, bot3.getNbPiece());
        }
    }

    @Test
    public void strategieAssassin(){
        bot1.setNbPoint(10);
        bot2.setNbPoint(20);
        bot3.setNbPoint(30);
        bot3.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Condottiere());
        bot1.setPersonnageACeTour(new Architecte());
        bot3.strategieAssassin(listeJoueurs,null);
        if (bot1.getPersonnageACeTour()==null){
            assertNull(bot1.getPersonnageACeTour());
            assertNotNull(bot2.getPersonnageACeTour());
        }
        else if (bot2.getPersonnageACeTour()==null) {
            assertNotNull(bot1.getPersonnageACeTour());
            assertNull(bot2.getPersonnageACeTour());
        }
    }

    @Test
    public void strategieMagicien(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        bot3.setPersonnageACeTour(new Magicien());
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot3.strategieMagicien(listeJoueurs,piocheCartesCitadelles);
        assertNotNull(bot3.getCartesCitadellesEnMain());
    }

    @Test
    public void strategieRoi(){
        bot3.setPersonnageACeTour(new Roi());
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(21, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(22, CouleurCarteCitadelles.JAUNE, "Palais", 5));
        bot3.strategieRoi();
        assertEquals(3,bot3.getNbPiece());
    }

    @Test
    public void strategieEveque() {
        bot3.setPersonnageACeTour(new Eveque());
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(7, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(10, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(11, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot3.strategieEveque();
        bot2.ajouterPiece(50);
        bot2.setPersonnageACeTour(new Condottiere());
        bot1.setPersonnageACeTour(new Architecte());
        bot2.strategieCondottiere(listeJoueurs, piocheCartesCitadelles);
        assertEquals(3, bot3.getNbPiece());
        assertEquals(3, bot3.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    public void strategieMarchand() {
        bot3.setPersonnageACeTour(new Marchand());
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(38, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(39, CouleurCarteCitadelles.VERT, "Port", 4));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot3.strategieMarchand();
        assertEquals(3, bot3.getNbPiece());
    }

    @Test
    public void strategieArchitecte() {
        bot3.ajouterPiece(30);
        bot3.setPersonnageACeTour(new Architecte());
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(17, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(18, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot3.strategieArchitecte();
        assertEquals(2,bot3.villeDuBot.getNbBatimentsConstruits());
    }

    @Test
    public void strategieCondottiere() {
        bot3.setPersonnageACeTour(new Condottiere());
        bot3.ajouterPiece(30);
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(41, CouleurCarteCitadelles.VERT, "Port", 4));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(42, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(52, CouleurCarteCitadelles.ROUGE, "Caserne", 3));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot3.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot3.strategieCondottiere(listeJoueurs, piocheCartesCitadelles);
        assertNotEquals(30, bot3.getNbPiece());
    }

    @Test
    public void strategieConstruction() {
        bot3.ajouterPiece(30);
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        bot3.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));
        bot3.strategieConstruction();
        assertNotEquals(0, bot3.villeDuBot.getNbTotalPoint());
    }

}
