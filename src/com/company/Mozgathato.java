package com.company;

public abstract class Mozgathato {
    private Mezo tartozkodasiMezo;

    public abstract void jatszik();
    public abstract void lep(Irany i);

    public void setMezo(Mezo m) {
        this.tartozkodasiMezo = m;

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
