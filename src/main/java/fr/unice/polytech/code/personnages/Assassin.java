package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.Personnage;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Assassin extends Personnage {

    public Assassin(){
        this.numero =1;
        this.nom = "Assassin";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction, PiocheCartesCitadelles piocheCartesCitadelles) {
        //System.out.println("L'assassin effectue sa spécialité ! \n");
        if(joueurQuiSubitAction!=joueurQuiEffectueAction){
            joueurQuiSubitAction.setPersonnageACeTour(null);
        }
    }
}
