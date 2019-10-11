package com.engine.jsm.ui;

import com.engine.jsm.entities.IRenderable;
import com.engine.jsm.entities.IUpdateable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUIManager implements IUpdateable, IRenderable, MouseMotionListener, MouseListener {

    private int idCounter;
    CopyOnWriteArrayList<CopyOnWriteArrayList<GUI>> items;

    public GUIManager() {
        items = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++) {
            items.add(new CopyOnWriteArrayList<>());
        }
    }

    public void add(GUI gui) {
        gui.setId(idCounter++);
        if (gui.getLayer() >= items.size()) {
            CopyOnWriteArrayList<GUI> layerEntities = new CopyOnWriteArrayList<>();
            layerEntities.add(gui);
            items.add(layerEntities);
        } else {
            items.get(gui.getLayer()).add(gui);
        }
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.render(g2, reference);
            }
        }
    }

    @Override
    public void update() {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.update();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.mouseClicked(e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.mousePressed(e);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.mouseReleased(e);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.mouseEntered(e);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.mouseExited(e);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.mouseDragged(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(CopyOnWriteArrayList<GUI> layer : items) {
            for(GUI item : layer) {
                item.mouseMoved(e);
            }
        }
    }
}
