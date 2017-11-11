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
public abstract class Personnages extends JComponent {

    protected Image imageFace, imageDos, imageDroite, imageGauche;
    protected int pointsDeVie;

    public Personnages(int pointsDeVie, Image imageFace, Image imageDos, Image imageDroite, Image imageGauche, int largeur, int hauteur) {
        this.imageFace = imageFace;
        this.imageDos = imageDos;
        this.imageDroite = imageDroite;
        this.imageGauche = imageGauche;
        this.pointsDeVie = pointsDeVie;

        setSize(largeur, hauteur);
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(imageDos, 0, 0, this);

    }

}
