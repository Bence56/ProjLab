package com.company;

public class Elelem extends Targy{
    public Elelem() {
    }

    //TODO Átírni a kommentet
    /**
     * Mivel az élelmet már a felvételkor "használjuk", ezért az accept függvényre nincs szükség, így false visszatérési értékkel implementáljuk.
     * @param v a paraméterül kapott visitor
     * @return
     */
    @Override
    public boolean accept(TargyVisitor v) { return v.visit(this);}

    /**
     * A tárgy felvétele során (amennyiben a kikapart tárgy élelem), a this-t paraméterül adja a játékos ElelemFelvesz(Elelem e) függvényének.
     * @param jatekos az élelmet kikaparó játékos
     */
    @Override
    public void felvesz(Jatekos jatekos){
        jatekos.elelemFelvesz(this);
    }
    /**
     * Üresen implementáljuk, mert az élelem már a felvétel során megnöveli a játékos testhőjét.
     * @param j
     */
    @Override
    public void hasznal(Jatekos j) { }
}
