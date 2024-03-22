package Modele;

import java.util.List;

public class etatJeu {
    private String pseudo;
    private List<String> inv;
    private String etat;

    public etatJeu(String pseud,List<String> in,String eta){
        pseudo=pseud;
        inv=in;
        etat=eta;
    }
}
