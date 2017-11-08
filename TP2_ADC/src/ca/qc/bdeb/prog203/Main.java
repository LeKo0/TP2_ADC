/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203;

import ca.qc.bdeb.prog203.modele.ModeleADC;
import ca.qc.bdeb.prog203.vue.FenetreADC;

/**
 *
 * @author 1666876
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ModeleADC modele = new ModeleADC();
        FenetreADC fenetre = new FenetreADC(modele);
    }
    
}
