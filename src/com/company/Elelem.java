package com.company;

public class Elelem extends Targy{

    @Override
    public boolean accept(TargyVisitor v) {
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Elelem.accept(TargyVisitor v)");
        Tab.tab--;
        return v.visit(this);
    }

    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Elelem.felvesz()");
        jatekos.elelemFelvesz(this);
    }

    @Override
    public void hasznal(Jatekos j) {

    }
}
