package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Mezo aktualisTabla = new Jegtabla();
        Mezo szomszed =new Jegtabla();
        Jatekos j = new Eszkimo();

        j.tartozkodasiMezo = aktualisTabla;
        j.tartozkodasiMezo.Szomszedok.put(Irany.Jobb, szomszed);

        j.lep(Irany.Jobb);
    }
}
