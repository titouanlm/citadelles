package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.Personnage;

public class Voleur extends Personnage {

    public Voleur(){
        this.numero =2;
        this.nom = "Voleur";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction) {
        //System.out.println("Le voleur effectue sa spécialité ! \n");
        if(!(joueurQuiSubitAction.getPersonnageACeTour()instanceof Assassin) && joueurQuiSubitAction!=joueurQuiEffectueAction && joueurQuiSubitAction.getPersonnageACeTour()!=null){
            joueurQuiEffectueAction.ajouterPiece(joueurQuiSubitAction.getNbPiece());
            joueurQuiSubitAction.retirerPiece(joueurQuiSubitAction.getNbPiece());
        }
    }
}
