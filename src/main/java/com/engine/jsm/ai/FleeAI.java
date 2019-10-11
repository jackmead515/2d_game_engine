package com.engine.jsm.ai;

import com.engine.jsm.creatures.Creature;

public class FleeAI extends AI {

    private Creature target;
    private double[] fleeDirection;
    private long lastUpdate;

    @Override
    public void update(Creature self) {

    }

    public Creature getTarget() {
        return target;
    }

    public void setTarget(Creature target) {
        this.target = target;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
