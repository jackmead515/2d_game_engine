package com.engine.jsm.util;

public class NumberUtil {

    public static double normalize(double value, double projected, double maximum) {
        return (projected*value)/maximum;
    }

    public static int[] toIntegers(double[] array) {
            int[] copy = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                copy[i] = (int)array[i];
            }
            return copy;
    }

    public static float random(float min, float max) {
        return (float)Math.random()*max + min;
    }

    /**
     * Random vector in any direction, negative or positive.
     * @param magnitude
     */
    public static double[] randomVector(double magnitude) {
        int n1 = Math.random() > 0.5 ? 1 : -1;
        int n2 = Math.random() > 0.5 ? 1 : -1;
        return new double[] {
            Math.random() * magnitude * n1,
            Math.random() * magnitude * n2
        };
    }

}
