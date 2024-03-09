package Affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Gui implements ActionListener {
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;

    private String nomPersonnage;
    private boolean jeuDemarre = false;

    public Gui() {
        page();
    }

    public void page() {
        fenetre = new JFrame("Royaume de Kattekat");
        image = new JLabel();
        texte = new JTextArea();
        entree = new JTextField();

        URL imageURL = this.getClass().getClassLoader().getResource("vikingsFond.jpg");
        image.setIcon(new ImageIcon(imageURL));

        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(100, 100));
        listScroller.setMinimumSize(new Dimension(100, 100));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);

        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        texte.setEditable(false);

        entree.addActionListener(this);
        texte.setText("BIENVENU SUR NOTRE JEU KATTEGAT \nChoisissez le nom de votre personnage");

        fenetre.pack();
        fenetre.setVisible(true);

        entree.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = entree.getText().toLowerCase();

        if (!jeuDemarre) {
            // Si le jeu n'a pas encore démarré
            if (input.equals("démarrer")) {
                texte.setText("Bienvenue, " + nomPersonnage + "! Les règles du jeu: ...");
                URL imageURL = this.getClass().getClassLoader().getResource("game/images/GrandeSalle.jpg");
                image.setIcon(new ImageIcon(imageURL));
                jeuDemarre = true;
            } else {
                // Si le joueur n'a pas encore choisi de nom, enregistrez le texte comme nom du personnage
                if (nomPersonnage == null) {
                    nomPersonnage = input;
                    texte.setText("Nom du personnage enregistré : " + nomPersonnage);
                    entree.setText("");  // Effacez le champ de texte
                } else {
                    texte.setText("Rentrez 'Démarrer' pour commencer le jeu.");
                }
            }
        } else {
            texte.setText("Commande non reconnue. Essayez autre chose.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gui());
    }
}
