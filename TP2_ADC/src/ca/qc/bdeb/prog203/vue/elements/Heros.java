/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Toolkit;

/**
 * Vitesse par defaut = 0,0
 */
public class Heros extends Personnages {

    private static int HAUTEUR = 50, LARGEUR = 22;
    
    
    public Heros() {

        super(3, Toolkit.getDefaultToolkit().getImage("images/herofront.gif"), Toolkit.getDefaultToolkit().getImage("images/heroback.gif"),
                Toolkit.getDefaultToolkit().getImage("images/herodroite.gif"), Toolkit.getDefaultToolkit().getImage("images/herogauche.gif"),
                LARGEUR, HAUTEUR, 4);
        
    }

}
