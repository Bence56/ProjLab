package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * A játékos egy jégtáblára lép
 */

public class Main {

    public static void szcenario1(Jatekos j, Mezo aktualisTabla){
        System.out.println("A JÁTÉKOS JÉGTÁBLÁRA LÉP");

        Mezo szomszed =new Jegtabla();
        j.tartozkodasiMezo.szomszedok.put(Irany.Jobb, szomszed);
        j.lep(Irany.Jobb);

    }

    public static void szcenario2(Jatekos j, Mezo aktualisTabla){
        System.out.println("A JÁTÉKOS LYUKRA LÉP ÉS VÍZBE ESIK");
        Buvarruha buvarruha=new Buvarruha();
        j.buvarruhaFelvesz(buvarruha);  // adunk neki búvárruhát, hogy legyen nála.

        Mezo szomszed2=new Lyuk();
        j.tartozkodasiMezo.szomszedok.put(Irany.Fel, szomszed2);
        j.lep(Irany.Fel);

    }

    public static void szcenario3(Jatekos j, Mezo aktualisTabla){
        System.out.println("A JÁTÉKOS KIHÚZZA A SZOMSZÉD MEZŐN VÍZBE ESETT JÁTÉKOST");
        Mezo szomszed=new Lyuk();
        Jatekos j2=new Eszkimo();
        szomszed.alloJatekos.add(j2);
        j.tartozkodasiMezo.szomszedok.put(Irany.Le, szomszed);

        Kotel kotel=new Kotel();
        j.kotelFelvesz(kotel); // a mentő játékosnak adunk kötelet

        j.kihuz(Irany.Le);
    }
    public static void main(String[] args) {

        Mezo aktualisTabla=new Jegtabla();
        Jatekos j = new Eszkimo();
        j.tartozkodasiMezo = aktualisTabla;

        int i=1;
        Scanner sc=new Scanner(System.in);
        while(i>0) {
            System.out.println("Adj meg egy számot: (ha ki akarsz lépni a 0-t)");
            i = sc.nextInt();
            switch (i) {
                case 1:
                    szcenario1(j, aktualisTabla);
                    break;
                case 2:
                    szcenario2(j, aktualisTabla);
                    break;
                case 3:
                    szcenario3(j, aktualisTabla);
                    break;
            }
        }
        sc.close();
    }
}
