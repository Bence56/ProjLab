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

    /**
     * A lapáttal való ásás két réteg havat takarít el
     * @param j
     */
    public void hasznal(Jatekos j){
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Lapat.as()");
        j.getTartozkodasiMezo().horetegCsokkent();
        j.getTartozkodasiMezo().horetegCsokkent();
        Tab.tab--;
    }

    /**
     * hozzáaadja a játékos eszközeihez magát
     * @param jatekos ennek a játékosank
     */
    @Override
    public void felvesz(Jatekos jatekos){
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Lapat.felvesz()");
        jatekos.lapatFelvesz(this);
        Tab.tab--;
    }
}
