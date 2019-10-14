package com.engine.jsm.abilities;

import com.engine.jsm.assets.AssetQuad;
import com.engine.jsm.boundaries.Boundary;
import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.ICreatureUpdateable;
import com.engine.jsm.main.Main;
import com.engine.jsm.util.GeomUtil;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Collision implements ICreatureUpdateable {

    public Collision() {}

    @Override
    public void update(Creature self) {
        self.setCollisionBox(self.getBounds());
        resolveCollision(self);
    }

    public void resolveCollision(Creature self) {
        AssetQuad<Boundary> boundaries = Main.world.getBoundaryQuad();
        ArrayList<Boundary> colliding = boundaries.query(self.getCollisionBox());
        if (colliding.size() > 0) {
            for (Boundary boundary : colliding) {
                double[] translation = GeomUtil.satWillIntersect(
                    self.getBounds(),
                    boundary.getBounds(),
                    self.getMovement().getProjectedVelocity(self)
                );
                if (translation != null) {
                    double[] position = self.getNextPosition();
                    self.setPositionX(position[0]+translation[0]);
                    self.setPositionY(position[1]+translation[1]);
                    break;
                }
            }
        } else {
            self.setPosition(self.getNextPosition());
        }
    }
}
