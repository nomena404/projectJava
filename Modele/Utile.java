package Modele;

import java.util.HashSet;
import java.util.Set;

public class Utile {
    public static String ListEnString(Set<String> liste){
        String n="";
        for(String elt: liste){
            n+=elt +" ";
        }
        return n;

    }

    public static Set<String> StringEnList(String inv){
        String [] strings = inv.split(" ");
        Set<String> liste= new HashSet<>();
        for(String elt : strings){
            liste.add(elt);
        }
        return liste;
    }
}
