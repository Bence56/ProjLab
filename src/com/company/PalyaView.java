package com.company;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PalyaView extends JPanel {
    private volatile ArrayList<Mezo> palya;
    private View view;


    /**
     * Egy Listener ami tagváltozók változását figyeli más osztályokban
     * Most csak egyet, de meg lehet írni máshogy is
     */

    PropertyChangeListener listener = new PropertyChangeListener() {

        /**
         * Ha valaki megnyomott egy gombot utánna ez mindig meghívódik.
         * Ha megváltozott a pálya újra kell rajzolni
         * @param event
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {
           propertyChangeHandler(event);
        }
    };

    private void propertyChangeHandler(PropertyChangeEvent event){
        if (event.getPropertyName().equals("palya")) {
            System.out.println("Pálya változott");
            // A pálya frissítése
            palya = (ArrayList<Mezo>) event.getNewValue();
            //Újra rajzolás
            //TODO
            // végig kell mennia az öszes mezőn mielőtt a páylát kicsréli
            // Összehasonlítani és azt a részt újra rajzolni
            update();
        }
        else if (event.getPropertyName().equals("palya")) {
            //TODO
            // Csak azt a mezőt kell újra kirajzolni amit paraméternek kap
        }
        else if (event.getPropertyName().equals("vege")) {
            // TODO ...
        }
    }

    /**
     * Létrehozza a pálya nézetet
     * @param kontroller A kontroller amitől le kell kérni a pályát amit meg akarunk jeleníteni
     * @param view Az a Frame amin látni akarjuk ezt a nézetet
     */
    PalyaView(Kontroller kontroller, View view){
        this.view = view;
        this.palya = kontroller.getPalya();

        //A Kontrollerhez hozzá kell adni a Listenerünket
        kontroller.addPropertyChangeListener(listener);

        this.setPreferredSize(new Dimension(758, 758));

        this.setBackground(Color.LIGHT_GRAY);

    }

    /**
     *
     */
    private void update(){
        //Most csak random átszínezzük
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);

        setBackground(new Color(r,g,b));

        //Változás történt a nézeten, újra kell rajzolni
        revalidate();
        view.repaint();

        //TODO A pálya újra rajzolása
    }
}
