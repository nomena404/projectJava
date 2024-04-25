package Zone;

import Modele.BaseDonnee;
import Modele.Utile;
import Vue.Gui;

import java.awt.*;
import java.util.*;
import java.util.List;

import static Controlleur.Controlleur.aide;
import static Controlleur.Controlleur.quitterEtSauvegarder;



public class ZoneForetDesAnciens implements IZone {
    public List<String> elemnts =new ArrayList<>(Arrays.asList("tambour", "pierre","baton", "buisson","fleur"));

    public List<String> elemnts_sud=new ArrayList<>(Arrays.asList("baton","pierre"));
    public List<String> elements_nord= new ArrayList<>(Arrays.asList("buisson","tambour","fleur","tronc"));
    public Set<String> cle= new HashSet<>(Arrays.asList("tambour","baton"));

    private Gui gui;

    public ZoneForetDesAnciens(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void entrer() {
        gui.chargerImage("Images/foret/ZonePrincipal.png");
        gui.setZoneActuel("ZonePrincipal");
        gui.setEtat("foretDesAnciens", "Vous êtes dans la Forêt des Anciens. \nChoisissez une direction (NORD, SUD, EST).\n" +
                "\nCommande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif ");

    }

    @Override
    public void traiterCommande(String commande)  {
        if("nord".equals(commande.toLowerCase().trim())){
            allerAuNord();
        } else if("sud".equals(commande.toLowerCase().trim())){
            allerAuSud();
        } else if("est".equals(commande.toLowerCase().trim())){
            allerAuEst();
        }  else if(elemnts_sud.contains(commande.toLowerCase())){
            if(gui.getZoneActuel().equals("ZoneSud") ){
                    ajoutInventaire(commande.toLowerCase());
                }
            }
        else if(elements_nord.contains(commande.toLowerCase())){
            if(gui.getZoneActuel().equals("ZoneNord") ){
                ajoutInventaire(commande.toLowerCase());
            }
        }
        else if("aide".equals(commande.toLowerCase().trim())) {
            aide();
        }
         else if("inventaire".equals(commande.toLowerCase().trim())){
            afficherInventaire();
        } else if("quitter".equals(commande.toLowerCase().trim())){
            quitterEtSauvegarder();
        } else if("arbre".equals(commande.toLowerCase().trim())){
            if(gui.getZoneActuel().equals("ZoneEstErmite")){
                sortirZone();
            }
        } else if("tambour_baton".equals(commande) ){
            System.out.println(gui.list());
            System.out.println((gui.list()).containsAll(cle));

            if((gui.list()).containsAll(cle)){
                afficherErmite();
            }

        } else if ("retour".equals(commande.toLowerCase())) {
            retour();

        }  else if ("objectif".equals(commande.toLowerCase())) {
            objectif();
        }
        else if ("recommencer".equals(commande.toLowerCase().trim())) {
            gui.setEtat("demarrerNvJeu","Bonjour "+ gui.getPseudo());
            BaseDonnee.suppressionEtat(gui.getPseudo());
            gui.inventaire.clear();
        }
        else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }

    }

    private void objectif() {
        gui.afficherMessage("Dans  cette partie , votre devez répondre  à une enigme  en\n" +
                "s'aider des  éléments présents (exemple :pierre , baton...)\n" +
                "maiiss , avant cela vous devez réveiller l'esprit du fôret" +
                "N'hésitez pas à explorer toutes les directions \n" +
                "Votre objectif est de récupérer une rune");
    }


    private void retour() {
        entrer();
    }


    private void sortirZone() {
        gui.chargerImage("Images/crane/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("foretDesCranes"," "+"Félicitations tu viens d'acquérir ta première rune :\n une force incroyable : l'INVISIBILITE\n " +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif -Recommencer ");
        gui.addElement("invisibilite");

    }

    private void afficherErmite() {
        gui.chargerImage("Images/foret/ZoneEstErmite.png");
        gui.setZoneActuel("ZoneEstErmite");
        gui.setEtat("foretDesAnciens",""+"Je grandis sans fin, plus vieux que les montagnes,\n nourrissant la vie sans jamais la prendre. Qui suis-je ?\n " +
                "Répondez correctement à l'énigme pour sortir de cette zone \n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif -Recommencer");

    }

    private void allerAuEst() {
        gui.chargerImage("Images/foret/ZoneEst.png");
        gui.setZoneActuel("ZoneEst");
        gui.setEtat("foretDesAnciens","Vous etes a l'est\n Utilisez deux  elements que tu possèdent  pour invoquer l'ermite\n " +
                "Par exemple cle_serrure, maintenat à toi !!!\n" +
                "Eléments présents : Pierre - Baton \n" +
                " Commande possible : Nord - Sud - Est -Recommencer- Inventaire - Retour - Quitter - Aide -Objectif ");

    }

    private void afficherInventaire() {

        gui.setEtat("foretDesAnciens","Voici les éléments que vous possédez :\n"+ gui.list()+"" +
                "\nCommande possible : Nord - Sud - Est- Inventaire  -Recommencer- Retour - Quitter - Aide -Objectif \")");

    }
    private void ajoutInventaire(String e){
        gui.addElement(e);
        gui.setEtat("foretDesAnciens","Vous avez pris:"+ e + "\n" +"" +
                "Commande possible : Nord - Sud - Est -Recommencer- Inventaire - Retour - Quitter - Aide -Objectif ");

    }

    private void allerAuSud() {

        gui.chargerImage("Images/foret/ZoneSud.png");
        gui.setZoneActuel("ZoneSud");
        gui.setEtat("foretDesAnciens","Vous êtes au Sud\n , prenez des éléments qui sont susceptibles\n de " +
                "vous être utiles dans votre quetes\n" +
                "Eléments présents : Pierre - Baton \n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer- Retour - Quitter - Aide -Objectif ");

    }

    private void allerAuNord() {
        gui.chargerImage("Images/foret/ZoneNord.png");
        gui.setZoneActuel("ZoneNord");
        gui.setEtat("foretDesAnciens","Vous êtes au Nord , prenez des éléments qui sont susceptibles\n de " +
                "vous être utiles dans votre quêtes\n" +
                "Eléments présents : Tambour - Fleur - Buisson- Tronc d'arbre  \n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer- Retour - Quitter - Aide -Objectif  ");

    }


}
