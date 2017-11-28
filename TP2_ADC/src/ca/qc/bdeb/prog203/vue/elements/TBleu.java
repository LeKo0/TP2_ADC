/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Toolkit;


public class TBleu extends Ennemis {

    public TBleu() {

        super(1, 2, 3, Toolkit.getDefaultToolkit().getImage("images/bleufront.gif"), Toolkit.getDefaultToolkit().getImage("images/bleuback.gif"),
                Toolkit.getDefaultToolkit().getImage("images/bleufront.gif"), Toolkit.getDefaultToolkit().getImage("images/bleuback.gif"));
    }

}
