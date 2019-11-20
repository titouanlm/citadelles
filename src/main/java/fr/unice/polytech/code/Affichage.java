package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.Bot;

import java.util.*;

public class Affichage {

    private final HashMap<String,int[]> mapJoueurs= new HashMap<>();
    private int nbParties;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_UNBOLD = "\u001B[21m";

    public Affichage(int nbParties) {
        this.nbParties=nbParties;
        for (int i=1;i<5;i++){
            mapJoueurs.put("Bot "+i,new int[2]);
        }
    }

    public int getNbParties() {
        return nbParties;
    }
    public final HashMap<String,int[]> getMapJoueurs(){return mapJoueurs;}

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
        Map<String, int[]> sortedMap = new TreeMap<>(mapJoueurs);
        Set<Map.Entry<String,int[] >> set = sortedMap.entrySet();
        for (Map.Entry<String, int[]> e : set) {
            String nomDuBot=e.getKey();
            int[] resultsVictoiresPointsEnMoyenne = e.getValue();
            double pourcentageVictoire = ((double)resultsVictoiresPointsEnMoyenne[0]/this.nbParties)*100;
            double pourcentageVictoireArrondi =  (double) Math.round(pourcentageVictoire * 100)/100;
            System.out.println( ANSI_BOLD + ANSI_RED + nomDuBot + " :"  + ANSI_RESET );
            System.out.println( ANSI_BOLD + ANSI_BLUE + "Victoire(s) : "  + ANSI_RESET + ANSI_BOLD + resultsVictoiresPointsEnMoyenne[0] + ANSI_UNBOLD + ANSI_RESET + " sur " + ANSI_BOLD + this.nbParties + ANSI_UNBOLD + ANSI_RESET  + " parties." );
            System.out.println("Soit " + ANSI_BOLD  + pourcentageVictoireArrondi + "%"+ ANSI_UNBOLD + ANSI_RESET + " de victoires.");
            System.out.println(ANSI_BOLD + ANSI_BLUE + "Nombre de points moyens par partie : " + ANSI_UNBOLD + ANSI_RESET + ANSI_BOLD + (double)resultsVictoiresPointsEnMoyenne[1]/this.nbParties + ANSI_UNBOLD + ANSI_RESET + "\n");
        }
    }
}
