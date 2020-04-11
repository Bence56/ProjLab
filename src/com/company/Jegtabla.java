package com.company;

import java.util.ArrayList;

public class Jegtabla extends Mezo{

    private ArrayList<Alkatresz> alkatreszek;
    private Alkatresz fagyottAlkatresz;
    private Targy fagyotttargy;
    private int satorMiotaVan;
    private boolean iglu;  //nem jo a mezoben is van iglu attributum.

   // Jegtabla(){}
    // A létrehozáshoz majd kell hogy tárgyakat lehessen tenni a jégtáblára
   // Jegtabla(Targy targy){
     //   this.fagyotttargy = targy;
   // }
    Jegtabla(int teherbiras, int hotakaro, Targy targy){
        super(teherbiras,hotakaro);
        this.fagyotttargy = targy;
        this.satorMiotaVan=0;
        this.iglu=false;
    }


    @Override
    public void setFagyottTargy(Targy t){
        fagyotttargy = t;
    }

    @Override
    public void satorIdoNovel(){
        satorMiotaVan+=1;
    }
    @Override
    public void satratNullaz() {
        satorMiotaVan=0;
    }

    @Override
    public int getSatorMiotaVan(){
        return satorMiotaVan;
    }
    /**
     * Elfogadja a  játékost, úgy hogy beállítja a mezőjének saját magát, illetve beteszi az állójátékosok közé
     * @param j A játékos amit el kell fogadni
     */
    @Override
    public void elfogad(Jatekos j){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Jegtabla.elfogad(Jatekos j)");

        j.setMezo(this);
        this.getAlloJatekos().add(j);
        if (getAlloJegesmedve()!=null)
            utkozik(getAlloJegesmedve());
         //   j.meghal();
        if (getTeherbiras()<getAlloJatekos().size()) {
            for (Jatekos j2 : getAlloJatekos())
                j2.vizbeEsik();
        }
        Tab.tab--;
    }

    /**
     *Visszaadja a befagyott tárgyat amit tárol/ha nem tárol null-t
     * @return visszaadja a befagyott targyat
     */
    @Override
    public Targy getTargy(){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Jegtabla.getTargy()");
        Tab.tab--;
        return this.fagyotttargy;
    }


    /**
     *Beállítja a tábla iglu tulajdonságát
     * @param iglu annak az értéke hogy a tábla iglu típusú lesz vagy nem
     */
    @Override
    public void setIglu(boolean iglu) {
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Jegtabla.setIglu(boolean iglu)");

        this.iglu=iglu;

        Tab.tab--;
    }

    @Override
    public boolean isIglu() {
        return iglu;

    }

    /**
     *Ez annak a modellezése amikor leteszik az  alkatrészeket a mezőre, hogy majd el lehessen sütni
     * és így megnyerjék a játékot. Hozzáadja a paraméterül kapott alkatrészt a kollekciójukhoz
     * @param a amit hozzá kell adni a kollekcióhoz
     */
    @Override
    public void alkatreszNovel(Alkatresz a){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Jegtabla.alkatreszNovel(Alkatresz a)");
        this.alkatreszek.add(a);


        Tab.tab--;
    }

    /**
     * Teszteléshez kell. Berakja az alkatrészeket a mező alkatrész tömbjébe.
     */
    @Override
    public void setAlkatreszek(Alkatresz a1, Alkatresz a2, Alkatresz a3){
        alkatreszek.add(0,a1);
        alkatreszek.add(1,a2);
        alkatreszek.add(2,a3);
    }


    /**
     * Visszadja a jégtáblába fagyott alkatrészeket.
     */
    @Override
    public Alkatresz getFagyottAlkatresz(){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Jegtabla.getFagyottAlkatresz()");
        Tab.tab--;

        return this.fagyottAlkatresz;
    }

    /**
     * A jégtáblára letett alkatrészeket adja oda amikor a játékos meghívja az alkatreszFelvesz fv-t
     * @return
     */
    @Override
    public ArrayList<Alkatresz> getAlkatreszek(){
        Tab.tab++;
        for(int i=0;i<Tab.tab;i++)System.out.print("\t");
        System.out.println("Jegtabla.getFagyottAlkatresz()");
        Tab.tab--;

        return this.alkatreszek;
    }

}
