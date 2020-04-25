package com.company;

public class Alkatresz extends Targy{

    /**
     * A függvény a kapott TargyVisitor visit(this) függvényét meghívja.
     * @param v a TargyVisitor, akinek a visit függvényét meghívja.
     * @return Amennyiben a paraméterül kapott TargyVisitor AlkatreszVisitor,
     * úgy a függvény visszatérési értéke true, ha bármi más a visszatérési értéke false.
     */
    @Override
    public boolean accept(TargyVisitor v) {
        return v.visit(this);
    }

    /**
     * Alkatrészt felvesz a játékos.
     * @param jatekos aki felveszi.
     */
    @Override
    public void felvesz(Jatekos jatekos){
        jatekos.alkatreszFelvesz(this);
    }

    /**
     * Üresen implementáljuk
     * @param  j
     */
    @Override
    public void hasznal(Jatekos j){};

}

