package com.company;

import javax.swing.*;
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
    boolean aktiv = true;
    boolean nyert = false;
    ArrayList<View> views = new ArrayList<>();
    MouseListener mouseListener;
    private ArrayList<Jatekos> jatekosok = new ArrayList<>();
    private volatile Jatekos aktivJatekos;
    private Jegesmedve jegesmedve = new Jegesmedve();
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
        //Amikor hozzáadjuk a nézetet az jelenjen is meg rögtön
        support.firePropertyChange("palya",0,palya);
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
                    ArrayList<Mezo> regiPalya = new ArrayList<>();
                    //TODO
                    // vihar előtti pálya klónozása és utána fireproperty a pályára. KESZ
                    for (Mezo m : palya) {
                        regiPalya.add((Mezo) m.clone());
                    }
                    vihar();
                    support.firePropertyChange("palya", regiPalya, palya);
                }
                //TODO
                // Le kell másolni a jegesmedve mezőjét és a körülötte lévő 8 mezőt előtte deep es shallow copyval, majd miután lépett,
                // minden megfelelő mezőre FIrePropertyChange("jegesemedve", regi, uj); ELVILEG KÉSZ
                //shallowcopy(sc)  a jegesmedve mezejéről és a körülötte lévő 8 mezőről
                ArrayList<Mezo> scRegiMezok = new ArrayList<>();
                Mezo scAholAll = jegesmedve.getTartozkodasiMezo();
                scRegiMezok.add(scAholAll);
                Irany[] arr = Irany.values(); //tömbre képezi le az enum irányokat, a felvétel sorrendjének megfelelően
                for (Irany i : arr) {
                    if (scAholAll.getSzomszed(i)!=null){
                        Mezo szomszed = scAholAll.getSzomszed(i);
                        scRegiMezok.add(szomszed);
                    }

                }

                //deepcopy létrehozása a tartozkodási mezőről és a körülötte lévő mezőkről
                ArrayList<Mezo> dcRegiMezok = new ArrayList<>();
                Mezo dcAholAll = (Mezo) jegesmedve.getTartozkodasiMezo().clone();
                dcRegiMezok.add(dcAholAll);
                for (Irany i : arr) {
                    if (dcAholAll.getSzomszed(i)!=null) {
                        Mezo szomszed2 = (Mezo) dcAholAll.getSzomszed(i).clone();
                        dcRegiMezok.add(szomszed2);
                    }
                }

                jegesmedve.jatszik();

                //Frissíteni kell a mezőket ahol állt és ahova léphetett (8 féle irány) a jegesmedve
                for (int i = 0; i < scRegiMezok.size(); i++) {
                    support.firePropertyChange("mezo", dcRegiMezok.get(i), scRegiMezok.get(i));
                }

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand=actionEvent.getActionCommand();
      if (actionCommand.equals("lapatol")) {
          aktivJatekos.lapatol();
        }
      if (actionCommand.equals("balfentről")) {
            aktivJatekos.kihuz(Irany.BalFel); //TODO: átírni h merre húzzon ki
        }
        if (actionCommand.equals("fentről")) {
            aktivJatekos.kihuz(Irany.Fel); //TODO: átírni h merre húzzon ki
        }
        if (actionCommand.equals("jobbfentről")) {
            aktivJatekos.kihuz(Irany.JobbFel);
        }
        if (actionCommand.equals("balról")) {
            aktivJatekos.kihuz(Irany.Bal);
        }
        if (actionCommand.equals("jobbról")) {
            aktivJatekos.kihuz(Irany.Jobb);
        }
        if (actionCommand.equals("ballentről")) {
            aktivJatekos.kihuz(Irany.BalLe);
        }
        if (actionCommand.equals("lentről")) {
            aktivJatekos.kihuz(Irany.Le);
        }
        if (actionCommand.equals("jobblentről")) {
            aktivJatekos.kihuz(Irany.JobbLe);
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
        if (actionCommand.equals("iglut epit")) {
            aktivJatekos.epit();
        }

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

                /*
                // A mezőket is le kell másolni az előzőhöz hasonló okok miatt
                ArrayList<Mezo> regiPalya = new ArrayList<>();
                for (Mezo m : palya) {
                    regiPalya.add((Mezo) m.clone());
                }
               */
                ArrayList<Mezo> scRegiMezok = ShallowCopySzomszedok();

                 //deepcopy létrehozása a tartozkodási mezőről és a körülötte lévő mezőkről
                ArrayList<Mezo> dcRegiMezok = DeepCopySzomszedok();

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
                // Ilyenkor a View csak azt a mezőt rajzolja újra amit kell

                //Frissíteni kell a View Aktív Játékosát
                support.firePropertyChange("aktivJatekos", regiJatekos, aktivJatekos);

               /* //Frisíteni kell a View pályályát
                support.firePropertyChange("palya", regiPalya, palya);
                */

               //Frissíteni kell a mezőket ahol állt és ahova lépett
                for (int i=0; i<scRegiMezok.size(); i++){
                    support.firePropertyChange("mezo", dcRegiMezok.get(i), scRegiMezok.get(i));
                }

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

        /**
         * shallowcopy(sc)  a játékos mezejéről és a körülötte lévő szomszédos mezőről
         * ha valamelyik szomszéd null, azt nem másolja le
         * @return Egy Array List a lemásolt mezőkről
         */
        private ArrayList<Mezo> ShallowCopySzomszedok(){
            ArrayList<Mezo> scRegiMezok=new ArrayList<>();
            Mezo scAholAll=aktivJatekos.getTartozkodasiMezo();
            scRegiMezok.add(scAholAll);

            //tömbre képezi le az enum irányokat, a felvétel sorrendjének megfelelően
            Irany[] arr =Irany.values();

            for(Irany i: arr){
                Mezo szomszed = scAholAll.getSzomszed(i);
                if(szomszed != null) {
                    scRegiMezok.add(szomszed);
                }
            }
            return  scRegiMezok;
        }

        private ArrayList<Mezo> DeepCopySzomszedok() throws CloneNotSupportedException {
            ArrayList<Mezo> dcRegiMezok=new ArrayList<>();
            Mezo dcAholAll= (Mezo)aktivJatekos.getTartozkodasiMezo().clone();
            dcRegiMezok.add(dcAholAll);

            //tömbre képezi le az enum irányokat, a felvétel sorrendjének megfelelően
            Irany[] arr = Irany.values();

            for(Irany i: arr){
                Mezo szomszed2= dcAholAll.getSzomszed(i);
                dcRegiMezok.add(szomszed2);
            }
            return  dcRegiMezok;
        }
    }
}