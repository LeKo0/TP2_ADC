/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Leo
 */
public class Roche extends Obstacles {

    public Roche() {
        super(Toolkit.getDefaultToolkit().getImage("images/roche1.gif"));
    }

}
