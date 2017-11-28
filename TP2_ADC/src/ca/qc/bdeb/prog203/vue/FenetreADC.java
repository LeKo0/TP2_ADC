/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.controlleur.ControlleurADC;
import ca.qc.bdeb.prog203.modele.ModeleADC;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class FenetreADC extends JFrame implements Observer{

    private JMenuBar mnuBar;
    private JMenu mnuFichier, mnuHelp;
    private JMenuItem mnuNouvellePartie, mnuQuitter, mnuAide, mnuAPropos;
    private Monde pnlMonde;
    private JPanel pnlInfo;
    private ControlleurADC controlleur;

    public FenetreADC(ControlleurADC controlleur, ModeleADC modele) {
        this.controlleur = controlleur;
        setTitle("Tantacule Mauve: La contre-attaque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();
        initMonde();
        initInfo();
        creerEvenement();

        setResizable(false);
        pack();
        setVisible(true);
        pnlMonde.setFocusable(true);
    }

    @Override//on doit update le chit des vies (comme ce qui est sous le jeu) ;)
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        pnlInfo = new JPanel();
        add(pnlInfo,BorderLayout.SOUTH);
        pnlInfo.add(new JLabel("JPANEL INFO"));
        
    }

    /**
     * La méthode sera utilisé pour initialiser l'affichage du jeux (c'est à
     * dire les personnages, les buissons, etc)
     */
    private void initMonde() {
        pnlMonde = new Monde(controlleur);
        add(pnlMonde);

    }
    private void nouvellePartie(){
        
    }

    
    

    private void creerEvenement() {
        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nouvellePartie();
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
