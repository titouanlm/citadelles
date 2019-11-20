package fr.unice.polytech.code.cartes;

public enum CouleurCarteCitadelles {
    BLEU("BLEU"),
    JAUNE("JAUNE"),
    VERT("VERT"),
    ROUGE("ROUGE"),
    VIOLET("VIOLET");

    private String couleur;

    CouleurCarteCitadelles(String couleur){
        this.couleur=couleur;
    }

    public String toString(){
        return couleur;
    }
}
