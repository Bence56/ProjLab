package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Mezo {
    int teherbiras;
    int hotakaro;
    Map<Irany, Mezo> szomszedok = new HashMap<>();
    ArrayList<Alkatresz>alkatreszek = new ArrayList<>();
    ArrayList<Jatekos> alloJatekos=new ArrayList<>();

    public void horetegNovel(){}
    public  abstract  void elfogad(Jatekos j);

    /**
     * Eltávolítja a Játékost erről a mezőről
     * @param j A játékos akit le kell venni
     */
    public void  eltavolit(Jatekos j){
        Tab.tab++;
        for(int i=0; i<Tab.tab; i++)System.out.print("\t");
        System.out.println("Mezo.eltavolit(Jatekos j)");
        Tab.tab--;
    }

    /**
     * Visszadja a szomszédos mezőt a paraméterként kapott irányba
     * @param i Az irány
     * @return A szomszédos mező
     */
    public Mezo  getSzomszed(Irany i){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Mezo.getSzomszed(" + i +")");
        Tab.tab--;
        return this.szomszedok.get(i);
    }
    /**
     *Ez annak a modellezése amikor leteszik az  alkatrészeket a mezőre, hogy majd el lehessen sütni
     * és így megnyerjék a játékot. Hozzáadja a paraméterül kapott alkatrészt a kollekciójukhoz
     * @param a amit hozzá kell adni a kollekcióhoz
     */
    public void alkatreszNovel(Alkatresz a){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Mezo.alkatreszNovel(Alkatresz a)");
        this.alkatreszek.add(a);
        Tab.tab--;
    }
    /**
     *Beállítja a tábla iglu tulajdonságát
     * @param iglu annak az értéke hogy a tábla iglu típusú lesz vagy nem
     */
    public void setIglu(boolean iglu) { }

    public void testhoCsokkent(){}
    public int getTeherbiras(){
        return teherbiras;
    }

    /**
     * Visszadaj a mezőn lévő alkatrészeket
     * @return
     */
    public ArrayList<Alkatresz> getAlkatreszek(){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Mezo.getAlkatreszek()");
        Tab.tab--;

        return this.alkatreszek;

    }
}
