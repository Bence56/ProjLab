package com.company;

public abstract class Mozgathato {
    private Mezo tartozkodasiMezo;

    public abstract void jatszik();
    public abstract void lep(Irany i);

    public void setMezo(Mezo m) {
        Tab.tab++;
        for (int j = 0; j < Tab.tab; j++) System.out.print("\t");
        System.out.println("Jatekos.setMezo(Mezo m)");

        this.tartozkodasiMezo = m;

        Tab.tab--;
    }

    public Mezo getTartozkodasiMezo() {
        return tartozkodasiMezo;
    }

    public void setTartozkodasiMezo(Mezo tartozkodasiMezo) {
        this.tartozkodasiMezo = tartozkodasiMezo;
    }

    // public void setTartozkodasiMezo(Mezo tartozkodasiMezo) {
       // this.tartozkodasiMezo = tartozkodasiMezo;
   // }
}
