package com.engine.jsm.images;

import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;

public class ImageStore {

    private ConcurrentHashMap<Integer, BufferedImage[]> images;

    public ImageStore() {
        images = new ConcurrentHashMap<>();
    }

    public void put(int id, BufferedImage[] sheet) {
        images.put(id, sheet);
    }

    public BufferedImage[] get(int id) {
        return images.get(id);
    }

}
