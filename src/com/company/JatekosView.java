package com.company;

import javax.swing.*;
import java.awt.*;
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
            if (event.getPropertyName().equals("aktivJatekos")) {
                System.out.println("Aktív Játékos változott");
                // Az aktív játékos frissítése
                aktivJatekos = (Jatekos) event.getNewValue();
                //TODO Az inventory újrarajzolása
                update();
            }
        }
    };

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
