package com.engine.jsm.input;

import com.engine.jsm.main.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseMotionListener, MouseListener {

    private double[] position;
    private int mousePressed;

    public Mouse() {
        mousePressed = 0;
        position = new double[] { 0, 0 };
    }

    public int getMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(int mousePressed) {
        this.mousePressed = mousePressed;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setPosition(new double[] { e.getX(), e.getY() });
        Main.gui.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setMousePressed(e.getButton());
        this.setPosition(new double[] { e.getX(), e.getY() });
        Main.gui.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setMousePressed(0);
        this.setPosition(new double[] { e.getX(), e.getY() });
        Main.gui.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setPosition(new double[] { e.getX(), e.getY() });
        Main.gui.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setPosition(new double[] { e.getX(), e.getY() });
        Main.gui.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.setPosition(new double[] { e.getX(), e.getY() });
        Main.gui.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.setPosition(new double[] { e.getX(), e.getY() });
        Main.gui.mouseMoved(e);
    }

}
