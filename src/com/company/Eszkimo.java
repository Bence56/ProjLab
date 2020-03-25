package com.company;

public class Eszkimo extends Jatekos{
    /**
     * Eszkimo iglut tud építeni
     */
    public void epit(){
        Tab.tab++;
        for(int vari=0;vari<Tab.tab;vari++)System.out.print("\t");
        System.out.println("Eszkimo.epit()");
        this.tartozkodasiMezo.setIglu(true);


        Tab.tab--;

    }
}
