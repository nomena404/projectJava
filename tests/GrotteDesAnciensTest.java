package tests;

import Vue.Gui;
import Zone.GrotteDesAnciens;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrotteDesAnciensTest {

    private Gui mockGui;
    private GrotteDesAnciens grotteDesAnciens;

    @BeforeEach
    public void setUp() {
        mockGui = new Gui(null); // Nous passons null car Gui n'est pas utilisé dans ces tests
        grotteDesAnciens = new GrotteDesAnciens(mockGui);
    }

    @Test
    public void testEntrer() {
        grotteDesAnciens.entrer();
        assertEquals("zonePrincipal", mockGui.getZoneActuel());
        assertTrue(mockGui.getEtatActuel().contains("grotteDesAnciens"));
        assertTrue(mockGui.getEtatActuel().contains("Vous êtes dans le Grotte des Anciens."));
    }

    @Test
    public void testTraitementCommande_nord() {
        grotteDesAnciens.traiterCommande("nord");
        assertEquals("zoneNord", mockGui.getZoneActuel());
    }

    @Test
    public void testTraitementCommande_inventaire() {
        grotteDesAnciens.traiterCommande("inventaire");
        assertTrue(mockGui.getEtatActuel().contains("grotteDesAnciens"));
        assertTrue(mockGui.txt.contains("Voici les éléments que vous possédez"));
    }

    @Test
    public void testTraitementCommande_sortirZone() {
        grotteDesAnciens.traiterCommande("meduse");
        assertEquals("zonePrincipal", mockGui.getZoneActuel());
        assertTrue(mockGui.getZoneActuel().contains("lacSacre"));
        assertTrue(mockGui.txt.contains("Félicitations tu viens d'acquérir ton troisième rune"));
        assertTrue(mockGui.list().contains("meduse"));
    }
}
