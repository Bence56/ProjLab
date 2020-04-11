package com.company;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

class JatekosTest {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private boolean konzol = true;

    JatekosTest() {
        this.konzol = true;
    }

    @Test
    public void epit() {
        Kontroller k=new Kontroller();
        Mezo m = new Jegtabla(4, 4, null);
        Jatekos j = new Eszkimo(k);
        j.setTartozkodasiMezo(m);

        j.epit();

        try {
            assertTrue(j.getTartozkodasiMezo().isIglu());
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: Nem épül iglu" + ANSI_RESET);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"jobb", "bal", "fel", "le" })
    public void uresreLep(String irany) {
        Kontroller k=new Kontroller();
        Irany i = Irany.StringToIrany(irany);
        Jatekos j = new Eszkimo(k);
        Mezo m = new Jegtabla(4, 4, null);
        Mezo m2 = new Jegtabla(4, 4, null);
        m.szomszedok.put(i, m2);
        j.setTartozkodasiMezo(m);
        j.lep(i);

        try {
            assertFalse(m2.getAlloJatekos().isEmpty());
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
            assertTrue(m.getAlloJatekos().isEmpty());
            System.out.println(ANSI_GREEN + "Siker" + ANSI_RESET);
        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: Nem lépett át a játékos" + ANSI_RESET);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"ASD", "ADS"})
    public void test1(String jatek){

    }


    @Test
    public void lapatolTest(){
        Kontroller k=new Kontroller();
        Eszkimo eszkimo=new Eszkimo();
        Kutato kutato=new Kutato(k);
        Jegtabla j1=new Jegtabla(5,4,new Lapat());
        Jegtabla j2=new Jegtabla(5,4,new Buvarruha());
        Jegtabla j3=new Jegtabla(5,1,new Buvarruha());
        eszkimo.setTartozkodasiMezo(j1);
        kutato.setTartozkodasiMezo(j2);
        Lapat l=new Lapat();
        eszkimo.lapatFelvesz(l);
        j1.elfogad(eszkimo);
        j2.elfogad(kutato);
        eszkimo.lapatol();
        kutato.lapatol();
        try {
            assertEquals(2,j1.getHotakaro());
            System.out.println(ANSI_GREEN + "Siker, amikor van lapát" + ANSI_RESET);
            assertEquals(3,j2.getHotakaro());
            System.out.println(ANSI_GREEN + "Siker, amikor nincs lapát" + ANSI_RESET);
        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: Nincs jól beállítva a lapátolás" + ANSI_RESET);
        }
        eszkimo.setTartozkodasiMezo(j3);
        j3.elfogad(eszkimo);
        eszkimo.lapatol();
        try {
            assertEquals(0,j3.getHotakaro());
            System.out.println(ANSI_GREEN + "Siker, amikor van lapát és 1 a hóréteg" + ANSI_RESET);
        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: Nincs jól beállítva amikor van lapát és 1 a hóréteg" + ANSI_RESET);
        }
    }
    @Test
    public void kihuzTest(){
        Jegtabla jegtabla=new Jegtabla(5,4,new Buvarruha());
        Lyuk lyuk=new Lyuk(0);
        Eszkimo megmento=new Eszkimo();
        Eszkimo fulldoklo=new Eszkimo();
        Kotel k=new Kotel();
        megmento.kotelFelvesz(k);
        megmento.setTartozkodasiMezo(jegtabla);
        jegtabla.elfogad(megmento);
        fulldoklo.setTartozkodasiMezo(lyuk);
        lyuk.elfogad(fulldoklo);
        jegtabla.szomszedok.put(Irany.Jobb,lyuk);
        lyuk.szomszedok.put(Irany.Bal,jegtabla);
        megmento.kihuz(Irany.Jobb);

        try {
            assertTrue(megmento.getTartozkodasiMezo()==jegtabla);
            assertTrue(jegtabla==fulldoklo.getTartozkodasiMezo());
            System.out.println(ANSI_GREEN + "Siker, ugyan azon a mezőn vannak" + ANSI_RESET);
        } catch (AssertionFailedError e) {
            System.out.println(ANSI_RED + "Fail: Nem működik a kihúzó rendszer" + ANSI_RESET);
        }
    }


    @Test
    public void kikaparTest(){
        Jegtabla jegtabla=new Jegtabla(5,0,new Kotel());
        Eszkimo eszkimo=new Eszkimo();
        ezskimo.setTartozkodasiMezo(jegtabla);
        jegtabla.elfogad(eszkimo);
        ezskimo.kapar();
        try{
            assertNotNull(eszkimo.targyak[0]);
            assertNull(jegtabla.getTargy());
            System.out.println(ANSI_GREEN + "Siker: A tárgy a játékoshoz került." + ANSI_RESET);
        } catch(AssertionFailedError e){
            System.out.println(ANSI_RED + "Fail: Nem működik a tárgy felvétel" +ANSI_RESET);
        }
    }

}