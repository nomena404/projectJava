package BaseDeDonnees;

import Exceptions.LePseudoExisteDéjà;
import Utilisateur.NvlPseudo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BasePers {
    protected static final String JSON_FILE_PATH = "Pseudo.json"; // Chemin du fichier JSON

    protected static JSONArray array = new JSONArray();

    // Méthode pour écrire les données JSON dans le fichier

    protected static void ecritureJson(String p) throws LePseudoExisteDéjà {
        NvlPseudo pseudo= new NvlPseudo(p);
        JSONObject js= new JSONObject();
        js.put("pseudo",pseudo.getPseudo());
        js.put("Numéro",pseudo.getNumero());
        array.add(js);
        try (FileWriter file = new FileWriter(JSON_FILE_PATH)) {
            file.write(array.toJSONString());
            file.flush();
        } catch (IOException e) {
            // Gérer l'exception IO de manière appropriée
            System.err.println("Erreur lors de l'écriture dans le fichier JSON : " + e.getMessage());
        }
    }

    protected static void ecritureJson(NvlPseudo p) {
        JSONObject js= new JSONObject();
        js.put("pseudo",p.getPseudo());
        js.put("num",p.getNumero());
        array.add(js);
        try (FileWriter file = new FileWriter(JSON_FILE_PATH)) {
            file.write(array.toJSONString());
            file.flush();
        } catch (IOException e) {
            // Gérer l'exception IO de manière appropriée
            System.err.println("Erreur lors de l'écriture dans le fichier JSON : " + e.getMessage());
        }
    }

    // Méthode pour lire les données JSON à partir du fichier
    protected static JSONArray lectureJson() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            JSONParser parser = new JSONParser();
            Object o=parser.parse(reader);
            return  (JSONArray) o ;
        } catch (IOException | ParseException e) {
            // Gérer les exceptions IO et de parsing de manière appropriée
            System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
            return new JSONArray(); // Retourner un nouveau tableau JSON en cas d'erreur
        }
    }
}
