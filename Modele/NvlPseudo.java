package Modele;

import Modele.Exceptions.LePseudoExisteDéjà;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NvlPseudo extends BaseDonnee {
    private String pseudo;
    private final int numero;
    private static int compteur = 0;

    public NvlPseudo(String pseudo) throws LePseudoExisteDéjà {
        if (pseudoExistant(pseudo)) {
            throw new LePseudoExisteDéjà("Le pseudo " + pseudo + " existe déjà.");
        }
        this.pseudo = pseudo;
        compteur++;
        numero = compteur;
        ecritureJson(this); // Directement sauvegarder le nouveau pseudo
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero + "  " + pseudo;
    }

    // Vérifie si le pseudo existe déjà dans le fichier JSON
    public static boolean pseudoExistant(String ps) {
        JSONArray jsonArray = lectureJson();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            String s = (String) jsonObject.get("pseudo");
            if (s.equals(ps)) {
                return true;
            }
        }
        return false;
    }
}
