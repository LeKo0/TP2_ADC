/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Image;

/**
 *
 * @author Leo
 */
public abstract class Ennemis extends Personnages {

    public static int HAUTEUR = 43, LARGEUR = 34;

    protected int points;
    protected int vitesse;

    public Ennemis(int pointsDeVie, int points, int vitesse, Image imageFace, Image imageDos,
            Image imageDroite, Image imageGauche) {
        super(pointsDeVie, imageFace, imageDos, imageDroite, imageGauche, LARGEUR, HAUTEUR);

        this.points = points;

    }

}
