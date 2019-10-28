package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Roi extends Personnage {

    public Roi(){
        this.numero =4;
        this.nom = "Roi";
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Le roi effectue sa spécialité ! \n");
    }
}
