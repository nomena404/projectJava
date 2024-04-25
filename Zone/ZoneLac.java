package Zone;

import Modele.BaseDonnee;
import Vue.Gui;

import static Controlleur.Controlleur.aide;
import static Controlleur.Controlleur.quitterEtSauvegarder;

public class ZoneLac implements IZone {


    public String enigmeI="Dans le voile épais de l'aube grise,\n" +
            "Je suis le souffle qui dissipe la brise.\n" +
            "Avec des mots simples mais puissants,\n" +
            "Je peux lever le brouillard en un instant.Qui suis-je ?\n";
    public String enigmeII="En pleine mer ou sur la côte,\n" +
            "Je suis le gardien des routes les plus étroites.\n" +
            "Parfois en bois, parfois en pierre,\n" +
            "Je suis là pour protéger, fier et austère.Qui suis-je ?\n";

    private String  enigme3= "Dans les ombres des sagas, je me dévoile,\n" +
            "Entre les pages des mythes, je m'étoile.\n" +
            "Mon nom résonne dans les halls des rois,\n" +
            "Je suis la raison de ta quête\n" +
            "Qui suis-je, gardien des secrets des bois";

    private Gui gui;

    public ZoneLac(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {

        gui.chargerImage("Images/lac/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("lacSacre", "Vous êtes  sur le lac sacré\n" +
                ". Choisissez une direction (NORD, SUD, EST)\n" +
                "Commande possible : Nord -Est- Inventaire - Retour - Quitter - Aide -Objectif");


    }

    @Override
    public void traiterCommande(String commande) {

        if ("nord".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zoneBateau")){
                prendreBateau();
            }else{
                gui.afficherMessage("Ce commande ne peut pas être encore utilisé");

            }


        } else if ("est".equals(commande.toLowerCase().trim())) {
            System.out.println(" LAC EST");
            System.out.println(gui.getZoneActuel()+" zone lac est");
            System.out.println(gui.getZoneActuel().equals("zonePrincipal") + " est lac");
            if(gui.getZoneActuel().equals("zonePrincipal")){
                zoneCrabe();

            }


        }else if ("force_baton".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zoneNordBateau")){
                avecForce();
            }

        } else if ("vent".equals(commande.toLowerCase().trim())) {
            System.out.println(gui.getZoneActuel()+ "lac");
            if(gui.getZoneActuel().equals("zoneEstBrouillard")){
                enleveBrouillard();
                gui.addElement("vent");
            }

        }
        else if ("quitter".equals(commande.toLowerCase().trim())) {
            quitterEtSauvegarder();
        }
        else if ("phare".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zoneSansBrouillard")){
                afficheBateau();
                gui.addElement("phare");
            }
        }

        else if ("lire".equals(commande.toLowerCase().trim())) {

            if(gui.getZoneActuel().equals("zoneEcriture")) {
              ecriture();
            }
            }

        else if ("rune".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zoneOasis")){
                afficheEcriture();
                gui.addElement("rune");
            }
            }

        else if ("inventaire".equals(commande.toLowerCase().trim())) {
            afficherInventaire();
        }
        else if ("suivant".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zoneEcriture") ){
                sortirZone();
            }

        }
        else if("aide".equals(commande.toLowerCase().trim())){
            aide();

        }else if("retour_crabe".equals(commande.toLowerCase().trim())){
            if(gui.getZoneActuel().equals("zoneEloigne")){
                zoneCrabe1();
            }


        }else if("objectif".equals(commande.toLowerCase().trim())){
            objectif();

        } else if ("retour".equals(commande.toLowerCase().trim())) {
          entrer();

        }
        else if ("recommencer".equals(commande.toLowerCase().trim())) {
            gui.setEtat("demarrerNvJeu","Bonjour "+ gui.getPseudo());
            BaseDonnee.suppressionEtat(gui.getPseudo());
            gui.inventaire.clear();
        }else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
        }
    }

    private void objectif() {
        gui.afficherMessage("Dans cette partie ,votre objectif est de résoudre les enigme\n" +
                "en suivant les indication, il n'y a pas d'éléments à récupérer\n" +
                "Commande possible : Nord  - Est- Inventaire - Retour - Quitter - Aide -Objectif");
    }

    private void prendreBateau() {
        gui.chargerImage("Images/lac/zoneNordBateau.png");
        gui.setZoneActuel("zoneNordBateau");
        gui.setEtat("lacSacre"," Vous êtes sur un lac déchainé\n sachez que vous avez les capacités à s'en sortir \n entrez la bonne commande \n  Les inventaires peuvent vous être utile" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif");

    }

    private void zoneCrabe() {
        gui.chargerImage("Images/lac/zoneEstBrouillard.png");
        gui.setZoneActuel("zoneEstBrouillard");
        gui.setEtat("lacSacre"," Vous etes a l'Est\n " + "  "+
                "Répondez correctement à l'énigme \n"+enigmeI +"\n" +
                "Commande possible :Est- Inventaire - Retour - Quitter - Aide -Objectif");

    }
    private void zoneCrabe1() {
        gui.chargerImage("Images/lac/zoneSansBrouillard.png");
        gui.setZoneActuel("zoneSansBrouillard");
        gui.setEtat("lacSacre"," Vous etes à l'Est\n "+"Résolvez l'enigme pour acquérir un nouvel element crucial\n"+enigmeII +"\n" +
                "Commande possible : Est- Inventaire - Retour - Quitter - Aide -Objectif");

    }

    private void afficheEcriture() {
        gui.chargerImage("Images/lac/zoneEcriture.png");
        gui.setZoneActuel("zoneEcriture");
        gui.setEtat("lacSacre"," Lisez bien et prenez notes des indications\n" +
                "Entrer la commande **suivant ** pour sortir de la zone \n" +
                "Commande possible : lire -suivant-  Quitter - Aide -Objectif");

    }

    private void afficherInventaire() {
        gui.setEtat("lacSacre","Voici les elements que vous possédez :\n"+ gui.list() +" \n" +
                "Commande possible : Nord - Est- Inventaire - Retour - Quitter - Aide -Objectif");

    }



    private void sortirZone() {
        gui.chargerImage("Images/labyrinthe/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("zoneLabyrinthe"," Félicitations tu viens d'acquérir ton quatrième rune :\n une force incroyable : maîtrise du l'EAU\n " +
                "Commande possible : Sud -  Inventaire - Retour - Quitter - Aide -Objectif");
        gui.addElement("rune");
        gui.addElement("eau");
        gui.addElement("vent");
        gui.addElement("force");
        gui.addElement("bjorn");
        gui.addElement("Aslaugh");
        gui.addElement("Lagertha");


    }

    private void afficheBateau() {
        gui.chargerImage("Images/lac/zoneBateau.png");
        gui.setZoneActuel("zoneBateau");
        gui.setEtat("lacSacre"," Bravo, vous venez d'acquérir un bateau\n " +
                "Entrez Nord pour prendre le bateau et se diiriger  vers  l'Oasis\n" +
                "Commande possible : Nord - Inventaire - Retour - Quitter - Aide -Objectif");
        gui.addElement("bateau");

    }

    private void avecForce() {

        gui.chargerImage("Images/lac/zoneOasis.png");
        gui.setZoneActuel("zoneOasis");
        gui.setEtat("lacSacre"," Vous etes sur l'oasis\n sachez que vous pouvez lire ce qui est ecrit sur \nla pierre " +
                "Mais à condition de répondre correctement à l'énigme\n, "+ enigme3+" \nentrez la bonne combinaison ( par exemple :hache_feu)\n" +
                "Commande possible :  Inventaire - Retour - Quitter - Aide -Objectif" +
                "");
    }

    private void enleveBrouillard() {

        gui.chargerImage("Images/lac/zoneEloigne.png");
        gui.setZoneActuel("zoneEloigne");
        gui.setEtat("lacSacre"," Le vent s'est levé et les brouillards ainsi partis\n " +
                "Votre quetes n'est pas encore terminé \n" +
                "Retournez vers le crabe\n" +
                "Commande possible : retour_crabe - Inventaire - Retour - Quitter - Aide -Objectif");
    }

    private void ecriture(){
        gui.chargerImage("Images/lac/zoneEcriture.png");
        gui.setZoneActuel("zoneEcriture");
        gui.setEtat("lacSacre"," Ragnar Lodbrok, une figure légendaire des vikings, est connu\n" +
                " pour ses exploits maritimes et son mariage avec trois femmes importantes :\n" +
                " Lagertha, Thóra Borgarhjǫrtr et Aslaug. \n" +
                "Son premier fils, Bjorn Ironside, est né de son union avec Lagertha. \n" +
                "Parmi ses nombreuses expéditions, Lindisfarne, en Angleterre, est souvent citée comme l'une de ses premières \n" +
                "cibles lors de ses raids en Europe occidentale.\n" +
                "Commande possible : Suivant -Inventaire - Retour - Quitter - Aide -Objectif");
    }


}

