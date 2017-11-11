/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.vue.elements.Buisson;
import ca.qc.bdeb.prog203.vue.elements.Heros;
import ca.qc.bdeb.prog203.vue.elements.Roche;
import javax.swing.JPanel;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLayeredPane;

/**
 *
 * @author 1648266
 */
public class Monde extends JPanel {

    public Monde() {
        setLayout(null);
        init();

    }

    private void init() {
        //Pour une raison que j'ignore paintComponent paint les images en du bas vers le haut. Si tu inverse this.add(roche) et this.add(gazon) tu vas voir que le gazon est sur le top de la roche.
        Roche roche = new Roche();
        Gazon gazon = new Gazon(16 * Gazon.DIMENSION_GAZON, 14 * Gazon.DIMENSION_GAZON);
        roche.setLocation(100,100);
        
        this.add(roche);
        this.add(gazon);
        
    }

}
