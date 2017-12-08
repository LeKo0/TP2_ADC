/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.controlleur.ControlleurADC;
import ca.qc.bdeb.prog203.modele.ModeleADC;
import ca.qc.bdeb.prog203.vue.elements.Coeur;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author 1666876
 */
public class FenetreADC extends JFrame implements Observer {

    private JMenuBar mnuBar;
    private JMenu mnuFichier, mnuHelp;
    private JMenuItem mnuNouvellePartie, mnuQuitter, mnuAide, mnuAPropos;
    private Monde pnlMonde;
    private JPanel pnlInfo, pnlInfoDroite;
    private final ControlleurADC controlleur;
    private final ModeleADC modele;
    private JLabel lblPoints;
    private ArrayList<Coeur> listeCoeur = new ArrayList<>();

    /**
     * Initialise toutes les composantes de la fenetre et donne le focus au
     * monde
     *
     * @param controlleur Controlleur
     * @param modele Modele
     */
    public FenetreADC(ControlleurADC controlleur, ModeleADC modele) {
        this.controlleur = controlleur;
        this.modele = modele;
        modele.addObserver(this);

        setTitle("Tantacule Mauve: La contre-attaque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();
        initMonde();
        initInfo();
        initEvenementMenu();

        setResizable(false);
        pack();
        pnlMonde.setFocusable(true);
        setVisible(true);

    }

    @Override//on doit update le chit des vies (comme ce qui est sous le jeu) ;)
    public void update(Observable o, Object arg) {

        switch (controlleur.getUpdate()) {
            case POINTS:
                lblPoints.setText("Pointage = " + Integer.toString(modele.getPointage()));
                break;
            case RECOMMENCER:
                lblPoints.setText("Pointage = " + Integer.toString(modele.getPointage()));

                for (int i = 0; i < modele.getPointsVie(); i++) {
                    listeCoeur.add(new Coeur());
                    pnlInfoDroite.add(listeCoeur.get(i));
                    listeCoeur.get(i).setLocation(2 * i * listeCoeur.get(i).getWidth(), 0);
                }
                pnlInfoDroite.invalidate();
                pnlInfoDroite.repaint();
                break;
            case VIE:
                pnlInfoDroite.remove(listeCoeur.get(listeCoeur.size() - 1));
                listeCoeur.remove(listeCoeur.size() - 1);
                pnlInfoDroite.invalidate();
                pnlInfoDroite.repaint();
                break;

        }
    }

    /**
     * La méthode sera utilisé pour initialiser le menu
     */
    private void initMenu() {
        mnuFichier = new JMenu("Fichier");
        mnuHelp = new JMenu("?");

        mnuNouvellePartie = new JMenuItem("Nouvelle Partie");
        mnuQuitter = new JMenuItem("Quitter");
        mnuAide = new JMenuItem("Aide");
        mnuAPropos = new JMenuItem("À propos");
        mnuBar = new JMenuBar();

        //les menus
        setJMenuBar(mnuBar);

        mnuBar.add(mnuFichier);
        mnuBar.add(mnuHelp);
        mnuFichier.add(mnuNouvellePartie);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);
        mnuHelp.add(mnuAide);
        mnuHelp.add(mnuAPropos);

    }

    /**
     * La méthode sera utilisé pour initialiser l'affichage des informations sur
     * le déroulement du jeux
     */
    private void initInfo() {
        lblPoints = new JLabel("Pointage = " + Integer.toString(modele.getPointage()));

        pnlInfo = new JPanel(new GridLayout(1, 0));
        pnlInfo.add(lblPoints);

        pnlInfoDroite = new JPanel(null);
        for (int i = 0; i < modele.getPointsVie(); i++) {
            listeCoeur.add(new Coeur());
            pnlInfoDroite.add(listeCoeur.get(i));
            listeCoeur.get(i).setLocation(2 * i * listeCoeur.get(i).getWidth(), 0);
        }
        pnlInfo.add(pnlInfoDroite);

        add(pnlInfo, BorderLayout.NORTH);

    }

    /**
     * Initialise le monde
     */
    private void initMonde() {
        pnlMonde = new Monde(controlleur, modele);
        add(pnlMonde);

    }

    /**
     * Créé une nouvelle partie
     */
    private void initPartie() {
        
    }

    /**
     * Initialise les evenements en lien avec le menu
     */
    private void initEvenementMenu() {
        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initPartie();
            }
        });
        mnuQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mnuAide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //à terminer
                JOptionPane.showMessageDialog(FenetreADC.this, "--Insérer message--", "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mnuAPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FenetreADC.this, "--Insérer message--", "À propos", JOptionPane.INFORMATION_MESSAGE);

            }
        });

    }

}
