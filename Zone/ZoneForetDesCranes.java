package Zone;

import Modele.BaseDonnee;
import Modele.Utile;
import Vue.Gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Controlleur.Controlleur.aide;
import static Controlleur.Controlleur.quitterEtSauvegarder;

public class ZoneForetDesCranes implements IZone{

    private boolean enigme =false;
    private Gui gui;
    private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","crane","baton","hibou"));

    public List<String> elemnts_sud=new ArrayList<>(Arrays.asList("chat, hibou"));
    public List<String> elements_nord= new ArrayList<>(Arrays.asList("pierre","baton"));

    public ZoneForetDesCranes(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {

        gui.chargerImage("Images/crane/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipalForet");
        gui.setEtat("foretDesCranes", "Vous êtes dans la Forêt des Crânes. \nChoisissez une direction (NORD, SUD, EST)\n" +
                "  \"Commandes possibles : Nord - Sud - Est - Aide");


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
        else if ("pierre_pierre".equals(commande.toLowerCase().trim()) ) {
            if(gui.list().contains("pierre") && gui.getZoneActuel().equals("zoneEst")){
                afficheEsprit();
            }

        }
        else if ("quitter".equals(commande.toLowerCase().trim())) {
            quitterEtSauvegarder();
        }
        else if ("retour".equals(commande.toLowerCase().trim())) {
           retour();
        }
        else if (elements.contains(commande.toLowerCase())) {
            if(gui.getZoneActuel().equals("zoneNord")){
                if(elements_nord.contains(commande.toLowerCase().trim())){
                    ajouteObj(commande.toLowerCase());
                }
            }
            if(gui.getZoneActuel().equals("zoneSud")){
                if(elemnts_sud.contains(commande.toLowerCase().trim())){
                    ajouteObj(commande.toLowerCase());
                }
            }

        }else if ("lave".equals(commande.toLowerCase().trim())) {
            sortirZone();
            gui.addElement("lave");
        }
    else if ("aide".equals(commande.toLowerCase().trim())) {
       aide();
    }
        else if ("recommencer".equals(commande.toLowerCase().trim())) {
            gui.setEtat("demarrerNvJeu","Bonjour "+ gui.getPseudo());
            BaseDonnee.suppressionEtat(gui.getPseudo());
            gui.inventaire.clear();
        }
        else if ("obectif".equals(commande.toLowerCase().trim())) {
            objectif();
        }
        else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }
    }

    private void objectif() {
        gui.afficherMessage("Dans cette zone , vous pouvez aller dans les directions que \n" +
                "vous souhaitez , c'est la même principe , c'est à dire ,\n" +
                "Résoudre l'enigme et récupérer une rune mais avant\n" +
                "A vous de faire en sorte que l'enigme apparaît\n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer- Retour - Quitter - Aide -Objectif");
    }

    private void afficherInventaire() {
        gui.setEtat("foretDesCranes","Voici les elements que vous possédez :\n"+ gui.list()+" \n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer- Retour - Quitter - Aide -Objectif");

    }

    private void sortirZone() {
      gui.setZoneActuel("zonePrincipal");
      gui.chargerImage("Images/grotte/zonePrincipal.png");
      gui.setEtat("grotteDesAnciens","Félicitations tu viens d'acquérir ta deuxième rune :\n une force incroyable : maîtrise du FEU\n" +
              "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer-Retour - Quitter - Aide -Objectif");
      gui.addElement("feu");
    }

    private void afficheEsprit() {
        gui.chargerImage("Images/crane/zoneEstEsprit.png");
        gui.setZoneActuel("zoneEstEsprit");
        gui.setEtat("foretDesCranes"," Vous etes a l'Est \n" + "\"Je suis plus chaude que le soleil, mais je ne brûle pas. Les hommes me cherchent dans \nles profondeurs de la terre et me trouvent dans les volcans.\n Les sorciers me maîtrisent avec des baguettes. Qui suis-je ?\n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer- Retour - Quitter - Aide -Objectif");


    }

    private void retour() {
        entrer();
    }

    private void allerNord() {

        gui.chargerImage("Images/crane/zoneNord.png");
        gui.setZoneActuel("zoneNord");
        gui.setEtat("foretDesCranes"," Vous êtes au Nord , prenez des éléments qui sont susceptibles de\n" +
                " vous être utiles dans votre quêtes \n" +
                "Eléments présents : pierre - baton\n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer-Retour - Quitter - Aide -Objectif" +
                "");

    }

    private void allerSud() {
        System.out.println("sud");
        gui.chargerImage("Images/crane/zoneSud.png");
        gui.setZoneActuel("zoneSud");
        gui.setEtat("foretDesCranes"," Vous êtes au Sud , prenez des éléments qui sont susceptibles de\n" +
               "vous être utiles dans votre quêtes\n" +
                "Eléments présents : hibou - chat\n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer- Retour - Quitter - Aide -Objectif");
    }

    private void allerEst() {
        System.out.println("est");
        gui.chargerImage("Images/crane/zoneEst.png");
        gui.setZoneActuel("zoneEst");
        gui.setEtat("foretDesCranes"," Vous etes a l'Est \n Une être mystique se trouve dans la grotte sombre , donnez lui de la lumière\n" +
                "Donne les elements qui permettent de le faire , une combinaison d'éléménts\n que tu possédent" +
                "par exemple : chat_hibou \n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer-Retour - Quitter - Aide -Objectif");
    }

    private void ajouteObj(String elt) {
        gui.addElement(elt);
        gui.setEtat("foretDesCranes","Vous avez pris:"+elt + "\n" + "Voici la liste de vos éléments : "+ gui.list()+" \n" +
                "Commande possible : Nord - Sud - Est- Inventaire  -Recommencer-Retour - Quitter - Aide -Objectif");

    }



}



