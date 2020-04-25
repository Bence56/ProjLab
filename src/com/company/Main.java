package com.company;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            System.out.println("Adj meg egy teszt fájl nevet: ");
            String parancs = scanner.nextLine();
            if (parancs.equals("q")) {
                break;
            }

            Tester tester = new Tester();
            tester.testfromfile(parancs);

        }


        Main m = new Main();
        Kontroller kont = new Kontroller();
        Mezo aktualisTabla = new Jegtabla(4, 4, null);
        Jatekos e = new Eszkimo(kont);
        e.setMezo(aktualisTabla);
        Jatekos k = new Kutato(kont);
        k.setMezo(aktualisTabla);
        kont.getJatekosok().add(e);
        kont.getJatekosok().add(k);

        int i = 1;
        Scanner sc = new Scanner(System.in);
        while (i > 0) {
            System.out.println("Adj meg egy számot: (ha ki akarsz lépni a 0-t)");
            i = sc.nextInt();
            switch (i) {
                case 14: {
                    Tester tester = new Tester();
                    tester.test();
                    break;
                }
                case 15: {
                    Tester tester = new Tester();
                    tester.testfromfile("lapatolastest.txt");
                    break;
                }
                case 16: {
                    Tester tester = new Tester();
                    tester.testfromfile("kihuztest.txt");
                    break;
                }
                case 17: {
                    Tester tester = new Tester();
                    tester.testfromfile("jegesmedvetest.txt");
                    break;
                }
                case 18: {
                    Tester tester = new Tester();
                    tester.testfromfile("vihartest.txt");
                    break;
                }
                case 19: {
                    Kontroller kontroller = new Kontroller();
                    Parser parser = new Parser();
                    try {
                        parser.palyaParse(kontroller, "palya.json");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                case 20: {
                    Tester tester = new Tester();
                    tester.testfromfile("Resources/palyatest.txt");
                }
            }
        }
        sc.close();
    }
}
