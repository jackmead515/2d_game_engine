package com.engine.jsm.world;

import com.engine.jsm.assets.LayeredAssetContainer;
import com.engine.jsm.entities.Debug;
import com.engine.jsm.entities.IRenderable;
import com.engine.jsm.entities.IUpdateable;
import com.engine.jsm.images.ImageConstants;
import com.engine.jsm.main.Constants;
import com.engine.jsm.main.Main;
import com.engine.jsm.main.Stats;
import com.engine.jsm.player.Player;
import com.engine.jsm.sprites.Sprite;
import com.engine.jsm.tiles.TileMap;
import com.engine.jsm.tiles.TileMapGenerator;

import java.awt.*;

public class World implements IRenderable, IUpdateable {

    private int idCounter;
    private Player player;
    private Camera camera;
    private Debug debug;
    private TileMap backgroundImage;
    private LayeredAssetContainer sprites;

    public World() {}

    public void init() {
        idCounter = 0;
        debug = new Debug();
        camera = new Camera();
        sprites = new LayeredAssetContainer();
        backgroundImage = TileMapGenerator.staticMap(ImageConstants.GRASS_BACKGROUND, Constants.WIDTH_REF_RATIO, Constants.HEIGHT_REF_RATIO);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        backgroundImage.render(g2, reference);
        sprites.render(g2, reference);
        debug.render(g2, reference);
        Main.guiManager.render(g2, reference);
    }

    private long lastUpdate;
    @Override
    public void update() {
        if(Stats.getGameTime()-lastUpdate >= 1000000) {
            lastUpdate = Stats.getGameTime();
            //camera.pan(0.1f, 0.1f);
        }
        sprites.update();
        Main.guiManager.update();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public TileMap getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(TileMap backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void addSprite(Sprite sprite) {
        sprite.setId(idCounter++);
        sprites.add(sprite);
    }
}
