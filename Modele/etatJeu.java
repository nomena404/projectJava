package Modele;

import java.util.List;

public class etatJeu {
    private String pseudo;
    private List<String> inv;
    private String etat;
    private String zone;

    public etatJeu(String pseud,List<String> in,String eta,String zon){
        pseudo=pseud;
        inv=in;
        etat=eta;
        zone=zon;
    }
}
