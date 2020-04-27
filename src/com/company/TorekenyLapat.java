package com.company;

public class TorekenyLapat extends Lapat {
    private int hasznalatSzama;

    /**
     * A törékeny lapát csak akkor takarít havat, ha kevesebb mint háromszor használták. A törékeny lapáttal való ásás két réteg havat takarít el, amennyiben 2 egység, vagy annál több hó van a mezőn, 1-et, ha csak 1, és semennyit, ha nincs.
     *
     * @param j a havat lapátoló játékos
     */
    public void hasznal(Jatekos j) {
        if (hasznalatSzama < 3) {
            int horeteg = j.getTartozkodasiMezo().getHotakaro();
            if (horeteg >= 2) {
                j.getTartozkodasiMezo().horetegCsokkent();
                j.getTartozkodasiMezo().horetegCsokkent();
                j.munkaLevon(1);
            } else if (horeteg == 1) {
                j.getTartozkodasiMezo().horetegCsokkent();
                j.munkaLevon(1);
            }
            hasznalatSzama++;
        }
    }
}
