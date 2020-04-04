package com.company;

public class Sator extends Targy{

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

    @Override
    public void hasznal(Jatekos j){
        j.tartozkodasiMezo.satorIdoNovel();
    }


}
