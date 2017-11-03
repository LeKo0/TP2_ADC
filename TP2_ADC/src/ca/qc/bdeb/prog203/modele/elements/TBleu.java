/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.modele.elements;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Vitesse de 2 et PV de 1
 *
 * @author Leo
 */
public class TBleu extends Ennemis {

    public TBleu() {
        super(2, 1, 2, "images/bleufront.gif", "images/bleuback.gif",
                "images/bleufront.gif", "images/bleuback.gif");
    }

    @Override
    void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

}
