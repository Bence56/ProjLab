package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Mezo {

    protected Map<Irany, Mezo> szomszedok = new HashMap<>();
    private String id;
    private int teherbiras;
    private int hotakaro;
    private ArrayList<Jatekos> alloJatekos = new ArrayList<>();
    private Jegesmedve alloJegesmedve;

    public Mezo(int teherbiras, int hotakaro) {
        this.teherbiras = teherbiras;
        this.hotakaro = hotakaro;
    }

    public Mezo(String id, int teherbiras, int hotakaro) {
        this.id = id;
        this.teherbiras = teherbiras;
        this.hotakaro = hotakaro;
    }

    public Mezo(int teherbiras, int hotakaro, Jegesmedve medve) {
        this.teherbiras = teherbiras;
        this.hotakaro = hotakaro;
        this.alloJegesmedve = medve;
    }

    public Mezo(String id, int teherbiras, int hotakaro, Jegesmedve medve) {
        this.id = id;
        this.teherbiras = teherbiras;
        this.hotakaro = hotakaro;
        this.alloJegesmedve = medve;
    }

    public String getID() {
        return id;
    }

    public void addSzomszedok(Irany i, Mezo m) {
        this.szomszedok.put(i, m);
    }

    //Ezt üresen kell hagyni
    Targy getTargy() {
        return null;
    }

    public void setAlkatreszek(Alkatresz a1, Alkatresz a2, Alkatresz a3) {
    }

    public void setFagyottTargy(Targy t) {
    }

    public void setFagyottAlk(Alkatresz t) {
    }

    public void horetegNovel(int num) {
        this.hotakaro += num;
    }

    /**
     * Csökkenti a mezőn lévő hóréteget egyel.
     */
    public void horetegCsokkent() {
        if (hotakaro > 0)
            this.hotakaro--;
    }

    /**
     * Absztrakt függvény, implementációja befogadja a rálépő játékost (attól függően, hogy jégtábla, vagy lyuk az adott mező)
     *
     * @param j a mezőre lépő játékos
     */

    public abstract void elfogad(Jatekos j);

    /**
     * A paraméterül kapott jegesmedvét a jégtáblára teszi
     *
     * @param j a jegesmedve akit rá kell tenni a jégtáblára
     */
    public void elfogad(Jegesmedve j) { // a Jegesmedve nem esik vízbe csak odalép vhova, mindegy hogy jégtábla vagy lyuk..
        j.setMezo(this);
        this.alloJegesmedve = j;
    }

    /**
     * Eltávolítja a Játékost erről a mezőről
     *
     * @param j A játékos akit le kell venni
     */
    public void eltavolit(Jatekos j) {

        alloJatekos.remove(j);
        System.out.println(alloJatekos.size());
    }

    /**
     * Eltávolítja a rajta álló jegesmedvét
     *
     * @param j a jegesmedve akit el kell távolítani
     */
    public void eltavolit(Jegesmedve j) {
        alloJegesmedve = null;
    }

    /**
     * A játékos és a jegesmedve találkozik.
     *
     * @param j a jegesmedve
     */
    public void utkozik(Jegesmedve j) {
        if (j != null) {
            if (!isIglu() && alloJatekos.size() >= 1) {
                alloJatekos.get(0).meghal();
                alloJatekos.remove(0);
            }
        }
    }

    /**
     * Visszaadja a mezőn álló játokosokat.
     *
     * @return A játékosok listája.
     */
    public ArrayList<Jatekos> getAlloJatekos() {
        return alloJatekos;
    }

    /**
     * Hozzáad egy játékost a mezőhöz.
     *
     * @param alloJatekos a játékos akit hozzáad.
     */
    public void addAlloJatekos(Jatekos alloJatekos) {
        this.alloJatekos.add(alloJatekos);
    }

    public boolean isIglu() {
        return false;
    }

    public void setIglu(boolean iglu) {
    }

    public void satorIdoNovel() {
    }

    public int getSatorMiotaVan() {
        return 0;
    }

    public void satratNullaz() {
    }

    /**
     * Visszadja a szomszédos mezőt a paraméterként kapott irányba
     *
     * @param i Az irány
     * @return A szomszédos mező
     */
    public Mezo getSzomszed(Irany i) {
        return this.szomszedok.get(i);
    }

    /**
     * Ez annak a modellezése amikor leteszik az  alkatrészeket a mezőre, hogy majd el lehessen sütni
     * és így megnyerjék a játékot. Hozzáadja a paraméterül kapott alkatrészt a kollekciójukhoz
     *
     * @param a amit hozzá kell adni a kollekcióhoz
     */
    public abstract void alkatreszNovel(Alkatresz a);

    /**
     * Ellenőrzi, hogy van-e a mezőn iglu, és ha nincs, a mezőn álló játékosok testhőjét csökkenti 1-gyel.
     */
    public void testhoCsokkent() {
        if (!this.isIglu()) {
            for (Jatekos jatekos : alloJatekos) {
                int ho = jatekos.getTestho();
                jatekos.setTestho(ho - 1);
            }
        }
    }

    /**
     * Megmondja mennyi a mező teherbírása.
     *
     * @return a teherbírás értéke.
     */
    public int getTeherbiras() {
        return teherbiras;
    }

    public void setTeherbiras(int teherbiras) {
        this.teherbiras = teherbiras;
    }

    /**
     * Visszadaj a mezőn lévő fagyott alkatrészt
     *
     * @return
     */
    public abstract Alkatresz getFagyottAlkatresz();

    /**
     * Visszadaj a mezőnre letett alkatrészeket
     *
     * @return
     */
    public abstract ArrayList<Alkatresz> getAlkatreszek();

    /**
     * Visszadja a mezőn lévő jegesmedvét.
     *
     * @return a jegesmedve
     */
    public Jegesmedve getAlloJegesmedve() {
        return alloJegesmedve;
    }

    /**
     * Elhelyez egy jegesmdvét a mezőn.
     *
     * @param alloJegesmedve akit elhelyez.
     */
    public void setAlloJegesmedve(Jegesmedve alloJegesmedve) {
        this.alloJegesmedve = alloJegesmedve;
    }

    /**
     * Visszadja mennyi hóréteg van a mezőn.
     *
     * @return a hóréteg értéke.
     */
    public int getHotakaro() {
        return hotakaro;
    }

    /**
     * Beállítja a hóréteget a mezőn.
     *
     * @param hotakaro a hótakaró rétegeinek száma.
     */
    public void setHotakaro(int hotakaro) {
        this.hotakaro = hotakaro;
    }

    public abstract void state();
}
