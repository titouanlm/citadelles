package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;

public abstract class Personnage {

    protected int numero;
    protected String nom;
    Affichage affichage;

    Personnage(Affichage affichage){
        this.affichage = affichage;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public Bot botQuiPossede(Personnage p , ArrayList<Bot> listeJoueurs){
        for(Bot b : listeJoueurs){
            if(b.getPersonnageACeTour() != null && p!=null) {
                if (b.getPersonnageACeTour().getNom() == p.getNom()){
                    return b;
                }
            }
        }
        return null;
    }

}