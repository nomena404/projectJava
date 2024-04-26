package tests;

import Vue.Gui;
import Zone.ZoneForetDesCranes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class ZoneForetDesCranesTest {

    private Gui mockGui;
    private ZoneForetDesCranes zoneForetDesCranes;

    @BeforeEach
    public void setUp() {
        mockGui = new Gui(null); // Nous passons null car Gui n'est pas utilisé dans ces tests
        zoneForetDesCranes = new ZoneForetDesCranes(mockGui);
    }

    @Test
    public void testEntrer() {
        zoneForetDesCranes.entrer();
        assertEquals("zonePrincipalForet", mockGui.getZoneActuel());
        assertTrue(mockGui.getEtatActuel().contains("foretDesCranes"));
        assertTrue(mockGui.txt.contains("Vous êtes dans la Forêt des Crânes."));
    }

    @Test
    public void testTraiterCommande_nord() {
        zoneForetDesCranes.traiterCommande("nord");
        assertEquals("zoneNord", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_sud() {
        zoneForetDesCranes.traiterCommande("sud");
        assertEquals("zoneSud", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_est() {
        zoneForetDesCranes.traiterCommande("est");
        assertEquals("zoneEst", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_inventaire() {
        zoneForetDesCranes.traiterCommande("inventaire");
        assertTrue(mockGui.getEtatActuel().contains("foretDesCranes"));
        assertTrue(mockGui.txt.contains("Voici les éléments que vous possédez"));
    }

    @Test
    public void testTraiterCommande_pierre_pierre() {
        Set<String> inventaire = mockGui.list();
        inventaire.add("pierre");
        zoneForetDesCranes.traiterCommande("pierre_pierre");
        assertEquals("zoneEstEsprit", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_inconnu() {
        zoneForetDesCranes.traiterCommande("commande inconnue");
        assertTrue(mockGui.txt.contains("Commande inconnue. Essayez à nouveau."));
    }
}
