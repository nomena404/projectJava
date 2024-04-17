package Controlleur;

import Modele.BaseDonnee;
import Modele.EtatJeu;
import Modele.Exceptions.LePseudoExisteDéjà;
import Modele.NvlPseudo;
import Vue.Gui;
import Zone.GrotteDesAnciens;
import Zone.ZoneForetDesAnciens;
import Zone.ZoneForetDesCranes;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class Controlleur {
    private static Gui vue;
    private ZoneForetDesAnciens foretDesAnciens;
    private ZoneForetDesCranes foretDesCranes;
    private GrotteDesAnciens grotteDesAnciens;

    public Controlleur() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            vue = new Gui(this);
            foretDesAnciens = new ZoneForetDesAnciens(vue);
            foretDesCranes= new ZoneForetDesCranes(vue);
            grotteDesAnciens=new GrotteDesAnciens(vue);
        });
    }

    public void nouvellePseudo(String pseudo) {
        try {
            if (!pseudo.isEmpty() && !NvlPseudo.pseudoExistant(pseudo)) {
                NvlPseudo pseudo1=  new NvlPseudo(pseudo);
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
        System.out.println("Vérification du pseudo: " + pseudo);
        if(NvlPseudo.pseudoExistant(pseudo)){
            vue.setPseudo(pseudo);
            System.out.println("Le pseudo existe.");
            vue.setEtat("UtilisateurConnu", "Bienvenue " + pseudo);
        } else {
            System.out.println("Le pseudo n'existe pas."); // Pour le débogage
            vue.setEtat("PseudoJoueurInconnu", "Pseudo inconnu. Réessayer ou faire Retour.");
        }
    }




    public void traiterEntreeForet(String entree) throws IOException, ParseException {
        if ("foretDesAnciens".equals(vue.getEtatActuel())) {
            foretDesAnciens.traiterCommande(entree);
        }

    }

    public void firstZone(){
        foretDesAnciens.entrer();
    }

    // lastZone permet de charger le contenu du du joueur qui se connecte
    public void lastZone() throws IOException, ParseException {
        EtatJeu etat=  EtatJeu.recupererEtatJeu(vue.getPseudo());
        System.out.println(etat);
        System.out.println(EtatJeu.recupererEtatJeu(vue.getPseudo()));
        switch (etat.getZone()){
            case"foretDesAnciens":
                vue.setEtat("foretDesAnciens","Bienvenue +"+ vue.getPseudo());
                vue.chargerImage("Images/foret/"+etat.getEtat()+".png");
                break;

            case"foretDesCranes":
                vue.setEtat("foretDesCranes","Bienvenue +"+ vue.getPseudo());
                vue.chargerImage("Images/crane/"+etat.getEtat()+".png");
                break;
            case"grotteDesAnciens":
                vue.setEtat("grotteDesAnciens","Bienvenue +"+ vue.getPseudo());
                vue.chargerImage("Images/grotte/"+etat.getEtat()+".png");
                break;
        }


    }


    public void traiterEntreeCranes(String trim) {

        if("foretDesCranes".equals(vue.getEtatActuel())){
            foretDesCranes.traiterCommande(trim);

        }
    }
    public void traiterEntreeGrotte(String trim) {
        System.out.println("hola crane");
        if("grotteDesAnciens".equals(vue.getEtatActuel())){

            grotteDesAnciens.traiterCommande(trim);

        }
    }


    public static void quitterEtSauvegarder() {
        if ((vue.list()).isEmpty()) {
            vue.list().add("vide");
        } else if (EtatJeu.pseudoSauvegarde(vue.getPseudo())) {
            BaseDonnee.suppressionEtat(vue.getPseudo());
            BaseDonnee.ecritureEtatJson(vue.getPseudo(), vue.list(), vue.getEtatAvant(), vue.getEtatActuel());
        } else {
            BaseDonnee.ecritureEtatJson(vue.getPseudo(), vue.list(), vue.getEtatAvant(), vue.getEtatActuel());
            vue.chargerImage("bye.png");
            vue.setEtat(vue.getEtatActuel(), " Aurevoir");
        }
    }

    public void next() {
        foretDesCranes.entrer();
    }
    public  void nextt(){ grotteDesAnciens.entrer();}
}
