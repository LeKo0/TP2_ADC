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
public class Balle extends Projectiles {

    public static int LARGEUR = 10, HAUTEUR = 10;

    /**
     * Initialise la balle avec une direction
     *
     * @param deltaX
     * @param deltaY
     */
    public Balle(int deltaX, int deltaY) {
        super(deltaX, deltaY, LARGEUR, HAUTEUR);
    }

    /**
     * Dessine la balle
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval(0, 0, LARGEUR, HAUTEUR);
        g.setColor(Color.blue);

    }
}
