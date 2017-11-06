/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Leo
 */
public class Heros extends Personnages {

    public Heros() throws MalformedURLException {
        
        
        
        super(new ImageIcon(new URL("images/herofront.gif")), new ImageIcon(new URL("images/heroback.gif")),
                new ImageIcon(new URL("images/herodroite.gif")), new ImageIcon(new URL("images/herogauche.gif")), 3);
    }

    @Override
    void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

}
