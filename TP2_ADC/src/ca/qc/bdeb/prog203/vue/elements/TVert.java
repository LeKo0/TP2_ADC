/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Toolkit;

/**
 * Vitesse de 1 et PV de 1
 *
 * @author Leo
 */
public class TVert extends Ennemis {

    public TVert() {

        super(1, 1, 1, Toolkit.getDefaultToolkit().getImage("images/greenfront.gif"), Toolkit.getDefaultToolkit().getImage("images/greenback.gif"),
                Toolkit.getDefaultToolkit().getImage("images/greenfront.gif"), Toolkit.getDefaultToolkit().getImage("images/greenback.gif"));
    }

}
