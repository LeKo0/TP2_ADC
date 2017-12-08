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

    /**
     * Type d'updates
     */
    public enum TypeUpdate {
        POINTS, VIE, RECOMMENCER, RESET_VIE
    };
    private TypeUpdate update = null;

    /**
     * Constructeur par défaut
     */
    public ControlleurADC() {
    }

    public TypeUpdate getUpdate() {
        return update;
    }

    /**
     * Détecte la fin du jeu
     *
     * @param vie
     * @return
     */
    public boolean finDePartie(int vie) {
        return vie <= 0;
    }

    /**
     * Redonne toute sa vie au joueur
     */
    public void resetVie() {
        update = TypeUpdate.RESET_VIE;
        modeleADC.setPointsVie(ModeleADC.INIT_VIE);
    }

    /**
     * Enlève un point de vie au héro
     */
    public void heroToucher() {
        update = TypeUpdate.VIE;
        modeleADC.setPointsVie(modeleADC.getPointsVie() - 1);
    }

    /**
     * Recommence la partie
     */
    public void recommencer() {
        update = TypeUpdate.RECOMMENCER;
        modeleADC.recommencer();
    }

    /**
     * Donne des points au joueur
     * @param points
     */
    public void augmenterPoints(int points) {
        update = TypeUpdate.POINTS;
        modeleADC.setPointage(modeleADC.getPointage() + points);
    }
}
