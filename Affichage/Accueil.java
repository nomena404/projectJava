package Affichage;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;

public class Accueil extends JFrame {

    JPanel panel; // mon conteneur
    JButton commencer; //
    JButton connexion ;
    JButton creerCompte;
    JTextArea texte;
    public Accueil() throws UnsupportedLookAndFeelException {

        super("Mon prémier lancement");
        reglageFenetre();
        configurationConteneur();
        reglageBouton();
        reglageText();
        fenetre();



    }

   /* public JToolBar bar(){
         JToolBar too=new JToolBar();
         too.add(connexion);
         too.add(commencer);
         too.add(creerCompte);
         return too;
    }

    */

    public void fenetre(){
        panel.add(texte,BorderLayout.WEST);
        panel.add(commencer,BorderLayout.SOUTH);

    }
    public void reglageText(){
        texte=new JTextArea("Commencer ici");
    }

    public void reglageBouton(){
        commencer=new JButton("Commencer");
        connexion = new JButton("Connexion");
        creerCompte= new JButton("Créer un compte");
    }
    public void configurationConteneur(){
        panel=new JPanel();
        panel.setLayout(new BorderLayout());
       // panel.setLayout(null);
      //  panel.setLayout(new FlowLayout());
        //panel.setLayout(new GridLayout(1,3));
        panel.setBackground(Color.gray);
       // panel.add(new JLabel(new ImageIcon("vikingsFond.jpg")));
//
        this.add(panel);
    }

    //

    public void reglageFenetre() throws UnsupportedLookAndFeelException {
        this.setVisible(true);
        //permet de rendre l'affichage des boutons plus moderne
        UIManager.setLookAndFeel(new MetalLookAndFeel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700,600);
        //permet de centrer l'application par rapport au bureau ,
        // on le mettant en null signifie qu'elle se triuve au milieu lorqu'on lance l'application
        this.setLocationRelativeTo(null);
    }

    public static void main(String []argc) throws UnsupportedLookAndFeelException {
        Accueil a= new Accueil();
        //
    }
}
