package com.engine.jsm.player;

import com.engine.jsm.creatures.Creature;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Creature implements KeyListener {

    public Player(int id) {
        super(id);
        this.getStats().setMovementSpeed(3);
        this.setAI(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W: getMovement().setVelocityY(-1); break;
            case KeyEvent.VK_A: getMovement().setVelocityX(-1); break;
            case KeyEvent.VK_S: getMovement().setVelocityY(1); break;
            case KeyEvent.VK_D: getMovement().setVelocityX(1); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W: getMovement().setVelocityY(0); break;
            case KeyEvent.VK_A: getMovement().setVelocityX(0); break;
            case KeyEvent.VK_S: getMovement().setVelocityY(0); break;
            case KeyEvent.VK_D: getMovement().setVelocityX(0); break;
        }
    }
}
