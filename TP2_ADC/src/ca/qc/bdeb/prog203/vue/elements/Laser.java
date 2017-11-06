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
public class Laser extends Projectiles {

    public Laser() throws MalformedURLException {
        //je sais pas encore c'est quoi la vitesse
        super(new ImageIcon(new URL("path de l'image")), 0);
    }

}
