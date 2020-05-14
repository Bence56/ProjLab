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
        setSize(958, 758);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.setVisible(true);
        //this.setResizable(false);
        this.setLocation(0, 0);
        this.setSize(222*N+256, 222*M);
        this.setMinimumSize(this.getSize());

        this.palyaView = new PalyaView(kontroller, this);
        this.jatekosView = new JatekosView(kontroller, this);

        this.getContentPane().add(palyaView, BorderLayout.CENTER);
        this.getContentPane().add(jatekosView, BorderLayout.EAST);
    }
    public void ujra(Kontroller k,Jatekos aktiv){
        jatekosView.ujra(aktiv);
        //palyaView.ujra(k);
    }
}