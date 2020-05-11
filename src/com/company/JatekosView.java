package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BoxLayout;
import javax.swing.Box;
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
import static javax.swing.BoxLayout.Y_AXIS;

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

        this.setPreferredSize(new Dimension(256, 758));
        this.setBackground(Color.DARK_GRAY);

        JLabel j2=new JLabel("Ki vagyok:");
        this.add(j2);
        //adatok panel létrehozása: felirat, kis kutató vagy eszkimó ikon, testhő és fulladási állapot.
        JPanel adatok=new JPanel();
        adatok.setPreferredSize(new Dimension(200,250));
        BoxLayout boxlayout= new BoxLayout(adatok, BoxLayout.Y_AXIS); //felülről lefelé adja hozzá az elemeket.
        adatok.setLayout(boxlayout);

        //TODO: eldönteni vhogy, hogy kutató v eszkimó és aszerint 1 eszkimot  v a kutatót kirajzolni.
        //if (aktivJatekos == eszkimo)
        ImageIcon eszkim=new ImageIcon("Resources/Assets/Kutato-teljes.png");
        JLabel eszkimo = new JLabel();
        eszkimo.setIcon(eszkim);
        adatok.add(eszkimo);

        JTextField aktualisertek=new JTextField("Testhőm: " + aktivJatekos.getTestho());
        aktualisertek.setEditable(false);
        adatok.add(aktualisertek);
        JTextField fulladasiAllapot=new JTextField("Állapotom: " + aktivJatekos.getAllapot());
        fulladasiAllapot.setEditable(false);
        adatok.add(fulladasiAllapot);
        JTextField buvarruha=new JTextField("Búvárruha: " + aktivJatekos.isVedett());
        JTextField munka=new JTextField("Munkák: " + aktivJatekos.getMunka());
        adatok.add(buvarruha);
        adatok.add(munka);
        this.add(adatok);

        //TODO:  végigmenni a játékos tömbjén hogy van-e az adott tárgyból. Képek, plusz sötét képek kellenek, ha egy tárgyból nincs..

        // Játékos cuccainak a panelja
        JLabel cimke=new JLabel("Cuccaim:");
        this.add(cimke);
        //A Játékos tárgyak tömbjének tartalma. Összes tárgyat tartalmazza, de majd amik nincsenek a játékosnál, az szürke lesz. Ha ráklikkelünk
        //az esemény végrehajtásra kerül, de ha nincs lapát a visitor miatt nem fog lapátolni csak kézzel.

        JPanel targyak=new JPanel();
        targyak.setPreferredSize(new Dimension(256,256));
        GridLayout gl1=new GridLayout(2,2);
        targyak.setLayout(gl1);
        ImageIcon lapatim=new ImageIcon("Resources/Assets/Lapat-01.png");
        JButton lapat=new JButton();
        lapat.setPreferredSize(new Dimension(100,100));
        lapat.setIcon((lapatim));
        targyak.add(lapat);
        lapat.setActionCommand("lapatol");
        lapat.addActionListener(kontroller);


        ImageIcon satorim=new ImageIcon("Resources/Assets/Sator-01.png");
        JButton sator=new JButton();
        sator.setPreferredSize(new Dimension(100,100));
        sator.setIcon(satorim);
        targyak.add(sator);
        sator.setActionCommand("satrat epit");
        sator.addActionListener(kontroller);

        //Kotel kihúz megvalósítása: kötél label és a 8 irány gomb
        JPanel targyak2=new JPanel();
        GridLayout gl5=new GridLayout(3,3);
        targyak2.setLayout(gl5);
        JButton balfel=new JButton();
        ImageIcon x=new ImageIcon("Resources/Assets/x.png");
        //balfel.setPreferredSize(new Dimension(60,60));
        balfel.setIcon(x);
        balfel.setActionCommand("balfentről");
        balfel.addActionListener(kontroller);
        targyak2.add(balfel);
        JButton fel=new JButton();
      //  fel.setPreferredSize(new Dimension(60,60));
        fel.setIcon(x);
        fel.setActionCommand("fentről");
        fel.addActionListener(kontroller);
        targyak2.add(fel);
        JButton jobbfel=new JButton();
      //  jobbfel.setPreferredSize(new Dimension(60,60));
        jobbfel.setIcon(x);
        jobbfel.setActionCommand("jobbfentről");
        jobbfel.addActionListener(kontroller);
        targyak2.add(jobbfel);
        JButton bal=new JButton();
       // bal.setPreferredSize(new Dimension(60,60));
        bal.setIcon(x);
        targyak2.add(bal);
        JLabel kotel=new JLabel();
        //kotel.setPreferredSize(new Dimension(60,60));
        ImageIcon kotelim=new ImageIcon("Resources/Assets/Kotel-01.png");
        kotel.setIcon(kotelim);
        targyak2.add(kotel);
        JButton jobb=new JButton();
        //jobb.setPreferredSize(new Dimension(60,60));
        jobb.setIcon(x);
        targyak2.add(jobb);
        JButton balle=new JButton();
        //balle.setPreferredSize(new Dimension(60,60));
        balle.setIcon(x);
        balle.setActionCommand("ballentről");
        balle.addActionListener(kontroller);
        targyak2.add(balle);
        JButton le=new JButton();
       // le.setPreferredSize(new Dimension(60,60));
        le.setIcon(x);
        le.setActionCommand("lentről");
        le.addActionListener(kontroller);
        targyak2.add(le);
        JButton jobble=new JButton();
        //jobble.setPreferredSize(new Dimension(60,60));
        jobble.setIcon(x);
        jobble.setActionCommand("jobblentről");
        jobble.addActionListener(kontroller);
        targyak2.add(jobble);
        targyak.add(targyak2);

        //Az alkatrész lerak úgy van megírva, hogy bármennyi alkatrészünk van, 1 lerak() hívással a 0. indexűt rakjuk le.  Itt 1 alkatrészt
        ImageIcon alkim=new ImageIcon("Resources/Assets/Pisztoly-teljes.png");
        JButton alkatresz=new JButton();
        alkatresz.setPreferredSize(new Dimension(100,100));
        alkatresz.setIcon(alkim);
        alkatresz.setActionCommand("lerak");
        alkatresz.addActionListener(kontroller);
        targyak.add(alkatresz);

        this.add(targyak, BorderLayout.LINE_START);

        //Egyéb cselekvési lehetőségek, állandóak.
        JLabel egyeblehetoseg=new JLabel("Egyéb lehetőségek: ");
        this.add(egyeblehetoseg);
        JPanel egyeb=new JPanel();
        GridLayout gl3=new GridLayout(2,2);
        egyeb.setLayout(gl3);
        JButton kapar=new JButton("Kapar");
        kapar.setActionCommand("kapar");
        kapar.addActionListener(kontroller);
        egyeb.add(kapar);

        JButton osszeszerel=new JButton("Összeszerel");
        osszeszerel.setActionCommand("összeszerel");
        osszeszerel.addActionListener(kontroller);
        egyeb.add(osszeszerel);

        JButton vizsgal=new JButton("Vizsgál");
        vizsgal.setActionCommand("vizsgal");
        vizsgal.addActionListener(kontroller);
        egyeb.add(vizsgal);

        JButton iglutepit=new JButton("Iglut epít");
        iglutepit.setActionCommand("iglut épłt");
        iglutepit.addActionListener(kontroller);
        egyeb.add(iglutepit);

        this.add(egyeb);
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