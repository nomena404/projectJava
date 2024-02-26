package Affichage;

import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame {

    JPanel panel; // mon conteneur
    JButton commencer;
    JButton connexion ;
    JButton creerCompte;
    public Accueil(){
        super("Mon prémier lancement");
        reglageFenetre();
        configurationConteneur();
        reglageBouton();



    }

    public void reglageBouton(){
        commencer=new JButton("Commencer");
        connexion = new JButton("Connexion");
        creerCompte= new JButton("Créer un compte");
        panel.add(commencer);
        panel.add(connexion);
        panel.add(creerCompte);
    }
    public void configurationConteneur(){
        panel=new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.gray);

        this.add(panel);
    }

    public void reglageFenetre(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700,600);
        //permet de centrer l'application par rapport au bureau ,
        // on le mettant en null signifie qu'elle se triuve au milieu lorqu'on lance l'application
        this.setLocationRelativeTo(null);
    }

    public static void main(String []argc){
        Accueil a= new Accueil();
        //
    }
}
