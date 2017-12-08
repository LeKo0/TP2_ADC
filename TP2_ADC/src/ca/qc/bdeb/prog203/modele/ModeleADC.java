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
    public static int INIT_VIE = 3;

    /**
     * Constructeur par défaut
     */
    public ModeleADC() {
        this.pointage = 0;
        this.pointsVie = INIT_VIE;
    }

    /**
     * Réinitialise la vie du joueur
     */
    public void recommencer() {
        pointage = 0;
        pointsVie = INIT_VIE;
        majObserver();
    }

    public int getPointage() {
        return pointage;
    }

    public void setPointage(int pointage) {
        this.pointage = pointage;
        majObserver();
    }

    public int getPointsVie() {
        return pointsVie;
    }

    /**
     * Met à jour les observateurs
     */
    public void majObserver() {
        setChanged();
        notifyObservers();
    }

    public void setPointsVie(int pointsVie) {
        this.pointsVie = pointsVie;
        majObserver();
    }

}
