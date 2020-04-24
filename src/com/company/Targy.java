package com.company;

public abstract class Targy {
  /*  public abstract boolean accept(TargyVisitor v);
    public abstract void felvesz(Jatekos j);
    public abstract void hasznal(Jatekos j);*/


    public boolean accept(TargyVisitor v){return false;}
    public void felvesz(Jatekos j){}
    public void hasznal(Jatekos j){}
}

