/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author 1648266
 */
public class Gazon extends JComponent {

    public static final Image IMAGE_GAZON = Toolkit.getDefaultToolkit().getImage("images/floor1.gif");
    public static final int DIMENSION_GAZON = 32; //On doit le mettre manuellement 
    private int hauteur, largeur;

    public Gazon(int largeur, int hauteur) {

        this.largeur = largeur;
        this.hauteur = hauteur;

        setSize(largeur, hauteur);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                g.drawImage(IMAGE_GAZON, i * DIMENSION_GAZON, j * DIMENSION_GAZON, this);

            }
        }
    }

}
