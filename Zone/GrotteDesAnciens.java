package Zone;

import Modele.BaseDonnee;
import Vue.Gui;

import java.util.ArrayList;
import java.util.Arrays;

import static Controlleur.Controlleur.aide;
import static Controlleur.Controlleur.quitterEtSauvegarder;

public class GrotteDesAnciens implements  IZone {
       private Gui gui;
        private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","eau"));

        public GrotteDesAnciens(Gui gui){
            this.gui=gui;
        }
        @Override
        public void entrer() {

            gui.chargerImage("Images/grotte/zonePrincipal.png");
            gui.setZoneActuel("zonePrincipal");
            gui.setEtat("grotteDesAnciens", "Vous êtes dans le Grotte des Anciens. \nChoisissez une direction (NORD, SUD, EST).\n" +
                    "Commandes possibles : Nord - Sud - Est - Aide ") ;
        }

        @Override
        public void traiterCommande(String commande) {
            if ("nord".equals(commande.toLowerCase().trim())) {
                allerNord();
            } else if ("sud".equals(commande.toLowerCase().trim())) {
                allerSud();
            } else if ("est".equals(commande.toLowerCase().trim())) {
                allerEst();
            }
            else if ("quitter".equals(commande.toLowerCase().trim())) {
                quitterEtSauvegarder();
            }
            else if ("recommencer".equals(commande.toLowerCase().trim())) {
                gui.setEtat("demarrerNvJeu","Bonjour "+ gui.getPseudo());
                BaseDonnee.suppressionEtat(gui.getPseudo());
                gui.inventaire.clear();
            }
            else if ("retour".equals(commande.toLowerCase().trim())) {
                retour();
            }
            else if ("inventaire".equals(commande.toLowerCase().trim())) {
                afficherInventaire();
            }
            else if ("boire".equals(commande.toLowerCase().trim()) ) {
                System.out.println(commande);
                System.out.println(gui.getZoneActuel().equals("zoneSud"));
                if((gui.getZoneActuel()).equals("zoneSud")){
                    afficheMeduse();
                }

            }
            else if("aide".equals(commande.toLowerCase().trim())){
                aide();
            }
            else if ("meduse".equals(commande.toLowerCase().trim()) ) {
                if(gui.getZoneActuel().equals("zoneSudMeduse")){
                    sortirZone();
                    gui.addElement("meduse");
                }

            }
            else if (elements.contains(commande.toLowerCase())) {
                ajouteObj(commande.toLowerCase());
            }
            else {
                gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
            }
        }

    private void afficherInventaire() {
        gui.setEtat("foretDesCranes","Voici les elements que vous possédez :\n"+ gui.list()+"\n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif");

    }

    private void sortirZone() {
        gui.chargerImage("Images/lac/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("lacSacre","Félicitations tu viens d'acquérir ton troisème rune : une FORCE inimaginable\n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif ");
        gui.addElement("force");
        gui.addElement("Rollo");
        gui.addElement("Ivar");
    }



        private void afficheMeduse() {

            gui.chargerImage("Images/grotte/zoneSudMeduse.png");
            gui.setZoneActuel("zoneSudMeduse");
            gui.setEtat("grotteDesAnciens"," Vous etes au Sud\n Je suis le roi des mers, un être redoutable avec mes t***  et mon regard pétrifiant\n. Dans les profondeurs de l'océan, je règne en maître. Cependant, \nmême les rois ont des liens de sang. Mon frère est un célèbre guerrier, redoutable Rollo \ndans son propre droit. Mon fils, surnommé Ivar Desosseux, est craint dans tout le royaume. \nQui suis-je ?\n" +
                    "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif" );

        }

        private void retour() {
            entrer(); }

        private void allerNord() {
            gui.chargerImage("Images/grotte/zoneNord.png");
            gui.setZoneActuel("zoneNord");
            gui.setEtat("grotteDesAnciens"," Vous êtes au Nord , prenez des éléments qui sont susceptibles\n de" +
                    "vous être utiles dans votre quêtes\n" +
                    "Elément présent : rocher" +
                    "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif-Boire");

        }

        private void allerSud() {

            gui.chargerImage("Images/grotte/zoneSud.png");
            gui.setZoneActuel("zoneSud");
            gui.setEtat("grotteDesAnciens"," Vous etes au Sud\n sachez que vous pouvez 'hydrater\n entrer la bonne commande\n" +
                    "Eléments présents : flac - rocher \n" +
                    "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif-Boire");
        }

        private void allerEst() {
            gui.chargerImage("Images/grotte/zoneEst.png");
            gui.setZoneActuel("zoneEst");
            gui.setEtat("grotteDesAnciens"," Vous etes a l' Est, que pensez vous faire?.. Libre à vous le choix\n" +
                    "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif -Boire");
        }

        private void ajouteObj(String elt) {
            gui.addElement(elt);
            gui.setEtat("grotteDesAnciens", "Vous avez ajouter "+ elt +"Voici la liste de vos éléments : \n"+ gui.list()+"\n" +
                    "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif");
        }




    }





