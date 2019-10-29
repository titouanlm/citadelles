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
    void implementerCartesPersonnageTest(){
        assertEquals(0, piocheCartesPersonnage.getPiocheCP().size());
        piocheCartesPersonnage.implementerCartesPersonnage();
        assertEquals(8, piocheCartesPersonnage.getPiocheCP().size());
    }

    @Test
    void piocherPersonnageAleatoireTest(){
        assertNull(piocheCartesPersonnage.piocherPersonnageAleatoire());
        piocheCartesPersonnage.implementerCartesPersonnage();
        assertNotNull(piocheCartesPersonnage.piocherPersonnageAleatoire());
    }

    @Test
    void reinitialiserTest(){
        piocheCartesPersonnage.implementerCartesPersonnage();
        piocheCartesPersonnage.piocherPersonnageAleatoire();
        piocheCartesPersonnage.piocherPersonnageAleatoire();
        assertEquals(6, piocheCartesPersonnage.getPiocheCP().size());
        piocheCartesPersonnage.reinitialiser();
        assertEquals(8, piocheCartesPersonnage.getPiocheCP().size());
    }

}
