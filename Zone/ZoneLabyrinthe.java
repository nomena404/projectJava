package Zone;

import Modele.Utile;
import Vue.Gui;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static Controlleur.Controlleur.quitterEtSauvegarder;

public class ZoneLabyrinthe implements  IZone{


    private boolean enigme =false;
    private Gui gui;
    private ArrayList<String> elements = new ArrayList<>(Arrays.asList("pierre","crane","baton","hibou"));

    public ZoneLabyrinthe(Gui gui){
        this.gui=gui;
    }
    @Override
    public void entrer() {

        gui.chargerImage("Images/labyrinthe/zonePrincipal.png");
        gui.setZoneActuel("zoneLabyrinthe");
        gui.setEtat("zoneLabyrinthe", "Vous êtes dans une labyrinthe. ");


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
        else if("feu".equals(commande.toLowerCase().trim())){
            if(gui.getZoneActuel().equals("zoneSouris")){
                lanceFeu();
            }

        }
        else if("retour".equals(commande.toLowerCase().trim())){
            if(gui.getZoneActuel().equals("zoneFeu")){
                zoneChoixFeu();
            }

        }else if("inventaire".equals(commande.toLowerCase().trim())){
            afficherInventaire();
        }
        else if("eau".equals(commande.toLowerCase().trim())){
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

        else if ("bjorn-ivar".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("maisonRagnark")){
                enigme2();
            }
        }
        else if ("eau_bateau".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("enigme2")){
                enigme3();
            }
        }

        else if ("esprit".equals(commande.toLowerCase().trim())) {
            if(gui.getZoneActuel().equals("enigme3")){
                enigme4();
                ajouteObj("esprit");
            }
        }
        else if ("iffes".equals(commande.toLowerCase().trim())) {
            if(Utile.StringEnList(gui.inventaire()).contains("esprit"))
                jeuFini();

        }
        else {
            gui.afficherMessage("Commande inconnue. Essayez à nouveau.");
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
                "Au cours de ta quete , tu as acquis des runes mais il t'en manque une," +
                "Ragnark t'as posé des question qui lui a relevé une grande ****se que tu as\n " +
                "il s'incline face à toi , pour finir le jeu \n" +
                "Tu dois citer dans l'ordre d'acquisition les runes que tu as récupérer ainsi le dernier que tu dois devinez" );

    }

    private void enigme3() {
        gui.chargerImage("Images/labyrinthe/maisonRagnark.png");
        gui.setZoneActuel("enigme3");
        gui.setEtat("zoneLabyrinthe","Excelent , Ragnark est épaté de votre intelligence " +
                "Avant une expédition , les vikings doivent faire des rituels  \n" +
                "Il le consulte avant de partir . Qui suis-je?" +
                "---Tu l'as rencontré au cours de ta quête---");

    }

    private void enigme2() {
        gui.chargerImage("Images/labyrinthe/maisonRagnark.png");
        gui.setZoneActuel("enigme2");
        gui.setEtat("zoneLabyrinthe","T'as pu répondre correctement à la question" +
                "Quels sont les deux éléments les plus importants pour la culture vikings lors de leur expédition \n" +
               "---Tu peux t'aider de tes inventaires---"+
                "--Vous deviez entre les noms exactement par Ordre l'une après l'autre");

    }

    private void maisonRagnark() {
        gui.chargerImage("Images/labyrinthe/maisonRagnark.png");
        gui.setZoneActuel("maisonRagnark");
        gui.setEtat("zoneLabyrinthe","Maintenant tu es face à face à Ragnark , le roi de Kattekat\n" +
                "il te demande des informations sur lui\n " +
                "Quel est le nom de mon premier fils\n" +
                "Le nom de ma deuxième Femme\n" +
                "Le nom de mon fils qui surnommé le Desosseux?\n" +
                "--Vous deviez entre les noms exactement par Ordre l'une après l'autre");
    }

    private void porteEnlever() {
        gui.chargerImage("Images/labyrinthe/zoneSansPortail.png");
        gui.setZoneActuel("zoneSansPortail");
        gui.setEtat("zoneLabyrinthe","Vous arrivez presque à la fin , \n sachez que vous deviez arriver indemme face à Ragnark\n Vous deviez passer un apercu face à ces gardes \n entrez la bonne commande");

    }

    private void zonePortail() {
        gui.chargerImage("Images/labyrinthe/zonePortail.png");
        gui.setZoneActuel("zonePortail");
        gui.setEtat("zoneLabyrinthe","Quel outils utiliserais vous pour casser cette porte?");

    }

    private void afficheEau() {
        gui.chargerImage("Images/labyrinthe/afficheEau.png");
        gui.setZoneActuel("afficheEau");
        gui.setEtat("zoneLabyrinthe","Vous êtes pris au piège, utilisez vos runes");

    }

    private void zoneChoix2() {
        gui.chargerImage("Images/labyrinthe/zoneChoix2.png");
        gui.setZoneActuel("zoneChoix2");
        gui.setEtat("zoneLabyrinthe","De nouvelle choix de direction à faire , choisissez la bonne");

    }

    private void zoneChoixFeu() {
        gui.chargerImage("Images/labyrinthe/zoneChoixFeu.png");
        gui.setZoneActuel("zoneChoixFeu");
        gui.setEtat("zoneLabyrinthe","Vous en êtes bien sorti , maintenat diriger vous vers \n la bonne direction");

    }

    private void lanceFeu() {
        gui.chargerImage("Images/labyrinthe/zoneFeu.png");
        gui.setZoneActuel("zoneFeu");
        gui.setEtat("zoneLabyrinthe","Les feux puissant ces misérables souris, \n utilisez la commande ** retour** pour la prochaine étape");

    }

    private void afficheSouris() {
        gui.chargerImage("Images/labyrinthe/zoneSouris.png");
        gui.setZoneActuel("zoneSouris");
        gui.setEtat("zoneLabyrinthe","Les souris vous attaquent , utilisez vos inventaires pour s'en débarasser");

    }

    private void afficheChoix() {
        gui.chargerImage("Images/labyrinthe/zoneChoix.png");
        gui.setZoneActuel("zoneChoix");
        gui.setEtat("zoneLabyrinthe","Choisissez une direction Nord - Est , des surprises vous attend");

    }

    private void soldatVaincu() {
        gui.chargerImage("Images/labyrinthe/zoneSoldatVaincu.png");
        gui.setZoneActuel("zoneSoldatVaincu");
        gui.setEtat("zoneLabyrinthe","Une victoire bien mérité , continuez votre lancé");

    }

    private void afficheAvecHache() {
        gui.chargerImage("Images/labyrinthe/zoneAvecHache.png");
        gui.setZoneActuel("zoneAvecHache");
        gui.setEtat("zoneLabyrinthe","Vous avez pris une hache, utilisez-le bien");
    }

    private void afficherInventaire() {
        gui.setEtat("zoneLabyrinthe","Voici les elements que vous possédez :\n"+ gui.inventaire());

    }






    private void allerNord() {
        gui.chargerImage("Images/labyrinthe/zoneAvecHache.png");
        gui.setZoneActuel("zoneAvecHache");
        gui.setEtat("zoneLabyrinthe"," Vous etes au Nord");

    }

    private void allerSud() {

        gui.chargerImage("Images/labyrinthe/zoneSud.png");
        gui.setZoneActuel("zoneSud");
        gui.setEtat("zoneLabyrinthe"," Vous etes au Sud , prenez ce dont vous en aurez besoin");

    }

    private void allerEst() {

        gui.chargerImage("Images/labyrinthe/zoneEstSoldat.png");
        gui.setZoneActuel("zoneEstSoldat");
        gui.setEtat("zoneLabyrinthe","Utilisez vos moyens pour vaincre ces soldats" );
    }

    private void ajouteObj(String elt) {
        gui.addElement(elt);
        gui.setEtat("zoneLabyrinthe","Vous avez pris:"+elt + "\n" + "Voici la liste de vos éléments : "+ gui.inventaire());

    }
}
