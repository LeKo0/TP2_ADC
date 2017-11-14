/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.vue.elements.Balle;
import ca.qc.bdeb.prog203.vue.elements.Buisson;
import ca.qc.bdeb.prog203.vue.elements.Heros;
import ca.qc.bdeb.prog203.vue.elements.Laser;
import ca.qc.bdeb.prog203.vue.elements.Obstacles;
import ca.qc.bdeb.prog203.vue.elements.Personnages;
import ca.qc.bdeb.prog203.vue.elements.Projectiles;
import ca.qc.bdeb.prog203.vue.elements.Roche;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author 1648266
 */
public class Monde extends JPanel {

    private enum typeProjectile {
        LASER, BALLE
    }

    private typeProjectile typeProjectile;
    private Heros heros;
    private final int tailleImageGazon = 32;
    private ArrayList<Integer> listeKeyCodes = new ArrayList();

    private ArrayList<Projectiles> projectiles = new ArrayList<Projectiles>();
    private ArrayList<Obstacles> obstacles = new ArrayList<Obstacles>();
    private int[] lastPosition = new int[2];

    private Timer tirer = new javax.swing.Timer(250, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            tirer();
        }

    });
    ;

    private Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                majJeu();
                
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ex) {
                }

            }

        }

    };

    public Monde() {
        setPreferredSize(new Dimension(16 * tailleImageGazon, 14 * tailleImageGazon));
        setLayout(null);
        typeProjectile = typeProjectile.LASER;
        init();
        tirer.setInitialDelay(0);
        nouvellePartie();

    }

    private void tirer() {

        switch (typeProjectile) {
            case LASER:
                tirerLaser();
                break;
            case BALLE:
                tirerBalle();
        }

    }

    private void majJeu() {
        if (listeKeyCodes.contains(KeyEvent.VK_A)) {
            heros.setDirection(Personnages.Direction.GAUCHE);
            heros.setLocation(heros.getX() - heros.getMaxVitesse(), heros.getY());
        }

        if (listeKeyCodes.contains(KeyEvent.VK_S)) {
            heros.setDirection(Personnages.Direction.BAS);
            heros.setLocation(heros.getX(), heros.getY()
                    + heros.getMaxVitesse());
        }
        if (listeKeyCodes.contains(KeyEvent.VK_W)) {
            heros.setDirection(Personnages.Direction.HAUT);
            heros.setLocation(heros.getX(), heros.getY() - heros.getMaxVitesse());
        }
        if (listeKeyCodes.contains(KeyEvent.VK_D)) {
            heros.setDirection(Personnages.Direction.DROITE);
            heros.setLocation(heros.getX() + heros.getMaxVitesse(), heros.getY());
        }
        
        
        for (Obstacles obstacle : obstacles) {
            if (heros.getBounds().intersects(obstacle.getBounds())) {
                heros.setLocation(lastPosition[0], lastPosition[1]);
            }
        }

        if (heros.getX() - heros.getMaxVitesse() <= 0) {
            heros.setLocation(lastPosition[0], lastPosition[1]);
        }
        if (heros.getY() + heros.getHeight() + heros.getMaxVitesse() >= this.getHeight()) {
            heros.setLocation(lastPosition[0], lastPosition[1]);
        }
        if (heros.getY() - heros.getMaxVitesse() <= 0) {
            heros.setLocation(lastPosition[0], lastPosition[1]);
        }
        if (heros.getX() + heros.getWidth() + heros.getMaxVitesse() >= this.getWidth()) {
            heros.setLocation(lastPosition[0], lastPosition[1]);
        }
        lastPosition[0] = heros.getX();
        lastPosition[1] = heros.getY();
        

        if (listeKeyCodes.contains(KeyEvent.VK_SPACE)) {
            if (!tirer.isRunning()) {
                tirer.start();
            }
        } else {
            tirer.stop();
        }

        for (Projectiles projectile : projectiles) {
            projectile.bouger();
        }

    }

    private void tirerLaser() {

        Laser laserTemp;

        switch (heros.getDirection()) {
            case HAUT:
                laserTemp = new Laser(0, -1);
                break;
            case BAS:
                laserTemp = new Laser(0, 1);
                break;
            case DROITE:
                laserTemp = new Laser(1, 0);
                break;
            default: //case GAUCHE:
                laserTemp = new Laser(-1, 0);
        }
        laserTemp.setLocation(heros.getX(), heros.getY());
        projectiles.add(laserTemp);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                add(laserTemp, 0);
            }
        });

    }

    private void tirerBalle() {

    }

    private void nouvellePartie() {
        creerEvenement();
        thread.start();

    }

    private void init() {
        heros = new Heros();
        Gazon gazon = new Gazon(16 * Gazon.DIMENSION_GAZON, 14 * Gazon.DIMENSION_GAZON);
        ArrayList<Roche> roches = new ArrayList<>();
        ArrayList<Buisson> buissons = new ArrayList<>();
        Roche rocheTemp;
        Buisson buissonTemp;

        //Ajout du gazon
        add(gazon, 0);

        System.out.println(gazon.getSize());
        //Ajout du heros au centre
        add(heros, 0);
        heros.setLocation(8 * tailleImageGazon - 11, 7 * tailleImageGazon - 25);
        lastPosition[0] = heros.getX();
        lastPosition[1] = heros.getY();
        //Disposition des roches
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if (((i == 0 | i == 13) & (j <= 4 || j >= 11))
                        || ((j == 0 | j == 15) & (i <= 3 || i >= 10))) {
                    rocheTemp = new Roche();
                    rocheTemp.setLocation(j * tailleImageGazon, i * tailleImageGazon);
                    roches.add(rocheTemp);
                    obstacles.add(rocheTemp);
                    add(rocheTemp, 0);
                }
            }
        }

        //Disposition des buissons
        for (int i = 0; i < 4; i++) {
            buissonTemp = new Buisson();
            buissons.add(buissonTemp);
            obstacles.add(buissonTemp);
            add(buissonTemp, 0);
        }
        buissons.get(0).setLocation(3 * tailleImageGazon, 3 * tailleImageGazon);
        buissons.get(1).setLocation(12 * tailleImageGazon, 3 * tailleImageGazon);
        buissons.get(2).setLocation(3 * tailleImageGazon, 10 * tailleImageGazon);
        buissons.get(3).setLocation(12 * tailleImageGazon, 10 * tailleImageGazon);

    }

    private void creerEvenement() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!listeKeyCodes.contains(e.getKeyCode())) {
                    listeKeyCodes.add(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                listeKeyCodes.remove(new Integer(e.getKeyCode()));

            }

        });

    }

}
