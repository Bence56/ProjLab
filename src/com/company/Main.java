package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Main m = new Main();
        Kontroller kont = new Kontroller();
        Mezo aktualisTabla = new Jegtabla();
        Jatekos e = new Eszkimo(kont);
        e.tartozkodasiMezo = aktualisTabla;
        Jatekos k = new Kutato(kont);
        k.tartozkodasiMezo = aktualisTabla;
        kont.jatekosok.add(e);
        kont.jatekosok.add(k);

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
                case 14:
                    // Egy feldolgozoval ertelmezni kell a szoveget

                    String input = "epit";
                    JatekosTest j = new JatekosTest();
                    java.lang.reflect.Method method;
                    try {
                        method = j.getClass().getMethod("epit");
                        try {
                            method.invoke(j);
                        } catch (IllegalArgumentException ex) {
                            ex.printStackTrace();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        } catch (InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    } catch (SecurityException ex) {
                        ex.printStackTrace();
                    } catch (NoSuchMethodException ex) {
                        ex.printStackTrace();
                        ;
                    }


                    break;

            }
        }
        sc.close();
    }

    public void szcenario1(Jatekos j, Mezo aktualisTabla) {
        System.out.println("A JÁTÉKOS JÉGTÁBLÁRA LÉP");

        Mezo szomszed = new Jegtabla();
        j.tartozkodasiMezo.szomszedok.put(Irany.Jobb, szomszed);
        j.lep(Irany.Jobb);

    }

    public void szcenario2(Jatekos j, Mezo aktualisTabla) {
        System.out.println("A JÁTÉKOS LYUKRA LÉP ÉS VÍZBE ESIK");
        Buvarruha buvarruha = new Buvarruha();
        j.buvarruhaFelvesz(buvarruha);  // adunk neki búvárruhát, hogy legyen nála.

        Mezo szomszed2 = new Lyuk();
        j.tartozkodasiMezo.szomszedok.put(Irany.Fel, szomszed2);
        j.lep(Irany.Fel);

    }

    public void szcenario3(Kontroller kont, Jatekos j, Mezo aktualisTabla) {
        System.out.println("A JÁTÉKOS KIHÚZZA A SZOMSZÉD MEZŐN VÍZBE ESETT JÁTÉKOST");
        Mezo szomszed = new Lyuk();
        Jatekos j2 = new Eszkimo(kont);
        szomszed.alloJatekos.add(j2);
        j.tartozkodasiMezo.szomszedok.put(Irany.Le, szomszed);

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
        Mezo szomszed = new Jegtabla();
        j.tartozkodasiMezo.szomszedok.put(Irany.Jobb, szomszed);
        j.vizsgal(Irany.Jobb);
    }

    public void szcenario6() {
        System.out.println("LAPATOT FELVESZ");
        Jatekos j = new Eszkimo();
        Targy t = new Lapat();
        Mezo m = new Jegtabla(t);

        j.tartozkodasiMezo = m;
        j.kapar();

    }

    public void szcenario7(Jatekos j) {
        System.out.println("JÉGTÁBLÁRA LÉP AMI ELSÜLLYED");
        Jegtabla szomszed = new Jegtabla(0, 0, null);
        j.tartozkodasiMezo.szomszedok.put(Irany.Le, szomszed);
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

        Jatekos j1 = new Eszkimo();
        Jatekos j2 = new Kutato();

        Mezo m1 = new Lyuk();
        Mezo m2 = new Jegtabla();

        kontroller.jatekosok.add(j1);
        kontroller.jatekosok.add(j2);

        kontroller.palya.add(m1);
        kontroller.palya.add(m2);


        while (kontroller.aktiv) {
            kontroller.detektal();

            if (kontroller.aktiv) {
                for (Jatekos j : kontroller.jatekosok) {
                    j.jatszik();
                }

                kontroller.vihar();
            }
        }
    }

    public void szcenario10() {
        System.out.println("9 RETEG HÓ VAN A MEZŐN, EGY JATEKOS FELVESZI A TOREKENY LAPATOT ÉS LAPATOL 4X TÖREKENY LAPATTAL. A 4. PRÓBÁLKOZÁS MÁR HIÁBA");
        Jatekos j = new Eszkimo();
        Targy t = new TorekenyLapat();
        int teherbiras = 5;
        int hotakaro = 9;
        Mezo m = new Jegtabla(teherbiras, hotakaro, t);
        j.tartozkodasiMezo = m;

        System.out.println(m.hotakaro);
        j.kapar();
        j.lapatol();
        System.out.println(m.hotakaro);
        j.lapatol();
        System.out.println(m.hotakaro);
        j.lapatol();
        System.out.println(m.hotakaro);
        j.lapatol();
        System.out.println(m.hotakaro);
        j.lapatol();
        System.out.println(m.hotakaro);
    }

    public void szcenario11() {
        System.out.println("A MEDVE A SZOMSZÉD MEZŐN ÁLL ÉS ODALÉP A JÁTÉKOS HOZZÁ");
        Kontroller k = new Kontroller();
        Jatekos j = new Eszkimo(k);
        Jegesmedve medve = new Jegesmedve();

        k.jatekosok.add(j);
        k.jegesmedve = medve;

        Mezo m = new Jegtabla();
        Jegtabla szomszed = new Jegtabla(2, 2, null);
        j.tartozkodasiMezo = m;
        m.szomszedok.put(Irany.Le, szomszed);
        medve.tartozkodasiMezo = szomszed;
        szomszed.alloJegesmedve = medve;

        j.lep(Irany.Le);
    }

    public void szcenario12() {
        System.out.println(" A JÁTÉKOS A SZOMSZED MEZŐN ÁLL ÉS ODALÉP A MEDVE A MEZEJÉRE");
        Kontroller k = new Kontroller();
        Jatekos j = new Eszkimo(k);
        Jegesmedve medve = new Jegesmedve();

        k.jatekosok.add(j);
        k.jegesmedve = medve;

        Mezo m = new Jegtabla();
        Mezo szomszed = new Jegtabla(2, 2, null);
        m.szomszedok.put(Irany.Le, szomszed);

        szomszed.alloJatekos.add(j);
        j.tartozkodasiMezo = szomszed;

        medve.tartozkodasiMezo = m;
        m.alloJegesmedve = medve;

        medve.lep(Irany.Le);
    }

    public void szcenario13() {
        System.out.println(" A JÁTÉKOS A LYukon ÁLL ÉS ODALÉP A MEDVE A MEZEJÉRE");
        Kontroller k = new Kontroller();
        Jatekos j = new Eszkimo(k);
        Jegesmedve medve = new Jegesmedve();
        k.jatekosok.add(j);
        k.jegesmedve = medve;

        Mezo m = new Jegtabla();
        Mezo szomszed = new Lyuk();
        m.szomszedok.put(Irany.Le, szomszed);

        szomszed.alloJatekos.add(j);
        j.tartozkodasiMezo = szomszed;

        medve.tartozkodasiMezo = m;
        m.alloJegesmedve = medve;

        medve.lep(Irany.Le);
    }

    public void szcenario14() {
        System.out.println("TOREKENY LAPATOT FELVESZ MAJD HASZNAL");
    }
}
