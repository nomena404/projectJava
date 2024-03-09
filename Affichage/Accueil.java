package Affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Accueil implements ActionListener {

    JFrame maFenetre;
    JTextArea descriptif;
    JTextField utilisateur;
    JLabel image ;
    boolean jeuDemarre;

    public Accueil() {
        configFenetre();

    }

    public void configFenetre(){
        maFenetre = new JFrame("Royaume de Kattekat");
        image = new JLabel();
        descriptif = new JTextArea();
        utilisateur = new JTextField();

        URL imageURL = this.getClass().getClassLoader().getResource("vikingsFond.jpg");
        image.setIcon(new ImageIcon(imageURL));

        JScrollPane listScroller = new JScrollPane(descriptif);
        listScroller.setPreferredSize(new Dimension(100, 100));
        listScroller.setMinimumSize(new Dimension(100, 100));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(utilisateur, BorderLayout.SOUTH);

        maFenetre.getContentPane().add(panel, BorderLayout.CENTER);
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        descriptif.setEditable(false);

        utilisateur.addActionListener(this);
        descriptif.setText("BIENVENU SUR NOTRE JEU KATTEGAT \nChoisissez le nom de votre personnage");


        maFenetre.setVisible(true);
maFenetre.pack();
        utilisateur.requestFocus();
    }
    @Override

    public void actionPerformed(ActionEvent e) {
        String input = utilisateur.getText().toLowerCase();


        if (!jeuDemarre) {
            // Si le jeu n'a pas encore démarré
            if (input.equals("démarrer")) {
                descriptif.setText("Bienvenue, " +  "! Les règles du jeu: ...");
                URL imageURL = this.getClass().getClassLoader().getResource("game/images/GrandeSalle.jpg");
                image.setIcon(new ImageIcon(imageURL));
                jeuDemarre = true;
            } else {
                // Si le joueur n'a pas encore choisi de nom, enregistrez le texte comme nom du personnage
                if (input == null) {
                    input = "";
                    descriptif.setText("Nom du personnage enregistré : " );
                    utilisateur.setText("");  // Effacez le champ de texte
                } else {
                    descriptif.setText("Rentrez 'Démarrer' pour commencer le jeu.");
                }
            }
        } else {
            descriptif.setText("Commande non reconnue. Essayez autre chose.");
        }
    }
    public static void main(String []argc){
        SwingUtilities.invokeLater(() -> new Accueil());

    }




}