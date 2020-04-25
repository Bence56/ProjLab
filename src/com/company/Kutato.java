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
    public int vizsgal(Irany i){
            Mezo m=this.getTartozkodasiMezo().getSzomszed(i);
            if (m!=null){
                munkaLevon(1);
                return m.getTeherbiras();
            }
            return -1;
    }
}
