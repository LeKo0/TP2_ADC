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
public class Heros extends Personnages {

    public Heros() {
        super("String de face", "String de dos", 3);
    }

    @Override
    URL getImageFace() throws MalformedURLException {
        return new URL(imageFace);
    }

    @Override
    URL getImageDos() throws MalformedURLException {
        return new URL(imageDos);
    }

    @Override
    void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

}
