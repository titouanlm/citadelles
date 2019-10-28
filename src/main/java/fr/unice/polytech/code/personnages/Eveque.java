package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Eveque extends Personnage {

    public Eveque(){
        this.numero =5;
        this.nom = "Evèque";
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
        System.out.println("L'évèque effectue sa spécialité ! \n");
    }
}
