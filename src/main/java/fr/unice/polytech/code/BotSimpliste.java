package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

public class BotSimpliste extends Bot {

    public BotSimpliste(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void setUpTypeBot(){
        Typedubot="Bête";
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles, Personnage personnageactuel) {
        if (personnageactuel != null) {
            if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
                this.ajouterPiece(2);
            } else {
                this.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());// Peut être déterminée par le joueur
            }
        }
    }

    @Override
    public void strategie(PiocheCartesCitadelles piocheCartesCitadelles) {
        this.choisirPiocherOuPrendrePiece(piocheCartesCitadelles, personnageACeTour);
        this.strategieConstruitDesQuilPeut(personnageACeTour);
    }


    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0) {
            return (int) Math.round(Math.random());
        } else {
            return 1;
        }
    }

    public void strategieConstruitDesQuilPeut(Personnage personnageactuel) {
        int i = 1;
        if (personnageactuel != null) {
            if (personnageactuel.getNom() == "Architecte") {
                i = 3;
            }
            while (i > 0) {
                boolean aConstruit = false;
                for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
                    if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain.getNom())) {
                        retirerPiece(carteEnMain.getPoint()); //on retire les pieces
                        villeDuBot.construireBatiment(carteEnMain); //on ajoute la carte dans la ville
                        cartesCitadellesEnMain.remove(carteEnMain); //on retire la carte de la main
                        aConstruit = true;
                        break;
                    }
                }
                i--;
            }
        }
    }
}




