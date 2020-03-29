package com.company;

import java.util.ArrayList;

public abstract class Jatekos {
    Mezo tartozkodasiMezo;
    private int munkakSzama = 4;
    private int testho;
    private boolean vedett;
    private ArrayList<Alkatresz> alkatreszek = new ArrayList<>();
    private ArrayList<Targy> targyak = new ArrayList<>();
    private FulladasiAllapot allapot;

    public int getTestho() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.getTestho()");
        Tab.tab--;
        return testho;

    }

    public void setTestho(int testho) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.setTestho(int testho)");
        this.testho = testho;
        Tab.tab--;
    }

    /**
     * Beállítja a védett attribútumot
     */
    public void setVedett(boolean b) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.setVedett(boolean b)");
        vedett = b;
        Tab.tab--;
    }

    /**
     * setter
     */
    public void setAllapot(FulladasiAllapot all) {
        allapot = all;
    }

    /**
     * Átlépteti a játékost a szomszédos mezőre a megadott irányba.
     *
     * @param i Az az irány amerre lépni szeretnénk.
     */
    public void lep(Irany i) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.lep(Irany i)");

        // Lekéri a szomszég mezőt
        Mezo szomszed = tartozkodasiMezo.getSzomszed(i);

        //eltávolítja a játékost
        this.tartozkodasiMezo.eltavolit(this);

        //Átadja magát a szomszédos játékosnak
        szomszed.elfogad(this);
        munkakSzama--;
        Tab.tab--;
    }

    public void jatszik() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.jatszik()");

        Tab.tab--;
        //ha elfogytak a munkák a következő játékos jön
        if(munkakSzama == 0)
            return;

    }

    /**
     * A saját mezőjén lévő tárgyat a  felveszi a játékos és a munkái számát egyel csökkenti.
     */
    public void kapar() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.kapar()");

        // Ezekből csak egy futhat le mert egy mezőn vagy alkatrész vagy tárgy van
        Targy targy = this.tartozkodasiMezo.getTargy();
        if (targy != null)
        {
            targy.felvesz(this);
        }

        Alkatresz alk = this.tartozkodasiMezo.getFagyottAlkatresz();
        if(alk != null){
                alk.felvesz(this);
        }

        this.munkakSzama--;


        Tab.tab--;
    }


    public void lapatFelvesz(Lapat l) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.lapatFelvesz()");
        targyak.add(l);
        Tab.tab--;
    }

    public void kotelFelvesz(Kotel k) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.kotelFelvesz()");
        targyak.add(k);
        Tab.tab--;
    }

    public void elelemFelvesz(Elelem e) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.elelemFelvesz()");
        testho++;
        Tab.tab--;
    }

    /**
     * Beteszi a kikapart búvárruhát a játékos tárgyai közé és védelmet nyújt
     *
     * @param b
     */
    public void buvarruhaFelvesz(Buvarruha b) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.buvarruhaFelvesz()");
        targyak.add(b);
        b.vedelem(this);

        Tab.tab--;
    }

    public void alkatreszFelvesz(Alkatresz a) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.alkatreszFelvesz()");
        alkatreszek.add(a);
        Tab.tab--;
    }


    /**
     * Kihúz egy másik játékost a saját táblájára
     *
     * @param i
     */

    public void kihuz(Irany i) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.kihuz()");

        KotelVisitor kv = new KotelVisitor();
        Mezo szomszed = this.tartozkodasiMezo.getSzomszed(i);
        for (Targy t : targyak) {
            if (t.accept(kv)) {     //ha a tárgy kötél akkor true
                Kotel k = new Kotel(); //csinalunk egy kotelet, hogy kihúzhassuk a játékost.
                for (Jatekos mentett : szomszed.alloJatekos) {  // a szomszéd mezőről minden játékost kihúz
                    k.huz(mentett);
                    szomszed.eltavolit(mentett);
                    this.tartozkodasiMezo.elfogad(mentett);
                }
            }
        }

        Tab.tab--;
    }

    /**
     * A játékos lapátol ha van lapátja
     */
    public void lapatol() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.lapatol()");
        LapatVisitor lv = new LapatVisitor();
        for (Targy t: targyak) {
            if(t.accept(lv)){
                Lapat lapat = new Lapat();
                lapat.as((Jegtabla)this.tartozkodasiMezo);
                return;
            }
        }
        ((Jegtabla)this.tartozkodasiMezo).horetegCsokkent();
        Tab.tab--;
    }

    /**
     * Beállítja a játékos allapot tagváltozójának értékét fuldoklikra,
     * valamint, ha a játékosnak nincs búvárruhája lecsökkenti a elvégezhető munkák számát (munkakSzama tagváltozó) nullára, hogy a következő játékos jöjjön
     */
    public void vizbeEsik() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.vízbeEsik()");
        if (!vedett) {
            allapot = FulladasiAllapot.fuldoklik;
            munkakSzama = 0;
        }
        //ha védett nem történik semmi
        Tab.tab--;
    }


    /**
     * Ellenőrzi, hogy az adott mezőn van-e mindhárom alkatrész,
     * és ha igen a játékos összeszereli és elsüti a jelzőrakétát
     */
    public void osszeszerel() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.osszeszerel()");

        ArrayList<Alkatresz> alkatreszek = this.tartozkodasiMezo.getAlkatreszek();
        if (alkatreszek.size() == 3) {
            this.elsut();
        }

        Tab.tab--;
    }

    /**
     * Lerak egy alkatrészt a mezőre hogy azt majd el lehessen sütni
     */
    public void lerak() {
        Tab.tab++;
        for (int vari = 0; vari < Tab.tab; vari++) System.out.print("\t");
        System.out.println("Jatekos.lerak()");

        if (alkatreszek.size() > 0) {
            Alkatresz alk = this.alkatreszek.remove(0);
            this.tartozkodasiMezo.alkatreszNovel(alk);
        }
        this.tartozkodasiMezo.alkatreszNovel(null);

        Tab.tab--;
    }

    public void munkaLevon(int i) {
        munkakSzama = munkakSzama-i;
    }

    /**
     * Ez a függvény az összeszerelés után automatikusan hívódik,
     * elsüti a rakétát és  véget vet a játéknak
     */
    public void elsut() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.elsut()");

        Tab.tab--;

    }

    public void setMezo(Mezo m) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.setMezo(Mezo m)");

        this.tartozkodasiMezo = m;

        Tab.tab--;
    }

    /**
     * Visszadaj a játékosnál lévő alkatrészeket
     *
     * @return
     */
    public ArrayList<Alkatresz> getAlkatreszek() {
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Jatekos.getAlkatreszek()");
        Tab.tab--;

        return this.alkatreszek;

    }
}
