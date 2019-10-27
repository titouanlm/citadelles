package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Assassin extends Personnage {

    public Assassin(){
        this.numero =1;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("L'assassin effectue sa spécialité ! \n");
    }
}
