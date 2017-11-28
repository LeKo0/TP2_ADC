/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Toolkit;


public class TMauve extends Ennemis {

    public TMauve() {
        super(2, 3, 2, Toolkit.getDefaultToolkit().getImage("images/purplefront.gif"), Toolkit.getDefaultToolkit().getImage("images/purpleback.gif"),
                Toolkit.getDefaultToolkit().getImage("images/purplefront.gif"), Toolkit.getDefaultToolkit().getImage("images/purpleback.gif"));
    }

}
