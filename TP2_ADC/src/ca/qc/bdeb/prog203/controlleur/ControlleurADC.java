/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.controlleur;

import ca.qc.bdeb.prog203.modele.ModeleADC;
import ca.qc.bdeb.prog203.vue.FenetreADC;

/**
 *
 * @author 1666876
 */
public class ControlleurADC {

    private final ModeleADC modeleADC = new ModeleADC();
    private final FenetreADC fenetreADC = new FenetreADC(this, modeleADC);
    public enum TypeUpdate{
        POINTS, VIE, RECOMMENCER, RESET_VIE
    };
    private TypeUpdate update = null;
    
    /**
     *
     */
    public ControlleurADC() {
    }
    
    public TypeUpdate getUpdate(){
        return update;
    }
    
    /**
     *
     * @param vie
     * @return
     */
    public boolean finDePartie(int vie){
        if (vie <= 0){
            return true;
        }
        return false;
    }
    
    public void resetVie(){
        update = TypeUpdate.RESET_VIE;
        modeleADC.setPointsVie(ModeleADC.INIT_VIE);
    }
    
    /**
     *
     */
    public void heroToucher(){
        update = TypeUpdate.VIE;
        modeleADC.setPointsVie(modeleADC.getPointsVie() - 1);
    }
    public void recommencer(){
        update = TypeUpdate.RECOMMENCER;
        modeleADC.recommencer();
    }
    /**
     * 
     * @param points 
     */
    public void augmenterPoints(int points){
        update = TypeUpdate.POINTS;
        modeleADC.setPointage(modeleADC.getPointage() + points);
    }
}
