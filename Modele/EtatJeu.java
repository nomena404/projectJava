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

    public EtatJeu(String pseudo, Set<String> inventaire, String etat, String zone) {

        this.pseudo = pseudo;
        this.inventaire = inventaire;
        this.etat = etat;
        this.zone = zone;
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
                System.out.println(eta+"\n"+ zon + "nothing here");
             return new EtatJeu(pseudoJson, Utile.StringEnList(inventaire), eta, zon);
            }

        }

        return null;
    }

    public static boolean pseudoSauvegarde(String pseudo)  {
        JSONArray jsonArray = lectureJsonEtat();
        for (Object item : jsonArray) {
            JSONObject etatJeuObj = (JSONObject) item;
            String pseudoJson = (String) etatJeuObj.get("pseudo");
            if (pseudoJson.equals(pseudo)) {
                return true;
            }

        }

        return false;
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




