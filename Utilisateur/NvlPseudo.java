package Utilisateur;

import BaseDeDonnees.BasePers;
import Exceptions.LePseudoExisteDéjà;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NvlPseudo extends BasePers {
    private String pseudo;
    private final int numero;
    private static int compteur = 0;

    public NvlPseudo(String pseudo) throws LePseudoExisteDéjà {
        // Initialisez le pseudo avant d'appeler pseudoExistant()
        if(pseudoExistant(pseudo)){
            throw new LePseudoExisteDéjà("Il existe déjjà");
        }
        this.pseudo = pseudo;
        compteur++;
        numero = compteur;

 }

    public String getPseudo() {
        return pseudo;
    }

    public int getNumero(){
        return numero;
    }

    @Override
    public String toString() {
        return numero + "  " + "  " + pseudo;
    }


    public static String parsePseudo(JSONObject e) {
        return (String) e.get("Pseudo");
    }

    public static boolean pseudoExistant(String ps) throws LePseudoExisteDéjà {
        // Utilisez la méthode lectureJson() de BasePers pour obtenir les pseudos existants

        boolean b= false;
        if(array.isEmpty()){
           b= false;

        }

        else{
            JSONArray jsonArray = lectureJson();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String s = parsePseudo(jsonObject);
                if (s.equals(ps)) {
                    b=false;
                    break;
                }
                b=true;
            }
        }

        return true==b;
    }

    public void ajout(){
        ecritureJson(this);
    }
}
