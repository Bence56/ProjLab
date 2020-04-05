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

    public static Irany StringToIrany(String string){
        if (string.equals("Fel")){
            return Fel;
        }
        else if(string.equals("Le")){
            return Le;
        }
        else if(string.equals("Bal")){
            return Bal;
        }
        else if(string.equals("Jobb")){
            return Jobb;
        }
        else return null;
    }

}
