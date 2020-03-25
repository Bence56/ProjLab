package com.company;

import java.util.ArrayList;

public class Jegtabla extends Mezo{
    boolean iglu=false;
    ArrayList<Alkatresz> alkatreszek;
    Alkatresz fagyottAlkatresz;

    public void horetegCsokkent(){}

    /**
     * Elfogadja a  játékost, úgy hogy beállítja a mezőjének saját magát.
     * @param j A játékos amit el kell fogadni
     */
    @Override
    public void elfogad(Jatekos j){
        j.setMezo(this);
    }
    public void getTargy(){}

    public void setIglu(boolean iglu) {}
    public void alkatreszNovel(Alkatresz a){}
    public void getAlkatresz(){}

}
