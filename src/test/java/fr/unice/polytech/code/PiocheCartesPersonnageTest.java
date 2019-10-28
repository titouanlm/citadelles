package fr.unice.polytech.code;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;
import fr.unice.polytech.code.personnages.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PiocheCartesPersonnageTest {

    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();

    @Test
    void piocherTest(){
        Personnage[] cp = new Personnage[8];
        cp[0] = new Assassin();
        cp[1] = new Voleur();
        cp[2] = new Magicien();
        cp[3] = new Roi();
        cp[4] = new Eveque();
        cp[5] = new Marchand();
        cp[6] = new Architecte();
        cp[7] = new Condottiere();

        for (int i = 0; i < 8; i++) {
            piocheCartesPersonnage.ajouterCartePersonnage(cp[i]);
        }
        assertNotNull(piocheCartesPersonnage.piocherPersonnageAleatoire());
    }

    @Test
    void melangerTest(){
        Personnage[] cp = new Personnage[8];
        cp[0] = new Assassin();
        cp[1] = new Voleur();
        cp[2] = new Magicien();
        cp[3] = new Roi();
        cp[4] = new Eveque();
        cp[5] = new Marchand();
        cp[6] = new Architecte();
        cp[7] = new Condottiere();

        for (int i = 0; i < 8; i++) {
            piocheCartesPersonnage.ajouterCartePersonnage(cp[i]);
        }
        assertNotEquals(cp,piocheCartesPersonnage.getPiocheCP());

    }
}
