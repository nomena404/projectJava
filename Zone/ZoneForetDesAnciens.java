package Zone;

import Modele.EtatJeu;
import Vue.Gui;

import java.util.*;

public class ZoneForetDesAnciens implements IZone {
    public List<String> elemnts_nord=new ArrayList<>(Arrays.asList("tambour", "pierre", "buisson","fleur"));

    public List<String> elemnts_sud=new ArrayList<>(Arrays.asList("pierre", "baton"));

    private Gui gui;
    private  Set<String> inventaire=new HashSet<String>();

    public ZoneForetDesAnciens(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void entrer() {
        gui.chargerImage("Images/foret/ForetDesAnciens.png");
        gui.afficherMessage("Vous êtes dans la Forêt des Anciens. Choisissez une direction (NORD, SUD, EST).");
        gui.setEtat("foretDesAnciens", "Vous êtes dans la Forêt des Anciens. Choisissez une direction (NORD, SUD, EST).");

    }

    @Override
    public void traiterCommande(String commande) {
        switch (commande.toLowerCase()) {
            case "nord":
                allerAuNord();
                break;
            case "sud":
                allerAuSud();
                break;
            case "est":
                allerAuEst();
                break;
            case "prendre":
                prendreObjet();
                break;
            case "inventaire":
                afficherInventaire();
                break;
            case "pierre":
                ajoutInventaire(commande);
                break;
            case "tambour":
               ajoutInventaire(commande);
                break;
            case "baton":
                ajoutInventaire(commande);
                break;
            case "buisson":
                ajoutInventaire(commande);
                break;
            case "hache":
                ajoutInventaire(commande);
                break;
            case"tambour_baton":
                afficherErmite();

            case"arbre":
                sortirZone();
                break ;

            case "quitter":
                quitterEtSauvegarder();
                break ;

            default:
                gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
                break;
        }

    }

    private void quitterEtSauvegarder() {
        EtatJeu etatJeu= new EtatJeu(gui.getPseudo(),gui.list(),gui.getEtatAvant(), gui.getEtatActuel());
        gui.chargerImage("bye.png");
        gui.setEtat("foretDesAnciens"," Aurevoir");
    }

    private void sortirZone() {
        gui.chargerImage("suite.png");
        gui.setEtat("foretDesAnciens"," "+"Félicitations tu viens d'avoir une force incroyable :l'INVISIBILITE ");

    }

    private void afficherErmite() {
        gui.chargerImage("Images/foret/ZoneEstErmite.png");
        gui.setEtat("foretDesAnciens",""+"Je grandis sans fin, plus vieux que les montagnes,\n nourrissant la vie sans jamais la prendre. Qui suis-je ? ");

    }

    private void allerAuEst() {
        gui.chargerImage("Images/foret/ZoneEst.png");
        gui.setEtatAvant("ZoneEst");
        gui.setEtat("foretDesAnciens","Vous etes a l'est\n Utilisez deux  elements que tu possèdent  pour invoquer l'ermite\n Par exemple cle_serrure, maintenat à toi !!!");

    }

    private void afficherInventaire() {

        gui.setEtat("foretDesAnciens","Voici les elements que vous possédez :\n"+ gui.inventaire());
    }
    private void ajoutInventaire(String e){


        gui.addElement(e);
        gui.setEtat("foretDesAnciens","Vous avez pris:"+e);

    }

    private void prendreObjet() {
        if(gui.getEtatAvant()=="ZoneSud"){
            gui.setEtat("foretDesAnciens","Vous etes au Sud\n Prenez des elements");
  }
        if(gui.getEtatAvant()=="ZoneNord"){
            gui.setEtat("foretDesAnciens","Vous etes au Nord\n Prenez des elements");
        }


    }

    private void allerAuSud() {

        gui.chargerImage("Images/foret/ZoneSud.png");
        gui.setEtatAvant("zoneSud");
        gui.setEtat("foretDesAnciens","Vous etes au Sud\n Prenez des elements");
    }

    private void allerAuNord() {

        gui.chargerImage("Images/foret/ZoneNord.png");
        gui.setEtatAvant("ZoneNord");
        gui.setEtat("foretDesAnciens","Vous etes au Nord");

    }


    // Ajoutez la gestion des autres directions et commandes

}
