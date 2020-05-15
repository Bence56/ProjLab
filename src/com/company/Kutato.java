package com.company;

public class Kutato extends Jatekos implements Cloneable{
    private int testho = 4;


    public Kutato(Kontroller k, int ID){
        super(k, 4, "K" + ID);
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
                m.setVizsgalt(true);
                return m.getTeherbiras();
            }
            return -1;
    }
    @Override
    public void epit(){
        System.out.println("\t\tKutato épít");
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
