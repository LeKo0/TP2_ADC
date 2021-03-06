/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue;

import ca.qc.bdeb.prog203.controlleur.ControlleurADC;
import ca.qc.bdeb.prog203.modele.ModeleADC;
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
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author 1648266
 */
public class Monde extends JPanel {

    /**
     * Deux types de projectiles
     */
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
    private final ArrayList<Ennemis> listeEnnemis = new ArrayList<>();
    private final ArrayList<Ennemis> listeEnnemisAEnlever = new ArrayList<>();
    private final ControlleurADC controlleur;
    private final ModeleADC modele;
    private int sleepingTime = 15;
    private final int rotationTirActuel = (int) 200 / sleepingTime;
    private final int rotationSpawnActuel = (int) 3000 / sleepingTime;
    private boolean gameOn = true;

    private int rotation_tir = rotationTirActuel;
    private int rotation_spawn = rotationSpawnActuel;
    private int niveau = 1;

    private final Random random = new Random();

    private void tir() {
        switch (typeProjectile) {
            case LASER:
                tirerLaser();
                break;
            case BALLE:
                tirerBalle();
        }
    }

    private void spawn() {

        int cote = random.nextInt(4);
        Boolean canSpawn;
        Point position;
        Ennemis ennemi;
        for (int i = 0; i < Math.ceil(niveau / 2.0); i++) {

            position = new Point();
            ennemi = null;

            if (niveau <= 3) {
                ennemi = new TVert();
            } else if (niveau <= 6) {
                switch (random.nextInt(2)) {
                    case 0:
                        ennemi = new TBleu();
                        break;
                    case 1:
                        ennemi = new TVert();

                }
            } else {
                switch (random.nextInt(3)) {
                    case 0:
                        ennemi = new TBleu();
                        break;
                    case 1:
                        ennemi = new TMauve();
                        break;
                    case 2:
                        ennemi = new TVert();
                        break;
                }
            }
            switch (cote) {
                case 0: //gauche
                    position.setLocation(-Ennemis.LARGEUR, (getHeight() / 2) - (Ennemis.HAUTEUR / 2) + (random.nextInt(4 * DIMENSION_GAZON) - 2 * DIMENSION_GAZON));
                    break;
                case 1: //droite
                    position.setLocation(Ennemis.LARGEUR + getWidth(), (getHeight() / 2) - (Ennemis.HAUTEUR / 2) + (random.nextInt(4 * DIMENSION_GAZON) - 2 * DIMENSION_GAZON));
                    break;
                case 2: //haut
                    position.setLocation((getWidth() / 2) - (Ennemis.LARGEUR / 2) + (random.nextInt(4 * DIMENSION_GAZON) - 2 * DIMENSION_GAZON), -Ennemis.HAUTEUR);
                    break;
                case 3: //bas
                    position.setLocation((getWidth() / 2) - (Ennemis.LARGEUR / 2) + (random.nextInt(4 * DIMENSION_GAZON) - 2 * DIMENSION_GAZON), getHeight() + Ennemis.HAUTEUR);

            }

            do {
                canSpawn = true;
                for (Ennemis ennemi2 : listeEnnemis) {
                    if ((new Rectangle(position, ennemi.getSize()).intersects(ennemi2.getBounds()))) {
                        canSpawn = false;
                    }
                }

                if (canSpawn) {
                    listeEnnemis.add(ennemi);
                    ennemi.setLocation(position);
                    Monde.this.add(ennemi, 0);

                } else {
                    switch (cote) {
                        case 0: //gauche
                            position = new Point(position.x - 2 * ennemi.getWidth(), position.y);
                            break;
                        case 1: //droite
                            position = new Point(position.x + 2 * ennemi.getWidth(), position.y);
                            break;
                        case 2: //haut
                            position = new Point(position.x, position.y - 2 * ennemi.getHeight());
                            break;
                        case 3: //bas
                            position = new Point(position.x, position.y + 2 * ennemi.getHeight());

                    }
                }
            } while (!canSpawn);

        }
    }

    /**
     * Thread de jeu. Appelle la mise à jour du jeu et fait sleep le thread 15
     * ms
     */
    private final Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                while (gameOn) {
                    majJeu();
                    revalidate();
                    repaint();
                    try {
                        Thread.sleep(sleepingTime);
                    } catch (InterruptedException ex) {
                        invalidate();
                        repaint();
                    }
                }
                gameEnd();
            }
        }

    };

    /**
     * Constructeur Initialise le projectile à LASER (par défaut) Ajuste le
     * délais initial de tir à 0
     *
     * @param controlleur Controlleur du jeu
     * @param modele Modele du jeu
     */
    public Monde(ControlleurADC controlleur, ModeleADC modele) {
        this.controlleur = controlleur;
        this.modele = modele;
        setPreferredSize(new Dimension(HAUTEUR * DIMENSION_GAZON, LARGEUR * DIMENSION_GAZON));
        setLayout(null);

        typeProjectile = typeProjectile.LASER;

        initGraphiques();
        initPartie();

    }

    /**
     * Initialise les graphiques du jeu
     */
    private void initGraphiques() {
        heros = new Heros();
        ArrayList<Roche> listeRoches = new ArrayList<>();
        ArrayList<Buisson> listeBuissons = new ArrayList<>();
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
                    listeRoches.add(rocheTemp);
                    listeObstacles.add(rocheTemp);
                    add(rocheTemp, 0);
                }
            }
        }

        //Disposition des buissons
        for (int i = 0; i < 4; i++) {
            buissonTemp = new Buisson();
            listeBuissons.add(buissonTemp);
            listeObstacles.add(buissonTemp);
            add(buissonTemp, 0);
        }
        listeBuissons.get(0).setLocation(3 * DIMENSION_GAZON, 3 * DIMENSION_GAZON);
        listeBuissons.get(1).setLocation(12 * DIMENSION_GAZON, 3 * DIMENSION_GAZON);
        listeBuissons.get(2).setLocation(3 * DIMENSION_GAZON, 10 * DIMENSION_GAZON);
        listeBuissons.get(3).setLocation(12 * DIMENSION_GAZON, 10 * DIMENSION_GAZON);

    }

    /**
     * Initialise la partie
     */
    private void initPartie() {
        initEvenementsTouches();
        thread.start();

    }

    /**
     * Initialise les évènements en lien avec les touches du clavier Si on appui
     * sur une touche : on ajoute le keyCode dans listeKeyCodes Si on relache la
     * touche : on retire le keyCode de listeKeyCodes
     */
    private void initEvenementsTouches() {
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

    /**
     * Met à jour l'état du jeu
     */
    private void majJeu() {

        bougerHero();
        if (rotation_spawn == 0) {
            spawn();
            niveau++;
            rotation_spawn = rotationSpawnActuel;

        } else {
            rotation_spawn -= 1;
        }

        majEnnemis();
        majProjectiles();
        majBonus();
        majTirer();

    }

    /**
     * Bouge le héros en fonction des touches enfoncés tout en gerant les
     * collisions
     */
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

    /**
     * Bouge les héros en fonction de la position du héros tout en gerant les
     * collisions
     */
    private void majEnnemis() {
        for (Ennemis ennemi : listeEnnemis) {
            int vitesseX = ennemi.getVitesse();
            int vitesseY = ennemi.getVitesse();

            if (heros.getX() < ennemi.getX()) {
                vitesseX = -vitesseX;
            } else if (heros.getX() >= ennemi.getX() - vitesseX && heros.getX() <= ennemi.getX() + vitesseX) {
                vitesseX = 0;
            }
            if (heros.getY() < ennemi.getY()) {
                vitesseY = -vitesseY;
            } else if (heros.getY() >= ennemi.getY() - vitesseY && heros.getY() <= ennemi.getY() + vitesseY) {
                vitesseY = 0;
            }
            ennemi.setLocation(ennemi.getX() + vitesseX, ennemi.getY());

            for (Obstacles obstacle : listeObstacles) {
                if (ennemi.getBounds().intersects(obstacle.getBounds())) {
                    ennemi.setLocation(ennemi.getLastPosition().x, ennemi.getY());
                }
            }
            for (Ennemis autreEnnemi : listeEnnemis) {
                if (!ennemi.equals(autreEnnemi) && ennemi.getBounds().intersects(autreEnnemi.getBounds())) {
                    ennemi.setLocation(ennemi.getLastPosition().x, ennemi.getY());
                }
            }

            ennemi.setLocation(ennemi.getX(), ennemi.getY() + vitesseY);
            for (Obstacles obstacle : listeObstacles) {
                if (ennemi.getBounds().intersects(obstacle.getBounds())) {
                    ennemi.setLocation(ennemi.getX(), ennemi.getLastPosition().y);
                }
            }
            for (Ennemis autreEnnemi : listeEnnemis) {
                if (!ennemi.equals(autreEnnemi) && ennemi.getBounds().intersects(autreEnnemi.getBounds())) {
                    ennemi.setLocation(ennemi.getX(), ennemi.getLastPosition().y);
                }
            }
            ennemi.setLastPosition(ennemi.getLocation());

            boolean dejaTouche = false;
            for (Projectiles projectile : listeProjectiles) {
                if (ennemi.getBounds().intersects(projectile.getBounds()) && !dejaTouche) {
                    listeProjectilesAEnlever.add(projectile);
                    ennemi.setPointsDeVie(ennemi.getPointsDeVie() - 1);
                    dejaTouche = true;

                }
            }

            if (ennemi.getBounds().intersects(heros.getBounds()) && !dejaTouche) {
                controlleur.heroToucher();
                if (controlleur.finDePartie(modele.getPointsVie())) {
                    gameOn = false;
                }

                ennemi.setPointsDeVie(0);
                dejaTouche = true;
            }

            if (dejaTouche && ennemi.getPointsDeVie() == 0) {
                listeEnnemisAEnlever.add(ennemi);
            }

        }
        for (Ennemis ennemi2 : listeEnnemisAEnlever) {
            dropBonus(ennemi2);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Monde.this.remove(ennemi2);
                    controlleur.augmenterPoints(ennemi2.getPoints());
                }
            });
        }
        listeEnnemis.removeAll(listeEnnemisAEnlever);
        listeEnnemisAEnlever.clear();

    }

    /**
     * Bouge les projectiles en fonction de leur vitesse de départ tout en
     * gerant les collisions
     */
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
                @Override
                public void run() {
                    Monde.this.remove(projectile);
                }
            });
        }
        listeProjectiles.removeAll(listeProjectilesAEnlever);
        listeProjectilesAEnlever.clear();

    }

    /**
     * Réinitialise l'état du jeu afin de pouvoir recommencer la partie
     */
    public void reset() {
        niveau = 0;
        listeKeyCodes.clear();
        for (Bonus temp : listeBonus) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Monde.this.remove(temp);
                }
            });
        }
        listeBonus.clear();
        listeBonusAEnlever.clear();
        for (Projectiles temp : listeProjectiles) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Monde.this.remove(temp);
                }
            });
        }
        listeProjectiles.clear();
        listeProjectilesAEnlever.clear();
        for (Ennemis temp : listeEnnemis) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Monde.this.remove(temp);
                }
            });
        }
        listeEnnemis.clear();
        listeEnnemisAEnlever.clear();
        typeProjectile = typeProjectile.LASER;
        this.invalidate();
        this.repaint();
        heros.setLocation(8 * DIMENSION_GAZON - 11, 7 * DIMENSION_GAZON - 25);
        gameOn = true;
        controlleur.recommencer();
    }

    /**
     * Demande au joueur si il veux recommencer
     */
    public void gameEnd() {

        String[] choices = {"Recommencer", "Quitter le jeu"};
        String input = (String) JOptionPane.showInputDialog(this, "Votre partie est terminé. Veuillez choisir ce que vous voulez faire. \n Si vous appuyer sur le X ou sur cancel cela sera considéré comme étant vouloir partir", "Fin de Partie", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        if (input == null || input.equals(choices[1])) {
            System.exit(0);
        } else {
            reset();
        }

    }

    /**
     * Déterminer si il faut tirer ou non
     */
    private void majTirer() {
        if (rotation_tir == 0) {
            if (listeKeyCodes.contains(KeyEvent.VK_SPACE)) {
                tir();
            }
            rotation_tir = rotationTirActuel;
        }
        rotation_tir -= 1;
    }

    /**
     * Initialise un nouveau laser partant du héros et allant dans sa direction
     */
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

    /**
     * Initialise trois nouvelles balles partant du héros et allant dans sa
     * direction
     *
     */
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

        add(ballesTemp[0], 0);
        add(ballesTemp[1], 0);
        add(ballesTemp[2], 0);

    }

    /**
     * Fait tomber le bonus de l'ennemi si il en possède un
     *
     * @param ennemi
     */
    private void dropBonus(Ennemis ennemi) {

        if (ennemi.getBonus() != null) {
            Bonus bonus = ennemi.getBonus();

            listeBonus.add(bonus);

            add(bonus, 0);

            bonus.setLocation(ennemi.getLocation());

        }
    }

    /**
     * Vérifie si le joueur récuperre un bonus et si oui applique l'effet
     */
    private void majBonus() {
        for (Bonus bonus : listeBonus) {
            if (bonus.getBounds().intersects(heros.getBounds())) {

                controlleur.augmenterPoints(Bonus.POINTS);
                this.listeBonusAEnlever.add(bonus);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
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
                                @Override
                                public void run() {
                                    Monde.this.remove(ennemi);
                                }
                            });
                        }
                        listeEnnemis.clear();
                        listeEnnemisAEnlever.clear();

                        break;
                    default: //case BOOST:
                        controlleur.resetVie();

                }

            }
        }
        listeBonus.removeAll(listeBonusAEnlever);
    }

    /**
     * Dessine le gazon
     */
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
