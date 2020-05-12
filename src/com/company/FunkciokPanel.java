/*package com.company;

import javax.swing.*;
import java.awt.*;

public class FunkciokPanel extends JPanel {
    JButton kapar=new JButton("Kapar");
    JButton osszeszerel=new JButton("Összeszerel");
    JButton iglutepit=new JButton("Iglut épít");
    JButton vizsgal=new JButton("Vizsgál");


    public FunkciokPanel(Kontroller kontroller){

        GridLayout gl3=new GridLayout(2,2);
        //this.setLayout(gl3);
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
        iglutepit.setActionCommand("iglut épít");
        iglutepit.addActionListener(kontroller);
        this.add(iglutepit);
    }
    //Egyéb cselekvési lehetőségek, állandóak.

    public void update(Jatekos aktivJatekos){
        revalidate();
    }
}
*/
package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FunkciokPanel extends JPanel {
    // Játékos cuccainak a panelja
    JButton kapar=new JButton();
    JButton osszeszerel=new JButton();
    JButton iglutepit=new JButton();
    JLabel vizsgal=new JLabel();




    private JButton balfel=new JButton();
    private JButton fel=new JButton();
    private JButton bal=new JButton();
    private JButton jobbfel=new JButton();

    private JButton jobb=new JButton();
    private JButton balle=new JButton();
    private JButton le=new JButton();
    private JButton jobble=new JButton();

    private ImageIcon osszeszerelim=new ImageIcon("Resources/Assets/osszeszerel.png");
    private ImageIcon nagyitoim=new ImageIcon("Resources/Assets/nagyito.png");
    private ImageIcon x=new ImageIcon("Resources/Assets/x.png");
    private ImageIcon csakanyim=new ImageIcon("Resources/Assets/csákány.png");
    private ImageIcon igluim=new ImageIcon("Resources/Assets/Iglu_I-01_kisebb.png");

    public FunkciokPanel(Kontroller kontroller){


        JPanel targyak=new JPanel();
        targyak.setPreferredSize(new Dimension(220,220));
        GridLayout gl1=new GridLayout(2,2);
        targyak.setLayout(gl1);


        this.add(kapar);
        //kapar.setPreferredSize(new Dimension(90,90));
        kapar.setIcon(csakanyim);
        targyak.add(kapar);
        kapar.setActionCommand("kapar");
        kapar.addActionListener(kontroller);

        osszeszerel.setIcon(osszeszerelim);
        targyak.add(osszeszerel);
        osszeszerel.setActionCommand("osszeszerel");
        osszeszerel.addActionListener(kontroller);

        //Vizsgál panel irányonként egy gombbal
        JPanel targyak2=new JPanel();
        GridLayout gl5=new GridLayout(3,3);
        targyak2.setLayout(gl5);


        //BalFel
        //balfel.setPreferredSize(new Dimension(60,60));
        balfel.setIcon(x);
        balfel.setActionCommand("balfentről");
        balfel.addActionListener(kontroller);
        targyak2.add(balfel);

        //Fel
        //  fel.setPreferredSize(new Dimension(60,60));
        fel.setIcon(x);
        fel.setActionCommand("fentről");
        fel.addActionListener(kontroller);
        targyak2.add(fel);

        //JobbFel
        //  jobbfel.setPreferredSize(new Dimension(60,60));
        jobbfel.setIcon(x);
        jobbfel.setActionCommand("jobbfentről");
        jobbfel.addActionListener(kontroller);
        targyak2.add(jobbfel);


        //Bal
        // bal.setPreferredSize(new Dimension(60,60));
        bal.setIcon(x);
        bal.setActionCommand("balról");
        bal.addActionListener(kontroller);
        targyak2.add(bal);

        //kotel.setPreferredSize(new Dimension(60,60));
        vizsgal.setIcon(nagyitoim);
        targyak2.add(vizsgal);

        //Jobb
        //jobb.setPreferredSize(new Dimension(60,60));
        jobb.setIcon(x);
        jobb.setActionCommand("jobbról");
        jobb.addActionListener(kontroller);
        targyak2.add(jobb);


        //BalLe
        //balle.setPreferredSize(new Dimension(60,60));
        balle.setIcon(x);
        balle.setActionCommand("ballentről");
        balle.addActionListener(kontroller);
        targyak2.add(balle);

        //Le
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
        iglutepit.setPreferredSize(new Dimension(90,90));
        iglutepit.setIcon(igluim); //pisztolyNULL
        iglutepit.setActionCommand("iglut epít");
        iglutepit.addActionListener(kontroller);
        targyak.add(iglutepit);

        targyak.add(targyak2, BorderLayout.LINE_START);
        this.add(targyak);
    }
    public void update(Jatekos aktivJatekos){
        revalidate();
    }
}
