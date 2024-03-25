package Zone;

import Modele.EtatJeu;
import Vue.Gui;

import java.util.*;

public class ZoneForetDesAnciens implements IZone {
    public List<String> elemnts =new ArrayList<>(Arrays.asList("tambour", "pierre", "buisson","fleur"));

    public List<String> elemnts_sud=new ArrayList<>(Arrays.asList("tambour", "baton"));

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
        if("nord".equals(commande.toLowerCase().trim())){
            allerAuNord();
        } else if("sud".equals(commande.toLowerCase().trim())){
            allerAuSud();
        } else if("est".equals(commande.toLowerCase().trim())){
            allerAuEst();
        } else if(elemnts.contains(commande.toLowerCase())){
            ajoutInventaire(commande.toLowerCase());
        } else if("inventaire".equals(commande.toLowerCase().trim())){
            afficherInventaire();
        } else if("quitter".equals(commande.toLowerCase().trim())){
            quitterEtSauvegarder();
        } else if("arbre".equals(commande.toLowerCase().trim())){
            sortirZone();
        } else if(gui.list().contains(Arrays.asList("tambour","baton")) && "tambour_baton".equals(commande.toLowerCase())){
            afficherErmite();
        } else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }


    }



    private void quitterEtSauvegarder() {
        EtatJeu etatJeu= new EtatJeu(gui.getPseudo(),gui.list(),gui.getEtatAvant(), gui.getEtatActuel());
        gui.chargerImage("bye.png");
        gui.setEtat("foretDesAnciens"," Aurevoir");
    }

    private void sortirZone() {

        System.out.println("zone Ici");
        gui.chargerImage("Images/crane/zonePrincipal.png");
        gui.setEtatAvant("foretDesAnciens");

        gui.setEtat("foretDesCranes"," "+"Félicitations tu viens d'avoir une force incroyable :l'INVISIBILITE\n suite");

        System.out.println(gui.getEtatActuel());

    }

    private void afficherErmite() {
        System.out.println("EST");
        gui.chargerImage("Images/foret/ZoneEstErmite.png");
        gui.setEtatAvant("ZoneEstErmite");
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
