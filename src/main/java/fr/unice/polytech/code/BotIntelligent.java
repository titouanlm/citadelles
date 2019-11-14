package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.Architecte;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

public class BotIntelligent extends Bot {

    public BotIntelligent(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategie(PiocheCartesCitadelles piocheCartesCitadelles) {
        this.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);
        this.strategieConstruit();
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage) {
        this.setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement(this.nbPiece, this.cartesCitadellesEnMain,this.villeDuBot));
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
        } else {
            this.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());// Peut être déterminée par le joueur
        }
    }

    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0 && getNbPiece() >= 5) {
            return 0;
        } else {
            return 1;
        }
    }

    public void strategieConstruit() { //Construire le plus gros batiment d'un coup
        int i = 1;
        if (this.personnageACeTour instanceof Architecte) {
            i = 3;
        }
        while (i > 0) {
            boolean aConstruit = false;
            CarteCitadelles carteEnMainDePlusHauteValeur = this.rechercheCartePlusHauteValeurConstruisable();
            if (carteEnMainDePlusHauteValeur != null) {
                this.retirerPiece(carteEnMainDePlusHauteValeur.getPoint());
                this.villeDuBot.construireBatiment(carteEnMainDePlusHauteValeur);
                //System.out.println(this.nom + " a construit le batiment " + quartierÀConstruire.getNom() + " dans sa ville.");
                this.cartesCitadellesEnMain.remove(carteEnMainDePlusHauteValeur);
                aConstruit = true;
            }
            i--;
        }
    }

    public CarteCitadelles rechercheCartePlusHauteValeurConstruisable() {
        int valeurCartePlusHauteEnMain = 0;
        CarteCitadelles quartierAConstruire = null;
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain)
                    && valeurCartePlusHauteEnMain < carteEnMain.getPoint()) {
                valeurCartePlusHauteEnMain = carteEnMain.getPoint();
                quartierAConstruire = carteEnMain;
            }
        }
        return quartierAConstruire;
    }
}


