package com.company;


import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalyaTest {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private boolean konzol = true;

    PalyaTest() {
        this.konzol = true;
    }


    @Test
    public void jatekTest(){
        Kontroller kontroller = new Kontroller();
        Parser parser = new Parser();

        try {
            parser.palyaParse(kontroller, "palya.json");
            System.out.println("A");








            Jatekos eszkimo1=kontroller.getJatekosok().get(0);
            try {
                assertEquals(kontroller.getPalya(5),eszkimo1.getTartozkodasiMezo());                //Bence karaktere ellenőrzése hogy jó helyre került, e
                System.out.println(ANSI_GREEN + "Siker, az eszkimó jó helyre került" + ANSI_RESET);
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem jó helyen áll" + ANSI_RESET);
            }


            eszkimo1.kihuz(Irany.BalLe);
            try {
                assertTrue(eszkimo1.getTartozkodasiMezo().getAlloJatekos().size()>=1);      //Bence karaktere kihúzza Peti karakterét
                System.out.println(ANSI_GREEN + "Siker, az eszkimó kihúzott valakit" + ANSI_RESET);
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem húzott ki senkit" + ANSI_RESET);
            }

            int oldSnow=eszkimo1.getTartozkodasiMezo().getHotakaro();
            eszkimo1.lapatol();                                                                         //Bence karaktere lapátol egyet
            try {
                if (oldSnow==0){
                    assertTrue(eszkimo1.getTartozkodasiMezo().getHotakaro()==0);
                    System.out.println(ANSI_GREEN + "Siker, a hótakaró 0 volt annyi is maradt" + ANSI_RESET);
                }
                else{
                    assertTrue(eszkimo1.getTartozkodasiMezo().getHotakaro()<oldSnow);
                    System.out.println(ANSI_GREEN + "Siker, a hótakaró nem 0 volt és kevesebb is lett" + ANSI_RESET);
                }

            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem jó a lapátolás" + ANSI_RESET);
            }




            eszkimo1.lep(Irany.Le);                                                                     //Bence karaktere lép egyetlefel a 12 es mezőre

            try {
                assertEquals(kontroller.getPalya(12),eszkimo1.getTartozkodasiMezo());
                System.out.println(ANSI_GREEN + "Siker, az eszkimó lefele lépett" + ANSI_RESET);
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem lépett lefele az eszkimó" + ANSI_RESET);
            }

            eszkimo1.epit();                                                                            //Bence karaktere épít egy iglut a 12-es mezőre

            try {
                assertTrue(kontroller.getPalya(12).isIglu());
                System.out.println(ANSI_GREEN + "Siker, az eszkimó megépítette az iglut" + ANSI_RESET);
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem lépett lefele az eszkimó" + ANSI_RESET);
            }
            eszkimo1.lep(Irany.Le);                                                                     //Bence olyan helyre próbál,lépni amilyen sziomszéd nincs
            try {                                                                                       //itt kell maradnia a 12-es mezőn
                assertEquals(kontroller.getPalya(12),eszkimo1.getTartozkodasiMezo());
                System.out.println(ANSI_GREEN + "Siker, nem lépett lehetetlen mezőre" + ANSI_RESET);
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem ott van ahol volt" + ANSI_RESET);
            }
           //13as mezőről a kutató lép, aztán kapar de ott nincs tárgy, továbblép és megint kapar ott van egy alkatrész
            Jatekos kutato2 = kontroller.getJatekosok().get(2);
            kutato2.lep(Irany.Bal);
            try {
                assertEquals(kutato2.getTartozkodasiMezo(),kontroller.palya.get(11));
                System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
                kutato2.state();
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem jó helyre lép" + ANSI_RESET);
            }
            kutato2.kapar();
            try {
                assertTrue(kutato2.getTargyak().size()==0);
                System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
                kutato2.state();
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail" + ANSI_RESET);
            }
            kutato2.lep(Irany.BalFel);
            try {
                assertEquals(kutato2.getTartozkodasiMezo(),kontroller.palya.get(8));
                System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
                kutato2.state();
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail: Nem jó helyre lép" + ANSI_RESET);
            }
            kutato2.kapar();
            try {
                assertTrue(kutato2.getTargyak().size()==1);
                System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
                kutato2.state();
            } catch (AssertionFailedError e) {
                System.out.println(ANSI_RED + "Fail" + ANSI_RESET);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

