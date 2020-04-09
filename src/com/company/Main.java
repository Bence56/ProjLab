package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Main m = new Main();
        Kontroller kont = new Kontroller();
        Mezo aktualisTabla = new Jegtabla(4,4, null);
        Jatekos e = new Eszkimo(kont);
        e.setTartozkodasiMezo(aktualisTabla);
        Jatekos k = new Kutato(kont);
        k.setTartozkodasiMezo(aktualisTabla);
        kont.getJatekosok().add(e);
        kont.getJatekosok().add(k);

        int i = 1;
        Scanner sc = new Scanner(System.in);
        while (i > 0) {
            System.out.println("Adj meg egy számot: (ha ki akarsz lépni a 0-t)");
            i = sc.nextInt();
            switch (i) {
                case 1:
                    m.szcenario1(e, aktualisTabla);
                    break;
                case 2:
                    m.szcenario2(e, aktualisTabla);
                    break;
                case 3:
                    m.szcenario3(kont, e, aktualisTabla);
                    break;
                case 4:
                    m.szcenario4(e);
                    break;
                case 5:
                    m.szcenario5(k);
                    break;
                case 6:
                    m.szcenario6();
                    break;
                case 7:
                    m.szcenario7(e);
                    break;
                case 8:
                    m.szcenario8(e);
                    break;
                case 9:
                    m.szcenario9();
                    break;
                case 10:
                    m.szcenario10();
                    break;
                case 11:
                    m.szcenario11();
                    break;
                case 12:
                    m.szcenario12();
                    break;
                case 13:
                    m.szcenario12();
                    break;
                case 14:{
                    Tester tester = new Tester();
                    tester.test();
                }
            }
        }
        sc.close();
    }

    public void szcenario1(Jatekos j, Mezo aktualisTabla) {
        System.out.println("A JÁTÉKOS JÉGTÁBLÁRA LÉP");

        Mezo szomszed = new Jegtabla(4, 4, null);
        j.getTartozkodasiMezo().szomszedok.put(Irany.Jobb, szomszed);
        j.lep(Irany.Jobb);

    }

    public void szcenario2(Jatekos j, Mezo aktualisTabla) {
        System.out.println("A JÁTÉKOS LYUKRA LÉP ÉS VÍZBE ESIK");
        Buvarruha buvarruha = new Buvarruha();
        j.buvarruhaFelvesz(buvarruha);  // adunk neki búvárruhát, hogy legyen nála.

        Mezo szomszed2 = new Lyuk(4);
        j.getTartozkodasiMezo().szomszedok.put(Irany.Fel, szomszed2);
        j.lep(Irany.Fel);

    }

    public void szcenario3(Kontroller kont, Jatekos j, Mezo aktualisTabla) {
        System.out.println("A JÁTÉKOS KIHÚZZA A SZOMSZÉD MEZŐN VÍZBE ESETT JÁTÉKOST");
        Mezo szomszed = new Lyuk(4);
        Jatekos j2 = new Eszkimo(kont);
        szomszed.getAlloJatekos().add(j2);
        j.getTartozkodasiMezo().szomszedok.put(Irany.Le, szomszed);

        Kotel kotel = new Kotel();
        j.kotelFelvesz(kotel); // a mentő játékosnak adunk kötelet

        j.kihuz(Irany.Le);
    }


    public void szcenario4(Jatekos j) {
        System.out.println("ESZKIMO IGLUT ÉPíT");
        j.epit();
    }

    public void szcenario5(Jatekos j) {
        System.out.println("KUTATÓ VIZSGAL");
        Mezo szomszed = new Jegtabla(4, 4, null);
        j.getTartozkodasiMezo().szomszedok.put(Irany.Jobb, szomszed);
        j.vizsgal(Irany.Jobb);
    }

    public void szcenario6() {
        System.out.println("LAPATOT FELVESZ");
        Kontroller kontr=new Kontroller();
        Jatekos j = new Eszkimo(kontr);
        Mezo m = new Jegtabla(4, 4, new Lapat());

        j.setTartozkodasiMezo(m);
        j.kapar();

    }

    public void szcenario7(Jatekos j) {
        System.out.println("JÉGTÁBLÁRA LÉP AMI ELSÜLLYED");
        Jegtabla szomszed = new Jegtabla(0, 0, null);
        j.getTartozkodasiMezo().szomszedok.put(Irany.Le, szomszed);
        j.lep(Irany.Le);
    }

    public void szcenario8(Jatekos j) {
        System.out.println("JÁTÉKOS ÖSSZESZERELI A PISZTOLYT ÉS VÉGE A JÁTÉKNAK");

        j.osszeszerel();
    }

    public void szcenario9() {
        System.out.println("JÁTÉK");

        boolean game = true;

        Kontroller kontroller = new Kontroller();

        Jatekos j1 = new Eszkimo(kontroller);
        Jatekos j2 = new Kutato(kontroller);

        Mezo m1 = new Lyuk(4);
        Mezo m2 = new Jegtabla(4, 4, null);

        kontroller.getJatekosok().add(j1);
        kontroller.getJatekosok().add(j2);

        kontroller.palya.add(m1);
        kontroller.palya.add(m2);


        while (kontroller.aktiv) {
            kontroller.detektal();

            if (kontroller.aktiv) {
                for (Jatekos j : kontroller.getJatekosok()) {
                    j.jatszik();
                }

                kontroller.vihar();
            }
        }
    }

    public void szcenario10() {
        System.out.println("9 RETEG HÓ VAN A MEZŐN, EGY JATEKOS FELVESZI A TOREKENY LAPATOT ÉS LAPATOL 4X TÖREKENY LAPATTAL. A 4. PRÓBÁLKOZÁS MÁR HIÁBA");
       Kontroller kontr=new Kontroller();
        Jatekos j = new Eszkimo(kontr);
        Targy t = new TorekenyLapat();
        int teherbiras = 5;
        int hotakaro = 9;
        Mezo m = new Jegtabla(teherbiras, hotakaro, t);
        j.setTartozkodasiMezo(m);

        System.out.println(m.getHotakaro());
        j.kapar();
        j.lapatol();
        System.out.println(m.getHotakaro());
        j.lapatol();
        System.out.println(m.getHotakaro());
        j.lapatol();
        System.out.println(m.getHotakaro());
        j.lapatol();
        System.out.println(m.getHotakaro());
        j.lapatol();
        System.out.println(m.getHotakaro());
    }

    public void szcenario11() {
        System.out.println("A MEDVE A SZOMSZÉD MEZŐN ÁLL ÉS ODALÉP A JÁTÉKOS HOZZÁ");
        Kontroller k = new Kontroller();
        Jatekos j = new Eszkimo(k);
        Jegesmedve medve = new Jegesmedve();

        k.getJatekosok().add(j);
        k.setJegesmedve(medve);

        Mezo m = new Jegtabla(4, 4, null);
        Jegtabla szomszed = new Jegtabla(2, 2, null);
        j.setTartozkodasiMezo(m);
        m.szomszedok.put(Irany.Le, szomszed);
        medve.setTartozkodasiMezo(szomszed);
        szomszed.setAlloJegesmedve(medve);

        j.lep(Irany.Le);
    }

    public void szcenario12() {
        System.out.println(" A JÁTÉKOS A SZOMSZED MEZŐN ÁLL ÉS ODALÉP A MEDVE A MEZEJÉRE");
        Kontroller k = new Kontroller();
        Jatekos j = new Eszkimo(k);
        Jegesmedve medve = new Jegesmedve();

        k.getJatekosok().add(j);
        k.setJegesmedve(medve);

        Mezo m = new Jegtabla(4, 4, null);
        Mezo szomszed = new Jegtabla(2, 2, null);
        m.szomszedok.put(Irany.Le, szomszed);

        szomszed.getAlloJatekos().add(j);
        j.setTartozkodasiMezo(szomszed);

        medve.setTartozkodasiMezo(m);
        m.setAlloJegesmedve(medve);

        medve.lep(Irany.Le);
    }

    public void szcenario13() {
        System.out.println(" A JÁTÉKOS A LYukon ÁLL ÉS ODALÉP A MEDVE A MEZEJÉRE");
        Kontroller k = new Kontroller();
        Jatekos j = new Eszkimo(k);
        Jegesmedve medve = new Jegesmedve();
        k.getJatekosok().add(j);
        k.setJegesmedve(medve);

        Mezo m = new Jegtabla(4, 4, null);
        Mezo szomszed = new Lyuk(4);
        m.szomszedok.put(Irany.Le, szomszed);

        szomszed.getAlloJatekos().add(j);
        j.setTartozkodasiMezo(szomszed);

        medve.setTartozkodasiMezo(m);
        m.setAlloJegesmedve(medve);

        medve.lep(Irany.Le);
    }

    public void szcenario14() {
        System.out.println("TOREKENY LAPATOT FELVESZ MAJD HASZNAL");
    }
}
