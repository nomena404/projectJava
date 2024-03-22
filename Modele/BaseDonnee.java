package Modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
    protected static void ecritureJson(String n, List<String> inv, String etat, String zone) {
        JSONArray jsonArray = lectureJsonEtat();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pseudo",n);
        jsonObject.put("Inventaire", inv);
        jsonObject.put("Etat", etat);
        jsonObject.put("Zone", zone);

        jsonArray.add(jsonObject);

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

    protected static JSONArray lectureJsonEtat() {
        JSONArray jsonArray = new JSONArray();
        try (FileReader reader = new FileReader(ETAT_JEU)) {
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
        }
        return jsonArray;
    }
}