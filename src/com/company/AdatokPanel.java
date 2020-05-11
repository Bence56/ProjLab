package com.company;

import javax.swing.*;
import java.awt.*;

public class AdatokPanel extends JPanel{
    private ImageIcon eszkim=new ImageIcon("Resources/Assets/Kutato-teljes.png");
    private ImageIcon kuatato=new ImageIcon("Resources/Assets/Kutato-teljes.png");
    private JLabel kep=new JLabel();
    JTextField fulladasiAllapot=new JTextField("Állapotom: ");
    JTextField testho=new JTextField("Testhőm: ");
    JTextField buvarruha=new JTextField("Búvárruha: " );
    JTextField munka=new JTextField("Munkák: ");
            public AdatokPanel(){
                this.setPreferredSize(new Dimension(256,270));
                JLabel j2=new JLabel("Ki vagyok:");
                this.add(j2);
                BoxLayout boxlayout= new BoxLayout(this, BoxLayout.Y_AXIS); //felülről lefelé adja hozzá az elemeket.
                this.setLayout(boxlayout);
                //TODO: eldönteni vhogy, hogy kutató v eszkimó és aszerint 1 eszkimot  v a kutatót kirajzolni.
                //if (aktivJatekos == eszkimo)
                kep.setIcon(eszkim);
                this.add(kep);
                testho.setEditable(false);
                this.add(testho);
                fulladasiAllapot.setEditable(false);
                this.add(fulladasiAllapot);
                buvarruha.setEditable(false);
                this.add(buvarruha);
                munka.setEditable(false);
                this.add(munka);
            }
            public void update(Jatekos aktivjatekos){
                fulladasiAllapot.setText("Állapotom: "+aktivjatekos.getAllapot().toString());
                testho.setText("Testhőm: "+aktivjatekos.getTestho());
                buvarruha.setText("Búvárruha: "+aktivjatekos.isVedett());
                munka.setText("Munkák: "+aktivjatekos.getMunka());
                revalidate();
            }












}
