package tests;

import Vue.Gui;
import Zone.ZoneForetDesAnciens;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class ZoneForetDesAnciensTest {

    private Gui mockGui;
    private ZoneForetDesAnciens zoneForetDesAnciens;

    @BeforeEach
    public void setUp() {
        mockGui = new Gui(null); // Nous passons null car Gui n'est pas utilisé dans ces tests
        zoneForetDesAnciens = new ZoneForetDesAnciens(mockGui);
    }

    @Test
    public void testEntrer() {
        zoneForetDesAnciens.entrer();
        assertEquals("ZonePrincipal", mockGui.getZoneActuel());
        assertTrue(mockGui.getEtatActuel().contains("foretDesAnciens"));
        assertTrue(mockGui.txt.contains("Vous êtes dans la Forêt des Anciens."));
    }

    @Test
    public void testTraiterCommande_nord() {
        zoneForetDesAnciens.traiterCommande("nord");
        assertEquals("ZoneNord", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_sud() {
        zoneForetDesAnciens.traiterCommande("sud");
        assertEquals("ZoneSud", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_est() {
        zoneForetDesAnciens.traiterCommande("est");
        assertEquals("ZoneEst", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_inventaire() {
        zoneForetDesAnciens.traiterCommande("inventaire");
        assertTrue(mockGui.getEtatActuel().contains("foretDesAnciens"));
        assertTrue(mockGui.txt.contains("Voici les éléments que vous possédez"));
    }

    @Test
    public void testTraiterCommande_tambour_baton() {
        Set<String> inventaire = mockGui.list();
        inventaire.add("tambour");
        inventaire.add("baton");
        zoneForetDesAnciens.traiterCommande("tambour_baton");
        assertEquals("ZoneEstErmite", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_inconnu() {
        zoneForetDesAnciens.traiterCommande("commande inconnue");
        assertTrue(mockGui.txt.contains("Commande inconnue. Essayez à nouveau."));
    }
}
