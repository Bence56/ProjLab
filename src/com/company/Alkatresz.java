package com.company;

public class Alkatresz extends Targy{

    @Override
    public boolean accept(TargyVisitor v) {
        return false;
    }

    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Alkatresz.felvesz()");
        jatekos.alkatreszFelvesz(this);
    }
    public void hasznal(Jatekos j){};

}

