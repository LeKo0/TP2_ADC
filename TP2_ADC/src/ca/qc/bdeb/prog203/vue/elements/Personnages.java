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

    public enum Direction {
        HAUT, BAS, DROITE, GAUCHE
    }

    protected int vitesse;
    protected Image imageFace, imageDos, imageDroite, imageGauche;
    protected int pointsDeVie;
    protected Direction direction = Direction.DROITE; //Direction par default

    public Personnages(int pointsDeVie, Image imageFace, Image imageDos, Image imageDroite, Image imageGauche, int largeur, int hauteur, int vitesse) {
        this.vitesse = vitesse;
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
    public int getVitesse(){
        return vitesse;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        switch (direction) {
            case HAUT:
                g.drawImage(imageDos, 0, 0, this);
                break;
            case BAS:
                g.drawImage(imageFace, 0, 0, this);
                break;
            case DROITE:
                g.drawImage(imageDroite, 0, 0, this);
                break;
            case GAUCHE:
                g.drawImage(imageGauche, 0, 0, this);
        }

    }

}
