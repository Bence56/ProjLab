package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class MezoPanel extends JPanel {
    private BufferedImage image;

    //Ez csak ,hogy megjelenjen valami a képen betölt egy képet
    public MezoPanel() {
        try {
            image = read(new File("Resources/Assets/Jegtabla-01.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setOpaque(false);
    }

    //Azt a képet rajzolja ki amit betöltött
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}