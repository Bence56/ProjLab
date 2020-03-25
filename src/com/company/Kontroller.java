package com.company;

import java.util.ArrayList;

public class Kontroller {
    ArrayList<Mezo>palya;

    public void jatek(){}
    public void vihar() {
        System.out.println("\tKONTROLLER.vihar()");
        for (Mezo item:palya) {
            item.horetegNovel();
        }
    }
    public void detektel(){}
    public void jatekVege(){}
}
