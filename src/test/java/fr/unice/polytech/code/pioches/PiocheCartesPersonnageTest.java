package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.Affichage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PiocheCartesPersonnageTest {
    private Affichage affichage =new Affichage(1);
    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage(affichage);

    @Test
    void implementerCartesPersonnageTest(){
        assertEquals(0, piocheCartesPersonnage.getPiocheCP().size());
        piocheCartesPersonnage.implementerCartesPersonnage();
        assertEquals(8, piocheCartesPersonnage.getPiocheCP().size());
    }

    @Test
    void piocherPersonnageAleatoireTest(){
        assertNull(piocheCartesPersonnage.piocherPersonnageAleatoirement());
        piocheCartesPersonnage.implementerCartesPersonnage();
        assertNotNull(piocheCartesPersonnage.piocherPersonnageAleatoirement());
    }

    @Test
    void reinitialiserTest(){
        piocheCartesPersonnage.implementerCartesPersonnage();
        piocheCartesPersonnage.piocherPersonnageAleatoirement();
        piocheCartesPersonnage.piocherPersonnageAleatoirement();
        assertEquals(6, piocheCartesPersonnage.getPiocheCP().size());
        piocheCartesPersonnage.reinitialiser();
        assertEquals(8, piocheCartesPersonnage.getPiocheCP().size());
    }

    @Test
    void prendre(){
        piocheCartesPersonnage.reinitialiser();
        piocheCartesPersonnage.prendre("Voleur");
        assertFalse(piocheCartesPersonnage.contient("Voleur"));
        assertNotEquals(8,piocheCartesPersonnage.getPiocheCP().size());
        assertEquals(7,piocheCartesPersonnage.getPiocheCP().size());
        for (int i=0; i<=6; i++)
            piocheCartesPersonnage.piocherPersonnageAleatoirement();
        assertEquals(0,piocheCartesPersonnage.getPiocheCP().size());
        assertNull(piocheCartesPersonnage.prendre("Condottiere"));
    }

}
