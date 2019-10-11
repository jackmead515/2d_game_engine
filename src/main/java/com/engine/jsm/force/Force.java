package com.engine.jsm.force;

public class Force {

    public float x;
    public float y;
    public float magnitude;

    public static Force from(float x, float y, float magnitude) {
        return new Force(x, y, magnitude);
    }

    public Force(float x, float y, float magnitude) {
        this.x = x;
        this.y = y;
        this.magnitude = magnitude;
    }

    public void apply(Force force) {

    }

    public float x() {
        return x;
    }

    public void x(float x) {
        this.x = x;
    }

    public float y() {
        return y;
    }

    public void y(float y) {
        this.y = y;
    }

    public float magnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }
}
