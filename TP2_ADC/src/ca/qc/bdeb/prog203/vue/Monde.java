/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.vue.elements.Buisson;
import ca.qc.bdeb.prog203.vue.elements.Heros;
import ca.qc.bdeb.prog203.vue.elements.Personnages;
import ca.qc.bdeb.prog203.vue.elements.Roche;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author 1648266
 */
public class Monde extends JPanel {
    
    private Heros heros;
    private final int tailleImageGazon = 32;
    private ArrayList<Integer> listeKeyCodes = new ArrayList();
    private Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                majJeu();
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                }
                
            }
            
        }
        
    };

    private void majJeu() {
        
        if(listeKeyCodes.contains(KeyEvent.VK_A)){
            heros.setDirection(Personnages.Direction.GAUCHE);
        }
        if(listeKeyCodes.contains(KeyEvent.VK_S)){
            heros.setDirection(Personnages.Direction.BAS);
        }
        if(listeKeyCodes.contains(KeyEvent.VK_W)){
            heros.setDirection(Personnages.Direction.HAUT);
        }
        if(listeKeyCodes.contains(KeyEvent.VK_D)){
            heros.setDirection(Personnages.Direction.DROITE);
        }
        
        
    }

    private void nouvellePartie() {
        creerEvenement();
        thread.start();
        
    }
    
    public Monde() {
        setPreferredSize(new Dimension(16 * tailleImageGazon, 14 * tailleImageGazon));
        setLayout(null);
        init();
        nouvellePartie();
        
    }
    
    private void init() {        
        heros = new Heros();
        
        Gazon gazon = new Gazon(16 * Gazon.DIMENSION_GAZON, 14 * Gazon.DIMENSION_GAZON);
        ArrayList<Roche> roches = new ArrayList<>();
        ArrayList<Buisson> buissons = new ArrayList<>();
        Roche rocheTemp;
        Buisson buissonTemp;

        //Ajout du gazon
        add(gazon, 0);
        
        System.out.println(gazon.getSize());
        //Ajout du heros au centre
        add(heros, 0);
        heros.setLocation(8 * tailleImageGazon - 11, 7 * tailleImageGazon - 25);

        //Disposition des roches
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if (((i == 0 | i == 13) & (j <= 4 || j >= 11))
                        || ((j == 0 | j == 15) & (i <= 3 || i >= 10))) {
                    rocheTemp = new Roche();
                    rocheTemp.setLocation(j * tailleImageGazon, i * tailleImageGazon);
                    roches.add(rocheTemp);
                    add(rocheTemp, 0);
                }
            }
        }

        //Disposition des buissons
        for (int i = 0; i < 4; i++) {
            buissonTemp = new Buisson();
            buissonTemp.setLocation(i, i);
            buissons.add(buissonTemp);
            add(buissonTemp, 0);
        }
        buissons.get(0).setLocation(3 * tailleImageGazon, 3 * tailleImageGazon);
        buissons.get(1).setLocation(12 * tailleImageGazon, 3 * tailleImageGazon);
        buissons.get(2).setLocation(3 * tailleImageGazon, 10 * tailleImageGazon);
        buissons.get(3).setLocation(12 * tailleImageGazon, 10 * tailleImageGazon);
        
    }
    
    private void creerEvenement() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(!listeKeyCodes.contains(e.getKeyCode())){
                    listeKeyCodes.add(e.getKeyCode());
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                listeKeyCodes.remove(new Integer(e.getKeyCode()));
            }
            
        });
        
    }
    
}
