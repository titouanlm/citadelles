package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;

/**
 * Cette classe implémente le pouvoir du Voleur
 * Il annonce quel personnage il vole (il lui prendra toutes ses pièces d'or)
 * Le Voleur ne peut voler ni l'Assassin, ni le personnage que l'Assassin a assassiné.
 */

public class Voleur extends Personnage {

    public Voleur(Affichage affichage){
        super(affichage);
        this.numero =2;
        this.nom = "Voleur";
    }

    public void effectuerSpecialiteVoleur(Bot joueurQuiEffectueAction, Personnage personnageAVoler, ArrayList<Bot> listeJoueurs) {

        if(!(personnageAVoler instanceof Assassin) && !(personnageAVoler instanceof Voleur)
                && personnageAVoler!=null){
            affichage.afficherDetails("Vole le personnage " + personnageAVoler.getNom());
            Bot joueurAVoler = this.botQuiPossede(personnageAVoler, listeJoueurs);
            if(joueurAVoler!=null){
                affichage.afficherDetails(joueurAVoler.getNom() + " perd donc " + joueurAVoler.getNbPiece() + " pièces." );
                joueurQuiEffectueAction.ajouterPiece(joueurAVoler.getNbPiece());
                joueurAVoler.retirerPiece(joueurAVoler.getNbPiece());
            }else{
                affichage.afficherDetails("Personne ne possède ce personnage.");
            }
        }
    }
}