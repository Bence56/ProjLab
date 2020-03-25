package com.company;

import java.util.Map;

public abstract class Mezo {
    int teherbiras;
    int hotakaro;
    Map<Mezo,Irany>Szomszedok;

    public void horetegNovel(){}
    public  abstract  void elfogad(Jatekos j);
    public void  eltavolit(Jatekos j){}
    public void  getSzomszed(Irany i){}
    public void testhoCsokkent(){}
    public int getTeherbiras(){
        return teherbiras;
    }
}
