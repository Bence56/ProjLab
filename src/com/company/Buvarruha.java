package com.company;

public class Buvarruha extends Targy{
    @Override
    public boolean accept(TargyVisitor v) {
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Buvarruha.accept()");
        boolean x=v.visit(this);
        Tab.tab--;
        return x;
    }
    public void vedelem(Jatekos j){}

}
