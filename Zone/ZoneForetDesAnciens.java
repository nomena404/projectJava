package Zone;

import Vue.Gui;

import java.util.*;

public class ZoneForetDesAnciens implements IZone {
    public List<String> invetaire=new ArrayList<>(Arrays.asList("tambour", "pierre", "baton","buisson","fleur"));

    private Gui gui;
    private  String  deb="Images/foret/";

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
            default:
                gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
                break;
        }




    }

    private void allerAuEst() {
        gui.chargerImage("Images/foret/JoueurEST.png");
        gui.setEtatAvant("zoneEst");
        gui.setEtat("foretDesAnciens","Vous etes a l'est");

    }

    private void afficherInventaire() {

    }

    private void prendreObjet() {

    }

    private void allerAuSud() {

        gui.chargerImage("Images/foret/AncienSud.png");
        gui.setEtatAvant("zoneSud");
        gui.setEtat("foretDesAnciens","Vous etes au Sud");
    }

    private void allerAuNord() {

        gui.chargerImage("Images/foret/AncienNordR.png");
        gui.setEtatAvant("zoneNord");
        gui.setEtat("foretDesAnciens","Vous etes au Nord");

    }


    // Ajoutez la gestion des autres directions et commandes

}
