package Affichage;

import javax.swing.*;
import java.util.*;
public class Jeu {
    private JButton Connexion;
    private JPanel non;
    private JButton jesp;
    private Gui gui;
    private Zone zoneCourante;
    private ArrayList<String> inventaire = new ArrayList<>();
    
    private void creerCarte() {
        Zone [] zones = new Zone [4];
        zones[0] = new Zone( "VillageVikingsAvant.png" );
        zones[1] = new Zone("grotteAvant.png" );
        zones[2] = new Zone("foretAvant.png" );
        zones[3] = new Zone("chateauAvant.png" );
        zones[0].ajouteSortie(Sortie.NORD, zones[1]);
        zones[1].ajouteSortie(Sortie.SUD, zones[0]);
        zones[0].ajouteSortie(Sortie.EST, zones[2]);
        zones[2].ajouteSortie(Sortie.OUEST, zones[0]);
        zones[3].ajouteSortie(Sortie.NORD, zones[0]);
        zones[0].ajouteSortie(Sortie.SUD, zones[3]);
        zoneCourante = zones[0]; 
    }
    // à utilser dans la methode prendre pour remplacer les photos après avoir pris les runes
    private void remplacerCarte(Zone zone, String nouveauNomImage) {
        // Vérifier si la zone existe dans la carte
        if (zone != null) {
            // Changer le nom de l'image associée à la zone
            zone.setNomImage(nouveauNomImage);

            // Afficher la nouvelle image dans l'interface graphique si elle est définie
            if (gui != null) {
                gui.afficheImage(nouveauNomImage);
            }
        }
       } 
    public void retourneInventaire() {
    	if (inventaire != null) {
            for (int i = 0; i < inventaire.size(); i++) {
                gui.afficher(inventaire.get(i));
            }
        } else {
            gui.afficher("L'inventaire est vide.");
        }
    }
    public void gererSortie(Sortie sortie) {
    	switch(sortie) {
    		case "NORD":
    			zoneCourante.
    			
    	}
    	
    }
    
}
