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
     * @param event
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
    public void ujra(){
        adatok.update(aktivJatekos);
        targyak.update(aktivJatekos);
        funkciok.update(aktivJatekos);
        revalidate();
        view.repaint();
    }
    void loadImages() {
        try {
            images.put("Sator", read(new File("Resources/Assets/Sator_I-01.png")));
            images.put("Sator_NULL", read(new File("Resources/Assets/Sator_I_NULL-01.png")));
            images.put("0-Pisztoly", read(new File("Resources/Assets/1-Pisztoly_I-01.png")));
            images.put("1-Pisztoly", read(new File("Resources/Assets/2-Pisztoly_I-01.png")));
            images.put("2-Pisztoly", read(new File("Resources/Assets/3-Pisztoly_I-01.png")));
            images.put("Pisztoly_NULL", read(new File("Resources/Assets/Pisztoly_I_NULL-01.png")));
            images.put("Buvarruha", read(new File("Resources/Assets/Buvarruha_I-01.png")));
            images.put("Buvarruha_NULL", read(new File("Resources/Assets/Buvarruha_I_NULL-01.png")));
            images.put("Kotel", read(new File("Resources/Assets/Kotel_I-01.png")));
            images.put("Kotel_NULL", read(new File("Resources/Assets/Kotel_I_NULL-01.png")));
            images.put("Lapat", read(new File("Resources/Assets/Lapat_I-01.png")));
            images.put("Lapat_NULL", read(new File("Resources/Assets/Lapat_I_NULL-01.png")));
            images.put("Etel", read(new File("Resources/Assets/Konzerv_I-01.png")));
            images.put("Etel_NULL", read(new File("Resources/Assets/Konzerv_I_NULL-01.png")));
            images.put("E", read(new File("Resources/Assets/Eszkimo_I-01.png")));
            images.put("K", read(new File("Resources/Assets/Kutato_I.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}