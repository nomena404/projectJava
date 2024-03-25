package Zone;

import Modele.EtatJeu;
import Vue.Gui;

import java.util.ArrayList;
import java.util.Arrays;

public class GrotteDesAnciens implements  IZone {
       private Gui gui;
        private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","eau"));

        public GrotteDesAnciens(Gui gui){
            this.gui=gui;
        }
        @Override
        public void entrer() {
            System.out.println(gui.getEtatActuel()+ "entree");
            System.out.println(gui.getEtatAvant()+ "entree etat ");
            gui.chargerImage("Images/grotte/zonePrincipal.png");
            gui.setEtatAvant("zonePrincipal");
            gui.setEtat("grotteDesAnciens", "Vous êtes dans la grotte Des Anciens. Choisissez une direction (NORD, SUD, EST).");


        }

        @Override
        public void traiterCommande(String commande) {
            System.out.println(commande);
            System.out.println("zone grotte traiter ");
            if ("nord".equals(commande.toLowerCase().trim())) {
                System.out.println("on est dans la boucle iff");
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
            else if ("boire".equals(commande.toLowerCase().trim()) && gui.getEtatAvant().equals("zoneSud")) {
                afficheMeduse();
            }
            else if ("sortir".equals(commande.toLowerCase().trim()) && gui.getEtatAvant().equals("zoneEst")) {
                suivant();
            }

            else if (elements.contains(commande.toLowerCase())) {
                ajouteObj(commande.toLowerCase());
            }
            else {
                gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
            }
        }

    private void suivant() {
        gui.chargerImage("suite.png");
        gui.setEtatAvant("grotteDesAnciensEst");
        gui.setEtat("grotteDesAnciens","biennvenu dans la suite erik");

    }



        private void afficheMeduse() {

            gui.chargerImage("Images/grotte/zoneSudMeduse.png");
            gui.setEtatAvant("zoneSudMeduse");
            gui.setEtat("grotteDesAnciens"," Vous etes au Sud et la meduse  vient de vous conféré une pouvoir mystique " );


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
            gui.chargerImage("Images/grotte/zoneNord.png");
            gui.setEtatAvant("zoneNord");
            gui.setEtat("grotteDesAnciens"," Vous etes au Nord");

        }

        private void allerSud() {
            System.out.println("sud");
            gui.chargerImage("Images/grotte/zoneSud.png");
            gui.setEtatAvant("zoneSud");
            gui.setEtat("grotteDesAnciens"," Vous etes au Sud");
        }

        private void allerEst() {
            System.out.println("est");
            gui.chargerImage("Images/grotte/zoneEst.png");
            gui.setEtatAvant("zoneEst");
            gui.setEtat("grotteDesAnciens"," Vous etes au Est");
        }

        private void ajouteObj(String elt) {
            gui.addElement(elt);
            gui.setEtat("grotteDesAnciens", "Vous avez ajouter "+ elt);
        }

        private void quitterEtSauvegarder() {
            EtatJeu etatJeu= new EtatJeu(gui.getPseudo(),gui.list(),gui.getEtatAvant(), gui.getEtatActuel());
            gui.chargerImage("bye.png");
            gui.setEtat("grotteDesAnciens"," Aurevoir");
        }


    }





