package Modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BaseDonnee {
    protected static  String JSON_FILE_PATH = "Add.json";
    protected static String ETAT_JEU="etatJeu.json";


    protected static void ecritureJson(NvlPseudo p) {
        JSONArray jsonArray = lectureJson();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pseudo", p.getPseudo());
        jsonObject.put("num", p.getNumero());
        jsonArray.add(jsonObject);

        try (FileWriter file = new FileWriter(JSON_FILE_PATH)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier JSON : " + e.getMessage());
        }
    }
    public static void ecritureJson(String n, Set<String> inv, String etat, String zone) throws IOException, ParseException {
        JSONArray jsonArray = lectureJsonEtat(ETAT_JEU);

        JSONObject nouvelEtat = new JSONObject();
        nouvelEtat.put("pseudo", n);
        nouvelEtat.put("Inventaire", inv);
        nouvelEtat.put("Etat", etat);
        nouvelEtat.put("Zone", zone);

        jsonArray.add(nouvelEtat);

        try (FileWriter file = new FileWriter(ETAT_JEU)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier JSON : " + e.getMessage());
        }
    }

    protected static JSONArray lectureJson() {
        JSONArray jsonArray = new JSONArray();
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
        }
        return jsonArray;
    }

    public static JSONArray lectureJsonEtat(String doc) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(doc)) {
            return (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
            throw e;
        }
    }

}
