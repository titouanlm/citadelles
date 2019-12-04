package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;

/**
 * Cette classe consiste à implémenter la spécialité de l'assassin
 * Il assassine un personnage et ce dernier ne jouera pas durant ce tour
 */

public class Assassin extends Personnage {

    public Assassin(Affichage affichage){
        super(affichage);
        this.numero =1;
        this.nom = "Assassin";
    }

    public void effectuerSpecialiteAssassin(Personnage personnageAAssassiner, ArrayList<Bot> listeJoueurs) {
        if(!(personnageAAssassiner instanceof Assassin) && personnageAAssassiner!=null){
            affichage.afficherDetails("Assassine le personnage " + personnageAAssassiner.getNom());
            Bot joueurAAssassiner= this.botQuiPossede(personnageAAssassiner, listeJoueurs);
            if(joueurAAssassiner!=null){
                joueurAAssassiner.setPersonnageACeTour(null);
                affichage.afficherDetails("Le " + joueurAAssassiner.getNom() + " ne pourra donc pas joueur ce tour-ci.");
            }else{
                affichage.afficherDetails("Personne ne possède ce personnage.");
            }
        }
    }
}