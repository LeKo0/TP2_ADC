/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.controlleur;

import ca.qc.bdeb.prog203.modele.ModeleADC;
import ca.qc.bdeb.prog203.vue.FenetreADC;
import ca.qc.bdeb.prog203.vue.elements.Ennemis;

/**
 *
 * @author 1666876
 */
public class ControlleurADC {

    private final ModeleADC modeleADC = new ModeleADC();
    private final FenetreADC fenetreADC = new FenetreADC(this,modeleADC);

    /**
     *
     */
    public ControlleurADC() {
    }
    
    /**
     *
     * @param vie
     * @return
     */
    public boolean finDePartie(int vie){
        if (vie >= 0){
            return true;
        }
        return false;
    }
    
    /**
     *
     */
    public void heroToucher(){
        modeleADC.setPointsVie(modeleADC.getPointsVie() - 1);
        finDePartie(modeleADC.getPointsVie());
    }

    /**
     *
     * @param ennemi
     */
    public void ennemiTuer(Ennemis ennemi){
        
    }
}
