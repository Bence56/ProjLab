package com.company;

public class Kutato extends Jatekos {
    private int testho = 4;


    public Kutato(Kontroller k){
        super(k, 4);
    }


    /**
     *Lekérdezi a mező teherbírását i irányú szomszéd
     * @return mennyi az olyan irányú szomszédjának a teherbírása
     */
    @Override
    public int vizsgal(Irany i){
            Mezo m=this.getTartozkodasiMezo().getSzomszed(i);
            return m.getTeherbiras();
    }
}
