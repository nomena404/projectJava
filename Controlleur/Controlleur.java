package Controlleur;

import Modele.BaseDonnee;
import Modele.EtatJeu;
import Modele.Exceptions.LePseudoExisteDéjà;
import Modele.NvlPseudo;
import Modele.Utile;
import Vue.Gui;
import Zone.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class Controlleur {
    private static Gui vue;
    private ZoneForetDesAnciens foretDesAnciens;
    private ZoneForetDesCranes foretDesCranes;
    private GrotteDesAnciens grotteDesAnciens;
    private ZoneLabyrinthe zoneLabyrinthe;
    private ZoneLac zoneLac;
    public Controlleur() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            vue = new Gui(this);
            foretDesAnciens = new ZoneForetDesAnciens(vue);
            foretDesCranes= new ZoneForetDesCranes(vue);
            grotteDesAnciens=new GrotteDesAnciens(vue);
            zoneLac=new ZoneLac(vue);
            zoneLabyrinthe= new ZoneLabyrinthe(vue);
        });
    }

    public void nouvellePseudo(String pseudo) {
        try {
            if (!pseudo.isEmpty() && !NvlPseudo.pseudoExistant(pseudo)) {
                new NvlPseudo(pseudo);
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
        if(NvlPseudo.pseudoExistant(pseudo)){
            vue.setPseudo(pseudo);
            System.out.println("Le pseudo existe.");
            vue.setEtat("UtilisateurConnu", "Bienvenue " + pseudo);
        } else {
            vue.setEtat("PseudoJoueurInconnu", "Pseudo inconnu. Réessayer ou faire Retour.");
        }
    }




    public void traiterEntreeForet(String entree) throws IOException, ParseException {
            foretDesAnciens.traiterCommande(entree);
    }
    public  void traiterEntreeLac(String entree){
        zoneLac.traiterCommande(entree);
    }
    public void traiterEntreeCranes(String trim) {

        foretDesCranes.traiterCommande(trim);
    }
    public void traiterEntreeGrotte(String trim) {

        grotteDesAnciens.traiterCommande(trim);

    }


    public  void traiterEntreeLabyrinthe(String entree){
        zoneLabyrinthe.traiterCommande(entree);
    }

    public void firstZone(){
        foretDesAnciens.entrer();
    }

    // lastZone permet de charger le contenu du du joueur qui se connecte
    public void lastZone() throws IOException, ParseException {
        EtatJeu etat = EtatJeu.recupererEtatJeu(vue.getPseudo());
        System.out.println(etat);
        System.out.println(etat.getZone() +"holà");
        System.out.println(EtatJeu.recupererEtatJeu(vue.getPseudo()));
        switch (etat.getEtat()) {
            case "foretDesAnciens":
                vue.setEtat("foretDesAnciens", "Bienvenue +" + vue.getPseudo());
                vue.chargerImage("Images/foret/" + etat.getZone() + ".png");
                break;

            case "foretDesCranes":
                vue.setEtat("foretDesCranes", "Bienvenue +" + vue.getPseudo());
                vue.chargerImage("Images/crane/" + etat.getZone() + ".png");
                break;
            case "grotteDesAnciens":
                vue.setEtat("grotteDesAnciens", "Bienvenue +" + vue.getPseudo());
                vue.chargerImage("Images/grotte/" + etat.getZone() + ".png");
                break;

            case "lacSacre":
                vue.setEtat("lacSacre", "Bienvenue +" + vue.getPseudo());
                vue.chargerImage("Images/lac/" + etat.getZone() + ".png");
                break;

            case "zoneLabyrinthe":
                vue.setEtat("zoneLabyrinthe", "Bienvenue " + vue.getPseudo());
                vue.chargerImage("Images/labyrinthe/" + etat.getZone() + ".png");
                break;
        }

    }


    public static void quitterEtSauvegarder() {
        // si la liste est vide et le joueur n'est pas encore sauvegardé dans la base des etat
        if ((vue.list()).isEmpty() && !(EtatJeu.pseudoSauvegarde(vue.getPseudo()))) {
            vue.list().add("vide");
            BaseDonnee.ecritureEtatJson(vue.getPseudo(), vue.list(), vue.getEtatActuel(), vue.getZoneActuel());
            vue.chargerImage("bye.png");
            vue.setEtat(vue.getEtatActuel(), " Aurevoir");
       // si la liste est vide et le joueur est sauvegardé dans la base des etat

        }  else if((vue.list()).isEmpty() && (EtatJeu.pseudoSauvegarde(vue.getPseudo()))) {
            BaseDonnee.suppressionEtat(vue.getPseudo());
            BaseDonnee.ecritureEtatJson(vue.getPseudo(), Utile.StringEnList(vue.inventaire()), vue.getEtatActuel(), vue.getZoneActuel());
            vue.chargerImage("bye.png");
            vue.setEtat(vue.getEtatActuel(), " Aurevoir");
        }
        else  {
            BaseDonnee.suppressionEtat(vue.getPseudo());
            BaseDonnee.ecritureEtatJson(vue.getPseudo(), vue.list(), vue.getEtatActuel(), vue.getZoneActuel());
            vue.chargerImage("bye.png");
            vue.setEtat(vue.getEtatActuel(), " Aurevoir");
        }
    }


}
