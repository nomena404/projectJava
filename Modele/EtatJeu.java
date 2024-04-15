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

        this.pseudo = pseudo;
        this.inventaire = inventaire;
        this.etat = etat;
        this.zone = zone;
    }
    public static EtatJeu recupererEtatJeu(String pseud) throws IOException, ParseException {
        System.out.println("ici");
        JSONArray jsonArray = lectureJsonEtat(ETAT_JEU);
        System.out.println(jsonArray.toJSONString()+ "here");

        for (Object item : jsonArray) {
            JSONObject etatJeuObj = (JSONObject) item;
            String pseudoJson = (String) etatJeuObj.get("pseudo");
            if (pseudoJson != null && pseudoJson.equals(pseud)) {
                JSONArray inventaireArray = (JSONArray) etatJeuObj.get("Inventaire");
                Set<String> inventaire = new HashSet<>();
                if (inventaireArray != null) {
                    for (Object invItem : inventaireArray) {
                        inventaire.add(String.valueOf(invItem));
                    }
                }


                String eta = (String) etatJeuObj.get("Etat");
                String zon = (String) etatJeuObj.get("Zone");
                System.out.println(eta+"\n"+ zon + "nothing here");


                return new EtatJeu(pseudoJson, inventaire, eta, zon);
            }
        }

        return null;
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




