package com.company;

import javax.swing.*;
import java.awt.*;

public class FunkciokPanel extends JPanel {
    JButton kapar=new JButton("Kapar");
    JButton osszeszerel=new JButton("Összeszerel");
    JButton vizsgal=new JButton("Vizsgál");
    public FunkciokPanel(Kontroller kontroller){

        GridLayout gl3=new GridLayout(2,2);
        this.setLayout(gl3);
        kapar.setActionCommand("kapar");
        kapar.addActionListener(kontroller);
        this.add(kapar);
        osszeszerel.setActionCommand("összeszerel");
        osszeszerel.addActionListener(kontroller);
        this.add(osszeszerel);
        vizsgal.setActionCommand("vizsgal");
        vizsgal.addActionListener(kontroller);
        this.add(vizsgal);
        JButton iglutepit=new JButton("Iglut epít");
        iglutepit.setActionCommand("iglut épłt");
        iglutepit.addActionListener(kontroller);
        this.add(iglutepit);
    }
    //Egyéb cselekvési lehetőségek, állandóak.

    public void update(Jatekos aktivJatekos){

    }
}
