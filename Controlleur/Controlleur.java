package Controlleur;

import Modele.Exceptions.LePseudoExisteDéjà;
import Modele.NvlPseudo;
import Vue.Gui;
import Zone.ZoneForetDesAnciens;


public class Controlleur {
    private Gui vue;
    private ZoneForetDesAnciens foretDesAnciens;

    public Controlleur() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            vue = new Gui(this);
            foretDesAnciens = new ZoneForetDesAnciens(vue); // Initialisez la zone avec la vue
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
            System.out.println("Le pseudo existe."); // Pour le débogage
            vue.setEtat("UtilisateurConnu", "Bienvenue " + pseudo);
        } else {
            System.out.println("Le pseudo n'existe pas."); // Pour le débogage
            vue.setEtat("PseudoJoueurInconnu", "Pseudo inconnu. Réessayer ou faire Retour.");
        }
    }




    public void traiterEntreeUtilisateur(String entree) {
        if ("foretDesAnciens".equals(vue.getEtatActuel())) {
            foretDesAnciens.traiterCommande(entree);
        }
        // Ajoutez d'autres conditions pour d'autres zones/états
    }
    public void connexion(String pseudo) {
        // Ici, implémentez la vérification de l'existence du pseudo
        // Si le pseudo existe, chargez l'état du jeu pour cet utilisateur
        // Sinon, affichez un message d'erreur
        // Pour simplifier, on suppose que tout pseudo entré est valide
        vue.afficherMessage("Connexion réussie. Bienvenue " + pseudo + " !");
        // Simulez la charge de la dernière zone de l'utilisateur
        // Par exemple, charger la Forêt des Anciens si c'est là qu'ils se sont arrêtés
        vue.setEtat("foretDesAnciens", "Vous êtes dans la Forêt des Anciens. Choisissez une direction (NORD, SUD, EST).");
    }

    public static void main(String[] args) {
        new Controlleur();
    }

    public void firstZone(){
        foretDesAnciens.entrer();
    }

    public void lastZone() {
        // à implémenter
        vue.chargerImage("suite.png");
    }
}
