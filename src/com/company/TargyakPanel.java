package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TargyakPanel extends JPanel {
    // Játékos cuccainak a panelja
    private JButton lapat=new JButton();
    private JButton sator=new JButton();
    private JButton alkatresz=new JButton();


    private JButton balfel=new JButton();
    private JButton fel=new JButton();
    private JButton bal=new JButton();
    private JButton jobbfel=new JButton();
    private JLabel kotel=new JLabel();
    private JButton jobb=new JButton();
    private JButton balle=new JButton();
    private JButton le=new JButton();
    private JButton jobble=new JButton();



    private ImageIcon lapatim=new ImageIcon("Resources/Assets/Lapat_I-01.png");
    private ImageIcon lapatimNULL=new ImageIcon("Resources/Assets/Lapat_I_NULL-01.png");
    private ImageIcon satorim=new ImageIcon("Resources/Assets/Sator_I-01.png");
    private ImageIcon satorimNULL=new ImageIcon("Resources/Assets/Sator_I_NULL-01.png");
    private ImageIcon x=new ImageIcon("Resources/Assets/x.png");
    private ImageIcon kotelim=new ImageIcon("Resources/Assets/Kotel_I-01.png");
    private ImageIcon kotelimNULL=new ImageIcon("Resources/Assets/Kotel_I_NULL-01.png");
    private ImageIcon pisztolyegy=new ImageIcon("Resources/Assets/1-Pisztoly_I-01.png");
    private ImageIcon pisztolyket=new ImageIcon("Resources/Assets/2-Pisztoly_I-01.png");
    private ImageIcon pisztolyhar=new ImageIcon("Resources/Assets/3-Pisztoly_I-01.png");
    private ImageIcon pisztolyNULL=new ImageIcon("Resources/Assets/Pisztoly_I_NULL-01.png");
    public TargyakPanel(Kontroller kontroller){

       // BoxLayout box=new BoxLayout(this,2);
       // this.setLayout(box);

        JPanel targyak=new JPanel();
        targyak.setPreferredSize(new Dimension(256,300));
        GridLayout gl1=new GridLayout(2,2);
        targyak.setLayout(gl1);

        lapat.setPreferredSize(new Dimension(100,100));
        lapat.setIcon((lapatim));
        targyak.add(lapat);
        lapat.setActionCommand("lapatol");
        lapat.addActionListener(kontroller);


        sator.setPreferredSize(new Dimension(100,100));
        sator.setIcon(satorim);
        targyak.add(sator);
        sator.setActionCommand("satrat epit");
        sator.addActionListener(kontroller);
        JPanel targyak2=new JPanel();
        GridLayout gl5=new GridLayout(3,3);
        targyak2.setLayout(gl5);


        //balfel.setPreferredSize(new Dimension(60,60));
        balfel.setIcon(x);
        balfel.setActionCommand("balfentről");
        balfel.addActionListener(kontroller);
        targyak2.add(balfel);
        //  fel.setPreferredSize(new Dimension(60,60));
        fel.setIcon(x);
        fel.setActionCommand("fentről");
        fel.addActionListener(kontroller);
        targyak2.add(fel);
        //  jobbfel.setPreferredSize(new Dimension(60,60));
        jobbfel.setIcon(x);
        jobbfel.setActionCommand("jobbfentről");
        jobbfel.addActionListener(kontroller);
        targyak2.add(jobbfel);
        // bal.setPreferredSize(new Dimension(60,60));
        bal.setIcon(x);
        targyak2.add(bal);
        //kotel.setPreferredSize(new Dimension(60,60));

        kotel.setIcon(kotelim);
        targyak2.add(kotel);
        //jobb.setPreferredSize(new Dimension(60,60));
        jobb.setIcon(x);
        targyak2.add(jobb);
        //balle.setPreferredSize(new Dimension(60,60));
        balle.setIcon(x);
        balle.setActionCommand("ballentről");
        balle.addActionListener(kontroller);
        targyak2.add(balle);
        // le.setPreferredSize(new Dimension(60,60));
        le.setIcon(x);
        le.setActionCommand("lentről");
        le.addActionListener(kontroller);
        targyak2.add(le);
        //jobble.setPreferredSize(new Dimension(60,60));
        jobble.setIcon(x);
        jobble.setActionCommand("jobblentről");
        jobble.addActionListener(kontroller);
        targyak2.add(jobble);
        targyak.add(targyak2);
        //Az alkatrész lerak úgy van megírva, hogy bármennyi alkatrészünk van, 1 lerak() hívással a 0. indexűt rakjuk le.  Itt 1 alkatrészt


        alkatresz.setPreferredSize(new Dimension(100,100));
        alkatresz.setIcon(pisztolyNULL);
        alkatresz.setActionCommand("lerak");
        alkatresz.addActionListener(kontroller);
        targyak.add(alkatresz);

        targyak.add(targyak2, BorderLayout.LINE_START);
        this.add(targyak);
    }
    public void update(Jatekos aktivJatekos){

    }
}
