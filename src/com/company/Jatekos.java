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


    public void lep(){}
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
    public void setMezo(Mezo m){}
    public void CAUSEIMHEARTLESS(){
        System.out.println("And im back to my ways caouse im heartless");
    }
}
