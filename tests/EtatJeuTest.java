package tests;
import Modele.EtatJeu;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class EtatJeuTest {

    @Test
    void testRecupererEtatJeuExistant() {
        String pseudo = "TestPseudo";
        Set<String> inventaire = new HashSet<>();
        inventaire.add("objet1");
        inventaire.add("objet2");
        String etat = "testEtat";
        String zone = "testZone";
        String texte = "testTexte";
        EtatJeu etatJeu = EtatJeu.recupererEtatJeu(pseudo);
        assertNotNull(etatJeu);
        assertEquals(etat, etatJeu.getEtat());
        assertEquals(zone, etatJeu.getZone());
        assertEquals(texte, etatJeu.getTexte());
    }

    @Test
    void testRecupererEtatJeuInexistant() {
        String pseudoInexistant = "PseudoInexistant";
        EtatJeu etatJeu = EtatJeu.recupererEtatJeu(pseudoInexistant);
        assertNull(etatJeu);
    }
}
