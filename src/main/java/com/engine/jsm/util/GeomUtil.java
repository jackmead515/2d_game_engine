package com.engine.jsm.util;

import com.engine.jsm.boundaries.Boundary;
import com.engine.jsm.entities.Entity;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GeomUtil {

    /**
     * Scales rectangle according to ratio.
     */
    public static double[] scale(double[] rect, double ratio) {
        return new double[] {};
    }

    /**
     * Retrieves the points from a rectangle in the form of [x, y, w, h].
     */
    public static List<double[]> getPoints(double[] rect) {
        List<double[]> points = new ArrayList<>(4);
        points.add(new double[] { rect[0], rect[1] });
        points.add(new double[] { rect[0]+rect[2], rect[1] });
        points.add(new double[] { rect[0], rect[1]+rect[3] });
        points.add(new double[] { rect[0]+rect[2], rect[1]+rect[3] });
        return points;
    }

    /**
     * Retrieves the center of a rect in the form of [x, y, w, h]
     */
    public static double[] center(double[] rect) {
        return new double[] { rect[0]+rect[2]/2, rect[1]+rect[3]/2 };
    }

    /**
     * Tests if two rectangles are intersecting.
     * Rectangles have to be in the form of [x, y, w, h]
     * @return
     */
    public static boolean intersects(double[] rect1, double[] rect2) {
        return rect1[0] < rect2[0]+rect2[2] &&
               rect1[0]+rect1[2] > rect2[0] &&
               rect1[1] < rect2[1]+rect2[3] &&
               rect1[1]+rect1[3] > rect2[1];
    }

    /**
     * Tests if a point is inside or touching the rectangle
     */
    public static boolean contains(double[] rect, double[] point) {
        return point[0] >= rect[0] && point[0] <= rect[0] + rect[2] && point[1] >= rect[1] && point[1] <= rect[1] + rect[3];
    }

    /**
     * Measures the distance between two points
     */
    public static double distance(double[] point1, double[] point2) {
        return Math.sqrt(Math.pow(Math.abs(point1[0] - point2[0]), 2) + Math.pow(Math.abs(point1[1] - point2[1]), 2));
    }

    /**
     * Measures the angle between two points in radians
     */
    public static double angle(double[] origin, double[] test) {
        return Math.atan2(origin[1] - test[1], test[0] - origin[0]);
    }

    public static double[] pointFrom(double[] origin, double[] tester, double reach) {
        double ox = origin[0]; double oy = origin[1];
        double tx = tester[0]; double ty = tester[1];

        double t = reach/distance(origin, tester);

        double fx = (1-t)*ox + t*tx;
        double fy = (1-t)*oy + t*ty;

        return new double[] { fx, fy };
    }

    public static Rectangle2D toRectangle2D(double[] rect) {
        return new Rectangle2D.Double(rect[0], rect[1], rect[2], rect[3]);
    }

    /**
     * Creates a Rectangle2D composed of all the intersections of the
     * colliding objects.
     * @param entites list of entities which are all colliding with rect
     * @param rect the collision box which is colliding with entities
     */
    public static <T extends Entity> Rectangle2D createIntersectingUnion(ArrayList<T> entites, double[] rect) {
        Rectangle2D rectangle = toRectangle2D(rect);
        Rectangle2D result = null;
        for (Entity e : entites) {
            Rectangle2D intersection = toRectangle2D(e.getBounds()).createIntersection(rectangle);
            if (result != null) {
                result = intersection.createUnion(result);
            } else {
                result = intersection;
            }
        }
        return result;
    }

    public static double[] intersection(Line2D line1, Line2D line2) {
        final double x1 = line1.getX1(); final double y1 = line1.getY1();
        final double x2 = line1.getX2(); final double y2 = line1.getY2();

        final double x3 = line2.getX1(); final double y3 = line2.getY1();
        final double x4 = line2.getX2(); final double y4 = line2.getY2();

        double[] p = null;

        final double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d != 0) {
            final double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
            final double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

            if (xi >= Math.min(line1.getX1(), line1.getX2()) &&
                xi <= Math.max(line1.getX1(), line1.getX2()) &&
                yi >= Math.min(line1.getY1(), line1.getY2()) &&
                yi <= Math.max(line1.getY1(), line1.getY2())) {
                p = new double[] { xi, yi };
            }
        }

        return p;
    }

}
