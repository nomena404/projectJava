package Zone;

import Vue.Gui;

import java.util.ArrayList;
import java.util.Arrays;

import static Controlleur.Controlleur.quitterEtSauvegarder;

public class ZoneForetDesCranes implements IZone{

    private boolean enigme =false;
    private Gui gui;
    private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","crane","baton","hibou"));

    public ZoneForetDesCranes(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {
        /*System.out.println(gui.getEtatActuel()+ "entree");
        System.out.println(gui.getEtatActuel()+ "entree etat ");

         */
        gui.chargerImage("Images/crane/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipalForet");
        gui.setEtat("foretDesCranes", "Vous êtes dans la Forêt des Cranes. Choisissez une direction (NORD, SUD, EST).");


    }

    @Override
    public void traiterCommande(String commande) {
        if ("nord".equals(commande.toLowerCase().trim())) {
            allerNord();
        } else if ("sud".equals(commande.toLowerCase().trim())) {
            allerSud();
        } else if ("est".equals(commande.toLowerCase().trim())) {
            allerEst();
        }else if("inventaire".equals(commande.toLowerCase().trim())){
            afficherInventaire();
        }
        else if ("pierrepierre".equals(commande.toLowerCase().trim()) ) {
            if(gui.list().contains("pierre") && gui.getZoneActuel()=="zoneEst"){
                afficheErmite();
            }

        }
        else if ("quitter".equals(commande.toLowerCase().trim())) {
            quitterEtSauvegarder();
        }
        else if ("retour".equals(commande.toLowerCase().trim())) {
           retour();
        }
        else if (elements.contains(commande.toLowerCase())) {
           ajouteObj(commande.toLowerCase());
        }else if ("lave".equals(commande.toLowerCase().trim())) {
            sortirZone();
            enigme=true;
        } else if ("suivant".equals(commande.toLowerCase().trim())) {
            if(enigme){
                sortirZone();
            }
        }

        else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }
    }

    private void afficherInventaire() {
        gui.setEtat("foretDesCranes","Voici les elements que vous possédez :\n"+ gui.inventaire());

    }

    private void sortirZone() {
      gui.setZoneActuel("zonePrincipal");
      gui.chargerImage("Images/grotte/zonePrincipal.png");
      gui.setEtat("grotteDesAnciens","bienvenue  dans la suite  ");
      gui.addElement("feu");
    }

    private void afficheErmite() {
        System.out.println("nord zone");
        System.out.println(gui.getEtatActuel() + " etat zone nord");
        gui.chargerImage("Images/crane/zoneEstEsprit.png");
        gui.setZoneActuel("zoneEstEsprit");
        gui.setEtat("foretDesCranes"," Vous etes a l'Est \n" + "\"Je suis plus chaude que le soleil, mais je ne brûle pas. Les hommes me cherchent dans \nles profondeurs de la terre et me trouvent dans les volcans.\n Les sorciers me maîtrisent avec des baguettes. Qui suis-je ?\"");


    }

    private void retour() {
        if(gui.getEtatActuel().equals("zoneEst")){
            allerEst();
        } else if (gui.getEtatActuel().equals("zoneSud")) {
            allerSud();
        }else if(gui.getEtatActuel().equals("zoneEst")){
            allerEst();
        }
        entrer();

    }

    private void allerNord() {
        System.out.println("nord zone");
        System.out.println(gui.getEtatActuel() + " etat zone nord");
        gui.chargerImage("Images/crane/zoneNord.png");
        gui.setZoneActuel("zoneNord");
        gui.setEtat("foretDesCranes"," Vous etes au Nord");

    }

    private void allerSud() {
        System.out.println("sud");
        gui.chargerImage("Images/crane/zoneSud.png");
        gui.setZoneActuel("zoneSud");
        gui.setEtat("foretDesCranes"," Vous etes au Sud");
    }

    private void allerEst() {
        System.out.println("est");
        gui.chargerImage("Images/crane/zoneEst.png");
        gui.setZoneActuel("zoneEst");
        gui.setEtat("foretDesCranes"," Vous etes a l'Est \n Dans une grotte sombre , donnez de la lumière pour les esprits\n" +
                "Donne les elements qui permettent de le faire , une combinaison d'éléménts que tu possédent");
    }

    private void ajouteObj(String elt) {
        gui.addElement(elt);
        gui.setEtat("foretDesCranes","Vous avez pris:"+elt + "\n" + "Voici la liste de vos éléments : "+ gui.inventaire());

    }



}



