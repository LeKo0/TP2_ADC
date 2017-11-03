/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.modele.elements;

/**
 *
 * @author Leo
 */
public abstract class Ennemis extends Personnages {

    protected int vitesse;
    protected int points;

    public Ennemis(int pointsDeVie, int points, int vitesse, String imageFace, String imageDos,
            String imageDroite, String imageGauche) {
        super(imageFace, imageDos, imageDroite, imageGauche, pointsDeVie);
        this.vitesse = vitesse;
        this.points = points;

    }

}
