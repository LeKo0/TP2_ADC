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
        super("images/herofront.gif", "images/heroback.gif", "images/herodroite.gif", "images/herogauche.gif", 3);
    }

    @Override
    void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

}
