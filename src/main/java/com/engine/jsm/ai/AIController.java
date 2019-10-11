package com.engine.jsm.ai;

import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.ICreatureUpdateable;

public class AIController implements ICreatureUpdateable {

    private AIState state;

    private WanderAI wanderAI;
    private boolean wanderEnabled;

    private FleeAI fleeAI;
    private boolean fleeEnabled;

    @Override
    public void update(Creature self) {
        switch(getState()) {
            case CYCLE: cycle(self); break;
            case WANDER: wander(self); break;
            case FLEE: flee(self); break;
            default: break;
        }
    }

    public void cycle(Creature self) {
        wander(self);
        flee(self);
    }

    public void wander(Creature self) {
        if (isWanderEnabled() && this.getWanderAI() != null) {
            this.getWanderAI().update(self);
        }
    }

    public void flee(Creature self) {
        if (this.isFleeEnabled() && this.getFleeAI() != null) {
            this.getFleeAI().update(self);
        }
    }

    public WanderAI getWanderAI() {
        return wanderAI;
    }

    public void setWanderAI(WanderAI wanderAI) {
        this.wanderAI = wanderAI;
    }

    public FleeAI getFleeAI() {
        return fleeAI;
    }

    public void setFleeAI(FleeAI fleeAI) {
        this.fleeAI = fleeAI;
    }

    public AIState getState() {
        return state;
    }

    public void setState(AIState state) {
        this.state = state;
    }

    public boolean isWanderEnabled() {
        return wanderEnabled;
    }

    public void setWanderEnabled(boolean wanderEnabled) {
        this.wanderEnabled = wanderEnabled;
    }

    public boolean isFleeEnabled() {
        return fleeEnabled;
    }

    public void setFleeEnabled(boolean fleeEnabled) {
        this.fleeEnabled = fleeEnabled;
    }
}
