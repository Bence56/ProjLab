package com.company;

public class Kotel extends Targy{
    /**
     * A függvény a kapott TargyVisitor visit(this) függvényét meghívja.
     * @param v a TargyVisitor, akinek a visit függvényét meghívja.
     * @return Amennyiben a paraméterül kapott TargyVisitor Kotel,
     * úgy a függvény visszatérési értéke true, ha bármi más TargyVisitor leszármazott, a visszatérési értéke false.
     */
    @Override
    public boolean accept(TargyVisitor v) {
        return v.visit(this);
    }

    public Kotel() {
    }

    /**
     * A tárgy felvétele során (amennyiben a kikapart tárgy kötél) a
     * this-t paraméterül adja a játékos KotelFelvesz(Kotel k) függvényének.
     * @param jatekos a kötelet kikaparó játékos
     */
    @Override
    public void felvesz(Jatekos jatekos){
        jatekos.kotelFelvesz(this);
        jatekos.munkaLevon(1);
    }

    /**
     * Beállítja a játékos állapotát aktívra
     * @param jatekos akinek az állapotát állítja
     */
    public void hasznal(Jatekos jatekos){
        jatekos.setAllapot(FulladasiAllapot.aktiv);
    }
}
