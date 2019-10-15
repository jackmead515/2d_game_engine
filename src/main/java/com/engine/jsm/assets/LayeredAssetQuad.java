package com.engine.jsm.assets;

import com.engine.jsm.entities.Entity;
import com.engine.jsm.main.Constants;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LayeredAssetQuad<T extends Entity> {

    CopyOnWriteArrayList<AssetQuad<T>> entities;

    public LayeredAssetQuad() {
        entities = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++) {
            entities.add(new AssetQuad<>(0, 100, 100, new double[] {0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT}));
        }
    }

    public void addAll(List<T> list) {
        for (T e : list) { add(e); }
    }

    public void add(T e) {
        if (e.getLayer() <= entities.size()-1) {
            AssetQuad<T> layerEntities = new AssetQuad<>(0, 100, 100, new double[] {0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT});
            layerEntities.insert(e);
            entities.add(layerEntities);
        } else {
            entities.get(e.getLayer()).insert(e);
        }
    }

    public void reset() {
        for(AssetQuad q : entities) {
            q.reset();
        }
    }

    public AssetQuad<T> get(int layer) {
        if (layer <= entities.size()-1) {
            return null;
        }
        return entities.get(layer);
    }

}
