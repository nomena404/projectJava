package Affichage;

import Exceptions.LePseudoExisteDéjà;
import Utilisateur.NvlPseudo;

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

    private boolean jeuDemarre = false;

    public Gui() {
        page();
    }

    public void page() {
        fenetre = new JFrame("Royaume de Kattekat");
        image = new JLabel();
        texte = new JTextArea();
        entree = new JTextField();

        URL imageURL = getClass().getClassLoader().getResource("labyrinthe.png");
        if (imageURL != null) {
            image.setIcon(new ImageIcon(imageURL));
        } else {
            System.err.println("Image introuvable : labyrinthe.png");
        }

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
        texte.setText("BIENVENUE SUR NOTRE JEU KATTEGAT\nLe jeu consiste à récupérer les runes pour affronter le terrible Ragnork.\nPour commencer, appuyez sur OK.");

        fenetre.setPreferredSize(new Dimension(800, 600));
        fenetre.pack();
        fenetre.setVisible(true);

        entree.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = entree.getText().toLowerCase();

        if (!jeuDemarre) {
            if (input.equals("ok")) {
                texte.setText("Les règles du jeu cher joueur : entrez votre pseudo pour une belle aventure ou reprenez là où vous vous êtes arrêté en entrant votre pseudo.");
                entree.setText("");
            } else {
                try {
                    if (NvlPseudo.pseudoExistant(input)) {
                        texte.setText("Bienvenue : " + input + "\nAppuyez sur OK pour démarrer.");
                        entree.setText("");
                        jeuDemarre = true;
                    } else {
                        NvlPseudo perso = new NvlPseudo(input);
                        perso.ajout();

                        texte.setText("Bienvenue : " + input + "\nAppuyez sur OK pour démarrer.");
                        entree.setText("");
                    }
                } catch (LePseudoExisteDéjà ex) {
                    System.err.println("Erreur : " + ex.getMessage());
                }
            }
        } else {
            texte.setText("Commande non reconnue. Essayez autre chose.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Gui::new);
    }
}
