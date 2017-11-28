/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.controlleur.ControlleurADC;
import ca.qc.bdeb.prog203.vue.elements.Balle;
import ca.qc.bdeb.prog203.vue.elements.Buisson;
import ca.qc.bdeb.prog203.vue.elements.Ennemis;
import ca.qc.bdeb.prog203.vue.elements.Heros;
import ca.qc.bdeb.prog203.vue.elements.Laser;
import ca.qc.bdeb.prog203.vue.elements.Obstacles;
import ca.qc.bdeb.prog203.vue.elements.Personnages;
import ca.qc.bdeb.prog203.vue.elements.Projectiles;
import ca.qc.bdeb.prog203.vue.elements.Roche;
import ca.qc.bdeb.prog203.vue.elements.TBleu;
import ca.qc.bdeb.prog203.vue.elements.TMauve;
import ca.qc.bdeb.prog203.vue.elements.TVert;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
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
    private ArrayList<Ennemis> ennemis = new ArrayList<Ennemis>();
    private ArrayList<Ennemis> enleverEnnemis = new ArrayList<Ennemis>();
    private ArrayList<Projectiles> eraseProjectile = new ArrayList();
    private ControlleurADC controlleur;
    private Random random = new Random();

    private final Timer tirer = new javax.swing.Timer(250, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            tirer();
        }

    });
//   private final Timer spawm = new javax.swing.Timer(3000, new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            Point position = new Point(0, 0);
//
//            switch (random.nextInt(3)) {
//                case 0:
//                    ennemis.add(new TBleu());
//                    break;
//                case 1:
//                    ennemis.add(new TMauve());
//                    break;
//                case 2:
//                    ennemis.add(new TVert());
//                    break;
//            }
//            switch (random.nextInt(4)) {
//                case 0:
//                    position.x = -Ennemis.LARGEUR;
//                    position.y = ((int) Monde.this.getHeight() / 2) - ((int) Ennemis.HAUTEUR / 2);
//                    break;
//                case 1:
//                    position.x = Ennemis.LARGEUR + Monde.this.getWidth();
//                    position.y = ((int) Monde.this.getHeight() / 2) - ((int) Ennemis.HAUTEUR / 2);
//                    break;
//                case 2:
//                    position.x = ((int) Monde.this.getWidth() / 2) - ((int) Ennemis.LARGEUR / 2);
//                    position.y = -Ennemis.HAUTEUR;
//                    break;
//                case 3:
//                    position.x = ((int) Monde.this.getWidth() / 2) - ((int) Ennemis.LARGEUR / 2);
//                    position.y = Monde.this.getHeight() + Ennemis.HAUTEUR;
//
//            }
//
//            ennemis.get(ennemis.size() - 1).setLocation(position);
//            Monde.this.add(ennemis.get(ennemis.size() - 1), 0);
//        }
//    });

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

    public Monde(ControlleurADC controlleur) {
        this.controlleur = controlleur;
        setPreferredSize(new Dimension(16 * tailleImageGazon, 14 * tailleImageGazon));
        setLayout(null);
        typeProjectile = typeProjectile.BALLE;
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

    private void bougerHero() {

         if (listeKeyCodes.contains(KeyEvent.VK_A)) {
            heros.setDirection(Personnages.Direction.GAUCHE);
            heros.setLocation(heros.getX() - heros.getVitesse(), heros.getY());
        }
        if (listeKeyCodes.contains(KeyEvent.VK_D)) {
            heros.setDirection(Personnages.Direction.DROITE);
            heros.setLocation(heros.getX() + heros.getVitesse(),heros.getY());
        }

        for (Obstacles obstacle : obstacles) {
            if (heros.getBounds().intersects(obstacle.getBounds())) {
                heros.setLocation(heros.getLastPosition().x, heros.getY());
            }
        }
        if (heros.getX() - heros.getVitesse() <= 0) {
            heros.setLocation(heros.getLastPosition().x, heros.getY());
        }
        if (heros.getX() + heros.getWidth() + heros.getVitesse() >= this.getWidth()) {
            heros.setLocation(heros.getLastPosition().x, heros.getY());
        }

        heros.setLastPosition(heros.getLocation());
        if (listeKeyCodes.contains(KeyEvent.VK_S)) {
            heros.setDirection(Personnages.Direction.BAS);
            heros.setLocation(heros.getX(),heros.getY() + heros.getVitesse());
        }
        if (listeKeyCodes.contains(KeyEvent.VK_W)) {
            heros.setDirection(Personnages.Direction.HAUT);
            heros.setLocation(heros.getX(),heros.getY() - heros.getVitesse());
        }
        for (Obstacles obstacle : obstacles) {
            if (heros.getBounds().intersects(obstacle.getBounds())) {
                heros.setLocation(heros.getX(), heros.getLastPosition().y);
            }
        }

        if (heros.getY() + heros.getHeight() + heros.getVitesse() >= this.getHeight()) {
            heros.setLocation(heros.getX(), heros.getLastPosition().y);
        }
        if (heros.getY() - heros.getVitesse() <= 0) {
            heros.setLocation(heros.getX(), heros.getLastPosition().y);
        }
        heros.setLastPosition(heros.getLocation());

    }

    private void bougerEnnemi(Ennemis ennemi) {
        int vitesseX = ennemi.getVitesse();
        int vitesseY = ennemi.getVitesse();

        if (heros.getX() < ennemi.getX()) {
            vitesseX = -vitesseX;
        }
        if (heros.getY() < ennemi.getY()) {
            vitesseY = -vitesseY;
        }
        ennemi.setLocation(ennemi.getX() + vitesseX, ennemi.getY());

        for (Obstacles obstacle : obstacles) {
            if (ennemi.getBounds().intersects(obstacle.getBounds())) {
                ennemi.setLocation(ennemi.getLastPosition().x, ennemi.getY());
            }
        }

        ennemi.setLocation(ennemi.getX(), ennemi.getY() + vitesseY);
        for (Obstacles obstacle : obstacles) {
            if (ennemi.getBounds().intersects(obstacle.getBounds())) {
                ennemi.setLocation(ennemi.getX(), ennemi.getLastPosition().y);
            }
        }
        ennemi.setLastPosition(ennemi.getLocation());

        boolean gone = false;
        for (Projectiles projectile : projectiles) {
            if (ennemi.getBounds().intersects(projectile.getBounds()) && !gone) {
                gone = true;
                eraseProjectile.add(projectile);
            }
        }

        if (ennemi.getBounds().intersects(heros.getBounds()) && !gone) {
            controlleur.heroToucher();
            gone = true;
        }

        if (gone) {
            enleverEnnemis.add(ennemi);
        }

    }

    private void majJeu() {

        bougerHero();
        for (Ennemis clone : ennemis) {
            bougerEnnemi(clone);
        }
        for (Ennemis delete : enleverEnnemis) {
            ennemis.remove(delete);
            this.remove(delete);
        }

        if (listeKeyCodes.contains(KeyEvent.VK_SPACE)) {
            if (!tirer.isRunning()) {
                tirer.start();
            }
        } else {
            tirer.stop();
        }

        for (Projectiles projectile : projectiles) {
            projectile.bouger();

            //colission avec la fenetre
            if ((projectile.getDeltaX() < 0 && projectile.getX() + projectile.getWidth() <= 0) || (projectile.getDeltaX() > 0 && projectile.getX() >= this.getWidth()) || (projectile.getDeltaY() < 0 && projectile.getY() + projectile.getHeight() <= 0) || (projectile.getDeltaY() > 0 && projectile.getY() >= this.getHeight())) {
                eraseProjectile.add(projectile);
            }

            for (Obstacles obstacle : obstacles) {
                if (projectile.getBounds().intersects(obstacle.getBounds())) {
                    eraseProjectile.add(projectile);
                }
            }

            //faire collision avec les obstacles
        }
        for (Projectiles projectile : eraseProjectile) {
            projectiles.remove(projectile);
            this.remove(projectile);
        }
        eraseProjectile.clear();

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
        laserTemp.setLocation(heros.getLocation());
        projectiles.add(laserTemp);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                add(laserTemp, 0);
            }
        });

    }

    private void tirerBalle() {

        Balle[] ballesTemp = new Balle[3];

        switch (heros.getDirection()) {
            case HAUT:
                ballesTemp[0] = new Balle(-1, -1);
                ballesTemp[1] = new Balle(0, -1);
                ballesTemp[2] = new Balle(1, -1);
                break;
            case BAS:
                ballesTemp[0] = new Balle(1, 1);
                ballesTemp[1] = new Balle(0, 1);
                ballesTemp[2] = new Balle(-1, 1);
                break;
            case DROITE:
                ballesTemp[0] = new Balle(1, -1);
                ballesTemp[1] = new Balle(1, 0);
                ballesTemp[2] = new Balle(1, 1);
                break;
            default: //case GAUCHE:
                ballesTemp[2] = new Balle(-1, 1);
                ballesTemp[0] = new Balle(-1, -1);
                ballesTemp[1] = new Balle(-1, 0);

        }

        for (Balle balle : ballesTemp) {
            balle.setLocation(heros.getLocation());
            projectiles.add(balle);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                add(ballesTemp[0], 0);
                add(ballesTemp[1], 0);
                add(ballesTemp[2], 0);

            }
        });

    }

    private void nouvellePartie() {
        creerEvenement();
        thread.start();
        //spawm.start();

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

        heros.setLastPosition(heros.getLocation());

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

