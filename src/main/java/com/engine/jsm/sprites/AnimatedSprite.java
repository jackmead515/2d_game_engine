package com.engine.jsm.sprites;

import com.engine.jsm.images.ImageManager;
import com.engine.jsm.main.Stats;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends Sprite {

    private BufferedImage[] images;
    private long updateTime;

    public AnimatedSprite(int id, int imageId) {
        super(id);
        updateTime = 0;
        images = ImageManager.get(imageId);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        double[] bounds = getScaledBounds();
        g2.drawImage(getImages()[getImageIndex()], (int)bounds[0], (int)bounds[1], reference);
    }

    public int getImageIndex() {
        return (int)((Stats.getGameTime() % (getImages().length * getUpdateTime())) / getUpdateTime());
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public BufferedImage[] getImages() { return images; }

    public void setImages(BufferedImage[] images) { this.images = images; }
}
