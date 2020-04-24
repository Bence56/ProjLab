package com.company;

import java.util.ArrayList;

public abstract class Jatekos extends Mozgathato {
    private Kontroller kontroller;
    private int munkakSzama = 4;
    private int testho;
    private boolean vedett;
    private ArrayList<Alkatresz> alkatreszek = new ArrayList<>();


    private ArrayList<Targy> targyak = new ArrayList<>();
    private FulladasiAllapot allapot = FulladasiAllapot.aktiv;

    Jatekos() {
        this.testho = testho;
    }

    Jatekos(Kontroller k, int testho) {
        this.kontroller = k;
        this.testho = 5;
    }

    /**
     * Lekérdezi a tárgyak listát
     */
    public ArrayList<Targy> getTargyak() {
        return targyak;
    }

    /**
     * Lekérdezi a testhő attribútumot
     */
    public int getTestho() {
        return testho;
    }


    /**
     * Testhő beállítása
     *
     * @param testho A testhőérték, amire be szeretnénk állítani
     */
    public void setTestho(int testho) {
        this.testho = testho;
    }

    /**
     * Beállítja a védett attribútumot
     */
    public void setVedett(boolean b) {
        vedett = b;
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
        // Lekéri a szomszéd mezőt
        Mezo aktualis = getTartozkodasiMezo();
        Mezo szomszed = aktualis.getSzomszed(i);
        if(szomszed!=null) {
            //eltávolítja a játékost
            aktualis.eltavolit(this);
            //Átadja magát a szomszédos játékosnak
            szomszed.elfogad(this);
            munkakSzama--;
        }
    }

    public void jatszik() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.jatszik()");

        Tab.tab--;
        //ha elfogytak a munkák a következő játékos jön
        if (munkakSzama == 0)
            return;

    }


    public void meghal() {
        kontroller.jatekVege(false);

    }

    /**
     * A saját mezőjén lévő tárgyat a játékos felveszi, a mezőn a felvett tárgy értékét nullra állítja, és a munkái számát egyel csökkenti.
     */
    public void kapar() {
        // Ezekből csak egy futhat le mert egy mezőn vagy alkatrész vagy tárgy van
        Mezo m = this.getTartozkodasiMezo();
        Targy targy = m.getTargy();
        if (targy != null) {
            targy.felvesz(this);
            m.setFagyottTargy(null);
        }

        this.munkakSzama--;
    }

    /**
     * Hozzáadja a tárgyak listájához a felvevendő lapátot.
     *
     * @param l A lapát amit fel szeretnénk venni.
     */
    public void lapatFelvesz(Lapat l) {
        targyak.add(l);
    }


    /**
     * Hozzáadja a tárgyak listájához a felvevendő kötelet.
     *
     * @param k A kötél amit fel szeretnénk venni.
     */
    public void kotelFelvesz(Kotel k) {
        targyak.add(k);
    }


    /**
     * Megnöveli a testhőt 1-el.
     *
     * @param e Az élelem, amit fel szeretnénk venni.
     */
    public void elelemFelvesz(Elelem e) {
        testho++;
    }

    /**
     * Beteszi a kikapart búvárruhát a játékos tárgyai közé és védelmet nyújt
     *
     * @param b A felvevendő búvárruha
     */
    public void buvarruhaFelvesz(Buvarruha b) {
        targyak.add(b);
        b.vedelem(this);
    }


    /**
     * Hozzáadja a tárgyak listájához a felvevendő sátrat.
     *
     * @param s A sátor amit fel szeretnénk venni.
     */
    public void satorFelvesz(Sator s) {
        targyak.add(s);
    }


    /**
     * Hozzáadja az alkatrészek listájához a felvevendő alkatrészt.
     *
     * @param a Az alkatrész, amit fel szeretnénk venni.
     */
    public void alkatreszFelvesz(Alkatresz a) {
        alkatreszek.add(a);
    }


    /**
     * Kihúz egy másik játékost a saját táblájára
     *
     * @param i Az irány, amerre lévő játékost szeretnénk kihúzni.
     */
    public void kihuz(Irany i) {
        KotelVisitor kv = new KotelVisitor();
        Mezo szomszed = this.getTartozkodasiMezo().getSzomszed(i);
        for (Targy t : targyak) {
            if (t.accept(kv)) {     //ha a tárgy kötél akkor true
                for (Jatekos mentett : szomszed.getAlloJatekos()) {  // a szomszéd mezőről minden játékost kihúz
                    t.hasznal(mentett);
                    szomszed.eltavolit(mentett);
                    this.getTartozkodasiMezo().elfogad(mentett);
                    // mentett.setTartozkodasiMezo(this.getTartozkodasiMezo());

                }
            }
        }
    }

    /**
     * A játékos lapátol, ha van lapátja, a lapáttal, ha nincs, akkor lapát nélkül, de úgy csak 1 hóréteget tud eltávolítani.
     */
    public void lapatol() {
        LapatVisitor lv = new LapatVisitor();
        boolean van_lapat = false;
            for (Targy t : targyak) {
                if (t.accept(lv)) {
                    t.hasznal(this);
                    van_lapat = true;
                    return;
                }
            }

        if (!van_lapat && this.getTartozkodasiMezo().getHotakaro() >= 1)
            this.getTartozkodasiMezo().horetegCsokkent();
    }

    /**
     * A játékos sátrat épít, ha van neki sátra.
     *
     */
    public void satratEpit() {
        SatorVisitor sv = new SatorVisitor();
        for (Targy t : targyak) {
            if (t.accept(sv)) {
                t.hasznal(this);
                return;
            }
        }
    }

    /**
     * Beállítja a játékos allapot tagváltozójának értékét fuldoklikra,
     * valamint, ha a játékosnak nincs búvárruhája lecsökkenti a elvégezhető munkák számát (munkakSzama tagváltozó) nullára, hogy a következő játékos jöjjön
     */
    public void vizbeEsik() {
        //a beszakadt mezőn lévő tárgyak eltűnnek
        Mezo m = this.getTartozkodasiMezo();
        m.setFagyottTargy(null);
        m.setFagyottAlk(null);
        if (!vedett) {
            allapot = FulladasiAllapot.fuldoklik;
            munkakSzama = 0;
        }
        //ha védett nem történik semmi
    }


    /**
     * Ellenőrzi, hogy az adott mezőn van-e mindhárom alkatrész,
     * és ha igen a játékos összeszereli és elsüti a jelzőrakétát
     */
    public void osszeszerel() {
        ArrayList<Alkatresz> alkatreszek = this.getTartozkodasiMezo().getAlkatreszek();
        if (alkatreszek != null) {
            if (alkatreszek.size() == 3) {
                this.elsut();
            }
        }
    }


    /**
     * Lerak egy alkatrészt a mezőre hogy azt majd el lehessen sütni
     */
    public void lerak() {
            if (alkatreszek.size() > 0) {
            Alkatresz alk = this.alkatreszek.remove(0);
            this.getTartozkodasiMezo().alkatreszNovel(alk);
        }
        this.getTartozkodasiMezo().alkatreszNovel(null);
    }

    /**
     * Csökkenti a hátramaradó munkát számát.
     *
     * @param i Ennyivel csökkentjük a munkák számát.
     */
    public void munkaLevon(int i) {
        munkakSzama = munkakSzama - i;
    }

    /**
     * Ez a függvény az összeszerelés után automatikusan hívódik,
     * elsüti a rakétát és  véget vet a játéknak
     */
    public void elsut() {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.elsut()");
        kontroller.jatekVege(true);

        Tab.tab--;

    }


    /**
     * Visszadaj a játékosnál lévő alkatrészeket
     *
     * @return
     */
    public ArrayList<Alkatresz> getAlkatreszek() {
        return this.alkatreszek;
    }

    /**
     * Az eszkimó valósítja meg
     */
    public void epit() {
    }



    /**
     * A kutató valósíja meg
     *
     * @param i Az adott irányban vizsgálja a mezőt
     * @return
     */
    public int vizsgal(Irany i) {
        return 0;
    }


}
