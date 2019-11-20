package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.personnages.Personnage;
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

    public Personnage piocherPersonnageNonAleatoirement(int nbPieceJoueur, ArrayList<CarteCitadelles> carteEnMain, Ville villeJoueur) {
        if (piocheCP.size() > 0) {
            Personnage personnageChoisi;
            for(Personnage p : this.piocheCP ){
                if (p.getNumero() == 7 && nbPieceJoueur > 7) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("ARCHITECTE INTEL");
                    return personnageChoisi;
                } else if (p.getNumero() == 4 && nbPieceJoueur < 4 && villeJoueur.compterNbQuartiersJaune() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("ROI INTEL");
                    return personnageChoisi;
                } else if (p.getNumero() == 6 && nbPieceJoueur < 4 && villeJoueur.compterNbQuartiersVert() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("MARCHAND INTEL");
                    return personnageChoisi;
                } else if (p.getNumero() == 5 && nbPieceJoueur < 4 && villeJoueur.compterNbQuartiersBleu() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("EVEQUE INTEL");
                    return personnageChoisi;
                } else if (p.getNumero() == 8 && nbPieceJoueur < 3 && villeJoueur.compterNbQuartiersRouge() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("CONDOTTIERE INTEL");
                    return personnageChoisi;
                } else if (p.getNumero() == 1 && nbPieceJoueur > 5 && carteEnMain.size() < 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("ASSASSIN INTEL");
                    return personnageChoisi;
                } else if (p.getNumero() == 2 && nbPieceJoueur < 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("VOLEUR INTEL");
                    return personnageChoisi;
                } else if (p.getNumero() == 3 && nbPieceJoueur > 7 && carteEnMain.size() < 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    System.out.println("MAGICIEN INTEL");
                    return personnageChoisi;
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


    public boolean contient(String personnage) {
        for(Personnage p: piocheCP){
            if(p.getNom().equals(personnage)){
                return true;
            }
        }
        return false;
    }

    public Personnage prendre(String personnageChoisi) {
        if(piocheCP.size()>0){
            for(Personnage p : piocheCP){
                if(p.getNom().equals(personnageChoisi)){
                    this.piocheCP.remove(p);
                    return p;
                }
            }
        }
        return null;
    }
}
