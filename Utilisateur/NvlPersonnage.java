package Utilisateur;

public class NvlPersonnage {
    private String nom;
    private String prenom;
    private String pseudo;
    private int numero;
    private String mdp;
    private static int compteur=0;

    public NvlPersonnage(String nom, String prenom, String pseudo,  String mdp) {
        compteur++;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.numero = compteur;
        this.mdp = mdp;
    }

    public String getPseudo(){
        return pseudo;
    }
}
