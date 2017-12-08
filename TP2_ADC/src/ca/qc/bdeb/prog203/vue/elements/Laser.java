/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public class Laser extends Projectiles {

    public static final int LARGEUR = 20,HAUTEUR = 5;

    /**
     * Initialise le laser avec sa direction
     * @param deltaX
     * @param deltaY
     */
    public Laser(int deltaX, int deltaY) {

        super(deltaX, deltaY, LARGEUR, HAUTEUR);

    }

    /**
     * Dessine le lase
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (deltaY == 0) {
            g.fillRect(0, 0, LARGEUR, HAUTEUR);
            g.setColor(Color.red);
            setSize(LARGEUR, HAUTEUR);

        } else {
            g.fillRect(0, 0, HAUTEUR, LARGEUR);
            g.setColor(Color.red);
            setSize(HAUTEUR, LARGEUR);
        }

    }

}
