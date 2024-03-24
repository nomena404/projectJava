package Controlleur;

import Modele.EtatJeu;
import Modele.Exceptions.LePseudoExisteDéjà;
import Modele.NvlPseudo;
import Vue.Gui;
import Zone.ZoneForetDesAnciens;
import Zone.ZoneForetDesCranes;


public class Controlleur {
    private Gui vue;
    private ZoneForetDesAnciens foretDesAnciens;
    private ZoneForetDesCranes foretDesCranes;

    public Controlleur() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            vue = new Gui(this);
            foretDesAnciens = new ZoneForetDesAnciens(vue);
            foretDesCranes= new ZoneForetDesCranes(vue);
        });
    }

    public void nouvellePseudo(String pseudo) {
        try {
            // Vérifie si le pseudo existe déjà. Sinon, crée un nouvel utilisateur.
            if (!pseudo.isEmpty() && !NvlPseudo.pseudoExistant(pseudo)) {
                new NvlPseudo(pseudo); // Enregistre le nouveau pseudo
                vue.setPseudo(pseudo);
                vue.setEtat("demarrerNvJeu", "Pseudo accepté. Bienvenue " + pseudo + "!");
            } else {
                vue.afficherMessage("Ce pseudo est déjà pris ou invalide. Veuillez en choisir un autre.");
            }
        } catch (LePseudoExisteDéjà e) {
            vue.afficherMessage(e.getMessage());
        } catch (Exception e) {
            vue.afficherMessage("Une erreur est survenue : " + e.getMessage());
        }
    }


    public void verifierPseudoJoueur(String pseudo){
        System.out.println("Vérification du pseudo: " + pseudo); // Pour le débogage
        if(NvlPseudo.pseudoExistant(pseudo)){
            vue.setPseudo(pseudo);
            System.out.println("Le pseudo existe.");
            vue.setEtat("UtilisateurConnu", "Bienvenue " + pseudo);
        } else {
            System.out.println("Le pseudo n'existe pas."); // Pour le débogage
            vue.setEtat("PseudoJoueurInconnu", "Pseudo inconnu. Réessayer ou faire Retour.");
        }
    }




    public void traiterEntreeForet(String entree) {
        if ("foretDesAnciens".equals(vue.getEtatActuel())) {
            foretDesAnciens.traiterCommande(entree);
        }
        if("foretDesCranes".equals(vue.getEtatActuel())){
            foretDesCranes.traiterCommande(entree);
        }

    }

    /*public static void main(String[] args) {
        new Controlleur();
    }

     */

    public void firstZone(){
        foretDesAnciens.entrer();
    }

    public void lastZone() {
        EtatJeu etat=  EtatJeu.recupererEtatJeu(vue.getPseudo());

        System.out.println(EtatJeu.recupererEtatJeu(vue.getPseudo()));
        switch (etat.getZone()){

            case"foretDesAnciens":
                vue.setEtat("foretDesAnciens","Bienvenue +"+ vue.getPseudo());
                vue.chargerImage("Images/foret/"+etat.getEtat()+".png");
                break;
        }


    }
}
