/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.controlleur.ControlleurADC;
import ca.qc.bdeb.prog203.vue.elements.Balle;
import ca.qc.bdeb.prog203.vue.elements.Bonus;
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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    public static final Image IMAGE_GAZON1 = Toolkit.getDefaultToolkit().getImage("images/floor1.gif");
    public static final Image IMAGE_GAZON2 = Toolkit.getDefaultToolkit().getImage("images/floor2.gif");
    public static final int DIMENSION_GAZON = 32;
    public static final int HAUTEUR = 16, LARGEUR = 14;
    
    private typeProjectile typeProjectile;
    
    private Heros heros;
    
    private final ArrayList<Integer> listeKeyCodes = new ArrayList();

    private final ArrayList<Obstacles> listeObstacles = new ArrayList<>();

    private final ArrayList<Bonus> listeBonus = new ArrayList<>();
    private final ArrayList<Bonus> listeBonusAEnlever = new ArrayList<>();

    private final ArrayList<Projectiles> listeProjectiles = new ArrayList<>();
    private final ArrayList<Projectiles> listeProjectilesAEnlever = new ArrayList();

    private ArrayList<Ennemis> listeEnnemis = new ArrayList<>();
    private final ArrayList<Ennemis> listeEnnemisAEnlever = new ArrayList<>();

    private final ControlleurADC controlleur;
    
    private Random random = new Random();

    private final Timer cadenceDeTir = new javax.swing.Timer(250, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch (typeProjectile) {
                case LASER:
                    tirerLaser();
                    break;
                case BALLE:
                    tirerBalle();
            }
        }

    });
    private final Timer spawm = new javax.swing.Timer(3000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point position = new Point(0, 0);

            switch (random.nextInt(3)) {
                case 0:
                    listeEnnemis.add(new TBleu());
                    break;
                case 1:
                    listeEnnemis.add(new TMauve());
                    break;
                case 2:
                    listeEnnemis.add(new TVert());
                    break;
            }
            switch (random.nextInt(4)) {
                case 0:
                    position.x = -Ennemis.LARGEUR;
                    position.y = ((int) Monde.this.getHeight() / 2) - ((int) Ennemis.HAUTEUR / 2);
                    break;
                case 1:
                    position.x = Ennemis.LARGEUR + Monde.this.getWidth();
                    position.y = ((int) Monde.this.getHeight() / 2) - ((int) Ennemis.HAUTEUR / 2);
                    break;
                case 2:
                    position.x = ((int) Monde.this.getWidth() / 2) - ((int) Ennemis.LARGEUR / 2);
                    position.y = -Ennemis.HAUTEUR;
                    break;
                case 3:
                    position.x = ((int) Monde.this.getWidth() / 2) - ((int) Ennemis.LARGEUR / 2);
                    position.y = Monde.this.getHeight() + Ennemis.HAUTEUR;

            }

            listeEnnemis.get(listeEnnemis.size() - 1).setLocation(position);
            Monde.this.add(listeEnnemis.get(listeEnnemis.size() - 1), 0);
        }
    });
    private final Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                majJeu();
                Monde.this.invalidate();
                Monde.this.repaint();
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ex) {
                    invalidate();
                    repaint();
                }

            }

        }

    };

    public Monde(ControlleurADC controlleur) {
        this.controlleur = controlleur;
        
        setPreferredSize(new Dimension(HAUTEUR * DIMENSION_GAZON, LARGEUR * DIMENSION_GAZON));
        setLayout(null);
        typeProjectile = typeProjectile.LASER;

        cadenceDeTir.setInitialDelay(0);
        initGraphiques();
        nouvellePartie();

    }

    private void initGraphiques() {
        heros = new Heros();
        ArrayList<Roche> roches = new ArrayList<>();
        ArrayList<Buisson> buissons = new ArrayList<>();
        Roche rocheTemp;
        Buisson buissonTemp;

        //Ajout du heros au centre
        add(heros, 0);
        heros.setLocation(8 * DIMENSION_GAZON - 11, 7 * DIMENSION_GAZON - 25);
        heros.setLastPosition(heros.getLocation());

        //Disposition des roches
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if (((i == 0 | i == 13) & (j <= 4 || j >= 11))
                        || ((j == 0 | j == 15) & (i <= 3 || i >= 10))) {
                    rocheTemp = new Roche();
                    rocheTemp.setLocation(j * DIMENSION_GAZON, i * DIMENSION_GAZON);
                    roches.add(rocheTemp);
                    listeObstacles.add(rocheTemp);
                    add(rocheTemp, 0);
                }
            }
        }

        //Disposition des buissons
        for (int i = 0; i < 4; i++) {
            buissonTemp = new Buisson();
            buissons.add(buissonTemp);
            listeObstacles.add(buissonTemp);
            add(buissonTemp, 0);
        }
        buissons.get(0).setLocation(3 * DIMENSION_GAZON, 3 * DIMENSION_GAZON);
        buissons.get(1).setLocation(12 * DIMENSION_GAZON, 3 * DIMENSION_GAZON);
        buissons.get(2).setLocation(3 * DIMENSION_GAZON, 10 * DIMENSION_GAZON);
        buissons.get(3).setLocation(12 * DIMENSION_GAZON, 10 * DIMENSION_GAZON);

    }

    private void nouvellePartie() {
        creerEvenement();
        thread.start();
        spawm.start();

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

    private void majJeu() {

        bougerHero();
        majEnnemis();
        majProjectiles();
        majBonus();
        majTirer();

    }

    private void bougerHero() {

        if (listeKeyCodes.contains(KeyEvent.VK_A)) {
            heros.setDirection(Personnages.Direction.GAUCHE);
            heros.setLocation(heros.getX() - heros.getVitesse(), heros.getY());
        }
        if (listeKeyCodes.contains(KeyEvent.VK_D)) {
            heros.setDirection(Personnages.Direction.DROITE);
            heros.setLocation(heros.getX() + heros.getVitesse(), heros.getY());
        }
        for (Obstacles obstacle : listeObstacles) {
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
            heros.setLocation(heros.getX(), heros.getY() + heros.getVitesse());
        }
        if (listeKeyCodes.contains(KeyEvent.VK_W)) {
            heros.setDirection(Personnages.Direction.HAUT);
            heros.setLocation(heros.getX(), heros.getY() - heros.getVitesse());
        }
        for (Obstacles obstacle : listeObstacles) {
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

    private void majEnnemis() {
        for (Ennemis ennemi : listeEnnemis) {
            int vitesseX = ennemi.getVitesse();
            int vitesseY = ennemi.getVitesse();

            if (heros.getX() < ennemi.getX()) {
                vitesseX = -vitesseX;
            }
            if (heros.getY() < ennemi.getY()) {
                vitesseY = -vitesseY;
            }
            ennemi.setLocation(ennemi.getX() + vitesseX, ennemi.getY());

            for (Obstacles obstacle : listeObstacles) {
                if (ennemi.getBounds().intersects(obstacle.getBounds())) {
                    ennemi.setLocation(ennemi.getLastPosition().x, ennemi.getY());
                }
            }

            ennemi.setLocation(ennemi.getX(), ennemi.getY() + vitesseY);
            for (Obstacles obstacle : listeObstacles) {
                if (ennemi.getBounds().intersects(obstacle.getBounds())) {
                    ennemi.setLocation(ennemi.getX(), ennemi.getLastPosition().y);
                }
            }
            ennemi.setLastPosition(ennemi.getLocation());

            boolean dejaTouche = false;
            for (Projectiles projectile : listeProjectiles) {
                if (ennemi.getBounds().intersects(projectile.getBounds()) && !dejaTouche) {
                    dejaTouche = true;
                    listeProjectilesAEnlever.add(projectile);
                }
            }

            if (ennemi.getBounds().intersects(heros.getBounds()) && !dejaTouche) {
                controlleur.heroToucher();
                dejaTouche = true;
            }

            if (dejaTouche) {
                listeEnnemisAEnlever.add(ennemi);
            }
        }
        for (Ennemis ennemi : listeEnnemisAEnlever) {
            dropBonus(ennemi);
            System.out.println("----");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Monde.this.remove(ennemi);
                }
            });
        }
        listeEnnemis.removeAll(listeEnnemisAEnlever);
        listeEnnemisAEnlever.clear();
    }

    private void majProjectiles() {
        for (Projectiles projectile : listeProjectiles) {

            projectile.bouger();

            if (!this.getBounds().contains(projectile.getBounds())) {
                listeProjectilesAEnlever.add(projectile);
            }

            for (Obstacles obstacle : listeObstacles) {
                if (projectile.getBounds().intersects(obstacle.getBounds())) {
                    listeProjectilesAEnlever.add(projectile);
                }
            }

        }
        for (Projectiles projectile : listeProjectilesAEnlever) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Monde.this.remove(projectile);
                }
            });
        }
        listeProjectiles.removeAll(listeProjectilesAEnlever);
        listeProjectilesAEnlever.clear();

    }

    private void majTirer() {
        if (listeKeyCodes.contains(KeyEvent.VK_SPACE)) {
            if (!cadenceDeTir.isRunning()) {
                cadenceDeTir.start();
            }
        } else {
            cadenceDeTir.stop();
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
        laserTemp.setLocation(heros.getLocation());
        listeProjectiles.add(laserTemp);
        
                add(laserTemp, 0);
            

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
            listeProjectiles.add(balle);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                add(ballesTemp[0], 0);
                add(ballesTemp[1], 0);
                add(ballesTemp[2], 0);

            }
        });

    }

    private void dropBonus(Ennemis ennemi) {

        if (ennemi.getBonus() != null) {
            Bonus bonus = ennemi.getBonus();
            listeBonus.add(bonus);
            add(bonus, 0);
            bonus.setLocation(ennemi.getLocation());

        }
    }

    private void majBonus() {
        for (Bonus bonus : listeBonus) {
            if (bonus.getBounds().intersects(heros.getBounds())) {
                
                this.listeBonusAEnlever.add(bonus);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Monde.this.remove(bonus);
                    }
                });

                switch (bonus.getType()) {
                    case BALLES:
                        this.typeProjectile = typeProjectile.BALLE;
                        break;
                    case ZAPPER:
                        for (Ennemis ennemi : listeEnnemis) {
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    Monde.this.remove(ennemi);
                                }
                            });
                        }
                        listeEnnemis.clear();
                        listeEnnemisAEnlever.clear();

                        break;
                    default: //case BOOST:
                    //Mettre points de vie max

                }

            }
        }
        listeBonus.removeAll(listeBonusAEnlever);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < HAUTEUR * DIMENSION_GAZON; i++) {
            for (int j = 0; j < LARGEUR * DIMENSION_GAZON; j++) {
                if ((i + j) % 2 == 0) {
                    g.drawImage(IMAGE_GAZON1, i * DIMENSION_GAZON, j * DIMENSION_GAZON, this);
                } else {
                    g.drawImage(IMAGE_GAZON2, i * DIMENSION_GAZON, j * DIMENSION_GAZON, this);
                }

            }
        }
    }

}
