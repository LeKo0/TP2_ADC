/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Vitesse de 1 et PV de 1
 *
 * @author Leo
 */
import javax.swing.ImageIcon;

public class TVert extends Ennemis {

    public TVert() throws MalformedURLException {

        super(1, 1, 1, new ImageIcon(new URL("images/greenfront.gif")), new ImageIcon(new URL("images/greenback.gif")),
                new ImageIcon(new URL("images/greenfront.gif")), new ImageIcon(new URL("images/greenback.gif")));
    }

    @Override
    void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

}
