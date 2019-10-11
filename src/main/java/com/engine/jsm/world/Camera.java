package com.engine.jsm.world;

import com.engine.jsm.main.Stats;

public class Camera {

    private double[] viewPort;
    private double[] focus;

    public Camera() {
        this.viewPort = new double[] { 0, 0, Stats.getScreenWidth(), Stats.getScreenHeight() };
        this.focus = new double[] { Stats.getScreenWidth()/2, Stats.getScreenHeight()/2 };
    }

    public double[] getViewPort() {
        return viewPort;
    }

    public void setViewPort(double[] viewPort) {
        this.viewPort = viewPort;
    }

    public double[] getFocus() {
        return focus;
    }

    public void setFocus(double[] focus) {
        this.focus = focus;
    }

    public void pan(float x, float y) {
        this.setFocus(new double[] { this.getFocus()[0]+x, this.getFocus()[1]+y});
    }
}
