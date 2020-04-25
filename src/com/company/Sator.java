package com.company;

public class Sator extends Targy{

    public Sator() {
    }

    @Override
    public boolean accept(TargyVisitor v) {
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Lapat.accept(TargyVisitor v)");
        Tab.tab--;
        return v.visit(this);
    }

    @Override
    public void felvesz(Jatekos j) {
        j.satorFelvesz(this);
    }


    /**
     * A paraméterül kapott játékos tartózkodási mezőjére épít egy sátrat.
     * @param j ez a játékos állít sátrat
     */
    @Override
    public void hasznal(Jatekos j){
        j.getTartozkodasiMezo().satorIdoNovel();
    }


}
