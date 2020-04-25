package com.company;


import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.IOException;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

