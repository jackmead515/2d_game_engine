package com.engine.jsm.assets;

import com.engine.jsm.entities.Entity;
import com.engine.jsm.entities.IRenderable;
import com.engine.jsm.entities.IUpdateable;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class LayeredAssetContainer implements IRenderable, IUpdateable {

    CopyOnWriteArrayList<CopyOnWriteArrayList<Entity>> entities;

    public LayeredAssetContainer() {
        entities = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++) {
            entities.add(new CopyOnWriteArrayList<>());
        }
    }

    public void add(Entity e) {
        if (e.getLayer() <= entities.size()-1) {
            CopyOnWriteArrayList<Entity> layerEntities = new CopyOnWriteArrayList<>();
            layerEntities.add(e);
            entities.add(layerEntities);
        } else {
            entities.get(e.getLayer()).add(e);
        }
    }

    public void remove(Entity e) {
        if (e.getLayer() <= entities.size()-1) {
            entities.get(e.getLayer()).remove(e);
        }
    }

    public CopyOnWriteArrayList<Entity> get(int layer) {
        return entities.get(layer);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        for(CopyOnWriteArrayList<Entity> layer : entities) {
            for(Entity item : layer) {
                item.render(g2, reference);
            }
        }
    }

    @Override
    public void update() {
        for(CopyOnWriteArrayList<Entity> layer : entities) {
            for(Entity item : layer) {
                item.update();
            }
        }
    }
}
