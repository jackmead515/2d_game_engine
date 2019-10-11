package com.engine.jsm.util;

public class FloatRange {

    private float min;
    private float max;
    private float value;

    public static FloatRange from(float min, float max) {
        return new FloatRange(min, max);
    }

    public FloatRange(float min, float max) {
        this.min = min;
        this.max = max;
        this.value = min;
    }

    public boolean inside(float value) {
        return value <= this.getMax() && value >= this.getMin();
    }

    public boolean outside(float value) {
        return value < this.getMin() || value > this.getMax();
    }

    public float randomize() {
        this.setValue((float)Math.random()*this.getMax() + this.getMin());
        return this.getValue();
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
