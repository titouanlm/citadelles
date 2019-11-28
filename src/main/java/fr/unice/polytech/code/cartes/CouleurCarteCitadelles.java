package fr.unice.polytech.code.cartes;

public enum CouleurCarteCitadelles {
    BLEU("\u001B[34m"),
    JAUNE("\u001B[33m"),
    VERT("\u001B[32m"),
    ROUGE("\u001B[31m"),
    VIOLET("\u001B[35m");

    private String couleur;

    CouleurCarteCitadelles(String couleur){
        this.couleur=couleur;
    }

    public String toString(){
        return couleur;
    }
}
