/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Leo
 */
public abstract class Personnages {

    protected ImageIcon imageFace, imageDos, imageDroite, imageGauche;
    protected int pointsDeVie;

    public Personnages(ImageIcon imageFace, ImageIcon imageDos, ImageIcon imageDroite,
            ImageIcon imageGauche, int pointsDeVie) {
        this.imageFace = imageFace;
        this.imageDos = imageDos;
        this.imageDroite = imageDroite;
        this.imageGauche = imageGauche;
        this.pointsDeVie = pointsDeVie;
    }

    abstract void setPointsDeVie(int pointsDeVie);

}
