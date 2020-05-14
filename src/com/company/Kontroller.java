package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Kontroller implements ActionListener { // konstruktorban kapja meg a játékosokat. Akkor tud a kontroller osztályra referenciát tartalmazni a játékos osztály
    /**
     * Egy segéd objektum ami Listener-ek listáját kezeli, és PropertyChangeEvent-eket küld nekik.
     * Ezek a PropertyChangeLister-ek regisztrálhatnak egy bizonyos nevű attribútum/property -re, vagy
     * akár az összesre is.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    protected ArrayList<Mezo> palya = new ArrayList<>();
    volatile boolean aktiv = true;
    boolean nyert = false;
    ArrayList<View> views = new ArrayList<>();
    MouseListener mouseListener;
    private final ArrayList<Jatekos> jatekosok = new ArrayList<>();
    private volatile Jatekos aktivJatekos;
    private Jegesmedve jegesmedve = new Jegesmedve();

    private volatile boolean kihuz;
    private volatile Irany kihuzIrany=Irany.Jobb;
    Kontroller() {
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
        support.firePropertyChange("aktivJatekos",null,aktivJatekos);
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
        //Amikor hozzáadjuk a nézetet az jelenjen is meg rögtön
        support.firePropertyChange("palya", 0, palya);
        support.firePropertyChange("aktiv mezo",null, aktivJatekos.getTartozkodasiMezo());
    }


    public Mezo getPalya(int i) {
        return palya.get(i);
    }

    /**
     * A játék menete, minden játékos köre előtt detektálás van, utána pedig vihar
     */
    public void jatek() {
        try {
            while (aktiv) {
                for (Jatekos j : jatekosok) {
                    this.setAktivJatekos(j);
                    System.out.println("Játékos váltás");
                    detektal();
                    j.jatszik();
                    if (j.getAllapot() == FulladasiAllapot.aktiv)
                        j.setMunkakSzama(4);
                }
                //TODO
                // vihar előtti pálya klónozása és utána fireproperty a pályára. KESZ
                ArrayList<Mezo> regiPalya = new ArrayList<>();
                for (Mezo m : palya) {
                    regiPalya.add((Mezo) m.clone());
                }
                vihar();
                //A vihar után az egész pályát újra kell rajzolni
                support.firePropertyChange("palya", regiPalya, palya);

               //a mezo
                //Az a mező lesz ahová megérkezik majd az aktív játékos a lépés után

                Mezo regiTartozkodasiMezo = (Mezo)jegesmedve.getTartozkodasiMezo().clone();
                jegesmedve.jatszik();
                Mezo ujTartozkodasiMezo=jegesmedve.getTartozkodasiMezo();

                if(ujTartozkodasiMezo != null)
                    support.firePropertyChange("mezo", null, ujTartozkodasiMezo);
                // Ahol előtte állt
                support.firePropertyChange("mezo", regiTartozkodasiMezo, jegesmedve.getTartozkodasiMezo());
                }


        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
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
            FulladasiAllapot allapot = j.getAllapot();
            if (allapot == FulladasiAllapot.fuldoklik){
                j.setAllapot(FulladasiAllapot.kimentheto);
            }
            else{
                if(allapot == FulladasiAllapot.kimentheto){
                    j.setAllapot(FulladasiAllapot.halott);
                    System.out.println("Megfulladtál.");
                    j.meghal();
                }
            }
        }

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
            System.out.println("Nincs meg az összes alkatrész.");
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
        aktiv = false;

        if (nyer) {
            System.out.println("NYERTEL");
            nyert = true;
        } else
            System.out.println("GAME OVER");
        Frame f = new JFrame();
        f.setVisible(true);
        System.exit(0);
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Ez az állapota a cselekvés előtt a Játékosnak, mindegy, hogy mennyire Deep a másolat,
        // Csak az számít, hogy ne legyen azonos a két objektum, és akkor felül lesz írva
        try {
            Jatekos regiJatekos = (Jatekos) aktivJatekos.clone();

            //Az a mező lesz ahová megérkezik majd az aktív játékos a lépés után
            Mezo ujTarozkodasiMezo;

            Mezo regiTartozkodasiMezo = (Mezo) aktivJatekos.getTartozkodasiMezo().clone();
            String actionCommand = actionEvent.getActionCommand();
            kihuz  = false;


            Mezo regiszomszed=null;
            Mezo ujSzomszed=null;
            if (actionCommand.equals("lapatol")) {
                aktivJatekos.lapatol();
            }
            if (actionCommand.equals("balfentről")) {
                aktivJatekos.kihuz(Irany.BalFel); //TODO: átírni h merre húzzon ki
                kihuz = true;
                kihuzIrany=Irany.BalFel;
            }
            if (actionCommand.equals("fentről")) {
                regiszomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Fel).clone();
                aktivJatekos.kihuz(Irany.Fel); //TODO: átírni h merre húzzon ki
                kihuz = true;
                kihuzIrany=Irany.Fel;
                ujSzomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Fel).clone();
            }
            if (actionCommand.equals("jobbfentről")) {
                regiszomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.JobbFel).clone();
                aktivJatekos.kihuz(Irany.JobbFel);
                kihuz = true;
                kihuzIrany=Irany.JobbFel;
                ujSzomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.JobbFel).clone();
            }
            if (actionCommand.equals("balról")) {
                regiszomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Bal).clone();
                aktivJatekos.kihuz(Irany.Bal);
                kihuz = true;
                kihuzIrany=Irany.Bal;
                ujSzomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Bal).clone();
            }
            if (actionCommand.equals("jobbról")) {
                regiszomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Jobb).clone();
                aktivJatekos.kihuz(Irany.Jobb);
                kihuz = true;
                kihuzIrany=Irany.Jobb;
                ujSzomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Jobb).clone();
            }
            if (actionCommand.equals("ballentről")) {
                regiszomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.BalLe).clone();
                aktivJatekos.kihuz(Irany.BalLe);
                kihuz = true;
                kihuzIrany=Irany.BalLe;
                ujSzomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.BalLe).clone();
            }
            if (actionCommand.equals("lentről")) {
                regiszomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Le).clone();
                aktivJatekos.kihuz(Irany.Le);
                kihuz = true;
                kihuzIrany=Irany.Le;
                ujSzomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.Le).clone();
            }
            if (actionCommand.equals("jobblentről")) {
                regiszomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.JobbLe).clone();
                aktivJatekos.kihuz(Irany.JobbLe);
                kihuz = true;
                kihuzIrany=Irany.JobbLe;
                ujSzomszed=(Mezo)aktivJatekos.getTartozkodasiMezo().getSzomszed(Irany.JobbLe).clone();
            }
            if (actionCommand.equals("satrat epit")) {
                aktivJatekos.satratEpit();
            }
            if (actionCommand.equals("lerak")) {
                aktivJatekos.lerak();
            }
            if (actionCommand.equals("kapar")) {
                aktivJatekos.kapar();
            }
            if (actionCommand.equals("összeszerel")) {
                aktivJatekos.osszeszerel();
            }
            if (actionCommand.equals("iglut épít")) {
                System.out.println("IGLUUUU");
                aktivJatekos.epit();
            }
            if (actionCommand.equals("vizsgál balfent")) {
                aktivJatekos.vizsgal(Irany.BalFel);
            }
            if (actionCommand.equals("vizsgál fent")) {
                aktivJatekos.vizsgal(Irany.Fel);
            }
            if (actionCommand.equals("vizsgál jobbfent")) {
                aktivJatekos.vizsgal(Irany.JobbFel);
            }
            if (actionCommand.equals("vizsgál balra")) {
                aktivJatekos.vizsgal(Irany.Bal);
            }
            if (actionCommand.equals("vizsgál jobbra")) {
                aktivJatekos.vizsgal(Irany.Jobb);
            }
            if (actionCommand.equals("vizsgál ballent")) {
                aktivJatekos.vizsgal(Irany.BalLe);
            }
            if (actionCommand.equals("vizsgál lent")) {
                aktivJatekos.vizsgal(Irany.Le);
            }
            if (actionCommand.equals("vizsgál jobblent")) {
                aktivJatekos.vizsgal(Irany.JobbLe);
            }
            if (kihuz) {
                support.firePropertyChange("mezo",regiszomszed,ujSzomszed);
                support.firePropertyChange("aktivJatekos", regiJatekos, aktivJatekos);
                support.firePropertyChange("mezo", regiTartozkodasiMezo, regiJatekos.getTartozkodasiMezo());
                support.firePropertyChange("aktiv mezo", regiTartozkodasiMezo, aktivJatekos.getTartozkodasiMezo());
            } else {
                System.out.println("valami");
                support.firePropertyChange("aktivJatekos", regiJatekos, aktivJatekos);
                support.firePropertyChange("mezo", regiTartozkodasiMezo, regiJatekos.getTartozkodasiMezo());
                support.firePropertyChange("aktiv mezo", regiTartozkodasiMezo, aktivJatekos.getTartozkodasiMezo());
            }
            for (View v:views) {
                v.requestFocusInWindow();
            }
        }
        catch (Exception e){}
    }

    /**
     * A billenytűk eseménykezelőjének belső osztálya.
     */
    class KeyboardListener implements KeyListener {

        /**
         * Nem csinál semmit
         *
         * @param e Az esemény
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


                //Az a mező lesz ahová megérkezik majd az aktív játékos a lépés után
                Mezo ujTarozkodasiMezo;

                Mezo regiTartozkodasiMezo = (Mezo)aktivJatekos.getTartozkodasiMezo().clone();

                if (e.getKeyCode() == (KeyEvent.VK_NUMPAD8) || e.getKeyCode() == KeyEvent.VK_UP) {
                    ujTarozkodasiMezo = copySzomszed(Irany.Fel);
                    aktivJatekos.lep(Irany.Fel);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD9)) {
                    ujTarozkodasiMezo = copySzomszed(Irany.JobbFel);
                    aktivJatekos.lep(Irany.JobbFel);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD6) || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ujTarozkodasiMezo = copySzomszed(Irany.Jobb);
                    aktivJatekos.lep(Irany.Jobb);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD3)) {
                    ujTarozkodasiMezo = copySzomszed(Irany.JobbLe);
                    aktivJatekos.lep(Irany.JobbLe);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD2) || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    ujTarozkodasiMezo = copySzomszed(Irany.Le);
                    aktivJatekos.lep(Irany.Le);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD1)) {
                    ujTarozkodasiMezo = copySzomszed(Irany.BalLe);
                    aktivJatekos.lep(Irany.BalLe);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD4) || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    ujTarozkodasiMezo = copySzomszed(Irany.Bal);
                    aktivJatekos.lep(Irany.Bal);
                } else if (e.getKeyCode() == (KeyEvent.VK_NUMPAD7)) {
                    ujTarozkodasiMezo = copySzomszed(Irany.BalFel);
                    aktivJatekos.lep(Irany.BalFel);
                } else {
                    return;
                }

                //Frissíteni kell a View Aktív Játékosát
                support.firePropertyChange("aktivJatekos", regiJatekos, aktivJatekos);

                // Frissíteni kell:
                // Ahová megérkezett
                if(ujTarozkodasiMezo != null)
                    support.firePropertyChange("mezo", null, ujTarozkodasiMezo);
                // Ahol előtte állt
                support.firePropertyChange("mezo", regiTartozkodasiMezo, regiJatekos.getTartozkodasiMezo());
                //Ahol az új játékos áll
                support.firePropertyChange("aktiv mezo", null,aktivJatekos.getTartozkodasiMezo());

            } catch (CloneNotSupportedException cloneNotSupportedException) {
                cloneNotSupportedException.printStackTrace();
            }
        }

        /**4
         * Nem csinál semmit
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
        }

        /**
         * Visszadja az akív játékos paraméterként kapott irányában a tartózkodási mező szomszédjának másolatát
         * @param i az irány amerrer a mezőt visszadja
         * @return Az aktív szomszéd másolatát
         */
        private Mezo copySzomszed(Irany i){
            Mezo mezo = aktivJatekos.getTartozkodasiMezo().getSzomszed(i);
            return mezo;
        }
    }
}