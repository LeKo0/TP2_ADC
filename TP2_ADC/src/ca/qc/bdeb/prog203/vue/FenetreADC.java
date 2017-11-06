/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.modele.ModeleADC;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author 1666876
 */
public class FenetreADC extends JFrame {
    
    private JMenuBar mnuBar =new JMenuBar();
    private JMenu mnuFichier = new JMenu("Fichier");
    private JMenu mnuHelp = new JMenu("Help");
    private JMenuItem mnuNouvellePartie = new JMenuItem("Nouvelle Partie");
    
    

    public FenetreADC(ModeleADC modele) {
        setSize(800,800);
        setTitle("Tantacule Mauve: La contre-attaque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        //les menus
        setJMenuBar(mnuBar);
        
        
        
        
        setVisible(true);
        
        
        
        
        
    }
    
    
}
