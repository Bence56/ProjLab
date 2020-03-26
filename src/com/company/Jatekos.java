package com.company;

import java.util.ArrayList;

public abstract class Jatekos {
    int munkakSzama;
    int testho;
    boolean vedett;
    Mezo tartozkodasiMezo;
    ArrayList<Alkatresz> alkatreszek =new ArrayList<>();
    ArrayList<Targy> targyak =new ArrayList<>();
    FulladasiAllapot allapot;


    /**
     * Átlépteti a játékost a szomszédos mezőre a megadott irányba.
     * @param i Az az irány amerre lépni szeretnénk.
     */
    public void lep(Irany i){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Jatekos.lep(Irany i)");

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

    /**
     * Beteszi a kikapart búvárruhát a játékos tárgyai közé
     * @param b
     */
    public void buvarruhaFelvesz(Buvarruha b){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Jatekos.buvarruhaFelvesz()");
        targyak.add(b);
    }
    public void alkatreszFelvesz(Alkatresz a){}
    public void kihuz(Irany i){}
    public void lapatol(){}

    /**
     * Az adott jégtáblán álló összes játékos vízbeEsik() függvényét meghívja.
     */
    public void mindenkiVizbeEsik(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Jatekos.mindenkiVizbeEsik()");
        this.vizbeEsik();

        Tab.tab--;
        }

    /**
     * Beállítja a játékos allapot tagváltozójának értékét fuldoklikra,
     * valamint lecsökkenti a elvégezhető munkák számát (munkakSzama tagváltozó) nullára, hogy a következő játékos jöjjön
     */
    public void vizbeEsik(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Jatekos.vízbeEsik()");
        BuvarruhaVisitor br=new BuvarruhaVisitor();
        Targy b=this.targyak.get(0);
        b.accept(br);

        Tab.tab--;
    }

    /**
     * Ellenőrzi, hogy az adott mezőn van-e mindhárom alkatrész,
     * és ha igen a játékos összeszereli és elsüti a jelzőrakétát
     */
    public void osszeszerel(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Jatekos.osszeszerel()");

        ArrayList<Alkatresz> alkatreszek= this.tartozkodasiMezo.getAlkatreszek();
        if(alkatreszek.size() == 3){
            this.elsut();
        }
        
        Tab.tab--;
    }

    /**
     * Lerak egy alkatrészt a mezőre hogy azt majd el lehessen sütni
     */
    public void lerak(){
        Tab.tab++;
        for(int vari=0;vari<Tab.tab;vari++)System.out.print("\t");
        System.out.println("Jatekos.lerak()");

        if(alkatreszek.size()>0){
        Alkatresz alk=this.alkatreszek.remove(0);
        this.tartozkodasiMezo.alkatreszNovel(alk);
        }
        this.tartozkodasiMezo.alkatreszNovel(null);

        Tab.tab--;
    }
    public void munkaLevon(int i){}

    /**
     * Ez a függvény az összeszerelés után automatikusan hívódik,
     * elsüti a rakétát és  véget vet a játéknak
     */
    public void elsut(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Jatekos.elsut()");
        Tab.tab--;

    }

    public void setMezo(Mezo m){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("Jatekos.setMezo(Mezo m)");

        this.tartozkodasiMezo = m;

        Tab.tab--;
    }
}
