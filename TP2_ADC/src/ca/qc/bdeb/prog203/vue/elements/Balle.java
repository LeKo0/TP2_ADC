/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import static ca.qc.bdeb.prog203.vue.elements.Laser.HAUTEUR;
import static ca.qc.bdeb.prog203.vue.elements.Laser.LARGEUR;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Leo
 */
public class Balle extends Projectiles {

    public static int LARGEUR = 0, HAUTEUR = 0;

    public Balle() {
        super(Toolkit.getDefaultToolkit().getImage("path de l'image"), 0, 0, LARGEUR, HAUTEUR);
    }

}
