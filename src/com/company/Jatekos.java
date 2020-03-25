package com.company;

import java.util.ArrayList;

public abstract class Jatekos {
    int munkakSzama;
    int testho;
    boolean vedett;
    Mezo tartozkodasiMezo;
    ArrayList<Alkatresz> alkatreszek;
    ArrayList<Targy> targyak;
    FulladasiAllpot allapot;


    public void lep(){}
    public void jatszik(){}
    public void kapar(){}
    public void lapatFelvesz(Lapat l){}
    public void kotelFelvesz(Kotel k){}
    public void elelemFelvesz(Elem e){}
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
}
