package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




public class Main {

    public void szcenario1(Jatekos j, Mezo aktualisTabla){
        System.out.println("A JÁTÉKOS JÉGTÁBLÁRA LÉP");

        Mezo szomszed =new Jegtabla();
        j.tartozkodasiMezo.szomszedok.put(Irany.Jobb, szomszed);
        j.lep(Irany.Jobb);

    }

    public void szcenario2(Jatekos j, Mezo aktualisTabla){
        System.out.println("A JÁTÉKOS LYUKRA LÉP ÉS VÍZBE ESIK");
        Buvarruha buvarruha=new Buvarruha();
        j.buvarruhaFelvesz(buvarruha);  // adunk neki búvárruhát, hogy legyen nála.

        Mezo szomszed2=new Lyuk();
        j.tartozkodasiMezo.szomszedok.put(Irany.Fel, szomszed2);
        j.lep(Irany.Fel);

    }

    public void szcenario3(Jatekos j, Mezo aktualisTabla){
        System.out.println("A JÁTÉKOS KIHÚZZA A SZOMSZÉD MEZŐN VÍZBE ESETT JÁTÉKOST");
        Mezo szomszed=new Lyuk();
        Jatekos j2=new Eszkimo();
        szomszed.alloJatekos.add(j2);
        j.tartozkodasiMezo.szomszedok.put(Irany.Le, szomszed);

        Kotel kotel=new Kotel();
        j.kotelFelvesz(kotel); // a mentő játékosnak adunk kötelet

        j.kihuz(Irany.Le);
    }


    public void szcenario4(Eszkimo e){
        System.out.println("ESZKIMO IGLUT ÉPíT");
        e.epit();
    }

    public void szcenario5(Kutato k){
        System.out.println("KUTATÓ VIZSGAL");
        Mezo szomszed =new Jegtabla();
        k.tartozkodasiMezo.szomszedok.put(Irany.Jobb, szomszed);
        k.vizsgal(Irany.Jobb);
    }

    public void szcenario6(){
        System.out.println("LAPATOT FELVESZ");
        Eszkimo e=new Eszkimo();
        Lapat l=new Lapat();
        Jegtabla j= new Jegtabla();
        j.fagyotttargy=l;
        e.tartozkodasiMezo=j;
        e.kapar();
    }

    public void szcenario7(Jatekos j){
        System.out.println("JÉGTÁBLÁRA LÉP? AMI ELSÜLLYED");
        Jegtabla szomszed=new Jegtabla();
        j.tartozkodasiMezo.szomszedok.put(Irany.Le, szomszed);
        szomszed.teherbiras=0;
        j.lep(Irany.Le);
    }

    public void szcenario8(Jatekos j){
        System.out.println("JÁTÉKOS ÖSSZESZERELI A PISZTOLYT ÉS VÉGE A JÁTÉKNAK");
        j.osszeszerel();

    }

    public static void main(String[] args) {

        Main m=new Main();
        Mezo aktualisTabla=new Jegtabla();
        Eszkimo e = new Eszkimo();
        e.tartozkodasiMezo = aktualisTabla;
        Kutato k = new Kutato();
        k.tartozkodasiMezo= aktualisTabla;

        int i=1;
        Scanner sc=new Scanner(System.in);
        while(i>0) {
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
                    m.szcenario3(e, aktualisTabla);
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


            }
        }
        sc.close();
    }
}
