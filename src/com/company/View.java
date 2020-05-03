package com.company;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.*;

class View extends JFrame {
    /**
     * Az aktuális Játékos Inventory-jének nézete.
     */
    JPanel jatekosView;

    /**
     * A pálya nézete
     */
    JPanel palyaView;


    // Konstruktor
    View(Kontroller kontroller) {
        //A Frame beállítása
        super("Jatek");
        setSize(958, 758);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setSize(958, 758);

        this.palyaView = new PalyaView(kontroller, this);
        this.jatekosView = new JatekosView(kontroller, this);

        this.getContentPane().add(palyaView, BorderLayout.CENTER);
        this.getContentPane().add(jatekosView, BorderLayout.EAST);
    }
}