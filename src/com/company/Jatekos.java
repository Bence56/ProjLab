package com.company;

import java.util.ArrayList;

public abstract class Jatekos {
    int munkakSzama;
    int testho;
    boolean vedett;
    Mezo tartozkodasiMezo;
    ArrayList<Alkatresz> alkatreszek;
    ArrayList<Targy> targyak;
    FulladasiAllapot allapot;


    /**
     * Átlépteti a játékost a szomszédos mezőre a megadott irányba.
     * @param i Az az irány amerre lépni szeretnénk.
     */
    public void lep(Irany i){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("lep(Irany i)");

        // Lekéri a szomszég mezőt
        Mezo szomszed = tartozkodasiMezo.getSzomszed(i);

        //eltávolítja a játékost
        this.tartozkodasiMezo.eltavolit(this);

        //Átadja magát a szomszédos játékosnak
        szomszed.elfogad(this);

        Tab.tab--;
    }

    public void jatszik(){}
    public void kapar(){}
    public void lapatFelvesz(Lapat l){}
    public void kotelFelvesz(Kotel k){}
    public void elelemFelvesz(Elelem e){}
    public void buvarruhaFelvesz(Buvarruha b){}
    public void alkatreszFelvesz(Alkatresz a){}
    public void kihuz(Irany i){}
    public void lapatol(){}
    public void vizbeEsik(){}
    public void Osszeszerel(){}
    public void lerak(){}
    public void munkaLevon(int i){}
    public void elsut(){}
    public void setMezo(Mezo m){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("setMezo(Mezo m)");
        this.tartozkodasiMezo = m;
        Tab.tab--;
    }
}
