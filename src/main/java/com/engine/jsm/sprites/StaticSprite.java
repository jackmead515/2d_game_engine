package com.engine.jsm.sprites;

import com.engine.jsm.images.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticSprite extends Sprite {

    private BufferedImage image;

    public StaticSprite(int id, int imageId) {
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
