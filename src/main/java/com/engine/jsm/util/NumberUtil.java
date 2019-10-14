package com.engine.jsm.util;

public class NumberUtil {

    public static double normalize(double value, double projected, double maximum) {
        return (projected*value)/maximum;
    }

    public static double[] normalizeVector(double[] vector) {
        double l = Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));
        return new double[] { vector[0]/l, vector[1]/l };
    }

    public static double dotProduct(double[] vector1, double[] vector2) {
        return vector1[0]*vector2[0] + vector1[1]+vector2[1];
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
