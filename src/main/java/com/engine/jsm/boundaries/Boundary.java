package com.engine.jsm.boundaries;

import com.engine.jsm.entities.Entity;

import java.awt.*;

public class Boundary extends Entity {
    public Boundary(int id) {
        super(id);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        double[] bounds = getBounds();
        g2.setColor(Color.GREEN);
        g2.drawRect((int)bounds[0], (int)bounds[1], (int)bounds[2], (int)bounds[3]);
    }
}
