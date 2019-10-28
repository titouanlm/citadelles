package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Condottiere extends Personnage {

    public Condottiere(){
        this.numero =8;
        this.nom = "Condottiere";
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Le condottière effectue sa spécialité ! \n");
    }

}
