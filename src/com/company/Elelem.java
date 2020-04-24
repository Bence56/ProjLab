package com.company;

public class Elelem extends Targy{
    /**
     * Mivel az élelmet már a felvételkor "használjuk", ezért az accept függvényre nincs szükség, így false visszatérési értékkel implementáljuk.
     * @param t a paraméterül kapott visitor
     * @return
     */
    @Override
    public boolean accept(TargyVisitor v) { return false; }

    /**
     * A tárgy felvétele során (amennyiben a kikapart tárgy élelem), a this-t paraméterül adja a játékos ElelemFelvesz(Elelem e) függvényének.
     * @param jatekos az élelmet kikaparó játékos
     */
    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Elelem.felvesz()");
        jatekos.elelemFelvesz(this);
    }
    /**
     * Üresen implementáljuk, mert az élelmet már a felvételkor "felveszi" a játékos.
     * @param j
     */
    @Override
    public void hasznal(Jatekos j) {

    }
}
