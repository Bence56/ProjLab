package com.company;

public enum Irany{
    Fel,
    Le,
    Bal,
    Jobb;

    public Irany ellentetes() {
        if(this==Irany.Fel)
        {
            return Le;
        }
        else if(this==Irany.Le)return Fel;
        else if(this==Irany.Jobb)return Bal;
        else return Jobb;
    }

}
