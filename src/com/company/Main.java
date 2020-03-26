package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Mezo aktualisTabla = new Jegtabla();
        Mezo szomszed =new Jegtabla();
        Jatekos j = new Eszkimo();

        j.tartozkodasiMezo = aktualisTabla;
        j.tartozkodasiMezo.szomszedok.put(Irany.Jobb, szomszed);

        j.lep(Irany.Jobb);


        /**
         * Második teszteset. Egy kutató lyukra lép és van rajta búvárruha.
          */

        Jatekos j2=new Kutato();
        Buvarruha buvarruha=new Buvarruha();
        j2.buvarruhaFelvesz(buvarruha);  // adunk neki búvárruhát, hogy legyen nála.
        Mezo szomszed2=new Lyuk();
        j2.tartozkodasiMezo=aktualisTabla;
        j2.tartozkodasiMezo.szomszedok.put(Irany.Fel, szomszed2);
        j2.lep(Irany.Fel);

    }
}
