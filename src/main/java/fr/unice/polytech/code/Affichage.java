package fr.unice.polytech.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Affichage {

    private final HashMap<String,Integer> listeJoueursGagnants = new HashMap<>();
    private int moyennePointJoueur1;
    private int moyennePointJoueur2;
    private int moyennePointJoueur3;
    private int moyennePointJoueur4;


    public Affichage() {
        for (int i=1;i<5;i++){
            listeJoueursGagnants.put("Bot "+i,0);
        }
        moyennePointJoueur1=0;
        moyennePointJoueur2=0;
        moyennePointJoueur3=0;
        moyennePointJoueur4=0;
    }


    public void incrementerNbVictoireDuBot(Bot joueurGagnant) {
        listeJoueursGagnants.put(joueurGagnant.getNom(), listeJoueursGagnants.get(joueurGagnant.getNom())+1);
    }

    public void afficherResultats() {
        Set<Map.Entry<String, Integer>> set = listeJoueursGagnants.entrySet();
        String nomDuBot;
        int nombreDePartiesGagnees;
        for (Map.Entry<String, Integer> e : set) {
            nomDuBot=e.getKey();
            nombreDePartiesGagnees=e.getValue();
            //System.out.println(nomDuBot);
            if(nomDuBot.equals("Bot 1")){
            System.out.println(nomDuBot + " : " + nombreDePartiesGagnees + " cela fait un winrate de "
                    + ((double)nombreDePartiesGagnees/1000)*100 +"%. \nPoints Moyens par partie : " + (double)moyennePointJoueur1/1000 +"\n");
            }

            else if(nomDuBot.equals("Bot 2")){
                System.out.println(nomDuBot + " : " + nombreDePartiesGagnees + " cela fait un winrate de "
                        + ((double)nombreDePartiesGagnees/1000)*100 +"%. \nPoints Moyens par partie : " + (double)moyennePointJoueur2/1000 +"\n");
            }

            else if(nomDuBot.equals("Bot 3")){
                System.out.println(nomDuBot + " : " + nombreDePartiesGagnees + " cela fait un winrate de "
                        + ((double)nombreDePartiesGagnees/1000)*100 +"%. \nPoints Moyens par partie : " + (double)moyennePointJoueur3/1000 +"\n");
            }

            else if(nomDuBot.equals("Bot 4")){
                System.out.println(nomDuBot + " : " + nombreDePartiesGagnees + " cela fait un winrate de "
                        + ((double)nombreDePartiesGagnees/1000)*100 +"%. \nPoints Moyens par partie : " + (double)moyennePointJoueur4/1000 +"\n");
            }

        }
    }

    public void NbDePointDesBotsEnMoyenne(ArrayList<Bot> listeDeJoueur){
        Set<Map.Entry<String, Integer>> set = listeJoueursGagnants.entrySet();
        for(Bot j : listeDeJoueur){
            if (j.getNom() == "Bot 1"){
                moyennePointJoueur1=moyennePointJoueur1+j.getNbPoint();
            }
            if (j.getNom() == "Bot 2"){
                moyennePointJoueur2=moyennePointJoueur2+j.getNbPoint();
            }
            if (j.getNom() == "Bot 3"){
                moyennePointJoueur3=moyennePointJoueur3+j.getNbPoint();
            }
            if (j.getNom() == "Bot 4"){
                moyennePointJoueur4=moyennePointJoueur4+j.getNbPoint();
            }
        }

    }
}
