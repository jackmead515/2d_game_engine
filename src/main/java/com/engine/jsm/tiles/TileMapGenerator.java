package com.engine.jsm.tiles;

public class TileMapGenerator {

    public static TileMap staticMap(int imageId, int width, int height) {
        int[][] tiles = new int[height][width];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tiles[y][x] = imageId;
            }
        }
        return new TileMap(tiles);
    }

}
