/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.modele;

import java.util.Observable;

/**
 *
 * @author 1666876
 */
public class ModeleADC extends Observable {
    private int pointage;
    private int pointsVie;
    private final int INIT_VIE = 3;
    
    
    public void recommencer(){
        pointage = 0;
        pointsVie = INIT_VIE;
        majObserver();
    }
    
    /**
     *
     */
    public ModeleADC() {
        this.pointage = 0;
        this.pointsVie = INIT_VIE;
    }

    /**
     *
     * @return
     */
    public int getPointage() {
        return pointage;
    }
    public void setPointage(int points){
        this.pointage = points;
        majObserver();
    }
    /**
     *
     * @return
     */
    public int getPointsVie() {
        return pointsVie;
    }
    
    /**
     *
     */
    public void majObserver(){
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param pointsVie
     */
    public void setPointsVie(int pointsVie) {
        this.pointsVie = pointsVie;
        majObserver();
    }
    
    
}
