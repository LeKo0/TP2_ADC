/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.vue.elements.Buisson;
import ca.qc.bdeb.prog203.vue.elements.Heros;
import ca.qc.bdeb.prog203.vue.elements.Roche;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author 1648266
 */
public class Monde extends JPanel {
    
    private Heros heros;

    public Monde() {
        setPreferredSize(new Dimension(16 * 32, 14 * 32));
        setLayout(null);
        init();

    }

    private void init() {    
        heros = new Heros();
        
        Gazon gazon = new Gazon(16 * Gazon.DIMENSION_GAZON, 14 * Gazon.DIMENSION_GAZON);
        ArrayList<Roche> roches = new ArrayList<>();
        ArrayList<Buisson> buissons = new ArrayList<>();
        Roche rocheTemp;
        Buisson buissonTemp;
        
        //Ajout du gazon
        add(gazon, 0);
                
        System.out.println(gazon.getSize());
        //Ajout du heros au centre
        add(heros,0);
        heros.setLocation(8*32-11, 7*32-25);

        //Disposition des roches
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if (((i == 0 | i == 13) & (j <= 4 || j >= 11))
                        || ((j == 0 | j == 15) & (i <= 3 || i >= 10))) {
                    rocheTemp = new Roche();
                    rocheTemp.setLocation(j * 32, i * 32);
                    roches.add(rocheTemp);
                    add(rocheTemp, 0);
                }
            }
        }

        //Disposition des buissons
        for (int i = 0; i < 4; i++) {
            buissonTemp = new Buisson();
            buissonTemp.setLocation(i, i);
            buissons.add(buissonTemp);
            add(buissonTemp,0);
        }
        buissons.get(0).setLocation(3*32, 3*32);
        buissons.get(1).setLocation(12*32, 3*32);
        buissons.get(2).setLocation(3*32, 10*32);
        buissons.get(3).setLocation(12*32, 10*32);

    }

}
