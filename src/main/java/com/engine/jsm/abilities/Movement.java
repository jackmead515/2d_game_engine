package com.engine.jsm.abilities;

import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.ICreatureUpdateable;

public class Movement implements ICreatureUpdateable {

    private double[] velocity;
    private double[] force;
    private float acceleration;
    private float deceleration;

    public Movement() {
        velocity = new double[] { 0, 0 };
        force = new double[] { 0, 0 };
        acceleration = 1;
        deceleration = 1;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
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
        double delta = com.engine.jsm.main.Stats.getDelta();
        double speed = self.getStats().getMovementSpeed();
        double[] force = getForce();
        double[] velocity = getVelocity();
        double[] position = self.getPosition();

        double x = position[0];
        double y = position[1];
        x+=velocity[0]*speed*delta;
        y+=velocity[1]*speed*delta;
        x+=force[0];
        y+=force[1];

        self.setNextPosition(new double[] { x, y });
        //self.setPosition(new double[] { x, y });
    }
}
