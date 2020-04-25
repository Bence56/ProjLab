package com.company;

public class Eszkimo extends Jatekos{

    public Eszkimo(){
        super.setTestho(5);
        super.setVedett(false);
    }

    public Eszkimo(Kontroller k){
        super(k, 5);
    } // amikor létrejön, 5 a testhője

    /**
     * Eszkimo iglut tud építeni
     */
    @Override
    public void epit(){
        this.getTartozkodasiMezo().setIglu(true);
        if (this.getTartozkodasiMezo().getTeherbiras() !=0)
            this.munkaLevon(1);
    }
}
