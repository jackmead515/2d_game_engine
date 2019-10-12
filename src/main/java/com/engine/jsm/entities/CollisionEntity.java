package com.engine.jsm.entities;

import com.engine.jsm.abilities.CollisionType;

public class CollisionEntity extends Entity {

    private CollisionType boundsType;
    private boolean collisionEnabled;
    private double[] collisionBox;

    public CollisionEntity(int id) {
        super(id);
        collisionEnabled = true;
        collisionBox = getBounds();
    }

    public CollisionType getBoundsType() {
        return boundsType;
    }

    public void setBoundsType(CollisionType boundsType) {
        this.boundsType = boundsType;
    }

    public boolean isCollisionEnabled() {
        return collisionEnabled;
    }

    public void setCollisionEnabled(boolean collisionEnabled) {
        this.collisionEnabled = collisionEnabled;
    }

    public double[] getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(double[] collisionBox) {
        this.collisionBox = collisionBox;
    }
}
