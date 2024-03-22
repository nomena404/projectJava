package Modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EtatJeu extends BaseDonnee {
    private static final String ETAT_JEU = "etatJeu.json";
   private String pseudo;
    private Set<String> inventaire;
    private String etat;
    private String zone;

    public EtatJeu(String pseudo, Set<String> inventaire, String etat, String zone) {
        ecritureJson( pseudo, inventaire, etat, zone);
        this.pseudo = pseudo;
        this.inventaire = inventaire;
        this.etat = etat;
        this.zone = zone;
    }
    public static EtatJeu recupererEtatJeu(String pseud) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(ETAT_JEU)) {
            // Lecture du fichier JSON existant
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            for (Object item : jsonArray) {
                JSONObject etatJeuObj = (JSONObject) item;
                String pseudoJson = (String) etatJeuObj.get("pseudo");
                if (pseudoJson.equals(pseud)) {

                    Set<String> inventair = new HashSet<>((JSONArray)etatJeuObj.get("Inventaire"));
                    String eta = (String) etatJeuObj.get("Etat");
                    String zon = (String) etatJeuObj.get("Zone");

                    return new EtatJeu(pseud, inventair, eta, zon);
                }
            }
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
        }

        return null; // Si le pseudo n'est pas trouvé, retourne null
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Set<String> getInventaire() {
        return inventaire;
    }

    public void setInventaire(Set<String> inventaire) {
        this.inventaire = inventaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

}




