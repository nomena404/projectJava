package Vue;

import Controlleur.Controlleur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class Gui implements ActionListener {
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel imageLabel;
    private Controlleur controlleur;
    private JButton demarrerBtn, continuerBtn, retour;
    private String etatActuel = "accueil";
    private String etatAvant="";
    private String pseudo="";
    private Set<String> inventaire=new HashSet<String>();

    public Gui(Controlleur controlleur) {
        this.controlleur = controlleur;
        initialiserGUI();
    }
    public void setEtatAvant(String s){
        etatAvant=s;
    }

    private void initialiserGUI() {
        fenetre = new JFrame("Royaume de Kattekat");
        fenetre.setLayout(new BorderLayout());

        imageLabel = new JLabel(new ImageIcon("Images/Accueil/Accueil.png")); // Assurez-vous d'avoir l'image appropriée
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
            controlleur.traiterEntreeForet(entree.getText().trim());
        }
        if("foretDesCranes".equals(etatActuel) && entree.getText().trim()=="suite" ){
            System.out.println("here ");
            controlleur.next();
        }
        if(etatActuel.equals("foretDesCranes")){
            controlleur.traiterEntreeCranes(entree.getText().trim());
        }
        if(etatActuel.equals("grotteDesAnciens") && entree.getText().trim().equals("suite") ){
            System.out.println(" actionListener ");
            controlleur.nextt();
        }
        if(etatActuel.equals("grotteDesAnciens")){
            System.out.println(" on passe etta");
            controlleur.traiterEntreeGrotte(entree.getText().trim());
        }





    }

    public void setEtat(String nouvelEtat, String message) {
        SwingUtilities.invokeLater(() -> {
            etatActuel = nouvelEtat;
            texte.setText(message);
            switch (etatActuel) {
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

                case "foretDesAnciens":
                    entree.setVisible(true);
                    break;
                case "foretDesCranes" :
                    entree.setVisible(true);
                    break;
                case "grotteDesAnciens" :
                    System.out.println("case grotte");
                    entree.setVisible(true);
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
    public  String getEtatActuel(){
    return etatActuel;
    }
    public String getPseudo(){
        return pseudo;

    }
    public String getEtatAvant(){
        return etatAvant;
    }
    public void  setPseudo(String p){
        pseudo=p;
    }
    public void addElement(String e){
        inventaire.add(e);
    }
    public String inventaire(){
        if(inventaire.size()==0){
            return "Vous avez zero elements";
        }
        return inventaire.toString();

    }
    public Set<String> list(){
        return inventaire;
    }
}
