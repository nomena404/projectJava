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
    protected static void ecritureJson(String n, Set<String> inv, String etat, String zone) {
        // Initialisation du tableau JSON à partir du fichier ou création d'un nouveau tableau si nécessaire
        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(ETAT_JEU)) {
            // Lecture du fichier JSON existant
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON ou fichier non trouvé, création d'un nouveau tableau : " + e.getMessage());
        }

        // Création du nouvel objet JSON
        JSONObject nouvelEtat = new JSONObject();
        nouvelEtat.put("pseudo", n);
        nouvelEtat.put("Inventaire", inv);
        nouvelEtat.put("Etat", etat);
        nouvelEtat.put("Zone", zone);

        // Recherche et suppression de l'ancien état
        boolean etatTrouve = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject etatJeu = (JSONObject) jsonArray.get(i);
            if (etatJeu.get("pseudo").equals(n)) {
                jsonArray.remove(i);
                etatTrouve = true;
                break;
            }
        }

        // Ajout du nouvel état si l'ancien a été trouvé et supprimé ou non
        jsonArray.add(nouvelEtat);

        // Écriture dans le fichier
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
