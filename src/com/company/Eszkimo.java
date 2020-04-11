package com.company;

public class Eszkimo extends Jatekos{
    //private int testho = 5;
    /**
     * Eszkimo iglut tud építeni
     */
    public Eszkimo(){
        super.setTestho(5);
        super.setVedett(false);
    }
    //public Eszkimo(){}
    public Eszkimo(Kontroller k){
        super(k, 5);
    } // amikor létrejön, 5 a testhője
    @Override
    public void epit(){
        Tab.tab++;
        for(int vari=0;vari<Tab.tab;vari++)System.out.print("\t");
        System.out.println("Eszkimo.epit()");
        this.getTartozkodasiMezo().setIglu(true);


        Tab.tab--;

    }
}
