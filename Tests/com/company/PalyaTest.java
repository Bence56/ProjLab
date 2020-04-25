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

