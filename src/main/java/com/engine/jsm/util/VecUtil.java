package com.engine.jsm.util;

import java.util.List;

public class VecUtil {

    public static double[] from(double value) {
        return new double[] { value, value };
    }

    public static double[] invert(double[] vector) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = -vector[i];
        }
        return vector;
    }

    public static double[] normalize(double[] vector) {
        double l = Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));
        return new double[] { vector[0]/l, vector[1]/l };
    }

    public static double[] normal(double[] vector) {
        return new double[] { -vector[1], vector[0] };
    }

    public static double[] multiply(double[] vec1, double[] vec2) {
        return new double[] { vec1[0]*vec2[0], vec1[1]*vec2[0] };
    }

    public static double dot(double[] vec1, double[] vec2) {
        return vec1[0]*vec2[0] + vec1[1]*vec2[1];
    }

    public static double[] subtract(double[] vec1, double[] vec2) {
        return new double[] { vec1[0]-vec2[0], vec1[1]-vec2[1] };
    }

    public static double[] add(double[] vec1, double[] vec2) {
        return new double[] { vec1[0]+vec2[0], vec1[1]+vec2[1] };
    }

}
