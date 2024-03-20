package Vue;

import Controlleur.Controlleur;
import Controlleur.Controlleur; // Assurez-vous que cette importation correspond au nom de votre package et de votre classe
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
    private Controlleur controlleur; // Notez la correction de la casse et de l'orthographe ici
    private boolean jeuDemarre;

    public Gui(Controlleur controlleur) {
        this.controlleur = controlleur; // Initialisez votre controlleur
        jeuDemarre = false;
        page();
    }

    public void page() {
        fenetre = new JFrame("Royaume de Kattekat");
        image = new JLabel(new ImageIcon(getClass().getResource("/labyrinthe.png"))); // Correction pour s'assurer que le chemin est correct
        texte = new JTextArea();
        entree = new JTextField();

        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(100, 100));
        listScroller.setMinimumSize(new Dimension(100, 100));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);

        fenetre.setContentPane(panel);
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
        controlleur.traiterEntreeUtilisateur(input);
    }

    public void afficherMessage(String message) {
        SwingUtilities.invokeLater(() -> texte.setText(message));
    }
}
