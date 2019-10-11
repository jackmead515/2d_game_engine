package com.engine.jsm.ai;

import com.engine.jsm.creatures.Creature;
import com.engine.jsm.main.Stats;
import com.engine.jsm.util.ValueRange;
import com.engine.jsm.util.NumberUtil;

public class WanderAI extends AI {

    private long lastStateChange;
    private long wanderStateTime;
    private long stillStateTime;
    private boolean wander;
    private boolean still;
    private double[] wanderVelocity;
    private ValueRange stateChange;

    public static WanderAI from(ValueRange stateChange) {
        return new WanderAI(stateChange);
    }

    public WanderAI(ValueRange stateChange) {
        this.stateChange = stateChange;
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

    public ValueRange getStateChange() {
        return stateChange;
    }

    public void setStateChange(ValueRange stateChange) {
        this.stateChange = stateChange;
    }

    public long getLastStateChange() {
        return lastStateChange;
    }

    public void setLastStateChange(long lastStateChange) {
        this.lastStateChange = lastStateChange;
    }

    @Override
    public void update(Creature self) {
        if (Stats.getGameTime()-getLastStateChange() >= getStateChange().getValue()) {
            setLastStateChange(Stats.getGameTime());

            if (isWander()) {
                setStill(true);
                setWander(false);
                getStateChange().randomize();
                setWanderVelocity(new double[] { 0, 0 });
                self.getMovement().setVelocity(getWanderVelocity());
            } else if (isStill()) {
                setWander(true);
                setStill(false);
                getStateChange().randomize();
                setWanderVelocity(NumberUtil.randomVector(1));
                self.getMovement().applyVelocity(getWanderVelocity());
            }
        }
    }

}
