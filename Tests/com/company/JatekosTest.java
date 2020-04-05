package com.company;


import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JatekosTest {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private boolean konzol = true;

    JatekosTest() {
        this.konzol = true;
    }

    JatekosTest(Boolean konzol) {
        this.konzol = konzol;
    }

    @Test
    public void epit() {
        Mezo m = new Jegtabla();
        Jatekos j = new Eszkimo();
        j.tartozkodasiMezo = m;

        j.epit();

        try {
            assertTrue(j.tartozkodasiMezo.isIglu());
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: Nem épül iglu" + ANSI_RESET);
        }
    }

    @Test
    public void uresreLep(String irany) {
        Irany i = Irany.StringToIrany(irany);
        Jatekos j = new Eszkimo();
        Mezo m = new Jegtabla();
        Mezo m2 = new Jegtabla();
        m.szomszedok.put(i, m2);
        j.tartozkodasiMezo = m;
        j.lep(i);

        try {
            assertFalse(m.alloJatekos.isEmpty());
            System.out.println(ANSI_RED + "Fail: Nem üres a mező amiről elléptek" + ANSI_RESET);
            assertTrue(m2.alloJatekos.isEmpty());
            System.out.println(ANSI_RED + "Fail: Nem lépett át a játékos" + ANSI_RESET);

        } catch (AssertionFailedError e) {
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
        }


    }    //@AfterEach


}