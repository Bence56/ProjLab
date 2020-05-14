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
import java.util.HashMap;
import java.util.Map;

import static javax.imageio.ImageIO.read;
import static javax.swing.BoxLayout.Y_AXIS;

public class JatekosView extends JPanel {
    private volatile Jatekos aktivJatekos;
    private View view;
    private AdatokPanel adatok;
    private TargyakPanel targyak;
    private FunkciokPanel funkciok;
    /**
     * Ebben lesznek tárolva a rajzoláshoz szükséges képek
     */
    Map<String, BufferedImage> images = new HashMap<>();
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
     * Ha az aktív játékosnak változott bármely tulajdonsága, akkor frissíti az adatokat.
     *
     * @param event Az esemény, amely értesít a változásról.
     */
    private void propertyChangeHandler(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("aktivJatekos")) {
            System.out.println("Aktív Játékos updatelve");
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
        adatok=new AdatokPanel();
        // A jelenleg aktív játékos inventory-jét is meg kell jeleníteni
        this.aktivJatekos = kontroller.getAktivJatekos();

        //A Kontrollerhez hozzá kell adni a Listenerünket
        kontroller.addPropertyChangeListener(listener);

        this.setPreferredSize(new Dimension(256, 758));
        this.setBackground(Color.DARK_GRAY);
        //loadImages();

        this.add(adatok);
        targyak=new TargyakPanel(kontroller);
        JLabel cimke=new JLabel("Cuccaim:");
        this.add(cimke);
        this.add(targyak);
        //TODO:  végigmenni a játékos tömbjén hogy van-e az adott tárgyból. Képek, plusz sötét képek kellenek, ha egy tárgyból nincs..



        JLabel egyeblehetoseg=new JLabel("Egyéb lehetőségek: ");
        this.add(egyeblehetoseg);
        funkciok=new FunkciokPanel(kontroller);
        this.add(funkciok);
        adatok.update(aktivJatekos);
        targyak.update(aktivJatekos);
        funkciok.update(aktivJatekos);
    }

    /**
     * Változás esetén érvénytelenítjük és újrarajzoljuk az adatokat.
     */

    private void update() {
        //Itt most csak ranodm átszínezzük
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);

        setBackground(new Color(r,g,b));

        //Változás történt a nézeten, újra kell rajzolni
        adatok.update(aktivJatekos);
        targyak.update(aktivJatekos);
        funkciok.update(aktivJatekos);
        revalidate();
        view.repaint();

    }
}