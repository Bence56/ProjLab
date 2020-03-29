package com.company;

import java.util.ArrayList;

public class Kontroller {
    ArrayList<Mezo>palya = new ArrayList<>();
    ArrayList<Jatekos> jatekosok = new ArrayList<>();

    boolean aktiv = true;

    /**
     * A játék menete, minden játékos köre előtt detektálás van, utána pedig vihar
     */
    public void jatek(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kontroller.jatek()");
        for(Jatekos j : jatekosok){
            detektal();
            j.jatszik();
            vihar();
        }
        Tab.tab--;
    }


    public void vihar() {
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kontroller.vihar()");
        for (Mezo item:palya) {
            item.horetegNovel();
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
                jatekVege();
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
            jatekVege();
        }

        Tab.tab--;
    }


    public void jatekVege(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kontroller.jatekVege()");

        this.aktiv = false;

        Tab.tab--;
    }
}
