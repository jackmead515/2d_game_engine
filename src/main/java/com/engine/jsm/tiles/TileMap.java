package com.engine.jsm.tiles;

import com.engine.jsm.entities.IRenderable;
import com.engine.jsm.images.ImageManager;
import com.engine.jsm.main.Constants;

import java.awt.*;

public class TileMap implements IRenderable {

    private int[][] tiles;

    public TileMap(int[][] tiles) {
        this.tiles = tiles;
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        for(int y = 0; y < tiles.length; y += 1) {
            for(int x = 0; x < tiles[y].length; x += 1) {
                g2.drawImage(
                    ImageManager.get(tiles[y][x])[0],
                    x * Constants.IMAGE_REFERENCE_SIZE,
                    y * Constants.IMAGE_REFERENCE_SIZE,
                    reference
                );
            }
        }
    }

    @Override
    public void renderDebug(Graphics2D g2, Canvas reference) {

    }

}
