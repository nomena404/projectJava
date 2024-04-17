package Zone;

import Modele.EtatJeu;
import Vue.Gui;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static Controlleur.Controlleur.quitterEtSauvegarder;

public class ZoneForetDesCranes implements IZone{

    private Gui gui;
    private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","crane","baton","hibou"));

    public ZoneForetDesCranes(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {
        System.out.println(gui.getEtatActuel()+ "entree");
        System.out.println(gui.getEtatAvant()+ "entree etat ");
        gui.chargerImage("Images/crane/zonePrincipal.png");
        gui.setEtatAvant("zonePrincipalForet");
        gui.setEtat("foretDesCranes", "Vous êtes dans la Forêt des Cranes. Choisissez une direction (NORD, SUD, EST).");


    }

    @Override
    public void traiterCommande(String commande) {
        System.out.println(commande);
        System.out.println("zone i ci");
        if ("nord".equals(commande.toLowerCase().trim())) {
            System.out.println("on est dans la boucle iff");
            allerNord();
        } else if ("sud".equals(commande.toLowerCase().trim())) {
            allerSud();
        } else if ("est".equals(commande.toLowerCase().trim())) {
            allerEst();
        } else if ("pierre_pierre".equals(commande.toLowerCase().trim()) && gui.list().contains("pierre")) {
            afficheErmite();
        }
        else if ("quitter".equals(commande.toLowerCase().trim())) {
            quitterEtSauvegarder();
        }
        else if ("retour".equals(commande.toLowerCase().trim())) {
           retour();
        }
        else if (elements.contains(commande.toLowerCase())) {
           ajouteObj(commande.toLowerCase());
        }else if ("enigme".equals(commande.toLowerCase().trim())) {
            sortirZone();
        }
        else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }
    }

    private void sortirZone() {
      gui.chargerImage("suite.png");
      gui.setEtatAvant("foretDesCranes");
      gui.setEtat("grotteDesAnciens","biennvenu dans la suite erik");
    }

    private void afficheErmite() {
        System.out.println("nord zone");
        System.out.println(gui.getEtatAvant() + " etat zone nord");
        gui.chargerImage("Images/crane/zoneEstEsprit.png");
        gui.setEtatAvant("zoneEst");
        gui.setEtat("foretDesCranes"," Vous etes a l'est" + "enigme");


    }

    private void retour() {
        if(gui.getEtatAvant().equals("zoneEst")){
            allerEst();
        } else if (gui.getEtatAvant().equals("zoneSud")) {
            allerSud();
        }else if(gui.getEtatAvant().equals("zoneEst")){
            allerEst();
        }
        entrer();

    }

    private void allerNord() {
        System.out.println("nord zone");
        System.out.println(gui.getEtatAvant() + " etat zone nord");
        gui.chargerImage("Images/crane/zoneNord.png");
        gui.setEtatAvant("zoneNord");
        gui.setEtat("foretDesCranes"," Vous etes au Nord");

    }

    private void allerSud() {
        System.out.println("sud");
        gui.chargerImage("Images/crane/zoneSud.png");
        gui.setEtatAvant("zoneSud");
        gui.setEtat("foretDesCranes"," Vous etes au Sud");
    }

    private void allerEst() {
        System.out.println("est");
        gui.chargerImage("Images/crane/zoneEst.png");
        gui.setEtatAvant("zoneEst");
        gui.setEtat("foretDesCranes"," Vous etes au Est");
    }

    private void ajouteObj(String elt) {
        gui.addElement(elt);
        gui.setEtat("foretDesCranes", "Vous avez ajouter "+ elt);
    }



}



