package com.company;

public class Kutato extends Jatekos {
    private int testho = 4;


    public Kutato(Kontroller k){
        super(k, 4);
    }
    /**
     *Lekérdezi a mező teherbírását i irányú szomszéd
     * @param i ebben az irányba lévő szomszéd mezőjéről kérdezi le a teherbírást
     */

    @Override
    public void vizsgal(Irany i){
            Tab.tab++;
            for(int var=0;var<Tab.tab;var++)System.out.print("\t");
            System.out.println("Kutato.Vizsgal(Irany i)");
            Mezo m=this.getTartozkodasiMezo().getSzomszed(i);
            m.getTeherbiras();
            Tab.tab--;
    }
}
