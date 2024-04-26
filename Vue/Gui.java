package Vue;

import Controlleur.Controlleur;
import Modele.BaseDonnee;
import Modele.EtatJeu;
import Modele.Utile;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Gui implements ActionListener {
    public JFrame fenetre;
    public JTextField entree;
    public JTextArea texte;
    public JLabel imageLabel;
    private Controlleur controlleur;
    public JButton demarrerBtn;
    public JButton continuerBtn;
    public JButton retour;
    private String etatActuel = "accueil";
    private String zoneActuel ="";

    public   String txt="";


    private String pseudo="";

    public Set<String> inventaire=new HashSet<String>();


    public Gui(Controlleur controlleur) {
        this.controlleur = controlleur;
        initialiserGUI();
    }


    // méthode qui sauvegarde la zone actuel ou se trouve le joueur
    public void setZoneActuel(String s){
        zoneActuel =s;
    }


    private void initialiserGUI() {
        fenetre = new JFrame("Royaume de Kattekat");
        fenetre.setLayout(new BorderLayout());

        imageLabel = new JLabel(new ImageIcon("Images/Accueil/Accueil.png"));
        texte = new JTextArea("Bienvenue sur notre jeu. Veuillez choisir une option.");
        texte.setEditable(false);
        texte.setPreferredSize(new Dimension(200, 100));

        JScrollPane scrollPane = new JScrollPane(texte);


        entree = new JTextField();
        demarrerBtn = new JButton("S'inscrire");
        continuerBtn = new JButton("Continuer");
        retour = new JButton("Retour");



        JPanel centrePanel = new JPanel(new BorderLayout());
        centrePanel.add(imageLabel, BorderLayout.CENTER);
        centrePanel.add(scrollPane, BorderLayout.SOUTH);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        fenetre.add(centrePanel, BorderLayout.CENTER);
        fenetre.add(entree, BorderLayout.SOUTH);
        fenetre.add(demarrerBtn, BorderLayout.EAST);
        fenetre.add(continuerBtn, BorderLayout.WEST);
        fenetre.add(retour, BorderLayout.NORTH);

        demarrerBtn.addActionListener(e -> demanderNvlPseudo());
        continuerBtn.addActionListener(e -> demanderPseudo());
        retour.addActionListener(e -> retourAccueil());

        entree.setVisible(false);
        retour.setVisible(false);
        entree.addActionListener(this);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(new Dimension(640, 680));
        fenetre.setVisible(true);
    }

    private void demanderPseudo() {
        etatActuel = "demanderPseudo";
        chargerImage("Images/Accueil/Objectif.png");
        texte.setText("Veuillez entrer votre pseudo :");
        entree.setVisible(true);
        demarrerBtn.setVisible(false);
        continuerBtn.setVisible(false);
        retour.setVisible(true);
    }
    private void demanderNvlPseudo() {
        etatActuel = "demanderNvlPseudo";
        chargerImage("Images/Accueil/Objectif.png");
        texte.setText("Veuillez entrer votre pseudo :");
        entree.setVisible(true);
        demarrerBtn.setVisible(false);
        continuerBtn.setVisible(false);
        retour.setVisible(true);
    }

    private void retourAccueil() {
        etatActuel = "accueil";
        chargerImage("Images/Accueil/Accueil.png");
        texte.setText("Bienvenue sur notre jeu. Veuillez choisir une option.");
        entree.setText("");
        entree.setVisible(false);
        demarrerBtn.setVisible(true);
        continuerBtn.setVisible(true);
        retour.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (etatActuel.equals("demanderPseudo")) {
            controlleur.verifierPseudoJoueur(entree.getText().trim());
        }
        if (etatActuel.equals("PseudoJoueurInconnu")) {
            entree.setText("");
            etatActuel = "demanderPseudo";
        }
        if("demanderNvlPseudo".equals(etatActuel)){
            controlleur.nouvellePseudo(entree.getText().trim());
        }
        if(etatActuel.equals("foretDesAnciens")){

            try {
                controlleur.traiterEntreeForet(entree.getText().trim());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        }
        if(etatActuel.equals("foretDesCranes")){
            controlleur.traiterEntreeCranes(entree.getText().trim());
        }

        if(etatActuel.equals("grotteDesAnciens")){
            controlleur.traiterEntreeGrotte(entree.getText().trim());
        }
        if(etatActuel.equals("lacSacre")){
            controlleur.traiterEntreeLac(entree.getText().trim());
        }
        if(etatActuel.equals("zoneLabyrinthe")){
            controlleur.traiterEntreeLabyrinthe(entree.getText().trim());
        }

    }

    public void setEtat(String nouvelEtat, String message) {
        SwingUtilities.invokeLater(() -> {
            etatActuel = nouvelEtat;
            txt=message;
            texte.setText(message);
            switch (etatActuel) {
                case "PseudoJoueurInconnu":
                    demandeReessayerOuRetour();
                    break;

                case"UtilisateurConnu" :
                    retour.setVisible(false);
                    entree.setVisible(true);
                    try {
                        controlleur.lastZone();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "demarrerNvJeu":
                    retour.setVisible(false);
                    entree.setVisible(true);
                    controlleur.firstZone();
                    break;

                case "foretDesAnciens":
                    entree.setVisible(true);
                    break;
                case "foretDesCranes" :
                    entree.setVisible(true);
                    break;
                case "grotteDesAnciens" :
                    entree.setVisible(true);
                    break;

                case "lacSacre" :
                    entree.setVisible(true);
                    break;

                case "zoneLabyrinthe" :
                    entree.setVisible(true);
                    break;



            }
        });
    }

    private void demandeReessayerOuRetour() {
        texte.setText("Pseudo inconnu. Veuillez réessayer ou appuyer sur 'Retour'.");
        entree.setText(" ");
    }

    public void afficherMessage(String message) {
        SwingUtilities.invokeLater(() -> texte.setText(message));
    }

    public void chargerImage(String cheminImage) {
        ImageIcon imageIcon = new ImageIcon(cheminImage);
        imageLabel.setIcon(imageIcon);
        fenetre.pack();
    }
    public  String getEtatActuel(){
    return etatActuel;
    }
    public String getPseudo(){
        return pseudo;

    }
    public String getZoneActuel(){
        return zoneActuel;
    }

    public void  setPseudo(String p){
        pseudo=p;
    }
    public void addElement(String e){
        inventaire.add(e);
    }


    public Set<String> list() {
        inventaire.addAll(BaseDonnee.listInventaire(getPseudo()));
        return inventaire;
    }


}
