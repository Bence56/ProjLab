package com.company;

import java.util.ArrayList;

public class Kontroller {
    ArrayList<Mezo>palya;
    ArrayList<Jatekos> jatekosok;

    public void jatek(){}
    public void vihar() {
        System.out.println("\tKontroller.vihar()");
        for (Mezo item:palya) {
            item.horetegNovel();
        }
    }

    public void detektel(){
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
            alkatreszSzam += alkatreszek.size();
        }

        if(alkatreszSzam <= 3)
        {
            jatekVege();
        }

        Tab.tab--;
    }


    public void jatekVege(){}
}
