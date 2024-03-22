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
    private JLabel imageLabel;
    private Controlleur controlleur;
    private JButton demarrerBtn, continuerBtn, retour;
    private String etat = "accueil";

    public Gui(Controlleur controlleur) {
        this.controlleur = controlleur;
        initialiserGUI();
    }

    private void initialiserGUI() {
        fenetre = new JFrame("Royaume de Kattekat");
        fenetre.setLayout(new BorderLayout());

        imageLabel = new JLabel(new ImageIcon("Accueil.png")); // Assurez-vous d'avoir l'image appropriée
        texte = new JTextArea("Bienvenue sur notre jeu. Veuillez choisir une option.");
        texte.setEditable(false);

        entree = new JTextField();
        demarrerBtn = new JButton("S'inscrire");
        continuerBtn = new JButton("Continuer");
        retour = new JButton("Retour");

        JPanel centrePanel = new JPanel(new BorderLayout());
        centrePanel.add(imageLabel, BorderLayout.CENTER);
        centrePanel.add(texte, BorderLayout.SOUTH);

        fenetre.add(centrePanel, BorderLayout.CENTER);
        fenetre.add(entree, BorderLayout.SOUTH);
        fenetre.add(demarrerBtn, BorderLayout.EAST);
        fenetre.add(continuerBtn, BorderLayout.WEST);
        fenetre.add(retour, BorderLayout.NORTH);

        demarrerBtn.addActionListener(e -> demanderNvlPseudo());
        continuerBtn.addActionListener(e -> demanderPseudo());
        retour.addActionListener(e -> retourAccueil());

        // Configuration initiale des composants
        entree.setVisible(false);
        retour.setVisible(false);
        entree.addActionListener(this);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(new Dimension(500, 500));
        fenetre.setVisible(true);
    }

    private void demanderPseudo() {
        etat = "demanderPseudo";
        chargerImage("Objectif.png");
        texte.setText("Veuillez entrer votre pseudo :");
        entree.setVisible(true);
        demarrerBtn.setVisible(false);
        continuerBtn.setVisible(false);
        retour.setVisible(true);
    }
    private void demanderNvlPseudo() {
        etat = "demanderNvlPseudo";
        chargerImage("Objectif.png");
        texte.setText("Veuillez entrer votre pseudo :");
        entree.setVisible(true);
        demarrerBtn.setVisible(false);
        continuerBtn.setVisible(false);
        retour.setVisible(true);
    }

    private void retourAccueil() {
        etat = "accueil";
        texte.setText("Bienvenue sur notre jeu. Veuillez choisir une option.");
        entree.setText("");
        entree.setVisible(false);
        demarrerBtn.setVisible(true);
        continuerBtn.setVisible(true);
        retour.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (etat.equals("demanderPseudo")) {
            controlleur.verifierPseudoJoueur(entree.getText().trim());
        }
        if (etat.equals("PseudoJoueurInconnu")) {
            entree.setText(""); // Nettoyer le champ pour la nouvelle saisie
            etat = "demanderPseudo"; // S'assurer que l'état permet une nouvelle vérification
        }
        if("demanderNvlPseudo".equals(etat)){
            controlleur.nouvellePseudo(entree.getText().trim());
        }


    }

    public void setEtat(String nouvelEtat, String message) {
        SwingUtilities.invokeLater(() -> {
            etat = nouvelEtat;
            texte.setText(message);
            switch (etat) {
                case "PseudoJoueurInconnu":
                    demandeReessayerOuRetour();
                    break;

                case"UtilisateurConnu" :
                    retour.setVisible(false);
                    entree.setVisible(true);
                    controlleur.lastZone();
                    break;
                case "demarrerNvJeu":
                    retour.setVisible(false);
                    entree.setVisible(true);
                    controlleur.firstZone();
                    break;

                // Autres cas pour différents états de jeu
            }
        });
    }

    private void demandeReessayerOuRetour() {
        texte.setText("Pseudo inconnu. Veuillez réessayer ou appuyer sur 'Retour'.");
        entree.setText("");
    }

    public void afficherMessage(String message) {
        SwingUtilities.invokeLater(() -> texte.setText(message));
    }

    public void chargerImage(String cheminImage) {
        ImageIcon imageIcon = new ImageIcon(cheminImage);
        imageLabel.setIcon(imageIcon);
        fenetre.pack();
    }
    public  String  getEtat(){
    return etat;
    }
}
