/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Image;
import java.util.Random;

/**
 * Classe mère pour les ennemis
 */
public abstract class Ennemis extends Personnages {

    public static int HAUTEUR = 43, LARGEUR = 34;
    protected int points;
    protected Bonus bonus;
    protected int pointsDeVie;

    public Ennemis(int pointsDeVie, int points, int vitesse, Image imageFace, Image imageDos,
            Image imageDroite, Image imageGauche) {
        super(imageFace, imageDos, imageDroite, imageGauche, LARGEUR, HAUTEUR, vitesse);

        this.points = points;
        this.pointsDeVie = pointsDeVie;

        initBonus();

    }

    /**
     * Initialise le bounus de l'ennemi 1 chance sur 15
     */
    private void initBonus() {
        Random random = new Random();
        if (random.nextInt(15) == 0) {
            this.bonus = new Bonus();
        } else {
            this.bonus = null;
        }
    }

    public Bonus getBonus() {
        return this.bonus;
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }
    public int getPoints(){
        return points;
    }

}
