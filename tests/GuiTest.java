package tests;
import Vue.Gui;
import Controlleur.Controlleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class GuiTest {

    private Gui gui;

    @BeforeEach
    public void setUp() {
        Controlleur controlleur = new Controlleur();
        gui = new Gui(controlleur);
    }

    @Test
    public void testInitialisation() {
        // Vérifie que la fenêtre a été créée avec succès
        assertNotNull(gui.fenetre);
        // Vérifie que la fenêtre est visible
        assertTrue(gui.fenetre.isVisible());
        // Vérifie que les éléments GUI ont été initialisés correctement
        assertNotNull(gui.texte);
        assertNotNull(gui.imageLabel);
        assertNotNull(gui.entree);
        assertNotNull(gui.demarrerBtn);
        assertNotNull(gui.continuerBtn);
        assertNotNull(gui.retour);
        // Vérifie les valeurs initiales des champs de texte et des états
        assertEquals("accueil", gui.getEtatActuel());
        assertEquals("", gui.getZoneActuel());
        assertEquals("", gui.txt);
        assertEquals("", gui.getPseudo());
        assertEquals(0, gui.inventaire.size());
    }

    @Test
    public void testChargerImage() {
        // Vérifier le chargement d'une image
        String cheminImage = "Images/Accueil/Accueil.png";
        gui.chargerImage(cheminImage);
        assertEquals(cheminImage, ((ImageIcon) gui.imageLabel.getIcon()).getDescription());
    }

    @Test
    public void testSetZoneActuel() {
        // Vérifie le changement de la zone actuelle
        String nouvelleZone = "foret";
        gui.setZoneActuel(nouvelleZone);
        assertEquals(nouvelleZone, gui.getZoneActuel());
    }

    @Test
    public void testSetEtat() {
        // Vérifie le changement d'état
        String nouvelEtat = "nouvelEtat";
        String message = "Un nouveau message";
        gui.setEtat(nouvelEtat, message);
        assertEquals(nouvelEtat, gui.getEtatActuel());
        assertEquals(message, gui.txt);
    }

    @Test
    public void testAddElement() {
        // Vérifie l'ajout d'un élément à l'inventaire
        String element = "épée";
        gui.addElement(element);
        assertTrue(gui.inventaire.contains(element));
    }
}
