/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.modele.elements;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Vitesse de 1 et PV de 2
 *
 * @author Leo
 */
public class TMauve extends Ennemis {

    public TMauve() {
        super(1, 2, 3, "images/purplefront.gif", "images/purpleback.gif",
                "images/purplefront.gif", "images/purpleback.gif");
    }

    @Override
    void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

}
