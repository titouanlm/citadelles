package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Condottiere extends Personnage {

    public Condottiere(){
        this.numero =8;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du condottiere");
    }

}
