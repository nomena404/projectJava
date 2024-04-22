package Zone;

import Modele.EtatJeu;
import Vue.Gui;

import java.util.ArrayList;
import java.util.Arrays;

import static Controlleur.Controlleur.quitterEtSauvegarder;

public class GrotteDesAnciens implements  IZone {
       private Gui gui;
        private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","eau"));

        public GrotteDesAnciens(Gui gui){
            this.gui=gui;
        }
        @Override
        public void entrer() {
            System.out.println(gui.getEtatActuel()+ "entree");
            System.out.println(gui.getEtatActuel()+ "entree etat ");
            gui.chargerImage("Images/grotte/zonePrincipal.png");
            gui.setZoneActuel("zonePrincipal");
            gui.setEtat("grotteDesAnciens", "Vous êtes dans la grotte Des Anciens. Choisissez une direction (NORD, SUD, EST).");


        }

        @Override
        public void traiterCommande(String commande) {
            System.out.println(commande);
            System.out.println("zone grotte traiter ");
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
            else if ("meduse".equals(commande.toLowerCase().trim()) ) {
                if(gui.getZoneActuel().equals("zoneSudMeduse")){
                    suivant();
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
        gui.setEtat("foretDesCranes","Voici les elements que vous possédez :\n"+ gui.inventaire());

    }

    private void suivant() {
        gui.chargerImage("suite.png");
        gui.setZoneActuel("grotteDesAnciensEst");
        gui.setEtat("grotteDesAnciens","biennvenu dans la suite , vous venez de passer à l'étape suivante");
        gui.addElement("Rollo");
        gui.addElement("Ivar");
    }



        private void afficheMeduse() {

            gui.chargerImage("Images/grotte/zoneSudMeduse.png");
            gui.setZoneActuel("zoneSudMeduse");
            gui.setEtat("grotteDesAnciens"," Vous etes au Sud\n Je suis le roi des mers, un être redoutable avec mes tentacules et mon regard pétrifiant\n. Dans les profondeurs de l'océan, je règne en maître. Cependant, \nmême les rois ont des liens de sang. Mon frère est un célèbre guerrier, redoutable \ndans son propre droit. Mon fils, surnommé le Desosseur, est craint dans tout le royaume. \nQui suis-je ?" );


        }

        private void retour() {
            gui.chargerImage("Images/crane/zoneEst.png");
            gui.setZoneActuel("zoneEst");
            gui.setEtat("foretDesCranes", "Bonjour vous etes de retour dans la zone Forêt de cranes");
        }

        private void allerNord() {
            gui.chargerImage("Images/grotte/zoneNord.png");
            gui.setZoneActuel("zoneNord");
            gui.setEtat("grotteDesAnciens"," Vous etes au Nord");

        }

        private void allerSud() {
            System.out.println("sud");
            gui.chargerImage("Images/grotte/zoneSud.png");
            gui.setZoneActuel("zoneSud");
            gui.setEtat("grotteDesAnciens"," Vous etes au Sud\n sachez que vous pouvez 'hydrater\n entrer la bonne commande");
        }

        private void allerEst() {
            System.out.println("est");
            gui.chargerImage("Images/grotte/zoneEst.png");
            gui.setZoneActuel("zoneEst");
            gui.setEtat("grotteDesAnciens"," Vous etes au Est");
        }

        private void ajouteObj(String elt) {
            gui.addElement(elt);
            gui.setEtat("grotteDesAnciens", "Vous avez ajouter "+ elt);
        }




    }





