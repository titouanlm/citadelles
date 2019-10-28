package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Voleur extends Personnage {

    public Voleur(){
        this.numero =2;
        this.nom = "Voleur";
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
        System.out.println("Le voleur effectue sa spécialité ! \n");
    }
}
