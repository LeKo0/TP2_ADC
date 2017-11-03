/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.modele.elements;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Leo
 */
public abstract class Ennemis extends Personnages {

    protected int vitesse;

    public Ennemis(int pointsDeVie, int vitesse, String imageFace, String imageDos) {
        super(imageFace, imageDos, pointsDeVie);
        this.vitesse = vitesse;

    }

}
