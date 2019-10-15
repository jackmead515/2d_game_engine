package com.engine.jsm.creatures;

import com.engine.jsm.abilities.Physics;
import com.engine.jsm.abilities.Stats;
import com.engine.jsm.ai.AIController;
import com.engine.jsm.entities.CollisionEntity;

public class Creature extends CollisionEntity {

    private Stats stats;
    private Physics physics;
    private AIController ai;

    public Creature(int id) {
        super(id);
        ai = new AIController();
        stats = new Stats();
        physics = new Physics();
    }

    @Override
    public void update() {
        this.updateImage();
        this.updateAI();
        this.updatePhysics();
        this.updateStats();
    }

    public void updateImage() {}

    public void updatePhysics() {
        this.getPhysics().update(this);
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

    public Physics getPhysics() {
        return physics;
    }

    public void setPhysics(Physics physics) {
        this.physics = physics;
    }

    public AIController getAI() {
        return ai;
    }

    public void setAI(AIController ai) {
        this.ai = ai;
    }
}
