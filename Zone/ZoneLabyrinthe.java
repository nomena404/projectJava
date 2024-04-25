package Zone;

import Modele.BaseDonnee;
import Modele.Utile;
import Vue.Gui;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static Controlleur.Controlleur.quitterEtSauvegarder;

public class ZoneLabyrinthe implements  IZone{


    private Gui gui;

    public ZoneLabyrinthe(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {

        gui.chargerImage("Images/labyrinthe/zonePrincipal.png");
        gui.setZoneActuel("zonePrincipal");
        gui.setEtat("zoneLabyrinthe", "Vous êtes dans une labyrinthe.\n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif - Prendre ");


    }

    @Override
    public void traiterCommande(String commande) {
        if ("nord".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zoneAvecHache")){
                allerNord();
            }
            if(gui.getZoneActuel().equals("zoneSoldatVaincu")){
                afficheChoix();
            }
            if(gui.getZoneActuel().equals("zoneChoix")){
                afficheSouris();
            }


        } else if ("sud".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zonePrincipal")){
                allerSud();
            }
            if(gui.getZoneActuel().equals("zoneChoix2")){
                zonePortail();
            }

        }else if("prendre".equals(commande.toLowerCase().trim())){
            if(gui.getZoneActuel().equals("zoneSud")){
                afficheAvecHache();
                ajouteObj("hache");
            }

        } else if ("est".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("zoneAvecHache")){
                allerEst();
            }
            if(gui.getZoneActuel().equals("zoneChoix")){
                zoneChoix2();
            }
            if(gui.getZoneActuel().equals("zoneChoixFeu")){
                zoneChoix2();
            }
            if(gui.getZoneActuel().equals("zoneChoix2")){
                afficheEau();
            }

        } else if ("force_hache".equals(commande.toLowerCase().trim())) {
            if (gui.getZoneActuel().equals("zoneEstSoldat")) {
                soldatVaincu();
            }
        }
        else if("feu_hache".equals(commande.toLowerCase().trim())){
            if(gui.getZoneActuel().equals("zoneSouris")){
                lanceFeu();
            }

        }
        else if("retour".equals(commande.toLowerCase().trim())){
            entrer();


        }else if("retour_choix".equals(commande.toLowerCase().trim())){
            if(gui.getZoneActuel().equals("zoneFeu")){
                zoneChoixFeu();
            }
        }
        else if("inventaire".equals(commande.toLowerCase().trim())){
            afficherInventaire();
        }
        else if("eau_force".equals(commande.toLowerCase().trim())){
           if(gui.getZoneActuel().equals("afficheEau")){
               zoneChoix2();
           }
        }
        else if ("hache".equals(commande.toLowerCase().trim())) {
            System.out.println(gui.getZoneActuel());
            if(gui.getZoneActuel().equals("zonePortail")){
                porteEnlever();
            }
        }

        else if ("invisibilite".equals(commande.toLowerCase().trim())) {
            maisonRagnark();
        }


        else if ("quitter".equals(commande.toLowerCase().trim())) {
            quitterEtSauvegarder();
        }
        else if ("recommencer".equals(commande.toLowerCase().trim())) {
            gui.setEtat("demarrerNvJeu","Bonjour "+ gui.getPseudo());
            BaseDonnee.suppressionEtat(gui.getPseudo());
            gui.inventaire.clear();
        }

        else if ("bjorn-aslaugh-ivar".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("maisonRagnark")){
                enigme2();
            }
        }
        else if ("eau-bateau".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("enigme2")){
                enigme3();
            }
        }

        else if ("esprit".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("enigme3")){
                enigme4();
               gui.addElement(commande);
            }
        }
        else if ("iffes".equals(commande.toLowerCase().trim())) {
            if(gui.list().contains("esprit"))
                jeuFini();

        }
        else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.\n" +
                    "\"Commande possible :  Nord - Est -Sud -Retour - Quitter  -Recommencer- Aide -Objectif\"");
        }
    }

    private void jeuFini() {
        gui.chargerImage("Images/Accueil/end.png");
        gui.setZoneActuel("jeufini");
        gui.texte.setText("JEU FINI BRAVO");
        gui.entree.setVisible(false);
    }

    private void enigme4() {
        gui.chargerImage("Images/labyrinthe/maisonRagnark.png");
        gui.setZoneActuel("enigme4");
        gui.setEtat("zoneLabyrinthe","Vous avez presque fini  " +
                "Au cours de ta quete , tu as acquis des runes mais il t'en manque une\n," +
                "Ragnark t'as posé des question qui lui a relevé une grande ****se que tu as\n " +
                "il s'incline face à toi , pour finir le jeu \n" +
                "Tu dois citer dans l'ordre d'acquisition les runes que tu as récupérer\n" +
                " ainsi le dernier que tu dois devinez\n" +
                "Donnez la première lettre de chacune d'entre elle (exemple aass)" +
                "Commande possible :  Retour - Quitter - Aide  -Recommencer- Objectif" );

    }

    private void enigme3() {
        gui.chargerImage("Images/labyrinthe/maisonRagnark.png");
        gui.setZoneActuel("enigme3");
        gui.setEtat("zoneLabyrinthe","Excelent , Ragnark est épaté de votre intelligence\n" +
                " " +
                "Avant une expédition , les vikings doivent faire des rituels  \n" +
                "Il le consulte avant de partir . Qui suis-je?\n" +
                "---Tu l'as rencontré au cours de ta quête---" +
                "\nCommande possible :  Inventaire - Retour - Quitter  -Recommencer- Aide -Objectif");

    }

    private void enigme2() {
        gui.chargerImage("Images/labyrinthe/maisonRagnark.png");
        gui.setZoneActuel("enigme2");
        gui.setEtat("zoneLabyrinthe","T'as pu répondre correctement aux  questions\n" +
                "Quels sont les deux éléments les plus importants pour la culture vikings lors de leur expédition \n" +
               "---Tu peux t'aider de tes inventaires---\n"+
                "--Vous deviez entrer les éléments exactement par Ordre l'une \n" +
                "après l'autre, comme la réponse précédente\n" +
                "Commande possible :Inventaire - Retour - Quitter  -Recommencer- Aide -Objectif");

    }

    private void maisonRagnark() {
        gui.chargerImage("Images/labyrinthe/maisonRagnark.png");
        gui.setZoneActuel("maisonRagnark");
        gui.setEtat("zoneLabyrinthe","Maintenant tu es face à face à Ragnark , le roi de Kattekat\n" +
                "il te demande des informations sur lui\n " +
                "Quel est le nom de son premier fils?\n" +
                "Le nom de ma troisième Femme\n" +
                "Le nom de mon fils qui surnommé le Desosseux?\n" +
                "--Vous deviez entre les noms exactement par Ordre l'une après l'autre \n" +
                "par exemple abc-ds-ak  --\n" +
                "Commande possible : Inventaire - Retour  -Recommencer- Quitter - Aide -Objectif");
    }

    private void porteEnlever() {
        gui.chargerImage("Images/labyrinthe/zoneSansPortail.png");
        gui.setZoneActuel("zoneSansPortail");
        gui.setEtat("zoneLabyrinthe","Vous arrivez presque à la fin , \n sachez que vous deviez arriver indemme face à Ragnark\n Vous deviez passer un inapercu face à ces gardes \n entrez le bon élément de l'inventaire\n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour  -Recommencer- Quitter - Aide -Objectif");

    }

    private void zonePortail() {
        gui.chargerImage("Images/labyrinthe/zonePortail.png");
        gui.setZoneActuel("zonePortail");
        gui.setEtat("zoneLabyrinthe","Quel outil utiliserais vous pour casser cette porte?\n" +
                "Commande possible : Inventaire - Retour -  -Recommencer- Quitter - Aide -Objectif");

    }

    private void afficheEau() {
        gui.chargerImage("Images/labyrinthe/afficheEau.png");
        gui.setZoneActuel("afficheEau");
        gui.setEtat("zoneLabyrinthe","Vous êtes pris au piège, utilisez vos runes\n" +
                "Une comabinaison est resuise pour s'en sortir de là" +
                "Commande possible :  Inventaire - Retour - Quitter - Aide -Objectif");

    }

    private void zoneChoix2() {
        gui.chargerImage("Images/labyrinthe/zoneChoix2.png");
        gui.setZoneActuel("zoneChoix2");
        gui.setEtat("zoneLabyrinthe","De nouvelle choix de direction à faire , choisissez la bonne\n" +
                "Commande possible :  Sud - Est-Recommencer- Inventaire - Retour - Quitter - Aide -Objectif");

    }

    private void zoneChoixFeu() {
        gui.chargerImage("Images/labyrinthe/zoneChoixFeu.png");
        gui.setZoneActuel("zoneChoixFeu");
        gui.setEtat("zoneLabyrinthe","Vous en êtes bien sorti , maintenat diriger vous vers la bonne direction\n" +
                "Commande possible : Est- Inventaire  -Recommencer-Retour - Quitter - Aide -Objectif");

    }

    private void lanceFeu() {
        gui.chargerImage("Images/labyrinthe/zoneFeu.png");
        gui.setZoneActuel("zoneFeu");
        gui.setEtat("zoneLabyrinthe","Les feux puissant éradiquent ces misérables souris, \n utilisez la commande ** retour_choix** pour la prochaine étape\n" +
                "Commande possible : retour_choix- Inventaire-Recommencer - Retour - Quitter - Aide -Objectif");

    }

    private void afficheSouris() {
        gui.chargerImage("Images/labyrinthe/zoneSouris.png");
        gui.setZoneActuel("zoneSouris");
        gui.setEtat("zoneLabyrinthe","Les souris vous attaquent , utilisez vos inventaires pour s'en débarasser\n" +
                " Inventaire - Retour - Quitter - Aide-Recommencer -Objectif");

    }

    private void afficheChoix() {
        gui.chargerImage("Images/labyrinthe/zoneChoix.png");
        gui.setZoneActuel("zoneChoix");
        gui.setEtat("zoneLabyrinthe","Choisissez une direction Nord - Est , des surprises vous attend\n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour --Recommencer -Quitter - Aide -Objectif");

    }

    private void soldatVaincu() {
        gui.chargerImage("Images/labyrinthe/zoneSoldatVaincu.png");
        gui.setZoneActuel("zoneSoldatVaincu");
        gui.setEtat("zoneLabyrinthe","Une victoire bien mérité , continuez votre lancé\n" +
                "Commande possible : Nord - Inventaire - Retour-Recommencer - Quitter - Aide -Objectif");

    }

    private void afficheAvecHache() {
        gui.chargerImage("Images/labyrinthe/zoneAvecHache.png");
        gui.setZoneActuel("zoneAvecHache");
        gui.setEtat("zoneLabyrinthe","Vous avez pris une hache, utilisez-le bien\n" +
                "Commande possible : Est - Inventaire -RecommencerRetour - Quitter - Aide -Objectif");
    }

    private void afficherInventaire() {
        gui.setEtat("zoneLabyrinthe","Voici les elements que vous possédez :\n"+ gui.list() +"\n" +
                "Commande possible : Nord - Sud - Est- Inventaire-Recommencer - Retour - Quitter - Aide -Objectif");

    }






    private void allerNord() {
        gui.chargerImage("Images/labyrinthe/zoneAvecHache.png");
        gui.setZoneActuel("zoneAvecHache");
        gui.setEtat("zoneLabyrinthe"," Vous etes au Nord\n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif");

    }

    private void allerSud() {

        gui.chargerImage("Images/labyrinthe/zoneSud.png");
        gui.setZoneActuel("zoneSud");
        gui.setEtat("zoneLabyrinthe"," Vous etes au Sud , prenez ce dont vous en aurez besoin\n" +
                "Commande possible : Prendre - Inventaire - Retour - Quitter - Aide -Objectif");

    }

    private void allerEst() {

        gui.chargerImage("Images/labyrinthe/zoneEstSoldat.png");
        gui.setZoneActuel("zoneEstSoldat");
        gui.setEtat("zoneLabyrinthe","Utilisez vos moyens pour vaincre ces soldats\n" +"Vos inventaires pourraient fortement vous aider"+
                "Commande possible : Inventaire - Retour - Quitter - Aide -Objectif" );
    }

    private void ajouteObj(String elt) {
        gui.addElement(elt);
        gui.setEtat("zoneLabyrinthe","Vous avez pris:"+elt + "\n" + "Voici la liste de vos éléments : "+ gui.list()+"\n" +
                "Commande possible : Nord - Sud - Est- Inventaire - Retour - Quitter - Aide -Objectif");

    }
}
