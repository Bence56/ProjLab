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

    JLabel label;

    /**
     * Egy Listener ami tagváltozók változását figyeli más osztályokban
     * Most csak egyet, de meg lehet írni máshogy is
     */
    /*
    PropertyChangeListener listener = new PropertyChangeListener() {

        @Override
        public void propertyChange(PropertyChangeEvent event) {

            String property = event.getPropertyName();

            if (property.equals("aktivJatekos")) {
                aktivJatekos = (Jatekos) event.getNewValue();
                //TODO Az inventory újrarajzolása
                updateView();
            }
            else if(property.equals("palya")){
                // A pálya frissítése
                palya = (ArrayList<Mezo>) event.getNewValue();

                //TODO A pálya újra rajzolása

            }
        }
    };
     */


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