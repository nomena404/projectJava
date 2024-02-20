package BaseDeDonnees;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BasePers {
    protected static JSONArray array = new JSONArray();

    public static void ecritureJson() {
        try (FileWriter file = new FileWriter("Personnage.json")) {
            file.write(array.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

