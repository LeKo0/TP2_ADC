/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.modele.ModeleADC;
import ca.qc.bdeb.prog203.vue.elements.Roche;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class FenetreADC extends JFrame {

    private JMenuBar mnuBar;
    private JMenu mnuFichier, mnuHelp;
    private JMenuItem mnuNouvellePartie, mnuQuitter, mnuAide, mnuAPropos;
    private Monde pnlMonde;
    private JPanel pnlInfo;

    public FenetreADC(ModeleADC modele) {
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
        pnlMonde = new Monde();
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
