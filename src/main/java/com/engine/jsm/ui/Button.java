package com.engine.jsm.ui;

import com.engine.jsm.util.GeomUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends GUI {

    private BufferedImage image;
    private boolean isPressed;
    private boolean isHovered;

    public Button(int id) {
        super(id);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        renderImage(g2, reference);
        if (isHovered()) {
            renderHovered(g2, reference);
        }
        if (isPressed()) {
            renderPressed(g2, reference);
        }
    }

    @Override
    public void renderDebug(Graphics2D g2, Canvas reference) {
        if (isHovered()) {
            double[] bounds = getScaledBounds();
            g2.setColor(Color.GREEN);
            g2.drawRect((int)bounds[0], (int)bounds[1], (int)bounds[2], (int)bounds[3]);
        }
        if (isPressed()) {
            double[] bounds = getScaledBounds();
            double x = bounds[0]; double y = bounds[1];
            double w = bounds[2]; double h = bounds[3];
            g2.setColor(Color.GREEN);
            g2.drawLine((int)x, (int)y, (int)(x+w), (int)(y+h));
            g2.drawLine((int)x+(int)w, (int)y, (int)x, (int)(y+h));
        }
    }

    public void renderImage(Graphics2D g2, Canvas reference) {
        double[] bounds = getScaledBounds();
        g2.drawImage(getImage(), (int)bounds[0], (int)bounds[1], reference);
    }

    public void renderHovered(Graphics2D g2, Canvas reference) {}

    public void renderPressed(Graphics2D g2, Canvas reference) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        double[] point = new double[] { e.getX(), e.getY() };
        if (GeomUtil.contains(getScaledBounds(), point)) {
            setHovered(true);
        } else {
            setHovered(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double[] point = new double[] { e.getX(), e.getY() };
        if (e.getButton() == MouseEvent.BUTTON1 && GeomUtil.contains(getScaledBounds(), point)) {
            setPressed(true);
        } else {
            setPressed(false);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setPressed(false);
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public boolean isHovered() {
        return isHovered;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
