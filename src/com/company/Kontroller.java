package com.company;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Kontroller { // konstruktorban kapja meg a játékosokat. Akkor tud a kontroller osztályra referenciát tartalmazni a játékos osztály
    protected ArrayList<Mezo> palya = new ArrayList<>();
    boolean aktiv = true;
    boolean nyert = false;
    private ArrayList<Jatekos> jatekosok = new ArrayList<>();
    private Jegesmedve jegesmedve = new Jegesmedve();


    public Mezo getPalya(int i){
        return palya.get(i);
    }
    /**
     * A játék menete, minden játékos köre előtt detektálás van, utána pedig vihar
     */
    public void jatek() {
        while (aktiv) {
            for (Jatekos j : jatekosok) {
                detektal();
                j.jatszik();
                vihar();
            }
            jegesmedve.jatszik();
        }
    }

    public void addJatekos(Jatekos j) {
        jatekosok.add(j);
    }

    public void addMezo(Mezo mezo){
        this.palya.add(mezo);
    }


    /**
     * Végig megy az összes mezőn és megnöveli a hóréteget egy random számmal (negatív lineáris eloszlással)
     * 0 és a maxHoreteg kozott Között, tehát jóval kisebb a valószínűsége nagy hónak mint a semminek.
     * Lecsökkenti a mezőkön álló játékosok testhőjét, ha nincs iglu a mezőn vagy a sátor már
     */
    public void vihar() {
        int maxHoreteg = 5;

        for (Mezo mezo : palya) {
            //Megnöveljük a hóréteget egy random számmal (negatív lineáris eloszlással) 0 és a maxHoreteg kozott Között
            while (true) {
                double r1 = Math.random();
                double r2 = Math.random();
                if (r2 > r1) {
                    mezo.horetegNovel((int) (r1 * (maxHoreteg + 1)));
                    break;
                }
            }

            int i = mezo.getSatorMiotaVan();
            // ha nincs sátor ill iglu, csökken a testhő
            if (!(mezo.isIglu()) || i == 0)
                for (Jatekos j : mezo.getAlloJatekos()) {
                    j.setTestho(j.getTestho() - 1);
                }
        }
    }

    /**
     * vizsgaljuk a játékosok állapotát, nem e hűlt ki, nem e fulladt meg
     */
    public void detektal() {

        int alkatreszSzam = 0;

        for (Jatekos j : jatekosok) {
            int ho = j.getTestho();

            if (ho == 0) {
                j.setAllapot(FulladasiAllapot.halott);
                jatekVege(false);
            }
        }

        for (Jatekos j : jatekosok) {
            ArrayList<Alkatresz> alkatreszek = j.getAlkatreszek();
            alkatreszSzam += alkatreszek.size();
        }
        for (Mezo m : palya) {
            ArrayList<Alkatresz> alkatreszek = m.getAlkatreszek();
            if (alkatreszek != null) {
                alkatreszSzam += alkatreszek.size();
            }
            if(m.getFagyottAlkatresz()!= null){
                alkatreszSzam ++;
            }
        }

        if (alkatreszSzam <= 3) {
            jatekVege(false);
        }

        for (Mezo m : palya) {
            int satorMiotaVan = m.getSatorMiotaVan();
            if (satorMiotaVan == ((jatekosok.size()))) { // ha lement egy kör eltűnteti a sátrat
                m.satratNullaz();
            } else {
                if (m.getSatorMiotaVan() > 0) // ha van a mezőn sátor
                    m.satorIdoNovel();  // 1-gyel nő a felállítástúl eltelt idő
            }
        }
    }

    /**
     * Véget vet a játéknak
     * @param nyer true érték esetén nyeréssel, false esetén vesztéssel ér véget a játék
     */
    public void jatekVege(boolean nyer) {
        this.aktiv = false;

        if (nyer) {
            System.out.println("NYERTEL");
            nyert = true;
        } else
            System.out.println("GAME OVER");
    }

    /**
     * getter
     * @return a játékban részt vevő játékosok listája
     */
    public ArrayList<Jatekos> getJatekosok() {
        return jatekosok;
    }

    /**
     * setter
     * @param jegesmedve a játék jegesmedvéje
     */
    public void setJegesmedve(Jegesmedve jegesmedve) {
        this.jegesmedve = jegesmedve;
    }
}

