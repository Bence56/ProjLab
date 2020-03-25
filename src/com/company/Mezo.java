package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Mezo {
    int teherbiras;
    int hotakaro;
    Map<Irany, Mezo> Szomszedok = new HashMap<>();

    public void horetegNovel(){}
    public  abstract  void elfogad(Jatekos j);
    public void  eltavolit(Jatekos j){
        Tab.tab++;
        for(int i=0; i<Tab.tab; i++)System.out.print("\t");
        System.out.println("eltavolit(Jatekos j)");
        Tab.tab--;
    }

    public Mezo  getSzomszed(Irany i){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("getSzomszed(" + i +")");
        Tab.tab--;
        return this.Szomszedok.get(i);
    }

    public void testhoCsokkent(){}
    public int getTeherbiras(){
        return teherbiras;
    }
}
