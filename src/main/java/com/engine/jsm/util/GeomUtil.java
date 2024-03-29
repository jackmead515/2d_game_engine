package com.engine.jsm.util;

import com.engine.jsm.boundaries.Boundary;
import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.Entity;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GeomUtil {

    /**
     * Retrieves the points from a rectangle in the form of [x, y, w, h].
     * Results are [x, y]. Points are defined in counter-clockwise rotation
     * starting from top left corner.
     */
    public static List<double[]> getPoints(double[] rect) {
        List<double[]> points = new ArrayList<>(4);
        points.add(new double[] { rect[0], rect[1] });
        points.add(new double[] { rect[0], rect[1]+rect[3] });
        points.add(new double[] { rect[0]+rect[2], rect[1]+rect[3] });
        points.add(new double[] { rect[0]+rect[2], rect[1] });
        return points;
    }

    /**
     * Retrieves the edges from a rectangle in the form of [x, y, w, h].
     * Results are [x, y] in vector form as the two positional points
     * subtracted from one another. Edges are defined in a
     * counter-clockwise rotation.
     */
    public static List<double[]> getEdges(double[] rect) {
        List<double[]> edges = new ArrayList<>(4);
        List<double[]> points = getPoints(rect);
        for (int i = 0; i < points.size(); i++) {
            double[] p1 = points.get(i);
            double[] p2 = points.get(i + 1 == points.size() ? 0 : i + 1);
            edges.add(VecUtil.subtract(p1, p2));
        }
        return edges;
    }

    public static Polygon toPolygon(List<double[]> points) {
        Polygon p = new Polygon();
        for (double[] point : points) {
            p.addPoint((int)point[0], (int)point[1]);
        }
        return p;
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

        double t = reach / distance(origin, tester);

        double fx = (1-t)*ox + t*tx;
        double fy = (1-t)*oy + t*ty;

        return new double[] { fx, fy };
    }

    public static Rectangle2D toRectangle2D(double[] rect) {
        return new Rectangle2D.Double(rect[0], rect[1], rect[2], rect[3]);
    }

    public static double[] toDoubleRect(Rectangle2D rect) {
        return new double[] { rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight() };
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

    /**
     * Tests intersect using the Separating Axis Theorem.
     */
    public static double[] satIntersects(double[] rect1, double[] rect2) {
        double minOverlap = Double.POSITIVE_INFINITY;
        double[] minAxis = new double[] { 0, 0 };
        List<double[]> points1 = getPoints(rect1);
        List<double[]> points2 = getPoints(rect2);
        List<double[]> edges1 = getEdges(rect1);
        List<double[]> edges2 = getEdges(rect2);

        for (double[] edge : edges1) {
            double[] axis = VecUtil.normalize(VecUtil.normal(edge));
            double[] mm1 = GeomUtil.project(axis, points1);
            double[] mm2 = GeomUtil.project(axis, points2);
            double overlap = Math.max(0, Math.min(mm1[1], mm2[1]) - Math.max(mm1[0], mm2[0]));
            if (overlap == 0) {
                return null;
            } else if (overlap < minOverlap) {
                minOverlap = overlap;
                minAxis = axis;
            }
        }
        for (double[] edge : edges2) {
            double[] axis = VecUtil.normalize(VecUtil.normal(edge));
            double[] mm1 = GeomUtil.project(axis, points1);
            double[] mm2 = GeomUtil.project(axis, points2);
            double overlap = Math.max(0, Math.min(mm1[1], mm2[1]) - Math.max(mm1[0], mm2[0]));
            if (overlap == 0) {
                return null;
            } else if (overlap < minOverlap) {
                minOverlap = overlap;
                minAxis = axis;
            }
        }

        double[] mtv = new double[] { minAxis[0]*minOverlap, minAxis[1]*minOverlap };
        //vector pointing from 2 -> 1. Meaning, it's pointing away.
        double[] c1c2 = VecUtil.subtract(GeomUtil.center(rect1), GeomUtil.center(rect2));
        if (VecUtil.dot(minAxis, c1c2) < 0) {
            return VecUtil.invert(mtv);
        } else {
            return mtv;
        }
    }

    /**
     * From a axis vector and a list of points,
     * projects each point to the axis. Returns a
     * vector [min, max] for the minimum and maximum
     * dot projects on that axis;
     */
    public static double[] project(double[] axis, List<double[]> points) {
        double dot = VecUtil.dot(axis, points.get(0));
        double min = dot; double max = dot;
        for (double[] point : points) {
            dot = VecUtil.dot(axis, point);
            if (dot < min) {
                min = dot;
            } else if (dot > max) {
                max = dot;
            }
        }
        return new double[] { min, max };
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

    public static double[] resolveCollision(Creature self, ArrayList<Boundary> colliding) {
        double[] position = self.getTopLeftCorner();
        double[] dimensions = self.getDimensions();
        double[] nextPosition = self.getPosition();
        nextPosition[0] = nextPosition[0] - dimensions[0]/2;
        nextPosition[1] = nextPosition[1] - dimensions[1]/2;
        double[] resolvedPosition = new double[] { nextPosition[0], position[1] };
        double[] nextCollisionBox = new double[] { resolvedPosition[0], resolvedPosition[1], dimensions[0], dimensions[1] };
        Rectangle2D intersectionX = GeomUtil.createIntersectingUnion(colliding, nextCollisionBox);
        if (intersectionX != null) {
            if (self.getCollisionBox()[0] < nextCollisionBox[0]) {
                resolvedPosition[0] = Math.max(position[0], resolvedPosition[0] - intersectionX.getWidth());
            } else {
                resolvedPosition[0] = Math.min(position[0], resolvedPosition[0] + intersectionX.getWidth());
            }
        }

        resolvedPosition[1] = nextPosition[1];
        nextCollisionBox = new double[] { resolvedPosition[0], resolvedPosition[1], dimensions[0], dimensions[1] };
        Rectangle2D intersectionY = GeomUtil.createIntersectingUnion(colliding, nextCollisionBox);
        if (intersectionY != null) {
            if (self.getCollisionBox()[1] < nextCollisionBox[1]) {
                resolvedPosition[1] = Math.max(position[1], resolvedPosition[1] - intersectionY.getHeight());
            } else {
                resolvedPosition[1] = Math.min(position[1], resolvedPosition[1] + intersectionY.getHeight());
            }
        }

        //self.setPositionX(resolvedPosition[0]+dimensions[0]/2);
        //self.setPositionY(resolvedPosition[1]+dimensions[1]/2);

        return new double[] {
                resolvedPosition[0]+dimensions[0]/2,
                resolvedPosition[1]+dimensions[1]/2
        };
    }

}
