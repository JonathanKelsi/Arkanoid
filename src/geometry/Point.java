package geometry;

import java.util.Random;

/**
 * The class Geometry.Point provides the point object,
 * as well as a few constructors and methods for handling it.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor.
     * @param p a point.
     */
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    /** Calculates the distance between two points.
     * @param other another point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /** Checks whether this point is equal to another.
     * @param other another point.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.x) <= 1e-10 && Math.abs(this.y - other.y) <= 1e-10;
    }

    /** X getter.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /** Y getter.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /** X setter.
     * @param x new x value;
     */
    public void setX(double x) {
        this.x = x;
    }

    /** Y setter.
     * @param y new y value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @param width width of the screen
     * @param height height of the screen
     * @return a random point
     */
    public static Point randomPoint(int width, int height) {
        Random rand = new Random(); // create a random-number generator
        double x = rand.nextInt(width) + 1;
        double y = rand.nextInt(height) + 1;
        return new Point(x, y);
    }

    /**
     * @param x1 the starting x value of the area in which the ball will be generated.
     * @param y1 the starting y value of the area in which the ball will be generated.
     * @param x2 the ending x value of the area in which the ball will be generated.
     * @param y2 the ending y value of the area in which the ball will be generated.
     * @return a random point
     */
    public static Point randomPoint(int x1, int y1, int x2, int y2) {
        Random rand = new Random(); // create a random-number generator
        double x = rand.nextInt(x2 - x1) + x1;
        double y = rand.nextInt(y2 - y1) + y1;
        return new Point(x, y);
    }
}
