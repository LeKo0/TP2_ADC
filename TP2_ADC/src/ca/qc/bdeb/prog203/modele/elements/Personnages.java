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
public abstract class Personnages {

    protected String imageFace, imageDos;
    protected int pointsDeVie;

    public Personnages(String imageFace, String imageDos, int pointsDeVie) {
        this.imageFace = imageFace;
        this.imageDos = imageDos;
        this.pointsDeVie = pointsDeVie;
    }

    abstract void setPointsDeVie(int pointsDeVie);

    abstract URL getImageFace() throws MalformedURLException;

    abstract URL getImageDos() throws MalformedURLException;

}
