package com.company;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

class KontrollerTest {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private boolean konzol = true;

    KontrollerTest() {
        this.konzol = true;
    }

    @Test
    public void viharTest(){
        Kontroller k =new Kontroller();
        Jegtabla m1=new Jegtabla(4,5,null);
        Jegtabla m2=new Jegtabla(4,5,null);
        Jegtabla m3=new Jegtabla(4,5,null);
        Jegtabla m4=new Jegtabla(4,5,null);
        Lyuk m5 =new Lyuk(5);
        Jegtabla m6=new Jegtabla(4,5,null);
        Jegtabla m7=new Jegtabla(4,5,null);
        Lyuk m8 =new Lyuk(5);
        Lyuk m9 =new Lyuk(5);

        m1.szomszedok.put(Irany.Jobb,m2);m1.szomszedok.put(Irany.Le,m4);k.palya.add(m1);
        m2.szomszedok.put(Irany.Bal,m1);m2.szomszedok.put(Irany.Le,m5);m2.szomszedok.put(Irany.Jobb,m3);k.palya.add(m2);
        m3.szomszedok.put(Irany.Bal,m2);m3.szomszedok.put(Irany.Le,m6);k.palya.add(m3);
        m4.szomszedok.put(Irany.Jobb,m5);m4.szomszedok.put(Irany.Fel,m1);m4.szomszedok.put(Irany.Le,m7);k.palya.add(m4);
        m5.szomszedok.put(Irany.Jobb,m6);m5.szomszedok.put(Irany.Fel,m2);m5.szomszedok.put(Irany.Le,m8);m5.szomszedok.put(Irany.Bal,m4);k.palya.add(m5);
        m6.szomszedok.put(Irany.Bal,m5);m6.szomszedok.put(Irany.Fel,m3);m6.szomszedok.put(Irany.Le,m9);k.palya.add(m6);
        m7.szomszedok.put(Irany.Fel,m4);m7.szomszedok.put(Irany.Jobb,m8);k.palya.add(m7);
        m8.szomszedok.put(Irany.Bal,m7);m8.szomszedok.put(Irany.Fel,m5);m8.szomszedok.put(Irany.Jobb,m9);k.palya.add(m8);
        m9.szomszedok.put(Irany.Fel,m6);m7.szomszedok.put(Irany.Bal,m8);k.palya.add(m9);

        Eszkimo e1=new Eszkimo();e1.setTartozkodasiMezo(m3);m3.elfogad(e1);k.addJatekos(e1);
        Eszkimo e2=new Eszkimo();e2.setTartozkodasiMezo(m3);m5.elfogad(e2);k.addJatekos(e2);
        Eszkimo e3=new Eszkimo();e3.setTartozkodasiMezo(m3);m7.elfogad(e3);k.addJatekos(e3);
        Eszkimo e4=new Eszkimo();e4.setTartozkodasiMezo(m3);m9.elfogad(e4);k.addJatekos(e4);

        k.vihar();
        try {
            boolean jo=true;
            for (Mezo m : k.palya) {
                if(!(m.getHotakaro()>5))jo=false;
            }
            assertTrue(jo);
            System.out.println(ANSI_GREEN + "Siker, minden mezon tobb a ho" + ANSI_RESET);

        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: nem minden mezon tobb a ho" + ANSI_RESET);
        }
        try {
            boolean jo = true;
            for (Jatekos j : k.getJatekosok()) {
                if (!(j.getTestho() >= 5)) jo = false;
            }
            assertTrue(jo);
            System.out.println(ANSI_GREEN + "Siker, minden jatekos meghult" + ANSI_RESET);
        }
        catch(AssertionFailedError e){
            System.out.println(ANSI_RED + "Fail: Nem minden jatekos hult ki" + ANSI_RESET);
        }
    }
}
