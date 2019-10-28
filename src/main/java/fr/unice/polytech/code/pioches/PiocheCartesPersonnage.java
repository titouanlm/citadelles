package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.Personnage;
import fr.unice.polytech.code.personnages.*;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCartesPersonnage {
    private ArrayList<Personnage> piocheCP;

    public PiocheCartesPersonnage() {
        this.piocheCP = new ArrayList<>();
    }

    public ArrayList<Personnage> getPiocheCP() {
        return piocheCP;
    }

    public void ajouterCartePersonnage(Personnage cp) {
        this.piocheCP.add(cp);
    }

    public void melanger(){
        Collections.shuffle(this.piocheCP);
    }

    public Personnage piocherPersonnageAleatoire(){
        int indicePersonnageAleatoire = (int)(Math.random()*piocheCP.size());
        Personnage personnageAleatoire = this.piocheCP.get(indicePersonnageAleatoire);
        this.piocheCP.remove(indicePersonnageAleatoire);
        return personnageAleatoire;
    }

    public void reinitialiser() {
        this.piocheCP.clear();
        this.implementerCartesPersonnage();
    }

    public void implementerCartesPersonnage() {
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
            this.ajouterCartePersonnage(cp[i]);
        }
        this.melanger();
    }
}
