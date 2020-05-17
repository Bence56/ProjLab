package com.company;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

class View extends JFrame {
    /**
     * Az aktuális Játékos Inventory-jének nézete.
     */
    JatekosView jatekosView;

    /**
     * A pálya nézete
     */
    PalyaView palyaView;


    // Konstruktor
    View(Kontroller kontroller) {
        //A Frame beállítása
        super("Jatek");
        //TODO
        // Ezeket ki kéne olvasni a JSON-ből (Még bele sincs írva)
        // A palyaView létrehozása már paraméterben kaphatja
        int N = 4;
        int M = 4;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.setVisible(true);
        //this.setResizable(false);
        this.setLocation(0, 0);
        this.setSize(222*N+256 + 30, 222*M + 40);
        this.setMinimumSize(this.getSize());

        this.palyaView = new PalyaView(kontroller, this);
        this.jatekosView = new JatekosView(kontroller, this);

        this.getContentPane().add(palyaView, BorderLayout.CENTER);
        this.getContentPane().add(jatekosView, BorderLayout.EAST);
    }

    /**
     * Textúrázva tölti ki a hátteret
     *
     * @param g
     */
    @Override
    public void paintComponents(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (g2 == null) {
            System.out.println("error");
            return;
        }
        try {
            BufferedImage image = ImageIO.read(new File("Resources/Assets/Tenger.png"));
            System.out.println(this.getWidth() + " " + this.getHeight());
            Rectangle2D rec = new java.awt.geom.Rectangle2D.Double(0, 0 , image.getWidth(), image.getHeight());
            TexturePaint tp = new TexturePaint(image, rec);
            g2.setPaint(tp);
            Rectangle2D r = new Rectangle(0, 0, this.getWidth(), this.getHeight());
            g2.fill(r);
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
    }
}