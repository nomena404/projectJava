package Zone;

import Modele.BaseDonnee;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IZone  {
    void entrer();
    void traiterCommande(String commande) throws IOException, ParseException;
}
