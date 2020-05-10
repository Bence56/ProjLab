package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class JatekosView extends JPanel {
    private volatile Jatekos aktivJatekos;
    private View view;

    /**
     * Egy Listener ami tagváltozók változását figyeli más osztályokban
     * Most csak egyet, de meg lehet írni máshogy is
     */
    PropertyChangeListener listener = new PropertyChangeListener() {

        /**
         * Ha megváltozik az aktív játékos valamiért mert interakcióba lépett valamivel
         * Újra kell rajzolni az Inventoryt
         * @param event
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            propertyChangeHandler(event);
        }
    };

    /**
     * @param event
     */
    private void propertyChangeHandler(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("aktivJatekos")) {
            System.out.println("Aktív Játékos változott");
            // Az aktív játékos frissítése
            aktivJatekos = (Jatekos) event.getNewValue();
            //TODO Az inventory újrarajzolása
            // végig kell mennia az öszes mezőn mielőttaz aktív játékost kicsréli
            // Összehasonlítani és azt a részt újra rajzolni
            update();
        }
    }

    /**
     * Létrehozza az aktív játékos inventory-jének nézetét
     *
     * @param kontroller A kontroller amitől le kell kérni az aktív játékost
     * @param view       Az a Frame amin látni akarjuk ezt a nézetet
     */
    JatekosView(Kontroller kontroller, View view) {

        this.view = view;

        // A jelenleg aktív játékos inventory-jét is meg kell jeleníteni
        this.aktivJatekos = kontroller.getAktivJatekos();

        //A Kontrollerhez hozzá kell adni a Listenerünket
        kontroller.addPropertyChangeListener(listener);

        this.setPreferredSize(new Dimension(200, 758));
        this.setBackground(Color.DARK_GRAY);
        //this.setLayout(new GridLayout(3,2));

        JLabel cimke=new JLabel("Cuccaim:");
        this.add(cimke, BorderLayout.PAGE_START);
        //A Játékos tárgyak tömbjének tartalma. Összes tárgyat tartalmazza, de majd amik nincsenek a játékosnál, az szürke lesz. Ha ráklikkelünk
        //az esemény végrehajtásra kerül, de ha nincs lapát a visitor miatt nem fog lapátolni csak kézzel.

        //TODO:  végigmenni a játékos tömbjén hogy van-e az adott tárgyból. Középső elrendezésű képek kellenek.
        JPanel targyak=new JPanel();
        targyak.setBackground(Color.GRAY);
        GridLayout gl1=new GridLayout(2,2);
        targyak.setLayout(gl1);
        ImageIcon lapatim=new ImageIcon("Resources/Assets/Lapat-01.png");
        JButton lapat=new JButton();
        lapat.setIcon((lapatim));
        targyak.add(lapat);
        lapat.setActionCommand("lapatol");
        lapat.addActionListener(kontroller);


        ImageIcon kotelim=new ImageIcon("Resources/Assets/Kotel-01.png");
        JButton kotel=new JButton();
        kotel.setIcon(kotelim);
        targyak.add(kotel);
        //TODO itt még kell vmi kell, hogy mind a 4 irányból legyen vmi gomb a kihúzásra
        kotel.setActionCommand("kihuzjobbra");
        kotel.addActionListener(kontroller);

        ImageIcon satorim=new ImageIcon("Resources/Assets/Sator-01.png");
        JButton sator=new JButton();
        sator.setIcon(satorim);
        targyak.add(sator);
        sator.setActionCommand("satrat epit");
        sator.addActionListener(kontroller);

        //Az alkatrész lerak úgy van megírva, hogy bármennyi alkatrészünk van, 1 lerak() hívással a 0. indexűt rakjuk le.  Itt 1 alkatrészt
        ImageIcon alk1im=new ImageIcon("Resources/Assets/3-Pisztoly-01.png");
        JButton alk1=new JButton();
        alk1.setIcon(alk1im);
        alk1.setActionCommand("lerak");
        alk1.addActionListener(kontroller);
        targyak.add(alk1);

        this.add(targyak, BorderLayout.LINE_START);

        //Egyéb cselekvési lehetőségek, állandóak.
        JPanel egyeb=new JPanel();
        GridLayout gl3=new GridLayout(1,2);
        egyeb.setLayout(gl3);
        JButton kapar=new JButton("Kapar");
        kapar.setActionCommand("kapar");
        kapar.addActionListener(kontroller);
        egyeb.add(kapar);

        JButton osszeszerel=new JButton("Összeszerel");
        osszeszerel.setActionCommand("összeszerel");
        osszeszerel.addActionListener(kontroller);
        egyeb.add(osszeszerel);

        this.add(egyeb);

        JLabel j2=new JLabel("Ki vagyok:");
        this.add(j2,BorderLayout.AFTER_LAST_LINE);

        JPanel jatekos=new JPanel();
        GridLayout gl2=new GridLayout(1,2);
        jatekos.setLayout(gl2);
        //TODO: eldönteni vhogy, hogy kutató v eszkimó és aszerint 1 eszkimot és az iglutépít ikonját betenni, v a kutatót és a teherbírást vizsgált.

        //if (aktivJatekos == eszkimo)
            ImageIcon eszkim = new ImageIcon("Resources/Assets/Eszkimo_Mid_Left-01.png");
            JLabel eszkimo = new JLabel();
            jatekos.add(eszkimo);
            eszkimo.setIcon(eszkim);
            ImageIcon iglu = new ImageIcon("Resources/Assets/Iglu-01.png");
            JButton iglutepit = new JButton();
            iglutepit.setIcon(iglu);
            iglutepit.setActionCommand("iglut epit");
            iglutepit.addActionListener(kontroller);
            jatekos.add(iglutepit);
            this.add(jatekos);


        JTextField aktualisertek=new JTextField("Testhőm: " + aktivJatekos.getTestho());
        aktualisertek.setEditable(false);
        this.add(aktualisertek, BorderLayout.CENTER);
        JTextField fulladasiAllapot=new JTextField("Állapotom: " + aktivJatekos.getAllapot());
        fulladasiAllapot.setEditable(false);
        this.add(fulladasiAllapot, BorderLayout.LINE_START);


    }

    private void update() {
        //Itt most csak ranodm átszínezzük
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);

        setBackground(new Color(r,g,b));

        //Változás történt a nézeten, újra kell rajzolni
        revalidate();
        view.repaint();

    }
}