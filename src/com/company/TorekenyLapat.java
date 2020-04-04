package com.company;

public class TorekenyLapat extends Lapat{
    int hasznalatSzama;

    public void hasznal(Jatekos j){
    if (hasznalatSzama<3) {
        j.tartozkodasiMezo.horetegCsokkent();
        j.tartozkodasiMezo.horetegCsokkent();
        hasznalatSzama++;
    }

}
}
