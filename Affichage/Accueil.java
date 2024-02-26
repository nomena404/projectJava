package Affichage;

import javax.swing.*;

public class Accueil extends JFrame {

    public Accueil(){
        super("Mon prémier lancement");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public static void main(String []argc){
        Accueil a= new Accueil();
        //
    }
}
