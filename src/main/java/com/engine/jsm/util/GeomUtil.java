package com.engine.jsm.util;

public class GeomUtil {

    public static double[] center(double[] bounds) {
        return new double[] { bounds[0]+bounds[2]/2, bounds[1]+bounds[3]/2 };
    }

    public static boolean intersects(double[] rect1, double[] rect2) {
        return !(rect1[0]+rect1[2] < rect2[0] ||
                rect2[0]+rect2[2] < rect1[0] ||
                rect1[1]+rect1[3] < rect2[1] ||
                rect2[1]+rect2[3] < rect1[1]);
    }

    /**
     * Tests if a point is inside or touching the rectangle
     */
    public static boolean contains(double[] rect, double[] point) {
        return point[0] >= rect[0] && point[0] <= rect[0] + rect[2] && point[1] >= rect[1] && point[1] <= rect[1] + rect[3];
    }

}
