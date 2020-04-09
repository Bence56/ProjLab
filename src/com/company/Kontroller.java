package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Kontroller { // konstruktorban kapja meg a játékosokat. Akkor tud a kontroller osztályra referenciát tartalmazni a játékos osztály
    protected ArrayList<Mezo>palya = new ArrayList<>();
    private ArrayList<Jatekos> jatekosok =new ArrayList<>();
    private Jegesmedve jegesmedve=new Jegesmedve();

    boolean aktiv = true;

    /**
     * A játék menete, minden játékos köre előtt detektálás van, utána pedig vihar
     */
    public void jatek(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kontroller.jatek()");
        while (aktiv) {
            for (Jatekos j : jatekosok) {
                detektal();
                j.jatszik();
                vihar();
            }
            jegesmedve.jatszik();
        }

        Tab.tab--;
    }


    public void vihar() {
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kontroller.vihar()");
        for (Mezo item:palya) {
            item.horetegNovel();

            Integer i=item.getSatorMiotaVan();
            if (!(item.isIglu()) || i.equals(0)) // ha nincs sátor ill iglu, csökken a testhő
                for (Jatekos j: item.getAlloJatekos()){
                    j.setTestho(j.getTestho()-1);
                }
        }


        Tab.tab--;
    }

    /**
     * vizsgaljuk a játékosok állapotát, nem e hűlt ki, nem e fulladt meg
     */
    public void detektal(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kontroller.detektal()");

        int alkatreszSzam = 0;

        for (Jatekos j: jatekosok) {
            int ho = j.getTestho();

            if( ho == 0){
                j.setAllapot(FulladasiAllapot.halott);
                jatekVege(false);
            }
        }

        for (Jatekos j: jatekosok) {
            ArrayList<Alkatresz> alkatreszek =  j.getAlkatreszek();
            alkatreszSzam += alkatreszek.size();
        }
        for (Mezo m : palya) {
            ArrayList<Alkatresz> alkatreszek =  m.getAlkatreszek();
            if(alkatreszek != null){
                alkatreszSzam += alkatreszek.size();
            }

        }

        if(alkatreszSzam <= 3)
        {
            jatekVege(false);
        }

        for (Mezo m: palya) {
            Integer satorMiotaVan=m.getSatorMiotaVan();
            if (satorMiotaVan.equals((jatekosok.size()))) { // ha lement egy kör eltűnteti a sátrat
                m.satratNullaz();
            } else {
                if (m.getSatorMiotaVan()>0) // ha van a mezőn sátor
                    m.satorIdoNovel();  // 1-gyel nő a felállítástúl eltelt idő
            }
        }

        Tab.tab--;
    }


    public void jatekVege(boolean nyer){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kontroller.jatekVege()");

        this.aktiv = false;

        if (nyer)
            System.out.println("NYERTEL");
        else
            System.out.println("GAME OVER");
        Tab.tab--;
    }

    public ArrayList<Jatekos> getJatekosok() {
        return jatekosok;
    }


    public void setJegesmedve(Jegesmedve jegesmedve) {
        this.jegesmedve = jegesmedve;
    }
}

