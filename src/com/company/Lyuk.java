package com.company;

public class Lyuk extends Mezo {

    /**
     * Vízbe ejti a játékost
     *
     * @param j A játékos amit el kell fogadnia a lyuknak
     */
    @Override
    public void elfogad(Jatekos j) {
        Tab.tab++;
        for (int i = 0; i < Tab.tab; i++) System.out.print("\t");
        System.out.println("Jegtabla.elfogad(Jatekos j)");


        j.setMezo(this);
        j.vizbeEsik();

        Tab.tab--;
    }

    @Override
    public void setIglu(boolean iglu) {
        return;
    }

}
