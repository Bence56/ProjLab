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
        Kontroller k=new Kontroller();
        Mezo m = new Jegtabla(4, 4, null);
        Jatekos j = new Eszkimo(k);
        j.setTartozkodasiMezo(m);

        j.epit();

        try {
            assertTrue(j.getTartozkodasiMezo().isIglu());
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: Nem épül iglu" + ANSI_RESET);
        }
    }

    @Test
    public void uresreLep(String irany) {
        Kontroller k=new Kontroller();
        Irany i = Irany.StringToIrany(irany);
        Jatekos j = new Eszkimo(k);
        Mezo m = new Jegtabla(4, 4, null);
        Mezo m2 = new Jegtabla(4, 4, null);
        m.szomszedok.put(i, m2);
        j.setTartozkodasiMezo(m);
        j.lep(i);

        try {
            assertFalse(m.getAlloJatekos().isEmpty());
            System.out.println(ANSI_RED + "Fail: Nem üres a mező amiről elléptek" + ANSI_RESET);
            assertTrue(m2.getAlloJatekos().isEmpty());
            System.out.println(ANSI_RED + "Fail: Nem lépett át a játékos" + ANSI_RESET);

        } catch (AssertionFailedError e) {
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
        }


    }    //@AfterEach


}