package com.company;

import java.util.Map;

public abstract class Mezo {
    int teherbiras;
    int hotakaro;
    Map<Irany, Mezo> Szomszedok;

    public void horetegNovel(){}
    public  abstract  void elfogad(Jatekos j);
    public void  eltavolit(Jatekos j){
        System.out.println("eltavolit(Jatekos j)");
    }

    public Mezo  getSzomszed(Irany i){
        System.out.println("getSzomszed(" + i +")");
        return this.Szomszedok.get(i);
    }

    public void testhoCsokkent(){}
    public int getTeherbiras(){
        return teherbiras;
    }
}
