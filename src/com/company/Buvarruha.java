package com.company;

public class Buvarruha extends Targy{
    public Buvarruha() {
    }

    /**
     * Mivel a búvárruhát már a felvételkor "használjuk", ezért az accept függvényre nincs szükség, így false visszatérési értékkel implementáljuk.
     * @param t a paraméterül kapott visitor
     * @return
     */
    @Override
    public boolean accept(TargyVisitor v) { return false; }

    /**
     * A tárgy felvétele során (amennyiben a kikapart tárgy búvárruha) a this-t paraméterül adja a játékos BuvarruhaFelvesz(Buvarruha b) függvényének.
     * @param jatekos a búvárruhát kikaparó játékos
     */
    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Buvarruha.felvesz()");
        jatekos.buvarruhaFelvesz(this);
    }

    /**
     * Üresen implementáljuk, mert a búvárruhát már a felvételkor "felveszi" a játékos.
     * @param j
     */
    @Override
    public void hasznal(Jatekos j) { }

    /**
     * Védettséget kap a játékos a búvárruhától
     * @param j a játékos aki védett lesz
     */
    public void vedelem(Jatekos j){
        Tab.tab++;
        for(int i=0; i<Tab.tab; i++)System.out.print("\t");
        System.out.println("Buvarruha.vedelem(Jatekos j)");
        j.setVedett(true);
        Tab.tab--;
    }

}
