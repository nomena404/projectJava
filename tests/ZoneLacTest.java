package tests;

import Vue.Gui;
import Zone.ZoneLac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZoneLacTest {

    private Gui mockGui;
    private ZoneLac zoneLac;

    @BeforeEach
    public void setUp() {
        mockGui = new Gui(null); // Nous passons null car Gui n'est pas utilisé dans ces tests
        zoneLac = new ZoneLac(mockGui);
    }

    @Test
    public void testEntrer() {
        zoneLac.entrer();
        assertEquals("zonePrincipal", mockGui.getZoneActuel());
        assertTrue(mockGui.getEtatActuel().contains("lacSacre"));
        assertTrue(mockGui.txt.contains("Vous êtes sur le lac sacré"));
    }

    @Test
    public void testTraiterCommande_est() {
        mockGui.setZoneActuel("zonePrincipal");
        zoneLac.traiterCommande("est");
        assertEquals("zoneEstBrouillard", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_force_baton() {
        mockGui.setZoneActuel("zoneNordBateau");
        zoneLac.traiterCommande("force_baton");
        assertEquals("zoneOasis", mockGui.getZoneActuel());
        assertTrue(mockGui.getEtatActuel().contains("lacSacre"));
        assertTrue(mockGui.txt.contains("Vous êtes sur l'oasis"));
    }

    @Test
    public void testTraiterCommande_inconnu() {
        zoneLac.traiterCommande("commande inconnue");
        assertTrue(mockGui.txt.contains("Commande inconnue. Essayez à nouveau."));
    }
}
