package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

public class PalyaView extends JPanel {
    private View view;
    // Ez lehet nem kell így
    private volatile ArrayList<Mezo> palya;
    // A megjelenítendő mezők paneljei
    ArrayList<ArrayList<JPanel>> panels = new ArrayList<>();
    //A layouthoz kell
    GridBagConstraints gbc = new GridBagConstraints();


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
        else if (event.getPropertyName().equals("mezo")) {
            //TODO
            // Csak azt a mezőt kell újra kirajzolni amit paraméternek kap
            update();
        }
        else if (event.getPropertyName().equals("vege")) {
            // TODO ...
            update();
        }
    }

    /**
     * Létrehozza a pálya nézetet
     * Léterhoz N darab M hosszú MezoPanel ArrayList-et amin majd megjelennek a mezők
     * @param kontroller A kontroller amitől le kell kérni a pályát amit meg akarunk jeleníteni
     * @param view Az a Frame amin látni akarjuk ezt a nézetet
     */
    PalyaView(Kontroller kontroller, View view){
        this.view = view;
        this.palya = kontroller.getPalya();
        //A Kontrollerhez hozzá kell adni a Listenerünket
        kontroller.addPropertyChangeListener(listener);

        //TODO Ezeket nem fiexen kéne ide írni
        int N = 4;
        int M = 4;


        this.setSize(N*210, M*222);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.setLayout(new GridBagLayout());

        //Páratlan sorok betöltése
        for(int i = 0; i< M; i++) {
            ArrayList<JPanel> row = new ArrayList<>();
            int top, left;
            //Hogy az első sort ne tolja vissza
            if(i == 0) top = 0; else top = -28;
            //Minden sorban 4 mező van
            for (int j = 0; j < N; j++) {
                //Hogy az első oszlopot ne tolja vissza
                if(j == 0) left = 0; else left = -28;

                JPanel p = new MezoPanel();

                gbc.insets = new Insets(top,left ,72,72 );
                gbc.gridx = j;
                gbc.gridy = i;

                p.setMinimumSize(new Dimension(128,128));
                p.setMaximumSize(new Dimension(128,128));
                p.setPreferredSize(new Dimension((int)(128),(int)(128)));

                row.add(p);
                add(p, gbc);
            }
            panels.add(row);
        }

        //Páros sorok betöltése
        for(int i = 0; i<M; i++) {
            ArrayList<JPanel> row = new ArrayList<>();
            int top, left;
            //Hogy az második sort ne tolja vissza
            if(i == 0) top = 100; else top = 72;
            //Minden sorban 4 mező van
            for (int j = 0; j < N; j++) {
                //Hogy az második oszlopot ne tolja vissza
                if(j == 0)left = 100; else left = 72;

                JPanel p = new MezoPanel();

                gbc.insets = new Insets(top,left,0 ,0 );
                gbc.gridx = j;
                gbc.gridy = i;

                p.setMinimumSize(new Dimension(128,128));
                p.setMaximumSize(new Dimension(128,128));
                p.setPreferredSize(new Dimension(128,128));

                row.add(p);
                add(p, gbc);
            }
            panels.add(2*i+1,row);
        }
    }

    /**
     *
     */
    private void update(){
        //Most csak random átszínezzük
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);

        //setBackground(new Color(r,g,b));

        //Változás történt a nézeten, újra kell rajzolni
        revalidate();
        view.repaint();

        //TODO A pálya újra rajzolása
    }

    /**
     * Textúrázva tölti ki a hátteret
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (g2==null)
        {
            System.out.println("error");
            return;
        }
        try
        {
            BufferedImage image = ImageIO.read(new File("Resources/Assets/Tenger.png"));
            Rectangle2D rec = new   java.awt.geom.Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
            TexturePaint tp = new TexturePaint(image, rec);
            g2.setPaint(tp);
            Rectangle2D  r = this.getBounds();
            g2.fill(r);
        }
        catch (java.io.IOException ex) {}
    }
}
