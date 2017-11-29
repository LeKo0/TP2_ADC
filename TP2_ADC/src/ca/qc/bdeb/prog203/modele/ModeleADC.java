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
    
    public ModeleADC() {
        this.pointage = 0;
        this.pointsVie = 0;
    }

    public int getPointage() {
        return pointage;
    }

    public int getPointsVie() {
        return pointsVie;
    }
    
    public void majObserver(){
        setChanged();
        notifyObservers();
    }

    public void setPointsVie(int pointsVie) {
        this.pointsVie = pointsVie;
    }
    
    
}
