package geometry;

import java.util.ArrayList;

/**
 * The class Geometry.Rectangle provides the Geometry.Rectangle object,
 * as well as a few constructors and methods for handling it.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.height = height;
    }

    /**
     * Copy constructor.
     * @param rect another rectangle.
     */
    public Rectangle(Rectangle rect) {
       this.upperLeft = new Point(rect.upperLeft);
       this.height = rect.height;
       this.width = rect.width;
    }

    /**
     * UpperLeft setter.
     * @param newPoint a new upper left point.
     */
    public void setUpperLeft(Point newPoint) {
        this.upperLeft = new Point(newPoint);
    }

    /**
     * @return the points of the rectangle
     */
    private Point[] rectanglePoints() {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        Point[] points = {upperLeft, upperRight, downLeft, downRight};
        return points;
    }

    /**
     * @return the points of the rectangle
     */
    private Line[] rectangleSides() {
        Point[] points = rectanglePoints();

        Line side1 = new Line(points[0], points[1]);
        Line side2 = new Line(points[0], points[2]);
        Line side3 = new Line(points[1], points[3]);
        Line side4 = new Line(points[2], points[3]);

        Line[] sides = {side1, side2, side3, side4};
        return sides;
    }

    /**
     * @return is the point inside the rectangle
     * @param point a point
     */
    public boolean isInRectangle(Point point) {
        double x = point.getX();
        double y = point.getY();

        boolean isInXRange = upperLeft.getX() < x && x < upperLeft.getX() + width;
        boolean isInYRange = upperLeft.getY() < y && y < upperLeft.getY() + height;

        return isInXRange && isInYRange;
    }

    /**
     * @param line some specified line
     * @return the list of intersection points with line
     */
    public ArrayList<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersections = new ArrayList<>();
        Line[] sides = rectangleSides();

        for (int i = 0; i < sides.length; i++) {
            Point p = sides[i].intersectionWith(line);
            if (p != null) {
                intersections.add(p);
            }
        }

        return intersections;
    }

    /**
     * Width getter.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Height getter.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Upper Left point getter.
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the upper-right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }
}
