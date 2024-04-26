package tests;
import Modele.BaseDonnee;
import Modele.Exceptions.LePseudoExisteDéjà;
import Modele.NvlPseudo;
import Modele.Utile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class BaseDonneeTest {

    @Test
    void testEcritureLectureJson() throws LePseudoExisteDéjà {
        NvlPseudo nvlPseudo = new NvlPseudo("TestPseudo");
        BaseDonnee.ecritureEtatJson(nvlPseudo);
        JSONArray jsonArray = BaseDonnee.lectureJson();
        assertNotNull(jsonArray);
        assertFalse(jsonArray.isEmpty());
        assertEquals(1, jsonArray.size());
        assertEquals("TestPseudo", ((JSONObject) jsonArray.get(0)).get("pseudo"));
    }

    @Test
    void testEcritureLectureJsonEtat() {
        String pseudo = "TestPseudo";
        Set<String> inventaire = new HashSet<>();
        inventaire.add("objet1");
        inventaire.add("objet2");
        String etat = "testEtat";
        String zone = "testZone";
        String texte = "testTexte";
        BaseDonnee.ecritureEtatJson(pseudo, inventaire, etat, zone, texte);
        JSONArray jsonArray = BaseDonnee.lectureJsonEtat();
        assertNotNull(jsonArray);
        assertFalse(jsonArray.isEmpty());
        assertEquals(1, jsonArray.size());
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        assertEquals(pseudo, jsonObject.get("pseudo"));
        assertEquals(inventaire, Utile.StringEnList((String) jsonObject.get("Inventaire")));
        assertEquals(etat, jsonObject.get("Etat"));
        assertEquals(zone, jsonObject.get("Zone"));
        assertEquals(texte, jsonObject.get("Texte"));
    }
}