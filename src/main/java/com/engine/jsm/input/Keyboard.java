package com.engine.jsm.input;

import com.engine.jsm.main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Main.world.getPlayer().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Main.world.getPlayer().keyReleased(e);
    }

}
