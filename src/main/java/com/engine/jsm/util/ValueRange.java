package com.engine.jsm.util;

public class ValueRange {

    private double min;
    private double max;
    private double value;

    public static ValueRange from(double min, double max) {
        return new ValueRange(min, max);
    }

    public ValueRange(double min, double max) {
        this.min = min;
        this.max = max;
        this.value = min;
    }

    public boolean inside(double value) {
        return value <= this.getMax() && value >= this.getMin();
    }

    public boolean outside(double value) {
        return value < this.getMin() || value > this.getMax();
    }

    public double randomize() {
        this.setValue(Math.random()*this.getMax() + this.getMin());
        return this.getValue();
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
