package Zone;

import Modele.Utile;
import Vue.Gui;

import java.util.Arrays;

import static Controlleur.Controlleur.quitterEtSauvegarder;

public class ZoneLac implements IZone {

    private boolean enigme1= false;
    public String enigmeI="Dans le voile épais de l'aube grise,\n" +
            "Je suis le souffle qui dissipe la brise.\n" +
            "Avec des mots simples mais puissants,\n" +
            "Je peux lever le brouillard en un instant.Qui suis-je ?\n";
    public String enigmeII="En pleine mer ou sur la côte,\n" +
            "Je suis le gardien des routes les plus étroites.\n" +
            "Parfois en bois, parfois en pierre,\n" +
            "Je suis là pour protéger, fier et austère.Qui suis-je ?\n";
    private boolean enigme2= false;
    private boolean enigme3= false;
    private boolean oasis=false;
    private boolean pierre=false;
    private boolean mer= false;
    private Gui gui;

    public ZoneLac(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {

        gui.chargerImage("Images/lac/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("lacSacre", "Vous êtes dans la grotte Des Anciens. Choisissez une direction (NORD, SUD, EST).");


    }

    @Override
    public void traiterCommande(String commande) {

        if ("nord".equals(commande.toLowerCase().trim())) {

                prendreBateau();


        } else if ("sud".equals(commande.toLowerCase().trim())) {

                zoneCrabe();


                //zoneCrabe1();



        }else if ("force".equals(commande.toLowerCase().trim())) {
             avecForce();

        } else if ("vent".equals(commande.toLowerCase().trim())) {

                enleveBrouillard();

        }
        else if ("quitter".equals(commande.toLowerCase().trim())) {
            quitterEtSauvegarder();
        }
        else if ("phare".equals(commande.toLowerCase().trim())) {
                afficheBateau();

        }

        else if ("lire".equals(commande.toLowerCase().trim())) {

                afficheEcriture();
                //on affiche ce qui est ecrit
            }

        else if ("rune".equals(commande.toLowerCase().trim())) {

                afficheEcriture();

                //on affiche ce qui est ecrit
            }

        else if ("inventaire".equals(commande.toLowerCase().trim())) {
            afficherInventaire();
        }
        else if ("suivant".equals(commande.toLowerCase().trim())) {

            sortirZone();
        }

        else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }
    }

    private void prendreBateau() {
        gui.chargerImage("Images/lac/zoneNordBateau.png");
        gui.setZoneActuel("zoneNordBateau");
        gui.setEtat("lacSacre"," Vous etes sur un lac déchainé\n sachez que vous avez les capacités à s'en sortir \n entrez la bonne commande");

    }

    private void zoneCrabe() {
        gui.chargerImage("Images/lac/zoneSud.png");
        gui.setZoneActuel("zoneSud");
        gui.setEtat("lacSacre"," Vous etes au Sud\n "+enigmeI);

    }
    private void zoneCrabe1() {
        gui.chargerImage("Images/lac/zoneSud2.png");
        gui.setZoneActuel("zoneSud2");
        gui.setEtat("lacSacre"," Vous etes au Sud\n "+"Résolvez l'enigme pour acquérir un nouvel element crucial"+enigmeII);

    }

    private void afficheEcriture() {
        gui.chargerImage("Images/lac/zoneEcriture.png");
        gui.setZoneActuel("zoneEcriture");
        gui.setEtat("lacSacre"," Lisez bien et prenez notes des indications");

    }

    private void afficherInventaire() {
        gui.setEtat("lacSacre","Voici les elements que vous possédez :\n"+ gui.inventaire());

    }



    private void sortirZone() {
        gui.chargerImage("Images/labyrinthe/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("zoneLabyrinthe","biennvenu dans la suite , ");
        gui.addElement("phare");
        gui.addElement("rune");
        gui.addElement("eau");
        gui.addElement("vent");
        gui.addElement("force");

    }

    private void afficheBateau() {
        gui.chargerImage("Images/lac/zoneBateau.png");
        gui.setZoneActuel("zoneBateau");
        gui.setEtat("lacSacre"," Vous etes au Nord");

    }

    private void avecForce() {

        gui.chargerImage("Images/lac/zoneOasis.png");
        gui.setZoneActuel("zoneOasis");
        gui.setEtat("lacSacre"," Vous etes au Sud\n sachez que vous pouvez 'hydrater\n entrer la bonne commande");
    }

    private void enleveBrouillard() {

        gui.chargerImage("Images/lac/zoneSansBrouillard.png");
        gui.setZoneActuel("zoneEst");
        gui.setEtat("lacSacre"," Le vent s'est levé et les brouillards ainsi partis\n " +
                "Votre quetes n'est pas encore terminé");
    }


}

