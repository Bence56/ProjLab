package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Mezo {
    int teherbiras;
    int hotakaro;
    Map<Irany, Mezo> Szomszedok = new HashMap<>();
    ArrayList<Alkatresz>alkatreszek;
    public void horetegNovel(){}
    public  abstract  void elfogad(Jatekos j);
    public void  eltavolit(Jatekos j){
        Tab.tab++;
        for(int i=0; i<Tab.tab; i++)System.out.print("\t");
        System.out.println("Mezo.eltavolit(Jatekos j)");
        Tab.tab--;
    }

    public Mezo  getSzomszed(Irany i){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Mezo.getSzomszed(" + i +")");
        Tab.tab--;
        return this.Szomszedok.get(i);
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

    public void testhoCsokkent(){}
    public int getTeherbiras(){
        return teherbiras;
    }
}
