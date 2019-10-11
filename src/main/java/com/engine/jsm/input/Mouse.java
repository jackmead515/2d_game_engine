package com.engine.jsm.input;

import com.engine.jsm.main.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseMotionListener, MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        Main.guiManager.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Main.guiManager.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Main.guiManager.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Main.guiManager.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Main.guiManager.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Main.guiManager.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Main.guiManager.mouseMoved(e);
    }

}
