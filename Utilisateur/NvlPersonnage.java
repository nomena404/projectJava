package Utilisateur;

import BaseDeDonnees.BasePers;
import Exceptions.LePseudoExisteDéjà;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class NvlPersonnage extends BasePers {
    private String nom;
    private String prenom;
    private String pseudo;
    private int numero;
    private String mdp;
    private static int compteur=0;


    public NvlPersonnage(String nom, String prenom, String pseudo,  String mdp) throws LePseudoExisteDéjà {
        pseudoExistant(pseudo);
        compteur++;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = compteur;
        this.mdp = mdp;
        ObjJson();
    }

    public String getPseudo(){
        return pseudo;
    }

    public String getMdp(){
        return mdp;
    }

    @Override
    public String toString(){
        return nom + "  "+ prenom +"  "+ pseudo ;
    }

    public void ObjJson(){
        JSONObject nvl= new JSONObject();
        nvl.put("Nom",nom);
        nvl.put("Prenom" ,prenom);
        nvl.put("Pseudo",pseudo);
        nvl.put("Mot_de_passe",mdp);
        nvl.put("Numéro", numero);
        array.add(nvl);
    }

    public String parsePeudo(JSONObject e){
        return (String) e.get("Pseudo");
    }

    public void pseudoExistant(String ps) throws LePseudoExisteDéjà {
        for(int i=0;i<array.size();i++){
            if(parsePeudo((JSONObject)array.get(i)).equals(ps)){
               throw new LePseudoExisteDéjà("Pseudo Existant");
            }
        }
        pseudo=ps;
    }


    }



