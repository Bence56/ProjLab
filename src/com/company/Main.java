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

    }
}
