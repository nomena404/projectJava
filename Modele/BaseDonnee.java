package Modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BaseDonnee {
    public static String JSON_FILE_PATH = "Add.json";
    public static String ETAT_JEU = "etatJeu.json";


    public static void ecritureEtatJson(NvlPseudo p) {
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
    public static JSONArray lectureJson() {
        JSONArray jsonArray = new JSONArray();
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);
            System.out.println(jsonArray + "pseudo");
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
        }
        return jsonArray;
    }


    public static void ecritureEtatJson(String n, Set<String> inv, String etat, String zone)  {
        JSONArray jsonArray = lectureJsonEtat();
        JSONObject nouvelEtat = new JSONObject();
        nouvelEtat.put("pseudo", n);
        nouvelEtat.put("Inventaire", Utile.ListEnString(inv));
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


    public static JSONArray lectureJsonEtat()  {
        JSONArray jsonArray = new JSONArray();
        try (FileReader reader = new FileReader(ETAT_JEU)) {
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);
            System.out.println(jsonArray + "etatJeu" );
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON ETAT JEU: " + e.getMessage());
        }
        return jsonArray;

    }

    public static void suppressionEtat(String pseudo){

        JSONArray jsonArray = lectureJsonEtat();
        Iterator<Object> iterator= jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject ob= (JSONObject) iterator.next();
            if(pseudo.equals(ob.get("pseudo"))){
                iterator.remove();
            }
        }

        try (FileWriter file = new FileWriter(ETAT_JEU)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier JSON : " + e.getMessage());
        }

    }

    public static Set<String> listInventaire(String pseudo){
        JSONArray jsonArray = lectureJsonEtat();
        Iterator<Object> iterator= jsonArray.iterator();
        Set<String> liste= new HashSet<>();
        while (iterator.hasNext()){
            JSONObject ob= (JSONObject) iterator.next();
            if(pseudo.equals(ob.get("pseudo"))){
               liste.addAll(Utile.StringEnList((String) ob.get("Inventaire")));
            }
        }
        return liste;

    }
}
