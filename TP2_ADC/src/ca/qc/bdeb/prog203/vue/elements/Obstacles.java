/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

/**
 *
 * @author Leo
 */
public abstract class Obstacles extends JComponent {

    public static int HAUTEUR = 32, LARGEUR = 32;

    protected Image image;

    /**
     * Constructeur par défaut
     * @param image
     */
    public Obstacles(Image image) {
        this.image = image;
        setSize(LARGEUR, HAUTEUR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

    }

}
