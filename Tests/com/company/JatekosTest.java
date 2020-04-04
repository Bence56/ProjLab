package com.company;


import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import static org.junit.jupiter.api.Assertions.*;

class JatekosTest {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Test
    public void epit(boolean file) {
        Mezo m = new Jegtabla();
        Jatekos j = new Eszkimo();
        j.tartozkodasiMezo = m;

        j.epit();

        try {
            assertTrue(!j.tartozkodasiMezo.isIglu());
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
        }
        catch(AssertionFailedError e){
            System.out.println(ANSI_RED + "Fail: Nem épül iglu" + ANSI_RESET);
            }
    }
    //@AfterEach


}