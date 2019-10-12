package com.engine.jsm.creatures;

import com.engine.jsm.abilities.Collision;
import com.engine.jsm.abilities.Movement;
import com.engine.jsm.abilities.Stats;
import com.engine.jsm.ai.AIController;
import com.engine.jsm.entities.CollisionEntity;

public class Creature extends CollisionEntity {

    private Stats stats;
    private Movement movement;
    private AIController ai;
    private Collision collision;

    public Creature(int id) {
        super(id);
        collision = new Collision();
        ai = new AIController();
        stats = new Stats();
        movement = new Movement();
    }

    @Override
    public void update() {
        this.updateImage();
        this.updateAI();
        this.updateMovement();
        this.updateCollision();
        this.updateStats();
    }

    public void updateCollision() {
        this.getCollision().update(this);
    }

    public void updateImage() {}

    public void updateMovement() {
        this.getMovement().update(this);
    }

    public void updateAI() {
        if(this.getAI() != null) {
            this.getAI().update(this);
        }
    }

    public void updateStats() {
        this.getStats().update(this);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public AIController getAI() {
        return ai;
    }

    public void setAI(AIController ai) {
        this.ai = ai;
    }

    public Collision getCollision() {
        return collision;
    }

    public void setCollision(Collision collision) {
        this.collision = collision;
    }
}
