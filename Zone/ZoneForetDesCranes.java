package Zone;

import Modele.EtatJeu;
import Vue.Gui;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ZoneForetDesCranes implements IZone{

    private Gui gui;
    private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","crane","baton","hibou"));
    private Set<String> inventaire=new HashSet<String>();
    public ZoneForetDesCranes(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {
        gui.chargerImage("Images/crane/ForetDesAnciens.png");
        gui.afficherMessage("Vous êtes dans la Forêt des crânes. Choisissez une direction (NORD, SUD, EST).");
        gui.setEtat("foretDesCranes", "Vous êtes dans la Forêt des Anciens. Choisissez une direction (NORD, SUD, EST).");


    }

    @Override
    public void traiterCommande(String commande) {
        if(commande.toLowerCase()=="nord"){
           allerNord();

        }
        if (commande.toLowerCase()=="sud"){
            allerSud();
        }
        if (commande.toLowerCase()=="est"){
            allerEst();
        }
        if(elements.contains(commande.toLowerCase())){
            ajouteObj(commande.toLowerCase());
        }
        if(commande.toLowerCase()=="quitter"){
            quitterEtSauvegarder();
            }
        }

    private void allerNord() {
        gui.setEtatAvant("zoneNord");
        gui.setEtat("foretDesAnciens"," Vous etes au Nord");

    }

    private void allerSud() {
        gui.setEtatAvant("zoneSud");
        gui.setEtat("foretDesAnciens"," Vous etes au Sud");
    }

    private void allerEst() {
        gui.setEtatAvant("zoneEst");
        gui.setEtat("foretDesAnciens"," Vous etes au Est");
    }

    private void ajouteObj(String elt) {
        inventaire.add(elt);
        gui.setEtat("foretDesCranes", "Vous avez ajouter "+ elt);
    }

    private void quitterEtSauvegarder() {
        EtatJeu etatJeu= new EtatJeu(gui.getPseudo(),gui.list(),gui.getEtatAvant(), gui.getEtatActuel());
        gui.chargerImage("bye.png");
        gui.setEtat("foretDesCranes"," Aurevoir");
    }


}



