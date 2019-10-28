package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Marchand extends Personnage {

    public Marchand(){
        this.numero =6;
        this.nom = "Marchand";
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
        System.out.println("Le marchand effectue sa spécialité ! \n");
    }
}
