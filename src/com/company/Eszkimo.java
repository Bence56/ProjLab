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
        this.getTartozkodasiMezo().setIglu(true);
    }
}
