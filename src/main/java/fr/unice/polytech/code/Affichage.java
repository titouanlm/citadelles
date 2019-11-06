package fr.unice.polytech.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Affichage {

    private final HashMap<String,int[]> mapJoueurs= new HashMap<>();
    private int nbParties;

    public Affichage(int nbParties) {
        this.nbParties=nbParties;
        for (int i=1;i<5;i++){
            mapJoueurs.put("Bot "+i,new int[2]);
        }
    }

    public int getNbParties() {
        return nbParties;
    }

    public void incrementerNbPointsDesBotsEnMoyenne(ArrayList<Bot> listeDeJoueur){
        for(Bot j : listeDeJoueur){
            int[] result = mapJoueurs.get(j.getNom());
            result[1]+=j.getNbPoint();
            mapJoueurs.put(j.getNom(), result);
        }
    }

    public void incrementerNbVictoireDuBot(Bot joueurGagnant) {
        int tab[] = mapJoueurs.get(joueurGagnant.getNom());
        tab[0]++;
        mapJoueurs.put(joueurGagnant.getNom(), tab);
    }

    public void afficherResultats() {
        Set<Map.Entry<String,int[] >> set = mapJoueurs.entrySet();
        for (Map.Entry<String, int[]> e : set) {
            String nomDuBot=e.getKey();
            int[] resultsVictoiresPointsEnMoyenne = e.getValue();
            System.out.println(nomDuBot + " :");
            System.out.println("Victoire(s) : " + resultsVictoiresPointsEnMoyenne[0] + " sur " + this.nbParties + " parties." );
            System.out.println("Soit " + ((double)resultsVictoiresPointsEnMoyenne[0]/this.nbParties)*100 + "% de victoires.");
            System.out.println("Nombre de points moyens par partie : " + (double)resultsVictoiresPointsEnMoyenne[1]/this.nbParties + "\n");
        }
    }
}
