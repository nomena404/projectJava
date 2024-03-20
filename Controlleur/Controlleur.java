package Controlleur;

import Modele.NvlPseudo;
import Modele.Exceptions.LePseudoExisteDéjà;
import Vue.Gui;

public class Controlleur {
    private Gui vue;

    public Controlleur() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            vue = new Gui(this); // Initialise la vue en passant ce contrôleur
        });
    }

    public void traiterEntreeUtilisateur(String input) {
        if (input == null || input.trim().isEmpty()) {
            vue.afficherMessage("Veuillez entrer un pseudo valide.");
            return;
        }

        try {
            if (NvlPseudo.pseudoExistant(input)) {
                // Le pseudo existe déjà, informer l'utilisateur
                vue.afficherMessage("Bienvenue " + input + ". Votre pseudo existe déjà. Appuyez sur OK pour démarrer.");
            } else {
                // Création d'un nouveau pseudo
                new NvlPseudo(input);
                vue.afficherMessage("Pseudo créé avec succès : " + input + ". Appuyez sur OK pour démarrer.");
            }
        } catch (LePseudoExisteDéjà e) {
            // Le pseudo existe déjà, gestion de l'exception
            vue.afficherMessage("Le pseudo " + input + " existe déjà. Essayez un autre pseudo.");
        } catch (Exception e) {
            // Gestion des autres exceptions
            vue.afficherMessage("Une erreur est survenue : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Controlleur();
    }
}
