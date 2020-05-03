package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Kontroller { // konstruktorban kapja meg a játékosokat. Akkor tud a kontroller osztályra referenciát tartalmazni a játékos osztály
    /**
     * Egy segéd objektum ami Listener-ek listáját kezeli, és PropertyChangeEvent-eket küld nekik.
     * Ezek a PropertyChangeLister-ek regisztrálhatnak egy bizonyos nevű attribútum/property -re, vagy
     * akár az összesre is.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    protected ArrayList<Mezo> palya = new ArrayList<>();
    boolean aktiv = true;
    boolean nyert = false;
    ArrayList<View> views = new ArrayList<>();
    MouseListener mouseListener;
    private ArrayList<Jatekos> jatekosok = new ArrayList<>();
    private volatile Jatekos aktivJatekos;
    private Jegesmedve jegesmedve = new Jegesmedve();
    Kontroller() {
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    /**
     * Hozzáad egy PropertyChangeListener-t a kontrollerhez, ami egy bizonyos property változása esetén
     * valamilyen műveletet hajt végre.
     * (A programunkban ezt a modell változásának megfigyelésére fogjuk használni)
     *
     * @param listener A Listener ami valamilyen property változásra reagál.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Eltávolít egy PropertyChangeListener-t a kontroller Listener-jei közűl.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Visszadja a jelenleg aktív játékost.
     *
     * @return Visszadja a jelenleg aktív játékost.
     */
    public Jatekos getAktivJatekos() {
        return aktivJatekos;
    }

    /**
     * Beállít aktív játékosnak egy új játékost,
     * értesíti a View-t, hogy megváltozott a játékos és frissíteni kell magát
     *
     * @param ujAktivJatekos Az új aktív játékos akit be akarunk állítani
     */
    public void setAktivJatekos(Jatekos ujAktivJatekos) {
        Jatekos regiJatekos = aktivJatekos;
        this.aktivJatekos = ujAktivJatekos;
        support.firePropertyChange("aktivJatekos", regiJatekos, ujAktivJatekos);
    }

    /**
     * Hozzáad egy új nézetet a kontroller nézeteihez, és felveszi hozzá a mod kontrollerillenytű eseménykezelőjét.
     *
     * @param view Egy új nézet ami hozzáad a kontroller nézeteihez.
     */
    public void addView(View view) {
        KeyboardListener keyboardListener = new KeyboardListener();
        view.addKeyListener(keyboardListener);
        this.views.add(view);
    }


    public Mezo getPalya(int i) {
        return palya.get(i);
    }

    /**
     * A játék menete, minden játékos köre előtt detektálás van, utána pedig vihar
     */
    public void jatek() {
        while (aktiv) {
            for (Jatekos j : jatekosok) {
                this.setAktivJatekos(j);
                System.out.println("Játékos váltás");
                detektal();
                j.jatszik();
                vihar();
            }
            //TODO
            // Le kell másolni a jegesmedve mezőjét előtte meg utánna, majd FIrePropertyChange("jegesemedve", regi, uj);

            jegesmedve.jatszik();
        }
    }


    public void addJatekos(Jatekos j) {
        jatekosok.add(j);
    }

    public void addMezo(Mezo mezo) {
        this.palya.add(mezo);
    }


    /**
     * Végig megy az összes mezőn és megnöveli a hóréteget egy random számmal (negatív lineáris eloszlással)
     * 0 és a maxHoreteg kozott Között, tehát jóval kisebb a valószínűsége nagy hónak mint a semminek.
     * Lecsökkenti a mezőkön álló játékosok testhőjét, ha nincs iglu a mezőn vagy a sátor már
     */
    //TODO
    // Akkor kell fire "palya", miután a lement a vihar
    public void vihar() {
        int maxHoreteg = 5;

        for (Mezo mezo : palya) {
            //Megnöveljük a hóréteget egy random számmal (negatív lineáris eloszlással) 0 és a maxHoreteg kozott Között
            while (true) {
                double r1 = Math.random();
                double r2 = Math.random();
                if (r2 > r1) {
                    mezo.horetegNovel((int) (r1 * (maxHoreteg + 1)));
                    break;
                }
            }

            int i = mezo.getSatorMiotaVan();
            // ha nincs sátor ill iglu, csökken a testhő
            if (!(mezo.isIglu()) || i == 0)
                for (Jatekos j : mezo.getAlloJatekos()) {
                    j.setTestho(j.getTestho() - 1);
                }
        }
    }

    /**
     * vizsgaljuk a játékosok állapotát, nem e hűlt ki, nem e fulladt meg
     */
    public void detektal() {

        int alkatreszSzam = 0;

        for (Jatekos j : jatekosok) {
            int ho = j.getTestho();

            if (ho == 0) {
                j.setAllapot(FulladasiAllapot.halott);
                jatekVege(false);
            }
        }

        for (Jatekos j : jatekosok) {
            ArrayList<Alkatresz> alkatreszek = j.getAlkatreszek();
            alkatreszSzam += alkatreszek.size();
        }
        for (Mezo m : palya) {
            ArrayList<Alkatresz> alkatreszek = m.getAlkatreszek();
            if (alkatreszek != null) {
                alkatreszSzam += alkatreszek.size();
            }
            if (m.getFagyottAlkatresz() != null) {
                alkatreszSzam++;
            }
        }

        if (alkatreszSzam < 3) {
            jatekVege(false);
        }

        for (Mezo m : palya) {
            int satorMiotaVan = m.getSatorMiotaVan();
            if (satorMiotaVan == ((jatekosok.size()))) { // ha lement egy kör eltűnteti a sátrat
                m.satratNullaz();
            } else {
                if (m.getSatorMiotaVan() > 0) // ha van a mezőn sátor
                    m.satorIdoNovel();  // 1-gyel nő a felállítástúl eltelt idő
            }
        }
    }

    /**
     * Véget vet a játéknak
     *
     * @param nyer true érték esetén nyeréssel, false esetén vesztéssel ér véget a játék
     */
    //TODO
    // Fire "vege" meg kell jeleníteni a vége képernyőt
    public void jatekVege(boolean nyer) {
        this.aktiv = false;

        if (nyer) {
            System.out.println("NYERTEL");
            nyert = true;
        } else
            System.out.println("GAME OVER");
    }

    /**
     * getter
     *
     * @return a játékban részt vevő játékosok listája
     */
    public ArrayList<Jatekos> getJatekosok() {
        return jatekosok;
    }

    /**
     * setter
     *
     * @param jegesmedve a játék jegesmedvéje
     */
    public void setJegesmedve(Jegesmedve jegesmedve) {
        this.jegesmedve = jegesmedve;
    }

    public ArrayList<Mezo> getPalya() {
        return this.palya;
    }

    /**
     * A billenytűk eseménykezelőjének belső osztálya.
     */
    class KeyboardListener implements KeyListener {

        /**
         * Nem csinál semmit
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * A billenytű lenyomások kezelő függvénye.
         * Egy billenytű lenyomásra végrehajt egy műveletet az aktív játékoson, majd értesíti a nézeteket,
         * hogy azok tudják frissíteni magukat.
         *
         * @param e A lenyomott billenytű KeyEvent-je
         */
        @Override
        public void keyPressed(KeyEvent e) {

            try {

                // Ez az állapota a cselekvés előtt a Játékosnak, mindegy, hogy mennyire Deep a másolat,
                // Csak az számít, hogy ne legyen azonos a két objektum, és akkor felül lesz írva
                Jatekos regiJatekos = (Jatekos) aktivJatekos.clone();

                // A mezőket is le kell másolni az előzőhöz hasonló okok miatt
                ArrayList<Mezo> regiPalya = new ArrayList<>();
                for (Mezo m : palya) {
                    regiPalya.add((Mezo) m.clone());
                }

                if (e.getKeyCode() == (KeyEvent.VK_NUMPAD8) || e.getKeyCode() == KeyEvent.VK_UP) {
                    aktivJatekos.lep(Irany.Fel);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD9)) {
                    aktivJatekos.lep(Irany.JobbFel);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD6) || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    aktivJatekos.lep(Irany.Jobb);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD3)) {
                    aktivJatekos.lep(Irany.JobbLe);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD2) || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    aktivJatekos.lep(Irany.Le);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD1)) {
                    aktivJatekos.lep(Irany.BalLe);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD4) || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    aktivJatekos.lep(Irany.Bal);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD7)) {
                    aktivJatekos.lep(Irany.BalFel);
                } else {
                    return;
                }

                //TODO
                // -Comparable-t implementálja: Mező, Pály, Lyuk, Játékos Eszkimó
                // Itt össze kell hasonlítani a régi játékost az új al
                // De ez úgyis megváltozik ezt valószínű minden cselekvés után újra kell rajzolni
                // ...
                // A pályán végig kell menni, összehasonlítani az összes új mezőt a régiekkel
                // Akkor kell fire "mezo", ha a mező nem ugyan olyan mint a régi
                // Ilyenkor a View csakk azt a mezőt rajzolja újra amit kell

                //Frissíteni kell a View Aktív Játékosát
                support.firePropertyChange("aktivJatekos", regiJatekos, aktivJatekos);

                //Frisíteni kell a View pályályát
                support.firePropertyChange("palya", regiPalya, palya);

                //System.out.println(aktivJatekos.getTartozkodasiMezo().getID());
            } catch (CloneNotSupportedException cloneNotSupportedException) {
                cloneNotSupportedException.printStackTrace();
            }
        }

        /**
         * Nem csinál semmit
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}