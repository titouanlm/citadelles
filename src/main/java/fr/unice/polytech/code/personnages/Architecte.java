package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.Personnage;

public class Architecte extends Personnage {

    public Architecte(){
        this.numero = 7;
        this.nom = "Architecte";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction) {
        //System.out.println("L'architecte effectue sa spécialité ! \n");
    }
}
