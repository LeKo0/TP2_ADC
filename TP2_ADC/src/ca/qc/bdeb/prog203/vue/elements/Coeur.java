/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author souleiman
 */
public class Coeur extends JComponent{
    private final Image img = Toolkit.getDefaultToolkit().getImage("images/coeur.gif");
    
    public Coeur(){
        setSize(20,18);
    }
    
    /**
     * Dessine le coeur
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0,0,this);
    }
    
    
    
}
