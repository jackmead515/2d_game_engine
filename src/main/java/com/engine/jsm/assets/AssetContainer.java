package com.engine.jsm.assets;

import com.engine.jsm.entities.Entity;
import com.engine.jsm.entities.IRenderable;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AssetContainer implements IRenderable {

    CopyOnWriteArrayList<Entity> entities;

    public AssetContainer() {
        entities = new CopyOnWriteArrayList<>();
    }

    public void add(Entity e) {
        entities.add(e);
    }

    public void remove(Entity e) {
        entities.remove(e);
    }

    public Entity get(int index) {
        return entities.get(index);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {

    }
}
