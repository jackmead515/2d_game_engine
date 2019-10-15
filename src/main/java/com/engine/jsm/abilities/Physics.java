package com.engine.jsm.abilities;

import com.engine.jsm.assets.AssetQuad;
import com.engine.jsm.boundaries.Boundary;
import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.CollisionEntity;
import com.engine.jsm.entities.Entity;
import com.engine.jsm.entities.ICreatureUpdateable;
import com.engine.jsm.entities.ISelfUpdateable;
import com.engine.jsm.main.Main;
import com.engine.jsm.util.GeomUtil;
import com.engine.jsm.util.VecUtil;
import sun.plugin2.gluegen.runtime.StructAccessor;

import java.util.ArrayList;

public class Physics implements ISelfUpdateable {

    private double[] velocity;
    private double[] force;
    private double mass;

    public Physics() {
        velocity = new double[] { 0, 0 };
        force = new double[] { 0, 0 };
    }

    /**
     * The velocity of this entity.
     * This does not include the movement
     * speed or delta time factor.
     */
    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public void setVelocityX(double x) {
        this.velocity[0] = x;
    }

    public void setVelocityY(double y) {
        this.velocity[1] = y;
    }

    public void applyVelocity(double[] velocity) {
        this.velocity[0] += velocity[0];
        this.velocity[1] += velocity[1];
    }

    public void applyForce(double[] force) {
        this.force[0] += force[0];
        this.force[1] += force[1];
    }

    public double[] getForce() {
        return force;
    }

    public void setForce(double[] force) {
        this.force = force;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public void update(Entity self) {
        Creature creature = (Creature) self;

        double delta = com.engine.jsm.main.Stats.getDelta();
        double speed = creature.getStats().getMovementSpeed();
        double[] position = creature.getPosition();
        double[] velocity = getVelocity();
        double[] force = getForce();

        double[] translation = new double[] {
            velocity[0]*speed*delta + force[0],
            velocity[1]*speed*delta + force[1]
        };

        creature.setPosition(VecUtil.add(position, translation));
        creature.setCollisionBox(creature.getBounds());

        resolveCollision(creature);
    }

    private void resolveCollision(Creature self) {
        boundaryCollision(self);
        //objectCollision(self);
    }

    private void boundaryCollision(Creature self) {
        AssetQuad<Boundary> boundaries = Main.world.getBoundaryQuad();
        ArrayList<Boundary> colliding = boundaries.query(self.getCollisionBox());
        if (colliding.size() > 0) {
            for (Boundary boundary : colliding) {
                double[] translation = GeomUtil.satIntersects(self.getCollisionBox(), boundary.getBounds());
                if (translation != null) {
                    double[] position = self.getPosition();
                    self.setPosition(VecUtil.add(position, translation));
                    self.setCollisionBox(self.getBounds());
                }
            }
        }
    }

    private void objectCollision(Creature self) {
        AssetQuad<CollisionEntity> colliders = Main.world.getCollisionEntityQuad().get(self.getLayer());
        ArrayList<CollisionEntity> colliding = colliders.query(self.getCollisionBox());
        if (colliding.size() > 0) {
            for (CollisionEntity entity : colliding) {
                double[] translation = GeomUtil.satIntersects(self.getCollisionBox(), entity.getCollisionBox());
                if (translation != null) {
                    double[] position = self.getPosition();
                    self.setPosition(VecUtil.add(position, translation));
                    self.setCollisionBox(self.getBounds());
                }
            }
        }
    }
}
