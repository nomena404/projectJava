package Zone;

import Modele.Utile;
import Vue.Gui;

import java.util.*;

import static Controlleur.Controlleur.quitterEtSauvegarder;
import static Modele.BaseDonnee.ecritureEtatJson;

public class ZoneForetDesAnciens implements IZone {
    public List<String> elemnts =new ArrayList<>(Arrays.asList("tambour", "pierre","baton", "buisson","fleur"));

    public Set<String> cle= new HashSet<>(Arrays.asList("tambour","baton"));

    private Gui gui;

    public ZoneForetDesAnciens(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void entrer() {
        gui.chargerImage("Images/foret/ZonePrincipal.png");
       // gui.afficherMessage("Vous êtes dans la Forêt des Anciens. Choisissez une direction (NORD, SUD, EST).");
        gui.setEtat("foretDesAnciens", "Vous êtes dans la Forêt des Anciens. Choisissez une direction (NORD, SUD, EST).");

    }

    @Override
    public void traiterCommande(String commande)  {
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
            if(gui.getZoneActuel()=="ZoneEstErmite"){
                sortirZone();
            }

        } else if("tambourbaton".equals(commande) ){
            /*System.out.println(Utile.StringEnList(gui.inventaire()) + "avant boucke" +
                    "");
            System.out.println(gui.list() + "list ");
            System.out.println((gui.list()).contains(cle));

             */
            if((gui.list()).containsAll(cle)){
                System.out.println(Utile.StringEnList(gui.inventaire()));
                System.out.println(Arrays.asList("tambour","baton"));
                afficherErmite();
            }
            else if(!(gui.list()).containsAll(cle)){
                gui.afficherMessage("Commande inconnue. Essayez à nouveau.");

            }

        } else if ("retour".equals(commande.toLowerCase())) {
            retour();
        } else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }

    }

    private void retour() {
        entrer();
    }


    private void sortirZone() {

        //System.out.println("zone Ici");
        gui.chargerImage("Images/crane/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("foretDesCranes"," "+"Félicitations tu viens d'avoir une force incroyable :l'INVISIBILITE\n suite");
         gui.addElement("invisibilite");

    }

    private void afficherErmite() {
        //System.out.println("EST");
        gui.chargerImage("Images/foret/ZoneEstErmite.png");
        gui.setZoneActuel("ZoneEstErmite");
        gui.setEtat("foretDesAnciens",""+"Je grandis sans fin, plus vieux que les montagnes,\n nourrissant la vie sans jamais la prendre. Qui suis-je ? ");

    }

    private void allerAuEst() {
        gui.chargerImage("Images/foret/ZoneEst.png");
        gui.setZoneActuel("ZoneEst");
        gui.setZoneAvant("ZoneEst");
        gui.setEtat("foretDesAnciens","Vous etes a l'est\n Utilisez deux  elements que tu possèdent  pour invoquer l'ermite\n Par exemple cle_serrure, maintenat à toi !!!");

    }

    private void afficherInventaire() {

        gui.setEtat("foretDesAnciens","Voici les elements que vous possédez :\n"+ gui.inventaire());

    }
    private void ajoutInventaire(String e){
      gui.addElement(e);
        gui.setEtat("foretDesAnciens","Vous avez pris:"+e + "\n" + "Voici la liste de vos éléments : "+ gui.inventaire());

    }

    private void allerAuSud() {

        gui.chargerImage("Images/foret/ZoneSud.png");
        gui.setZoneActuel("zoneSud");
        gui.setZoneAvant("zoneSud");
        gui.setEtat("foretDesAnciens","Vous etes au Sud\n , prenez des éléments qui sont susceptibles\n de " +
                "vous etre utiles dans votre quetes ");
    }

    private void allerAuNord() {

        gui.chargerImage("Images/foret/ZoneNord.png");
        gui.setZoneActuel("ZoneNord");
        gui.setZoneAvant("ZoneNord");
        gui.setEtat("foretDesAnciens","Vous etes au Nord , prenez des éléments qui sont susceptibles\n de " +
                "vous etre utiles dans votre quetes ");

    }




}
