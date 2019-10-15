package com.engine.jsm.assets;

import com.engine.jsm.entities.Entity;
import com.engine.jsm.entities.IRenderable;
import com.engine.jsm.entities.IUpdateable;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class LayeredAssetContainer<T extends Entity> implements IRenderable, IUpdateable {

    CopyOnWriteArrayList<CopyOnWriteArrayList<T>> entities;

    public LayeredAssetContainer() {
        entities = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++) {
            entities.add(new CopyOnWriteArrayList<>());
        }
    }

    public void add(T e) {
        if (e.getLayer() <= entities.size()-1) {
            CopyOnWriteArrayList<T> layerEntities = new CopyOnWriteArrayList<>();
            layerEntities.add(e);
            entities.add(layerEntities);
        } else {
            entities.get(e.getLayer()).add(e);
        }
    }

    public CopyOnWriteArrayList<T> list() {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        for (CopyOnWriteArrayList l : entities) {
            list.addAll(l);
        }
        return list;
    }

    public void remove(T e) {
        if (e.getLayer() <= entities.size()-1) {
            entities.get(e.getLayer()).remove(e);
        }
    }

    public CopyOnWriteArrayList<T> get(int layer) {
        if (layer <= entities.size()-1) {
            return null;
        }
        return entities.get(layer);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        for(CopyOnWriteArrayList<T> layer : entities) {
            for(T item : layer) {
                item.render(g2, reference);
            }
        }
    }

    @Override
    public void renderDebug(Graphics2D g2, Canvas reference) {
        for(CopyOnWriteArrayList<T> layer : entities) {
            for(T item : layer) {
                item.renderDebug(g2, reference);
            }
        }
    }

    @Override
    public void update() {
        for(CopyOnWriteArrayList<T> layer : entities) {
            for(T item : layer) {
                item.update();
            }
        }
    }
}
