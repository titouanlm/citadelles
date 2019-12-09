package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class BotFairPlayTest {


    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    Affichage affichage  = new Affichage(1);
    Bot bot1 = new BotTricheur("Bot 1", "\033[35m",affichage);
    Bot bot2 = new BotFairPlay("Bot 2", "\033[34m",affichage);
    Bot bot3 = new BotAleatoire("Bot 3", "\033[36m",affichage);
    ArrayList<Bot> listeJoueurs = new ArrayList<>();

    @BeforeEach
    void setUp() {
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);
    }

    @Test
    public void strategieVoleur() {
        bot1.ajouterPiece(10);
        bot2.ajouterPiece(0);
        bot3.ajouterPiece(40);
        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot2.setPersonnageACeTour(new Voleur(affichage));
        bot3.setPersonnageACeTour(new Marchand(affichage));
        bot2.strategieVoleur(listeJoueurs, new Eveque(affichage));
        if (bot3.getNbPiece() == 0) {
            assertNotEquals(0, bot2.getNbPiece());
        }
        else if (bot1.getNbPiece()==0){
            assertNotEquals(0, bot2.getNbPiece());
        }
    }

    @Test
    public void strategieAssassin(){
        bot2.setPersonnageACeTour(new Assassin(affichage));
        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot3.setPersonnageACeTour(new Architecte(affichage));
        bot2.strategieAssassin(listeJoueurs,new Marchand(affichage));
        if (bot1.getPersonnageACeTour()!=null) {
            assertSame("Condottiere", bot1.getPersonnageACeTour().getNom());
        }
        else {
            assertSame("Architecte", bot3.getPersonnageACeTour().getNom());
        }
    }

    @Test
    public void strategieMagicien1(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        bot2.setPersonnageACeTour(new Magicien(affichage));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot2.strategieMagicien(listeJoueurs,piocheCartesCitadelles);
        assertSame("Hôtel de ville",bot2.getCartesCitadellesEnMain().get(0).getNom());
        assertSame("Dracopert",bot2.getCartesCitadellesEnMain().get(1).getNom());
        assertSame("Universitè",bot2.getCartesCitadellesEnMain().get(2).getNom());
        assertEquals(0,bot1.getCartesCitadellesEnMain().size());
    }

    @Test
    public void strategieMagicien2(){
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        bot2.setPersonnageACeTour(new Magicien(affichage));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));
        bot3.ajouterCartesCitadellesDansMain(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot2.strategieMagicien(listeJoueurs,piocheCartesCitadelles);
        assertEquals(2,bot1.getCartesCitadellesEnMain().size());
        assertEquals(0,bot2.getCartesCitadellesEnMain().size());
    }

    @Test
    public void strategieRoi(){
        bot2.setPersonnageACeTour(new Roi(affichage));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(21, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(22, CouleurCarteCitadelles.JAUNE, "Palais", 5));
        bot2.strategieRoi();
        assertEquals(3,bot2.getNbPiece());
    }

    @Test
    public void strategieEveque() {
        bot2.setPersonnageACeTour(new Eveque(affichage));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(7, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(10, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(11, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot2.strategieEveque();
        bot1.ajouterPiece(50);
        bot1.setPersonnageACeTour(new Condottiere(affichage));
        bot3.setPersonnageACeTour(new Architecte(affichage));
        bot1.strategieCondottiere(listeJoueurs);
        assertEquals(3, bot2.getNbPiece());
    }

    @Test
    public void strategieMarchand() {
        bot2.setPersonnageACeTour(new Marchand(affichage));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(38, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(39, CouleurCarteCitadelles.VERT, "Port", 4));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.strategieMarchand();
        assertEquals(3, bot2.getNbPiece());
    }

    @Test
    public void strategieArchitecte() {
        bot2.ajouterPiece(30);
        bot2.setPersonnageACeTour(new Architecte(affichage));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(17, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(18, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.strategieArchitecte();
        assertEquals(2,bot2.villeDuBot.getNbBatimentsConstruits());
    }

    @Test
    public void strategieCondottiere() {
        bot2.setPersonnageACeTour(new Condottiere(affichage));
        bot2.ajouterPiece(30);
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(41, CouleurCarteCitadelles.VERT, "Port", 4));
        bot1.villeDuBot.construireBatiment(new CarteCitadelles(42, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(52, CouleurCarteCitadelles.ROUGE, "Caserne", 3));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot2.villeDuBot.construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.strategieCondottiere(listeJoueurs);
        assertEquals(28, bot2.getNbPiece());
        assertEquals(1,bot1.villeDuBot.getNbBatimentsConstruits());
    }

    @Test
    public void strategieConstruction() {
        bot2.ajouterPiece(30);
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));
        bot2.cartesCitadellesEnMain.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));
        bot2.strategieConstruction();
        assertEquals(8, bot2.villeDuBot.getNbTotalPoint());
    }

}
