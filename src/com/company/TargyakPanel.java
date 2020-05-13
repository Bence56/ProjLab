package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TargyakPanel extends JPanel {
    // Játékos cuccainak a panelja
    private JButton lapat = new JButton();
    private JButton sator = new JButton();
    private JButton alkatresz = new JButton();
    private JLabel kotel = new JLabel();


    private JButton balfel = new JButton();
    private JButton fel = new JButton();
    private JButton bal = new JButton();
    private JButton jobbfel = new JButton();

    private JButton jobb = new JButton();
    private JButton balle = new JButton();
    private JButton le = new JButton();
    private JButton jobble = new JButton();

    private ArrayList<BufferedImage> retegek=new ArrayList<BufferedImage>();

    private ImageIcon kotelim=new ImageIcon("Resources/Assets/Kotel_I-02_kisebb.png");
    private ImageIcon kotelimNULL=new ImageIcon("Resources/Assets/Kotel_I_NULL-02_kisebb.png");
    private ImageIcon lapatim = new ImageIcon("Resources/Assets/Lapat_I-01.png");
    private ImageIcon lapatimNULL = new ImageIcon("Resources/Assets/Lapat_I_NULL-01.png");
    private ImageIcon satorim = new ImageIcon("Resources/Assets/Sator_I-01_kisebb.png");
    private ImageIcon satorimNULL = new ImageIcon("Resources/Assets/Sator_I_NULL-01_kisebb.png");
    private ImageIcon x = new ImageIcon("Resources/Assets/x.png");
    private ImageIcon kotelim1 = new ImageIcon("Resources/Assets/Kotel_I-02.png");
    private ImageIcon kotelimNULL1 = new ImageIcon("Resources/Assets/Kotel_I_NULL-02.png");
    private BufferedImage pisztolyNULL;
    private BufferedImage pisztolyegy;
    private BufferedImage pisztolyket;
    private BufferedImage pisztolyhar;

    public TargyakPanel(Kontroller kontroller) {

        try {
            pisztolyNULL = ImageIO.read(new File("Resources/Assets/Pisztoly_I_NULL-01.png"));
            pisztolyegy = ImageIO.read(new File("Resources/Assets/1-Pisztoly_I-01.png"));
            pisztolyket = ImageIO.read(new File("Resources/Assets/2-Pisztoly_I-01.png"));
            pisztolyhar = ImageIO.read(new File("Resources/Assets/3-Pisztoly_I-01.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        retegek.add(pisztolyNULL);

        JPanel targyak = new JPanel();
        targyak.setPreferredSize(new Dimension(220, 220));
        GridLayout gl1 = new GridLayout(2, 2);
        targyak.setLayout(gl1);

      //  lapat.setPreferredSize(new Dimension(80, 80));
        lapat.setIcon((lapatim));
        targyak.add(lapat);
        lapat.setActionCommand("lapatol");
        lapat.addActionListener(kontroller);


      //  sator.setPreferredSize(new Dimension(80, 80));
        sator.setIcon(satorimNULL);
        targyak.add(sator);
        sator.setActionCommand("satrat epit");
        sator.addActionListener(kontroller);

        //Kihúz panel irányonként egy gombbal
        JPanel targyak2 = new JPanel();
        GridLayout gl5 = new GridLayout(3, 3);
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
        kotel.setIcon(kotelim);
        targyak2.add(kotel);

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
       // alkatresz.setPreferredSize(new Dimension(80, 80));
        //alkatresz.setIcon(pisztolyNULL);
        alkatresz.setActionCommand("lerak");
        alkatresz.addActionListener(kontroller);
        targyak.add(alkatresz);

        targyak.add(targyak2, BorderLayout.LINE_START);
        this.add(targyak);
    }

    public void update(Jatekos aktivJatekos) {
        LapatVisitor lv = new LapatVisitor();
        SatorVisitor sv = new SatorVisitor();
        KotelVisitor kv = new KotelVisitor();
        AlkatreszVisitor av = new AlkatreszVisitor();
        fel.setIcon(x);
        jobbfel.setIcon(x);
        jobb.setIcon(x);
        jobble.setIcon(x);
        le.setIcon(x);
        balle.setIcon(x);
        bal.setIcon(x);
        balfel.setIcon(x);
        lapat.setIcon(lapatimNULL);
        sator.setIcon(satorimNULL);
        //alkatresz.setIcon(pisztolyNULL);
        kotel.setIcon(kotelimNULL);
        //TODO hogy kell összemergelni a képeket helyesen, ha csak két alkatrészem van pl.?
        boolean vankotel = false;
        for (Targy t : aktivJatekos.getTargyak()) {
            if (t.accept(lv)) lapat.setIcon(lapatim);
            else if (t.accept(sv)) sator.setIcon(satorim);
            else if (t.accept(kv)) {
                kotel.setIcon(kotelim);
                vankotel = true;
            }
        }
        Mezo m = aktivJatekos.getTartozkodasiMezo();
        if (vankotel) {
            if (m.getSzomszed(Irany.Fel) != null) fel.setIcon(kotelim);
            if (m.getSzomszed(Irany.JobbFel) != null) jobbfel.setIcon(kotelim);
            if (m.getSzomszed(Irany.Jobb) != null) jobb.setIcon(kotelim);
            if (m.getSzomszed(Irany.JobbLe) != null) jobble.setIcon(kotelim);
            if (m.getSzomszed(Irany.Le) != null) le.setIcon(kotelim);
            if (m.getSzomszed(Irany.BalLe) != null) balle.setIcon(kotelim);
            if (m.getSzomszed(Irany.Bal) != null) bal.setIcon(kotelim);
            if (m.getSzomszed(Irany.BalFel) != null) balfel.setIcon(kotelim);
        }

        retegek.clear();
        retegek.add(pisztolyNULL);
        for (Alkatresz a:aktivJatekos.getAlkatreszek()) {
            if (a.ID==0)retegek.add(pisztolyegy);
            if (a.ID==1)retegek.add(pisztolyket);
            if (a.ID==2)retegek.add(pisztolyhar);
        }
        revalidate();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage combined = new BufferedImage(pisztolyegy.getWidth(), pisztolyegy.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics ger = combined.getGraphics();
        for (BufferedImage img:retegek) {
            ger.drawImage(img,0,0,null);
        }
        ger.dispose();
        alkatresz.setIcon(new ImageIcon(combined));
    }
}