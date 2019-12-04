package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.personnages.Personnage;
import fr.unice.polytech.code.personnages.*;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCartesPersonnage {
    private ArrayList<Personnage> piocheCP;
    private Affichage affichage;

    public PiocheCartesPersonnage(Affichage affichage) {
        this.piocheCP = new ArrayList<>();
        this.affichage = affichage;
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

    public Personnage piocherPersonnageNonAleatoirement(Bot joueurQuiPioche) {
        if (piocheCP.size() > 0) {
            Personnage personnageChoisi;
            for(Personnage p : this.piocheCP ){
                if (p.getNumero() == 7 && joueurQuiPioche.getNbPiece() > 7 && joueurQuiPioche.getCartesCitadellesEnMain().size() > 1) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    return personnageChoisi;
                } else if (p.getNumero() == 4 && joueurQuiPioche.getNbPiece() < 4 && joueurQuiPioche.getVilleDuBot().compterNbQuartiersJaune() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    return personnageChoisi;
                } else if (p.getNumero() == 6 && joueurQuiPioche.getNbPiece() < 4 && joueurQuiPioche.getVilleDuBot().compterNbQuartiersVert() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    return personnageChoisi;
                } else if (p.getNumero() == 5 && joueurQuiPioche.getNbPiece() < 4 && joueurQuiPioche.getVilleDuBot().compterNbQuartiersBleu() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    return personnageChoisi;
                } else if (p.getNumero() == 8 && joueurQuiPioche.getNbPiece() < 3 && joueurQuiPioche.getVilleDuBot().compterNbQuartiersRouge() > 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    return personnageChoisi;
                } else if (p.getNumero() == 1 && joueurQuiPioche.getNbPiece() > 5 && joueurQuiPioche.getCartesCitadellesEnMain().size() < 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    return personnageChoisi;
                } else if (p.getNumero() == 2 && joueurQuiPioche.getNbPiece() < 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
                    return personnageChoisi;
                } else if (p.getNumero() == 3 && joueurQuiPioche.getNbPiece() > 7 && joueurQuiPioche.getCartesCitadellesEnMain().size() < 2) {
                    personnageChoisi = p;
                    this.piocheCP.remove(p);
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
        cp[0] = new Assassin(this.affichage);
        cp[1] = new Voleur(this.affichage);
        cp[2] = new Magicien(this.affichage);
        cp[3] = new Roi(this.affichage);
        cp[4] = new Eveque(this.affichage);
        cp[5] = new Marchand(this.affichage);
        cp[6] = new Architecte(this.affichage);
        cp[7] = new Condottiere(this.affichage);

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