package com.engine.jsm.ai;

import com.engine.jsm.creatures.Creature;
import com.engine.jsm.main.Stats;
import com.engine.jsm.util.ValueRange;
import com.engine.jsm.util.NumberUtil;

public class WanderAI extends AI {

    private long lastStateChange;
    private boolean wander;
    private boolean still;
    private double[] wanderVelocity;
    private ValueRange stillChange;
    private ValueRange wanderChange;

    public static WanderAI from(ValueRange still, ValueRange wander) {
        return new WanderAI(still, wander);
    }

    public WanderAI(ValueRange still, ValueRange wander) {
        this.stillChange = still;
        this.wanderChange = wander;
        this.still = true;
        this.wander = false;
    }

    public boolean isWander() {
        return wander;
    }

    public void setWander(boolean wander) {
        this.wander = wander;
    }

    public boolean isStill() {
        return still;
    }

    public void setStill(boolean still) {
        this.still = still;
    }

    public double[] getWanderVelocity() {
        return wanderVelocity;
    }

    public void setWanderVelocity(double[] wanderVelocity) {
        this.wanderVelocity = wanderVelocity;
    }

    public ValueRange getStillChange() {
        return stillChange;
    }

    public void setStillChange(ValueRange stillChange) {
        this.stillChange = stillChange;
    }

    public ValueRange getWanderChange() {
        return wanderChange;
    }

    public void setWanderChange(ValueRange wanderChange) {
        this.wanderChange = wanderChange;
    }

    public long getLastStateChange() {
        return lastStateChange;
    }

    public void setLastStateChange(long lastStateChange) {
        this.lastStateChange = lastStateChange;
    }

    @Override
    public void update(Creature self) {
        double past = Stats.getGameTime()-getLastStateChange();
        if (isWander() && past >= getWanderChange().getValue()) {
            setStill(true);
            setWander(false);
            getStillChange().randomize();
            setWanderVelocity(new double[] { 0, 0 });
            self.getPhysics().setVelocity(getWanderVelocity());
            setLastStateChange(Stats.getGameTime());
        } else if (isStill() && past >= getStillChange().getValue()) {
            setWander(true);
            setStill(false);
            getWanderChange().randomize();
            setWanderVelocity(NumberUtil.randomVector(1));
            self.getPhysics().applyVelocity(getWanderVelocity());
            setLastStateChange(Stats.getGameTime());
        }
    }

}
