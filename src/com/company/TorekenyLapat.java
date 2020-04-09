package com.company;

public class TorekenyLapat extends Lapat{
   private int hasznalatSzama;

    public void hasznal(Jatekos j){
    if (hasznalatSzama<3) {
        j.getTartozkodasiMezo().horetegCsokkent();
        j.getTartozkodasiMezo().horetegCsokkent();
        hasznalatSzama++;
    }

}
}
