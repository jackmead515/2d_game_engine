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
            double[] position = self.getTopLeftCorner();
            double[] dimensions = self.getDimensions();
            double[] nextPosition = self.getNextPosition();
            nextPosition[0] = nextPosition[0] - dimensions[0]/2;
            nextPosition[1] = nextPosition[1] - dimensions[1]/2;
            double[] resolvedPosition = new double[] { nextPosition[0], position[1] };
            double[] nextCollisionBox = new double[] { resolvedPosition[0], resolvedPosition[1], dimensions[0], dimensions[1] };
            Rectangle2D intersectionX = GeomUtil.createIntersectingUnion(colliding, nextCollisionBox);
            if (intersectionX != null) {
                if (self.getCollisionBox()[0] < nextCollisionBox[0]) {
                    resolvedPosition[0] = Math.max(position[0], resolvedPosition[0] - intersectionX.getWidth());
                } else {
                    resolvedPosition[0] = Math.min(position[0], resolvedPosition[0] + intersectionX.getWidth());
                }
            }

            resolvedPosition[1] = nextPosition[1];
            nextCollisionBox = new double[] { resolvedPosition[0], resolvedPosition[1], dimensions[0], dimensions[1] };
            Rectangle2D intersectionY = GeomUtil.createIntersectingUnion(colliding, nextCollisionBox);
            if (intersectionY != null) {
                if (self.getCollisionBox()[1] < nextCollisionBox[1]) {
                    resolvedPosition[1] = Math.max(position[1], resolvedPosition[1] - intersectionX.getHeight());
                } else {
                    resolvedPosition[1] = Math.min(position[1], resolvedPosition[1] + intersectionX.getHeight());
                }
            }

            self.setPositionX(resolvedPosition[0]+dimensions[0]/2);
            self.setPositionY(resolvedPosition[1]+dimensions[1]/2);
        } else {
            self.setPosition(self.getNextPosition());
        }
    }
}
