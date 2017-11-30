/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import javax.swing.JComponent;

/**
 * Classe mère pour les projectiles
 */
public abstract class Projectiles extends JComponent {

    protected int deltaX, deltaY;
    protected int hauteur, largeur;
    protected int vitesse = 5;

    public Projectiles(int deltaX, int deltaY, int largeur, int hauteur) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.hauteur = hauteur;
        this.largeur = largeur;

        setSize(largeur, hauteur);
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    /**
     * Bouge le projectile en fonction de son delta et sa vitesse
     */
    public void bouger() {
        setLocation(getX() + deltaX * vitesse, getY() + deltaY * vitesse);
    }

}
