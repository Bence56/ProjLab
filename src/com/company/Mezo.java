package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Mezo {

    private int teherbiras;
    private int hotakaro;
    protected Map<Irany, Mezo> szomszedok = new HashMap<>();
    private ArrayList<Jatekos> alloJatekos = new ArrayList<>();
    private Jegesmedve alloJegesmedve;

    public Mezo(int teherbiras, int hotakaro){
        this.teherbiras=teherbiras;
        this.hotakaro=hotakaro;
    }

    public Mezo(int teherbiras, int hotakaro, Jegesmedve medve){
    this.teherbiras=teherbiras;
    this.hotakaro=hotakaro;
    this.alloJegesmedve=medve;
    }


    //Ezt üresen kell hagyni
    Targy getTargy() {
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Mezo.getTargy()");
        Tab.tab--;
        return null;
    }

    void setFagyottTargy(Targy t){ }


    public void horetegNovel() {
    }

    public void horetegCsokkent(){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Jegtabla.horetegCsokkent()");
        if (hotakaro>0)
        this.hotakaro--;
        Tab.tab--;
    }


    public abstract void elfogad(Jatekos j);

    public void elfogad(Jegesmedve j) { // a Jegesmedve nem esik vízbe csak odalép vhova, mindegy hogy jégtábla vagy lyuk..
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Mezo.elfogad(Jegesmedve j)");

        j.setMezo(this);
        this.alloJegesmedve = j;
        Tab.tab--;
    }

    /**
     * Eltávolítja a Játékost erről a mezőről
     *
     * @param j A játékos akit le kell venni
     */
    public void eltavolit(Jatekos j) {
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Mezo.eltavolit(Jatekos j)");

        Tab.tab--;
    }

    public void eltavolit(Jegesmedve j) {
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Mezo.eltavolit(Jegesmedve j)");

        Tab.tab--;
    }

    public void utkozik(Jegesmedve j){
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Mezo.utkozik(Jegesmedve j)");

        if (!isIglu() && alloJatekos.size()>=1){
            alloJatekos.get(0).meghal();
            alloJatekos.remove(0);
        }

        Tab.tab--;
    }

    public ArrayList<Jatekos> getAlloJatekos() {
        return alloJatekos;
    }

    public boolean isIglu() {
        return false;
    }
    public void setIglu(boolean iglu) {}

    public void satorIdoNovel() {}

    public void satratNullaz() {}

    public int getSatorMiotaVan(){
        return 0;
    }


    /**
     * Visszadja a szomszédos mezőt a paraméterként kapott irányba
     *
     * @param i Az irány
     * @return A szomszédos mező
     */
    public Mezo getSzomszed(Irany i) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Mezo.getSzomszed(" + i + ")");
        Tab.tab--;
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
     * Beállítja a tábla iglu tulajdonságát
     *
     * @param iglu annak az értéke hogy a tábla iglu típusú lesz vagy nem
     */

    ;

    /**
     * Ellenőrzi, hogy van-e a mezőn iglu, és ha nincs, a mezőn álló játékosok testhőjét csökkenti 1-gyel.
     */
    public void testhoCsokkent() {
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Mezo.testhoCsokkent()");
        if (!this.isIglu()) {
            for (Jatekos jatekos : alloJatekos) {
                int ho = jatekos.getTestho();
                jatekos.setTestho(ho - 1);
            }
        }
        Tab.tab--;
    }

    public int getTeherbiras() {
        return teherbiras;
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

    public Jegesmedve getAlloJegesmedve() {
        return alloJegesmedve;
    }

    public int getHotakaro() {
        return hotakaro;
    }

    public void setHotakaro(int hotakaro) {
        this.hotakaro = hotakaro;
    }

    public void setAlloJegesmedve(Jegesmedve alloJegesmedve) {
        this.alloJegesmedve = alloJegesmedve;
    }
}
