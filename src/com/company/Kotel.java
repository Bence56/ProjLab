package com.company;

public class Kotel extends Targy{
    @Override
    public boolean accept(TargyVisitor v) {
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kotel.accept(TargyVisitor v)");

        Tab.tab--;
        return v.visit(this);
    }

    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("" +
                "kotel.felvesz()");
        jatekos.kotelFelvesz(this);
    }

    /**
     * Beállítja a játékos állapotát aktívra
     * @param jatekos akinek az állapotát állítja
     */
    public void hasznal(Jatekos jatekos){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Kotel.huz(Jatekos jatekos)");

        jatekos.setAllapot(FulladasiAllapot.aktiv);

        Tab.tab--;
    }
}
