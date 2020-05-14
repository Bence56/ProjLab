package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javax.imageio.ImageIO.read;

public class PalyaView extends JPanel {
    /**
     * A megjelenítendő mezők paneljei
     */
    ArrayList<ArrayList<MezoPanel>> panels = new ArrayList<>();
    /**
     * A layouthoz kell
     */
    GridBagConstraints gbc = new GridBagConstraints();
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
         * Ha valaki megnyomott egy gombot utánna ez mindig meghívódik.
         * Ha megváltozott a pálya újra kell rajzolni
         * @param event
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            propertyChangeHandler(event);
        }
    };
    private View view;

    /**
     * Létrehozza a pálya nézetet
     * Léterhoz N darab M hosszú MezoPanel ArrayList-et amin majd megjelennek a mezők
     *
     * @param kontroller A kontroller amitől le kell kérni a pályát amit meg akarunk jeleníteni
     * @param view       Az a Frame amin látni akarjuk ezt a nézetet
     */
    PalyaView(Kontroller kontroller, View view) {
        this.view = view;

        //A Kontrollerhez hozzá kell adni a Listenerünket
        kontroller.addPropertyChangeListener(listener);

        //Betölti a szükséges képeket
        loadImages();

        //TODO Ezeket nem fiexen kéne ide írni
        int N = 4;
        int M = 2;


        this.setSize(N * 210, M * 222);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.setLayout(new GridBagLayout());

        //Páratlan sorok betöltése
        for (int i = 0; i < M; i++) {
            ArrayList<MezoPanel> row = new ArrayList<>();
            int top, left;
            //Hogy az első sort ne tolja vissza
            if (i == 0) top = 0;
            else top = -28;
            //Minden sorban 4 mező van
            for (int j = 0; j < N; j++) {
                //Hogy az első oszlopot ne tolja vissza
                if (j == 0) left = 0;
                else left = -28;

                MezoPanel p = new MezoPanel();

                gbc.insets = new Insets(top, left, 72, 72);
                gbc.gridx = j;
                gbc.gridy = i;

                p.setMinimumSize(new Dimension(128, 128));
                p.setMaximumSize(new Dimension(128, 128));
                p.setPreferredSize(new Dimension(128, 128));

                row.add(p);
                add(p, gbc);
            }
            panels.add(row);
        }

        //Páros sorok betöltése
        for (int i = 0; i < M; i++) {
            ArrayList<MezoPanel> row = new ArrayList<>();
            int top, left;
            //Hogy az második sort ne tolja vissza
            if (i == 0) top = 100;
            else top = 72;
            //Minden sorban 4 mező van
            for (int j = 0; j < N; j++) {
                //Hogy az második oszlopot ne tolja vissza
                if (j == 0) left = 100;
                else left = 72;

                MezoPanel p = new MezoPanel();

                gbc.insets = new Insets(top, left, 0, 0);
                gbc.gridx = j;
                gbc.gridy = i;

                p.setMinimumSize(new Dimension(128, 128));
                p.setMaximumSize(new Dimension(128, 128));
                p.setPreferredSize(new Dimension(128, 128));

                row.add(p);
                add(p, gbc);
            }
            panels.add(2 * i + 1, row);
        }
    }

    /**
     *Tulajdonság változását kezeli.
     *
     * @param event Az esemány, amely a változást jelzi.
     */
    private void propertyChangeHandler(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("palya")) {
            System.out.println("Pálya változott");

            // A pálya frissítése
            ArrayList<Mezo> palya = (ArrayList<Mezo>) event.getNewValue();

            //Újra rajzolás
            update(palya);
        }

        else if (event.getPropertyName().equals("mezo")) {
            Mezo mezo = (Mezo) event.getNewValue();
            update(mezo);
        }

        else if(event.getPropertyName().equals("aktiv mezo")){
            Mezo mezo = (Mezo) event.getNewValue();
            update(mezo, "aktiv");
        }

        else if (event.getPropertyName().equals("vege")) {

            //TODO...
            //update();
        }
    }

    /**
     * Az egész pályát újra rajzolja (Minden menzőre meghívja az update()-t.)
     */
    private void update(ArrayList<Mezo> palya) {
        for (Mezo mezo : palya) {
            update(mezo);
        }
    }

    /**
     * Egy mezőt újra rajzol. A mezőhöz tartozó mezoPanel-t megkeresi és beállítja a rajta látható rétegeket,
     * majd ezt újra rajzolja.
     *
     * @param mezo A paraméterként kapott mező amit újra fog rajzolni.
     */
    public void update(Mezo mezo, String... aktiv) {
        int sor = mezo.getSor();
        int oszlop = mezo.getOszlop();
        MezoPanel mezoView = panels.get(sor).get(oszlop);
        ArrayList<BufferedImage> retegek = new ArrayList<>();
        //A legalsó réteg Jégtábla vagy lyuk
        if (mezo.getID().contains("J") && mezo.getTeherbiras() >= mezo.getAlloJatekos().size()) {
            retegek.add(images.get("Jegtabla"));
        } else if (mezo.getID().contains("Y") || mezo.getTeherbiras() < mezo.getAlloJatekos().size()) {
            retegek.add(images.get("Lyuk"));
        }

        //A rakéta alkatrészek
        if (mezo.getFagyottAlkatresz() != null) {
            retegek.add(images.get(mezo.getFagyottAlkatresz().getID() + "-Pisztoly"));
        }

        ArrayList<Jatekos> jatekosok = mezo.getAlloJatekos();
        if (!jatekosok.isEmpty()) {
            for (Jatekos jatekos : jatekosok) {
                retegek.add(images.get(jatekos.getID()));
            }
        }

        //A megfelelő rétegeket kell rárajzolni egy mezőPanelra ami megfelel a mezőnek
        if (mezo.getAlloJegesmedve() != null) {
            retegek.add(images.get("Medve"));
        }

        KotelVisitor kv = new KotelVisitor();
        if (mezo.getTargy() != null) {
            if (mezo.getTargy().accept(kv)) {
                retegek.add(images.get("Kotel"));
            }
        }

        LapatVisitor lv = new LapatVisitor();
        if (mezo.getTargy() != null) {
            if (mezo.getTargy().accept(lv)) {
                retegek.add(images.get("Lapat"));
            }
        }

        BuvarruhaVisitor bv = new BuvarruhaVisitor();
        if (mezo.getTargy() != null) {
            if (mezo.getTargy().accept(bv)) {
                retegek.add(images.get("Buvarruha"));
            }
        }

        ElelemVisitor ev = new ElelemVisitor();
        if (mezo.getTargy() != null) {
            if (mezo.getTargy().accept(ev)) {
                retegek.add(images.get("Etel"));
            }
        }

        //A hó kirajzolása
        if (mezo.getHotakaro() != 0) {
            retegek.add(images.get("Ho"));
        }

        SatorVisitor sv = new SatorVisitor();
        if (mezo.getTargy() != null) {
            if (mezo.getTargy().accept(sv)) {
                retegek.add(images.get("Sator"));
            }
        }

        if (mezo.isIglu()) {
            retegek.add(images.get("Iglu"));
        }

        for(String s: aktiv){
            if (s.equals("aktiv")){
                retegek.add(images.get("Aktiv"));
            }
        }

        //Ki kell rajzolni
        mezoView.update(retegek);
    }

    /**
     * Textúrázva tölti ki a hátteret
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (g2 == null) {
            System.out.println("error");
            return;
        }
        try {
            BufferedImage image = ImageIO.read(new File("Resources/Assets/Tenger.png"));
            Rectangle2D rec = new java.awt.geom.Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
            TexturePaint tp = new TexturePaint(image, rec);
            g2.setPaint(tp);
            Rectangle2D r = this.getBounds();
            g2.fill(r);
        } catch (java.io.IOException ex) {
        }
    }

    /**
     * Pálya frissítése.
     *
     * @param k A kontroller amitől lekérjük a pályát amit frissiteni szeretnénk.
     */
   /* public void ujra(Kontroller k) {
        update(k.getPalya());

    }

    */

    /**
     * Ikonok betöltése
     */

    void loadImages() {
        try {
            images.put("Jegtabla", read(new File("Resources/Assets/Jegtabla-01.png")));
            images.put("Lyuk", read(new File("Resources/Assets/Lyuk-01.png")));
            images.put("Ho", read(new File("Resources/Assets/Ho-01.png")));
            images.put("Iglu", read(new File("Resources/Assets/Iglu-01.png")));
            images.put("Sator", read(new File("Resources/Assets/Sator-01.png")));
            images.put("0-Pisztoly", read(new File("Resources/Assets/1-Pisztoly-01.png")));
            images.put("1-Pisztoly", read(new File("Resources/Assets/2-Pisztoly-01.png")));
            images.put("2-Pisztoly", read(new File("Resources/Assets/3-Pisztoly-01.png")));
            images.put("Buvarruha", read(new File("Resources/Assets/Buvarruha-01.png")));
            images.put("Kotel", read(new File("Resources/Assets/Kotel-01.png")));
            images.put("Lapat", read(new File("Resources/Assets/Lapat-01.png")));
            images.put("Etel", read(new File("Resources/Assets/Konzerv-01.png")));
            images.put("Medve", read(new File("Resources/Assets/Medve-01.png")));
            images.put("E0", read(new File("Resources/Assets/Eszkimo_Top-01.png")));
            images.put("E1", read(new File("Resources/Assets/Eszkimo_Mid_Left-01.png")));
            images.put("E2", read(new File("Resources/Assets/Eszkimo_Mid_Right-01.png")));
            images.put("E3", read(new File("Resources/Assets/Eszkimo_Bottom_Left-01.png")));
            images.put("E4", read(new File("Resources/Assets/Eszkimo_Bottom_Center-01.png")));
            images.put("E5", read(new File("Resources/Assets/Eszkimo_Bottom_Right-01.png")));
            images.put("K0", read(new File("Resources/Assets/Kutato_Bottom-01.png")));
            images.put("K1", read(new File("Resources/Assets/Kutato_Mid_Left-01.png")));
            images.put("K2", read(new File("Resources/Assets/Kutato_Mid_Right-01.png")));
            images.put("K3", read(new File("Resources/Assets/Kutato_Top_Left-01.png")));
            images.put("K4", read(new File("Resources/Assets/Kutato_Top_Center-01.png")));
            images.put("K5", read(new File("Resources/Assets/Kutato_Top_Right-01.png")));
            images.put("Aktiv", read(new File("Resources/Assets/Active-01.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
