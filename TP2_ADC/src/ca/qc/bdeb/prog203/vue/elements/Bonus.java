/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.vue.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author 1666876
 */
public class Bonus extends JComponent {

    private Random random;
    public static int HAUTEUR = 32, LARGEUR = 32;
    private Image[] images = {
        Toolkit.getDefaultToolkit().getImage("images/boni4.gif"),
        Toolkit.getDefaultToolkit().getImage("images/boni5.gif"),
        Toolkit.getDefaultToolkit().getImage("images/boni6.gif")};

    public enum Type {
        BALLES, ZAPPER, BOOST
    }

    private Type type;

    public Bonus() {
        setSize(HAUTEUR, LARGEUR);
        initType();
    }

    private void initType() {
        random = new Random();

        switch (random.nextInt(3)) {
            case 1:
                this.type = Type.BALLES;
                break;
            case 2:
                this.type = Type.ZAPPER;
                break;
            default: //case 3:
                this.type = Type.BOOST;
        }

    }

    public Type getType() {
        return type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (type) {
            case BALLES:
                g.drawImage(images[0], 0, 0, this);
                break;
            case ZAPPER:
                g.drawImage(images[1], 0, 0, this);
                break;
            default: //case BOOST:
                g.drawImage(images[2], 0, 0, this);

        }

    }
}
