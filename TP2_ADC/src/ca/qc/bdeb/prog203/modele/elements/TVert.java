/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.modele.elements;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Vitesse de 1 et PV de 1
 *
 * @author Leo
 */
public class TVert extends Ennemis {

    public TVert() {
        super(1, 1, "/images/greenfront", "/images/greenback");
    }

    @Override
    void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    @Override
    URL getImageFace() throws MalformedURLException {

        return new URL(imageFace);

    }

    @Override
    URL getImageDos() throws MalformedURLException {
        return new URL(imageDos);
    }

}
