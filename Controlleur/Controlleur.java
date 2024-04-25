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
import java.util.Set;

import static Modele.BaseDonnee.pseudoSauvegarde;
import static Modele.BaseDonnee.suppressionEtat;


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

    public  void firstZone(){
        foretDesAnciens.entrer();
    }

    // lastZone permet de charger le contenu du du joueur qui se connecte
    public void lastZone() throws IOException, ParseException {
        EtatJeu etat = EtatJeu.recupererEtatJeu(vue.getPseudo());
        System.out.println(etat.getZone() +" Last Zone\n");
        System.out.println(etat.getEtat() +" Last Zone");

        switch (etat.getEtat()) {
            case "foretDesAnciens":
                vue.setEtat("foretDesAnciens", "Heureux de vous revoir " + vue.getPseudo()+"\n" +
                        etat.getTexte()+"\n" );
                vue.setZoneActuel(etat.getZone());
                vue.chargerImage("Images/foret/" + etat.getZone() + ".png");
                break;

            case "foretDesCranes":
                vue.setEtat("foretDesCranes", "Heureux de vous revoir " + vue.getPseudo()+"\n" +
                        etat.getTexte()+" \n" );
                vue.setZoneActuel(etat.getZone());
                vue.chargerImage("Images/crane/" + etat.getZone() + ".png");
                break;
            case "grotteDesAnciens":
                vue.setEtat("grotteDesAnciens", "Heureux de vous revoir " + vue.getPseudo()+"\n" +
                        etat.getTexte()+"\n");
                vue.setZoneActuel(etat.getZone());
                vue.chargerImage("Images/grotte/" + etat.getZone() + ".png");
                break;

            case "lacSacre":
                vue.setEtat("lacSacre", "Heureux de vous revoir " + vue.getPseudo()+"\n" +
                        etat.getTexte()+" \n");
                vue.setZoneActuel(etat.getZone());
                vue.chargerImage("Images/lac/" + etat.getZone() + ".png");
                break;

            case "zoneLabyrinthe":
                vue.setEtat("zoneLabyrinthe", "Heureux de vous revoir " + vue.getPseudo()+"\n" +
                        etat.getTexte() +"\n" +
                        "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif");
                vue.setZoneActuel(etat.getZone());
                vue.chargerImage("Images/labyrinthe/" + etat.getZone() + ".png");
                break;
        }

    }


    public static void quitterEtSauvegarder() {
        if(!pseudoSauvegarde(vue.getPseudo())){
            BaseDonnee.ecritureEtatJson(vue.getPseudo(), vue.list(), vue.getEtatActuel(), vue.getZoneActuel(), vue.txt);
        }
       // Set<String> inv=vue.list();
        BaseDonnee.suppressionEtat(vue.getPseudo());
        BaseDonnee.ecritureEtatJson(vue.getPseudo(), vue.list(), vue.getEtatActuel(), vue.getZoneActuel(),vue.txt);
        vue.chargerImage("bye.png");
        vue.setEtat(vue.getEtatActuel(), " Aurevoir");
    }

    public static void aide() {
        vue.afficherMessage("Inventaire : Affiche la liste des inventaire que vous possédez\n" +
                "Quitter: Sauvegarde votre partie\n" +
                "Objectif : montre les objectifs à atteindre dans les zones" +
                "Boire: commande accessible que dans une zone en particulière" +
                "Retour : permet au joueur de revenir à l'état initial (ZonePrincipal) d'entrer (par exemple grotte des anciens)\n" +
                "Prendre : permet de prendre un élément\n"+"" +
                "Suivant : commande possible que à une certaine niveau du jeu\n" +
                "----- Il est important de savoir que lorsque vous entrez dans une zone \n, vous ne pouvez plus " +
                "en sortir tant que l'énigme n'est pas résolu-----\n" +
                "Recommencer : vous recommencez le jeu dès le début , et perdra tous vos inventaires" +

                "---Fin Aide --- \n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif ");
    }

}
