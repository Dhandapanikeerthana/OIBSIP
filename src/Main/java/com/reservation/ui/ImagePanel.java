package com.reservation.ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(LayoutManager layout, String imagePath) {
        super(layout);
        URL imgUrl = getClass().getResource(imagePath);
        if (imgUrl != null) {
            backgroundImage = new ImageIcon(imgUrl).getImage();
        } else {
            System.err.println("Could not find background image: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
