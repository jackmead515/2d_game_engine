package com.engine.jsm.assets;

import com.engine.jsm.entities.Entity;
import com.engine.jsm.entities.IRenderable;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AssetContainer<T extends Entity> implements IRenderable {

    CopyOnWriteArrayList<T> entities;

    public AssetContainer() {
        entities = new CopyOnWriteArrayList<>();
    }

    public void add(T e) {
        entities.add(e);
    }

    public void remove(T e) {
        entities.remove(e);
    }

    public T get(int index) {
        return entities.get(index);
    }

    public CopyOnWriteArrayList<T> list() {
        return entities;
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        for (T item : entities) {
            item.render(g2, reference);
        }
    }

    @Override
    public void renderDebug(Graphics2D g2, Canvas reference) {
        for (T item : entities) {
            item.renderDebug(g2, reference);
        }
    }
}
