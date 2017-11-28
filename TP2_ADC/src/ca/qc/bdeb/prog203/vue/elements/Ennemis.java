/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Image;
import java.util.Random;

/**
 *
 * @author Leo
 */
public abstract class Ennemis extends Personnages {

    public static int HAUTEUR = 43, LARGEUR = 34;
    protected int deltaX, deltaY;
    protected int points;
    protected Bonus bonus;

    public Ennemis(int pointsDeVie, int points, int vitesse, Image imageFace, Image imageDos,
            Image imageDroite, Image imageGauche) {
        super(pointsDeVie, imageFace, imageDos, imageDroite, imageGauche, LARGEUR, HAUTEUR, vitesse);

        this.points = points;
        initBonus();

    }

    private final void initBonus() {
        Random random = new Random();
        if (true) {
            this.bonus = new Bonus();
        } else {
            this.bonus = null;
        }
    }
    
    public Bonus getBonus(){
        return this.bonus;
    }

    

}
