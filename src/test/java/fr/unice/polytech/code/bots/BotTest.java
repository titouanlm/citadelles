package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.cartes.CourDesMiracles;
import fr.unice.polytech.code.personnages.Architecte;
import fr.unice.polytech.code.personnages.Assassin;
import fr.unice.polytech.code.personnages.Magicien;
import fr.unice.polytech.code.personnages.Voleur;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BotTest {

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
    void ajouterPieceTest(){
        assertEquals(0, bot1.getNbPiece());
        bot1.ajouterPiece(2);
        assertEquals(2, bot1.getNbPiece());
    }

    @Test
    void retirerPieceTest(){
        bot1.ajouterPiece(2);
        assertEquals(2, bot1.getNbPiece());
        bot1.retirerPiece(1);
        assertEquals(1, bot1.getNbPiece());
        bot1.retirerPiece(3);
        assertEquals(0, bot1.getNbPiece());
    }

    @Test
    void ajouterCartesCitadellesDansMain(){
        bot1.ajouterCartesCitadellesDansMain(null);
        assertEquals(0, bot1.getCartesCitadellesEnMain().size());
        bot1.ajouterCartesCitadellesDansMain(new CourDesMiracles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        assertEquals(1, bot1.getCartesCitadellesEnMain().size());
    }

    @Test
    void contientDansSaMain(){
        CarteCitadelles c = new CourDesMiracles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 );
        CarteCitadelles c2 = new CourDesMiracles(56, CouleurCarteCitadelles.VIOLET, "Cour", 2 );
        bot1.ajouterCartesCitadellesDansMain(c);
        assertTrue(bot1.contientDansSaMain(c));
        assertFalse(bot1.contientDansSaMain(c2));
    }


    @Test
    void contientDansSaMain2(){
        bot1.ajouterCartesCitadellesDansMain(new CourDesMiracles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        assertTrue(bot1.contientDansSaMain("Cour des miracles"));
        assertFalse(bot1.contientDansSaMain("Marché"));
    }

    @Test
    void rechercheCartePlusHauteValeurConstruisableTest(){
        bot1.ajouterPiece(3);
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));

        assertNull(bot1.rechercheCartePlusHauteValeurConstruisable());
        bot1.ajouterPiece(3);
        assertEquals("Hôtel de ville" ,bot1.rechercheCartePlusHauteValeurConstruisable().getNom());
    }

    @Test
    void listePersonnageTest(){
        assertTrue(bot1.listePersonnages(0) instanceof Assassin);
        assertTrue(bot1.listePersonnages(1) instanceof Voleur);
        assertTrue(bot1.listePersonnages(2) instanceof Magicien);
        assertFalse(bot1.listePersonnages(5) instanceof Architecte);
    }





}
