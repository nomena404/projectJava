package tests;

import Vue.Gui;
import Zone.ZoneLabyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZoneLabyrintheTest {

    private Gui mockGui;
    private ZoneLabyrinthe zoneLabyrinthe;

    @BeforeEach
    public void setUp() {
        mockGui = new Gui(null); // Nous passons null car Gui n'est pas utilisé dans ces tests
        zoneLabyrinthe = new ZoneLabyrinthe(mockGui);
    }

    @Test
    public void testEntrer() {
        zoneLabyrinthe.entrer();
        assertEquals("zonePrincipal", mockGui.getZoneActuel());
        assertTrue(mockGui.getEtatActuel().contains("zoneLabyrinthe"));
        assertTrue(mockGui.txt.contains("Vous êtes dans une labyrinthe."));
    }

    @Test
    public void testTraiterCommande_nord() {
        zoneLabyrinthe.traiterCommande("nord");
        assertEquals("zoneAvecHache", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_sud() {
        mockGui.setZoneActuel("zonePrincipal");
        zoneLabyrinthe.traiterCommande("sud");
        assertEquals("zoneSud", mockGui.getZoneActuel());
    }

    @Test
    public void testTraiterCommande_inconnu() {
        zoneLabyrinthe.traiterCommande("commande inconnue");
        assertTrue(mockGui.txt.contains("Commande inconnue. Essayez à nouveau."));
    }

    @Test
    public void testTraiterCommande_prendre() {
        mockGui.setZoneActuel("zoneSud");
        zoneLabyrinthe.traiterCommande("prendre");
        assertEquals("zoneAvecHache", mockGui.getZoneActuel());
        assertTrue(mockGui.inventaire.contains("hache"));
    }
}
