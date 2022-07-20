package geometry;

/**
 * The Geometry.Velocity class provides an object that specifies the change in position on the `x` and the `y` axes.
 * In the class are a few constructors, getters and setters.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx x-axis velocity
     * @param dy y-axis velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Copy constructor.
     * @param v another velocity
     */
    public Velocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * dx getter.
     * @return dx the x-axis velocity
     */
    public double getDx() {
        return dx;
    }

    /**
     * dy getter.
     * @return dy the y-axis velocity
     */
    public double getDy() {
        return dy;
    }

    /**
     * @return the size of the velocity
     */
    public double getSize() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * @return the angle of the velocity
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan(dy / dx));
    }

    /**
     * @param p a point with position (x,y)
     * @return a new point with position (x+dx,y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * constructor.
     * @param angle the angle of the velocity.
     * @param speed the size of the velocity.
     * @return the matching velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
