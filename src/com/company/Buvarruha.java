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


    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Buvarruha.felvesz()");
        jatekos.buvarruhaFelvesz(this);
    }

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
