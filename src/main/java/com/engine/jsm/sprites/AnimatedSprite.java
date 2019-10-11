package com.engine.jsm.sprites;

import com.engine.jsm.images.ImageManager;
import com.engine.jsm.main.Stats;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends Sprite {

    private BufferedImage[] images;
    private int imageIndex;
    private long updateTime;
    private long lastUpdate;

    public AnimatedSprite(int id, int imageId) {
        super(id);
        imageIndex = 0;
        lastUpdate = 0;
        updateTime = 0;
        images = ImageManager.get(imageId);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        double[] bounds = getScaledBounds();
        g2.drawImage(getImages()[getImageIndex()], (int)bounds[0], (int)bounds[1], reference);
    }

    @Override
    public void update() {
        super.update();
        if (Stats.getGameTime() - getLastUpdate() >= getUpdateTime()) {
            setLastUpdate(Stats.getGameTime());
            if (getImageIndex() >= getImages().length-1) {
                setImageIndex(0);
            } else {
                setImageIndex(getImageIndex()+1);
            }
        }
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public BufferedImage[] getImages() { return images; }

    public void setImages(BufferedImage[] images) { this.images = images; }
}
