package com.engine.jsm.util;

public class FloatUtil {

    public static float random(float min, float max) {
        return (float)Math.random()*max + min;
    }

    /**
     * Random vector in any direction, negative or positive.
     * @param magnitude
     */
    public static FloatPoint randomVector(float magnitude) {
        float n1 = Math.random() >= 0.5 ? 1 : -1;
        float n2 = Math.random() >= 0.5 ? 1 : -1;
        return FloatPoint.from((float)Math.random()*magnitude*n1, (float)Math.random()*magnitude*n2);
    }

}
