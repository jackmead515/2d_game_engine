package com.engine.jsm.abilities;

import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.ICreatureUpdateable;

public class Movement implements ICreatureUpdateable {

    private double[] velocity;
    private double[] force;

    public Movement() {
        velocity = new double[] { 0, 0 };
        force = new double[] { 0, 0 };
    }

    /**
     * This gets the velocity of the target after
     * the delta time and movement speed have been
     * included.
     * @param self - A reference to the creature
     */
    public double[] getProjectedVelocity(Creature self) {
        double delta = com.engine.jsm.main.Stats.getDelta();
        double speed = self.getStats().getMovementSpeed();
        double[] velocity = getVelocity();
        return new double[] {
            velocity[0]*speed*delta,
            velocity[1]*speed*delta
        };
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

    @Override
    public void update(Creature self) {
        double[] force = getForce();
        double[] position = self.getPosition();
        double[] velocity = getProjectedVelocity(self);

        self.setNextPosition(new double[] {
            position[0]+velocity[0]+force[0],
            position[1]+velocity[1]+force[1]
        });
    }
}
