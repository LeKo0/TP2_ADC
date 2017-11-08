/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

/**
 *
 * @author 1648266
 */
public class Dessin extends JComponent{
    
    private Image img;
    
    public Dessin(Image img){
        this.img = img;
        setSize(img.getWidth(this),img.getHeight(this) );
        
    }
    public void paintComponent(Graphics g){
        g.drawImage(img, 0,0,this);
    }
    
}
