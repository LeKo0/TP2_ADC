/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.modele.elements;

/**
 *
 * @author Leo
 */
public abstract class Projectiles {

    protected String image;
    protected int vitesse;

    public Projectiles(String image, int vitesse) {
        this.image = image;
        this.vitesse = vitesse;
    }

}
