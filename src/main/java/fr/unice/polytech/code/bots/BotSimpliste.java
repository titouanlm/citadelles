package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.personnages.Architecte;
import fr.unice.polytech.code.personnages.Personnage;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

public class BotSimpliste extends Bot {

    public BotSimpliste(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategie(PiocheCartesCitadelles piocheCartesCitadelles) {
        this.strategieConstruitDesQuilPeut();
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible) {
        Personnage pChoisi = piocheCartesPersonnage.piocherPersonnageAleatoirement();
        this.setPersonnageACeTour(pChoisi);
        System.out.println(this.getNom() + " a pris le personnage " + pChoisi.getNom() + ".");
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
            System.out.println("Prend 2 pièces.");
        } else {
            CarteCitadelles cartePrise = piocheCartesCitadelles.piocher();
            this.ajouterCartesCitadellesDansMain(cartePrise);
            System.out.println("Choisit de prendre la carte " + cartePrise.getNom() + " dans sa main.");
        }
    }

    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0) {
            return (int) Math.round(Math.random());
        } else {
            return 1;
        }
    }

    public void strategieConstruitDesQuilPeut() {
        int i = 1;
        if (this.personnageACeTour != null) { //Inutile (déjà testé en début de tour)
            if (personnageACeTour instanceof Architecte) {
                i = 3;
            }
            while (i > 0) {
                for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
                    if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain)) {
                        retirerPiece(carteEnMain.getPoint()); //on retire les pieces
                        villeDuBot.construireBatiment(carteEnMain); //on ajoute la carte dans la ville
                        cartesCitadellesEnMain.remove(carteEnMain); //on retire la carte de la main
                        break;
                    }
                }
                i--;
            }
        }
    }
}




