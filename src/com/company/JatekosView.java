package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
     *
     *
     * @param event
     */
    private void propertyChangeHandler(PropertyChangeEvent event){
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
     * @param kontroller A kontroller amitől le kell kérni az aktív játékost
     * @param view Az a Frame amin látni akarjuk ezt a nézetet
     */
    JatekosView(Kontroller kontroller, View view){

        this.view = view;

        // A jelenleg aktív játékos inventory-jét is meg kell jeleníteni
        this.aktivJatekos = kontroller.getAktivJatekos();

        //A Kontrollerhez hozzá kell adni a Listenerünket
        kontroller.addPropertyChangeListener(listener);

        this.setPreferredSize(new Dimension(200, 758));
        this.setBackground(Color.DARK_GRAY);
        this.addMouseListener(kontroller.getMouseListener());
    }

        private void update(){
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