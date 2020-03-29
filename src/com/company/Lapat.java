package com.company;

public class Lapat extends Targy{
    @Override
    public boolean accept(TargyVisitor v) {
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Lapat.accept(TargyVisitor v)");
        Tab.tab--;
        return v.visit(this);
    }
    public void as(Jegtabla tabla){}

    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Lapat.felvesz()");
        jatekos.lapatFelvesz(this);
    }
}
