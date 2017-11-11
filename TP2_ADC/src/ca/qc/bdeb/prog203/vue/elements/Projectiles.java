/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author Leo
 */
public abstract class Projectiles extends JComponent {

    protected Image image;
    protected int vitesseX, vitesseY;
    protected int hauteur, largeur;

    public Projectiles(Image image, int vitesseX, int vitesseY, int largeur, int hauteur) {
        this.image = image;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.hauteur = hauteur;
        this.largeur = largeur;

        setSize(largeur, hauteur);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
