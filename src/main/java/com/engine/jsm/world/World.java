package com.engine.jsm.world;

import com.engine.jsm.boundaries.Boundary;
import com.engine.jsm.assets.AssetContainer;
import com.engine.jsm.assets.AssetQuad;
import com.engine.jsm.assets.LayeredAssetContainer;
import com.engine.jsm.entities.CollisionEntity;
import com.engine.jsm.entities.IRenderable;
import com.engine.jsm.entities.IUpdateable;
import com.engine.jsm.images.ImageConstants;
import com.engine.jsm.main.Constants;
import com.engine.jsm.main.Main;
import com.engine.jsm.player.Player;
import com.engine.jsm.sprites.Sprite;
import com.engine.jsm.tiles.TileMap;
import com.engine.jsm.tiles.TileMapGenerator;

import java.awt.*;

public class World implements IRenderable, IUpdateable {

    private int idCounter;
    private Player player;
    private Camera camera;
    private TileMap backgroundImage;
    private LayeredAssetContainer<Sprite> sprites;
    private LayeredAssetContainer<CollisionEntity> collisionEntities;
    private AssetContainer<Boundary> boundaries;
    private AssetQuad<Boundary> boundaryQuad;

    public World() {}

    public void init() {
        idCounter = 0;
        player = new Player(0);
        player.setBounds(new double[] { 200, 800, 32, 32 });

        camera = new Camera();
        collisionEntities = new LayeredAssetContainer<>();
        sprites = new LayeredAssetContainer<>();
        boundaries = new AssetContainer<>();
        boundaryQuad = new AssetQuad<>(0, 100, 100, new double[] {0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT});
        backgroundImage = TileMapGenerator.staticMap(ImageConstants.GRASS_BACKGROUND, Constants.WIDTH_REF_RATIO, Constants.HEIGHT_REF_RATIO);
    }

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        backgroundImage.render(g2, reference);
        player.render(g2, reference);
        sprites.render(g2, reference);
        collisionEntities.render(g2, reference);
        boundaries.render(g2, reference);
    }

    @Override
    public void renderDebug(Graphics2D g2, Canvas reference) {
        backgroundImage.renderDebug(g2, reference);
        player.renderDebug(g2, reference);
        sprites.renderDebug(g2, reference);
        collisionEntities.renderDebug(g2, reference);
        boundaries.renderDebug(g2, reference);
    }

    @Override
    public void update() {
        boundaryQuad.reset();
        boundaryQuad.insertAll(boundaries.list());
        camera.update();
        player.update();
        sprites.update();
        collisionEntities.update();
        Main.gui.update();
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

    public void addBoundary(Boundary boundary) {
        boundary.setId(idCounter++);
        boundaries.add(boundary);
        boundaryQuad.insert(boundary);
    }

    public void addCollisionEntity(CollisionEntity entity) {
        entity.setId(idCounter++);
        collisionEntities.add(entity);
    }

    public LayeredAssetContainer getSprites() {
        return sprites;
    }

    public AssetContainer<Boundary> getBoundaries() {
        return boundaries;
    }

    public LayeredAssetContainer<CollisionEntity> getCollisionEntities() { return collisionEntities; }

    public AssetQuad<Boundary> getBoundaryQuad() {
        return boundaryQuad;
    }
}
