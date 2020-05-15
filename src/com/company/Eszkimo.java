package com.company;

public class Eszkimo extends Jatekos implements Cloneable{

    public Eszkimo(){
        super.setTestho(5);
        super.setVedett(false);
    }

    public Eszkimo(Kontroller k, int ID){
        super(k, 5, "E" + ID);
    } // amikor létrejön, 5 a testhője

    /**
     * Eszkimo iglut tud építeni
     */
    @Override
    public void epit(){
        System.out.println("\t\tEszkimó épít");
        this.getTartozkodasiMezo().setIglu(true);
        if (this.getTartozkodasiMezo().getTeherbiras() !=0)
            this.munkaLevon();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
