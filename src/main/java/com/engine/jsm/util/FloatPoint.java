package com.engine.jsm.util;

public class FloatPoint {

    private float x;
    private float y;

    public static FloatPoint from(float x, float y) {
        return new FloatPoint(x, y);
    }

    public FloatPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float y() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x() + "," + y() + "]";
    }

}
