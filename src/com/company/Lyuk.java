package com.company;

import java.util.ArrayList;

public class Lyuk extends Mezo {

    /**
     * Vízbe ejti a játékost
     *
     * @param j A játékos amit el kell fogadnia a lyuknak
     */
    @Override
    public void elfogad(Jatekos j) {
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Jegtabla.elfogad(Jatekos j)");


        j.setMezo(this);
        this.alloJatekos.add(j);
        utkozik(alloJegesmedve);
        j.vizbeEsik();

        Tab.tab--;
    }


    /**
     *Ez annak a modellezése amikor leteszik az  alkatrészeket a mezőre, hogy majd el lehessen sütni
     * és így megnyerjék a játékot. Hozzáadja a paraméterül kapott alkatrészt a kollekciójukhoz
     * @param a amit hozzá kell adni a kollekcióhoz
     */
    @Override
    public void alkatreszNovel(Alkatresz a){}


    @Override
    public Alkatresz getFagyottAlkatresz() {
        return null;
    }

    @Override
    public ArrayList<Alkatresz> getAlkatreszek() {
        return null;
    }
}
