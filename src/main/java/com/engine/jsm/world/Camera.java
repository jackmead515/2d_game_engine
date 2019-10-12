package com.engine.jsm.world;

import com.engine.jsm.entities.IUpdateable;
import com.engine.jsm.input.InputManager;
import com.engine.jsm.main.Constants;
import com.engine.jsm.main.Stats;
import com.engine.jsm.util.GeomUtil;
import com.engine.jsm.util.NumberUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.event.MouseEvent;

public class Camera implements IUpdateable {

    private double[] captureWheel;
    private double[] dimensions;
    private double[] focus;

    public Camera() {
        this.captureWheel = null;
        this.setViewPort(new double[] { 0, 0, Stats.getScreenWidth(), Stats.getScreenHeight() });
    }

    public double[] getCaptureWheel() { return captureWheel; }

    public void setCaptureWheel(double[] captureWheel) { this.captureWheel = captureWheel; }

    public double[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(double[] dimensions) {
        this.dimensions = dimensions;
    }

    public void setWidth(double w) {
        this.dimensions[0] = w;
    }

    public void setHeight(double h) {
        this.dimensions[1] = h;
    }

    public double[] getPosition() {
        return new double[] { focus[0]-dimensions[0]/2, focus[1]-dimensions[1]/2 };
    }

    /**
     * Gets the frame of the game which can be seen
     */
    public double[] getViewPort() {
        double[] position = getPosition();
        return new double[] { position[0], position[1], dimensions[0], dimensions[1] };
    }

    public void setViewPort(double[] viewPort) {
        this.setFocus(new double[] { viewPort[0]+viewPort[2]/2, viewPort[1]+viewPort[3]/2 });
        this.setDimensions(new double[] { viewPort[2], viewPort[3] });
    }

    /**
     * Returns the center point of the camera;
     */
    public double[] getFocus() {
        return focus;
    }

    public void setFocus(double[] focus) {
        this.focus = focus;
    }

    public void pan(double x, double y) {
        this.setFocus(new double[] { focus[0]+x, focus[1]+y });
    }

    @Override
    public void update() {
        snapToGame();
        updatePan();
    }

    public void updatePan() {
        double[] mouse = InputManager.getMouse().getPosition();

        if (InputManager.getMouse().getMousePressed() != MouseEvent.BUTTON2) {
            this.setCaptureWheel(null);
            return;
        }

        if (this.getCaptureWheel() == null) {
            this.setCaptureWheel(mouse);
        }

        double angle = GeomUtil.angle(mouse, this.getCaptureWheel());
        double distance = GeomUtil.distance(mouse, this.getCaptureWheel());
        double magnitude = Math.round(NumberUtil.normalize(distance, 10, 500));
        double px = magnitude * Math.cos(angle);
        double py = magnitude * Math.sin(angle);

        pan(-px, py);
    }

    public void snapToGame() {
        double[] focus  = getFocus();
        double[] dimensions = getDimensions();

        if (focus[0] - dimensions[0]/2 - 1 < 0) {
            focus[0] = dimensions[0]/2;
        } else if (focus[0] + dimensions[0]/2 + 1 > Constants.GAME_WIDTH) {
            focus[0] = Constants.GAME_WIDTH - dimensions[0]/2;
        }
        if (focus[1] - dimensions[1]/2 - 1 < 0) {
            focus[1] = dimensions[1]/2;
        } else if (focus[1] + dimensions[1]/2 + 1 > Constants.GAME_HEIGHT) {
            focus[1] = Constants.GAME_HEIGHT - dimensions[1]/2;
        }

        this.setFocus(focus);
    }
}
