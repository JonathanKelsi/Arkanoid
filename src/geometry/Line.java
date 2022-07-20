package geometry;

import java.util.ArrayList;

/**
 * The class Geometry.Line represents a segment in the R^2 plain - it has a starting point,
 * and an end point. The class also provides several constructors, getters and setters
 * and other functions for handling segments and their basic properties, such as intersection
 * points, length, middle point, etc.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor.
     * @param start starting point of the segment
     * @param end end point of the segment
     */
    public Line(Point start, Point end) {
        this.start =  new Point(start);
        this.end =  new Point(end);
    }

    /**
     * Constructor.
     * @param x1 starting point's x coordinate
     * @param y1 starting point's y coordinate
     * @param x2 end point's x coordinate
     * @param y2 end point's y coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);

        this.start = start;
        this.end = end;
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;

        return new Point(x, y);
    }

    /**
     * @return the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other another line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
       boolean opt1 = start.equals(other.start) && end.equals(other.end);
       boolean opt2 = start.equals(other.end) && end.equals(other.start);
       return opt1 || opt2;
    }

    /**
     * @return the slope of the line.
     */
    private double slope() {
        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY;
        }

        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * @return the b in y = mx + b
     */
    private double freeValue() {
        double m = this.slope();
        if (m == Double.POSITIVE_INFINITY) {
            return this.start.getX();
        }
        return this.start.getY() - m * this.start.getX();
    }

    /**
     * @param p a point
     * @return whether p is on the segment
     */
    private boolean isOnLine(Point p) {
        //check that p is on the line using triangle's inequality.
        return Math.abs(start.distance(p) + end.distance(p) - length()) <= 1e-10;
    }

    /**
     * @param x a real number
     * @param y a real number
     * @return whether the x is approx. y
     */
    private boolean doubleEqual(double x, double y) {
        //check that p is on the line using triangle's inequality.
        return x == y || Math.abs(x - y) <= 1e-10;
    }

    /**
     * @param other another line
     * @return true if the lines intersect, false otherwise;
     */
    public boolean isIntersecting(Line other) {
        //if the segments are parallel
        if (doubleEqual(this.slope(), other.slope()) && !doubleEqual(this.freeValue(), other.freeValue())) {
            return false;
        }

        //if the segments are on the same line
        if (doubleEqual(this.slope(), other.slope())) {
            //check if one of the end points of one of the segments is on the other line:
            boolean isStartOnOther = other.isOnLine(start);
            boolean isEndOnOther = other.isOnLine(end);
            boolean isStartOnThis = this.isOnLine(other.start);
            boolean isEndOnThis = this.isOnLine(other.end);

            return isStartOnOther || isEndOnOther || isStartOnThis || isEndOnThis;
        }

        return intersectionWith(other) != null;
    }

    /**
     * @param other another line
     * @return the intersection point if the lines intersect, null otherwise;
     */
    public Point intersectionWith(Line other) {
        //if the two segments are parallel or on the same line
        if (doubleEqual(this.slope(), other.slope())) {
            return null;
        }

        //L1: y = m1x+b1
        //L2: y = m2x+b2
        //solving we get:
        //P = ((b2-b1)/(m1-m2), the corresponding y value)
        //if one of the lines is of the form x = b1, it means the other is of the form y = mx + b2
        //or else they would not intersect.

        double x, y; //result
        double m1 = this.slope(), b1 = this.freeValue();
        double m2 = other.slope(), b2 = other.freeValue();

        if (m1 == Double.POSITIVE_INFINITY) {
            x = this.start.getX();
            y = m2 * this.start.getX() + b2;
        } else if (m2 == Double.POSITIVE_INFINITY) {
            x = other.start.getX();
            y = m1 * other.start.getX() + b1;
        } else {
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
        }

        //check the intersection point is on both segments
        Point inter = new Point(x, y);
        boolean isOnLine1 = this.isOnLine(inter);
        boolean isOnLine2 = other.isOnLine(inter);

        if (isOnLine1 && isOnLine2) {
            return inter;
        }

        return null;
    }

    /**
     * @param rect a rectangle
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> intersections = rect.intersectionPoints(this);

        //if there are no intersection points at all
        if (intersections.size() == 0) {
            return null;
        }

        double min = Double.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < intersections.size(); i++) {
            if (this.start.distance(intersections.get(i)) < min) {
                min = this.start.distance(intersections.get(i));
                index = i;
            }
        }

        return intersections.get(index);
    }
}
