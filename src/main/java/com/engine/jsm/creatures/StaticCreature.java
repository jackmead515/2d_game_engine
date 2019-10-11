package com.engine.jsm.creatures;

import com.engine.jsm.images.ImageManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class StaticCreature extends Creature {

    protected BufferedImage image;

    public StaticCreature(int id, int imageId) {
        super(id);
        image = ImageManager.get(imageId)[0];
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        double[] bounds = getScaledBounds();
        g2.drawImage(getImage(), (int)bounds[0], (int)bounds[1], reference);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
