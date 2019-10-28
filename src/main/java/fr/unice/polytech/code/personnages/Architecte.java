package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Architecte extends Personnage {

    public Architecte(){
        this.numero = 7;
        this.nom = "Architecte";
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
        System.out.println("L'architecte effectue sa spécialité ! \n");
    }
}
