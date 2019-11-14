package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.CouleurCarteCitadelles;
import fr.unice.polytech.code.Personnage;
import fr.unice.polytech.code.Ville;
import fr.unice.polytech.code.personnages.*;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCartesPersonnage {
    private ArrayList<Personnage> piocheCP;

    public PiocheCartesPersonnage() {
        this.piocheCP = new ArrayList<>();
    }

    public ArrayList<Personnage> getPiocheCP() {
        return piocheCP;
    }

    public int getPioheCPSize() { return piocheCP.size(); }

    public void ajouterCartePersonnage(Personnage cp) {
        this.piocheCP.add(cp);
    }

    public void melanger(){
        Collections.shuffle(this.piocheCP);
    }

    public Personnage piocherPersonnageAleatoirement(){
        if(piocheCP.size()>0){
            int indicePersonnageAleatoire = (int)(Math.random()*piocheCP.size());
            Personnage personnageAleatoire = this.piocheCP.get(indicePersonnageAleatoire);
            this.piocheCP.remove(indicePersonnageAleatoire);
            return personnageAleatoire;
        }
        return null;
    }

    public Personnage piocherPersonnageNonAleatoirement(int piècePersonnage, ArrayList<CarteCitadelles> carteEnMain, Ville villejoueur) {
        int nombreQuartierJaune=0;
        int nombreQuartierVert=0;
        for(CarteCitadelles c : villejoueur.getBatimentsConstruits()) {
            if (c.getCouleur() == CouleurCarteCitadelles.JAUNE) {
                nombreQuartierJaune += 1;
            }
            if (c.getCouleur() == CouleurCarteCitadelles.VERT) {
                nombreQuartierVert += 1;
            }
        }
        if (piocheCP.size() > 0){
            for (int i = 0; i < piocheCP.size(); i++) {
                if (piocheCP.get(i).getNumero() == 1 && piècePersonnage < 4 && carteEnMain.size() < 3) {
                    Personnage personnagechoisi = this.piocheCP.get(i);
                    this.piocheCP.remove(i);
                    return personnagechoisi;
                } else if (piocheCP.get(i).getNumero() == 2 && piècePersonnage < 4) {
                    Personnage personnagechoisi = this.piocheCP.get(i);
                    this.piocheCP.remove(i);
                    return personnagechoisi;
                } else if (piocheCP.get(i).getNumero() == 3 && piècePersonnage > 7 && carteEnMain.size() < 2) {
                    Personnage personnagechoisi = this.piocheCP.get(i);
                    this.piocheCP.remove(i);
                    return personnagechoisi;
                } else if (piocheCP.get(i).getNumero() == 7 && piècePersonnage > 7) {
                    Personnage personnagechoisi = this.piocheCP.get(i);
                    this.piocheCP.remove(i);
                    return personnagechoisi;
                } else if (piocheCP.get(i).getNumero() == 4 && piècePersonnage > 4 && nombreQuartierJaune > 3) {
                    Personnage personnagechoisi = this.piocheCP.get(i);
                    this.piocheCP.remove(i);
                    return personnagechoisi;
                } else if (piocheCP.get(i).getNumero() == 6 && piècePersonnage > 4 && nombreQuartierVert > 3) {
                    Personnage personnagechoisi = this.piocheCP.get(i);
                    this.piocheCP.remove(i);
                    return personnagechoisi;
                }
            }

                int indicePersonnageAleatoire = (int) (Math.random() * piocheCP.size());
                Personnage personnageAleatoire = this.piocheCP.get(indicePersonnageAleatoire);
                this.piocheCP.remove(indicePersonnageAleatoire);
                return personnageAleatoire;
            }
            return null;
    }

    public void reinitialiser() {
        this.piocheCP.clear();
        this.implementerCartesPersonnage();
    }

    public void implementerCartesPersonnage() {
        Personnage[] cp = new Personnage[8];
        cp[0] = new Assassin();
        cp[1] = new Voleur();
        cp[2] = new Magicien();
        cp[3] = new Roi();
        cp[4] = new Eveque();
        cp[5] = new Marchand();
        cp[6] = new Architecte();
        cp[7] = new Condottiere();

        for (int i = 0; i < 8; i++) {
            this.ajouterCartePersonnage(cp[i]);
        }
        this.melanger();
    }
}
