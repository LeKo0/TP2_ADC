/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import javax.swing.JPanel;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author 1648266
 */
public class Monde extends JPanel {
    
    private final Image imgGazon = Toolkit.getDefaultToolkit().getImage("images/floor1.gif");
    
    public Monde(){
        setLayout(null);
        initPlateforme();
        
        
        
    }
    private void initPlateforme(){
        int nombre_x = round(this.getWidth()/imgGazon.getHeight(this));
        int nombre_y = round(this.getHeight()/imgGazon.getWidth(this));
        System.out.println(imgGazon.getHeight(this));
        System.out.println(nombre_y);
        
        ArrayList<ArrayList> listeGazon = new ArrayList();
        for (int i = 0; i<nombre_x; i++){
            ArrayList<Dessin> temp = new ArrayList();
            for (int j = 0; j<nombre_y; j++){
                temp.add(new Dessin(imgGazon));
                ((Dessin)temp.get(j)).setLocation(imgGazon.getWidth(this) * i, imgGazon.getHeight(this)*j);
            }
            listeGazon.add(temp);
        }
        for (int i = 0; i<nombre_x; i++){
            for (int j = 0; j<nombre_y; j++){
                add((Dessin) listeGazon.get(i).get(j));
            }
        }
    }
    
}
