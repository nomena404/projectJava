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
    private String pseudo;
    private Set<String> inventaire;
    private String etat;
    private String zone;
    private  String texte;

    public EtatJeu(String pseudo, Set<String> inventaire, String etat, String zone,String txt) {

        this.pseudo = pseudo;
        this.inventaire = inventaire;
        this.etat = etat;
        this.zone = zone;
        this.texte=txt;
    }
    public static EtatJeu recupererEtatJeu(String pseudo)  {
        JSONArray jsonArray = lectureJsonEtat();
        for (Object item : jsonArray) {
            JSONObject etatJeuObj = (JSONObject) item;
            String pseudoJson = (String) etatJeuObj.get("pseudo");

            if (pseudoJson.equals(pseudo)) {
                String inventaire = (String ) etatJeuObj.get("Inventaire");
                String eta = (String) etatJeuObj.get("Etat");
                String zon = (String) etatJeuObj.get("Zone");
                String tx= (String) etatJeuObj.get("Texte");

             return new EtatJeu(pseudoJson, Utile.StringEnList(inventaire), eta, zon,tx);
            }

        }

        return null;
    }


    public String getEtat() {
        return etat;
    }

public String getTexte(){
        return texte;
}

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

}




