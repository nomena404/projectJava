package Vue;

import Controlleur.Controlleur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel imageLabel; // Label pour afficher l'image
    private Controlleur controlleur;
    private JButton demarrerBtn;
    private JButton continuerBtn;
    private String etat = "demandePseudo"; // Gère les états de l'interface

    public Gui(Controlleur controlleur) {
        this.controlleur = controlleur;
        initialiserGUI();
    }

    private void initialiserGUI() {
        fenetre = new JFrame("Royaume de Kattekat");
        fenetre.setLayout(new BorderLayout());

        imageLabel = new JLabel(); // Prépare le JLabel pour l'image
        chargerImage("/vikingsFond.jpg"); // Chargez l'image initiale ici

        texte = new JTextArea("Bienvenue sur notre jeu. Veuillez entrer votre pseudo.");
        entree = new JTextField();
        demarrerBtn = new JButton("Démarrer");
        continuerBtn = new JButton("Continuer");

        JPanel centrePanel = new JPanel(new BorderLayout());
        centrePanel.add(imageLabel, BorderLayout.CENTER); // Place l'image au centre
        centrePanel.add(texte, BorderLayout.SOUTH);

        fenetre.add(centrePanel, BorderLayout.CENTER);
        fenetre.add(entree, BorderLayout.SOUTH);
        fenetre.add(demarrerBtn, BorderLayout.EAST);
        fenetre.add(continuerBtn, BorderLayout.WEST);

        demarrerBtn.setVisible(false);
        continuerBtn.setVisible(false);
        texte.setEditable(false);

        entree.addActionListener(this);
        demarrerBtn.addActionListener(e -> controlleur.actionDemarrer());
        continuerBtn.addActionListener(e -> controlleur.actionContinuer());

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(new Dimension(800, 600));
        fenetre.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("demandePseudo".equals(etat)) {
            controlleur.verifierPseudo(entree.getText().trim());
        }
    }

    public void setEtat(String nouvelEtat, String message) {
        SwingUtilities.invokeLater(() -> {
            etat = nouvelEtat;
            texte.setText(message);
            switch (etat) {
                case "demarrerJeu":
                    chargerImage("/labyrinthe.png");
                    demarrerBtn.setVisible(true);
                    entree.setVisible(false);
                    break;
                case "continuerJeu":
                    continuerBtn.setVisible(true);
                    break;

            }
        });
    }

    public void afficherMessage(String message) {
        SwingUtilities.invokeLater(() -> texte.setText(message));
    }


    private void chargerImage(String cheminImage) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(cheminImage));
        imageLabel.setIcon(imageIcon);
        fenetre.pack();
    }
}
