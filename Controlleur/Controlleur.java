package Controlleur;

import Vue.Gui;
import Modele.NvlPseudo;
import Modele.Exceptions.LePseudoExisteDéjà;

public class Controlleur {
    private Gui vue;

    public Controlleur() {
        javax.swing.SwingUtilities.invokeLater(() -> vue = new Gui(this));
    }

    public void verifierPseudo(String pseudo) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                if (!pseudo.isEmpty() && NvlPseudo.pseudoExistant(pseudo)) {
                    vue.afficherMessage("Ce pseudo est déjà pris. Veuillez en choisir un autre.");
                } else {
                    new NvlPseudo(pseudo); // Enregistre le nouveau pseudo
                    vue.setEtat("demarrerJeu", "Pseudo accepté. Appuyez sur 'Démarrer' pour commencer le jeu.");
                }
            } catch (LePseudoExisteDéjà e) {
                vue.afficherMessage(e.getMessage());
            } catch (Exception e) {
                vue.afficherMessage("Une erreur est survenue : " + e.getMessage());
            }
        });
    }

    public void actionDemarrer() {
        vue.setEtat("continuerJeu", "Jeu démarré. Appuyez sur 'Continuer' pour la suite du jeu.");

    }

    public void actionContinuer() {

        vue.afficherMessage("Bienvenue dans la première zone du jeu. Bonne chance !");

    }

    public static void main(String[] args) {
        new Controlleur();
    }
}
