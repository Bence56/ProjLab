package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Jegesmedve extends Mozgathato {

    @Override
    public void jatszik() {  // random irányba meghí@ja a lép fv-t
        Random rand = new Random();
        int i = rand.nextInt(4);

        switch (i) {
            case 0:
                lep(Irany.Fel);
                break;
            case 1:
                lep(Irany.Le);
                break;
            case 2:
                lep(Irany.Jobb);
                break;
            case 3:
                lep(Irany.Bal);
                break;
        }
    }


            @Override
    public void lep(Irany i) { // miután a mezőre lépett csekkolja, hogy van-e iglu, ha nincs, akkor öl.
                Tab.tab++;
                for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
                System.out.println("Jegesmedve.lep(Irany i)");

                // Lekéri a szomszég mezőt
                Mezo szomszed = getTartozkodasiMezo().getSzomszed(i);

                //eltávolítja a játékost
                this.getTartozkodasiMezo().eltavolit(this);

                //Átadja magát a szomszédos játékosnak
                szomszed.elfogad(this);

                szomszed.utkozik(this);
                Tab.tab--;
            }

}

