package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Jegesmedve extends Mozgathato {

    /**
     * kiírja a jegesmedve állapotát
     */
    public void state(){
        String id = this.getTartozkodasiMezo().getID();
        System.out.println("Tartozkodasi mezo: " + id);
    }

    @Override
    public void jatszik() {  // random irányba meghí@ja a lép fv-t
        Random rand = new Random();
        int i = rand.nextInt(8);

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
            case 4:
                lep(Irany.JobbFel);
                break;
            case 5:
                lep(Irany.JobbLe);
                break;
            case 6:
                lep(Irany.BalLe);
                break;
            case 7:
                lep(Irany.BalFel);
                break;
        }
    }


            @Override
    public void lep(Irany i) { // miután a mezőre lépett csekkolja, hogy van-e iglu, ha nincs, akkor öl.

                Mezo szomszed = getTartozkodasiMezo().getSzomszed(i);
                if (szomszed!=null) {
                    this.getTartozkodasiMezo().eltavolit(this);
                    szomszed.elfogad(this);
                    szomszed.utkozik(this);
                }
                else jatszik();

            }
}

