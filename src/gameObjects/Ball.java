package gameObjects;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import levels.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The Sprites.Ball class represents a ball in the R^2 plane - it has
 * a center point, a radius, color and velocity. The class provides several constructors
 * for the ball object, as well as a few getters and setters and a random ball function that generates
 * random ball, and a moveStep function the moves the ball in the R^2 plane, according to its velocity.
 */
public class Ball implements Sprite {
    private Point point;
    private int size;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnv;
    private Paddle paddle;

    /**
     * Constructor.
     * @param center the center of the ball
     * @param r it's radius
     * @param color it's color
     */
    public Ball(Point center, int r, Color color) {
        this.point = new Point(center); //use copy constructor
        this.size = r;
        this.color = color;
    }

    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param r r
     * @param color color
     */
    public Ball(double x, double y, int r, Color color) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
    }

    /**
     * x-coordinate getter.
     * @return the ball's location's x-coordinate.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * y-coordinate getter.
     * @return the ball's location's y-coordinate
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * size getter.
     * @return size (radius) of the ball
     */
    public int getSize() {
        return this.size;
    }

    /**
     * color getter.
     * @return color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * velocity getter.
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * velocity setter.
     * @param v new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }

    /**
     * velocity setter.
     * @param dx x-axis velocity
     * @param dy y-axis velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Game.Game Environment setter.
     * @param gameEnv the environment
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnv = gameEnv;
    }

    /**
     * Sprites.Paddle setter.
     * @param paddle a paddle
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }


    /**
     * @param size the size of the ball
     * @param x1 the starting x value of the area in which the ball will be generated.
     * @param y1 the starting y value of the area in which the ball will be generated.
     * @param x2 the ending x value of the area in which the ball will be generated.
     * @param y2 the ending y value of the area in which the ball will be generated.
     * @return a random ball.
     */
    public static Ball randomBall(int size, int x1, int y1, int x2, int y2) {
        Random rand = new Random(); //RNG

        //location
        Point center = Point.randomPoint(x1, y1, x2, y2);

        //color
        Color randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());

        //size
        int newSize = (int) Math.min(Math.min(x2 - x1, y2 - y1) * 0.5, size);

        Ball result = new Ball(center, newSize, randomColor);

        //velocity
        double angle = rand.nextInt(180) + 1;
        double speed = 250.0 / result.getSize();

        if (result.getSize() >= 50) { //constant speed for large balls
            speed = 5;
        }

        result.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));

        return result;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface a gui draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) point.getX(), (int) point.getY(), size);

        surface.setColor(color);
        surface.fillCircle((int) point.getX(), (int) point.getY(), size);
    }

    /**
     * this method changes the balls location according to the velocity.
     */
    public void moveOneStep() {
        //if the ball is inside the paddle
        if (this.paddle != null && paddle.getCollisionRectangle().isInRectangle(point)) {
            point = new Point(point.getX(),
                    paddle.getCollisionRectangle().getUpperLeft().getY() - 2);
            return;
        }

        //the ball trajectory (considering the radius)
        Line trajectory = new Line(point, velocity.applyToPoint(point));

        //check if moving on this trajectory will hit anything
        CollisionInfo info = gameEnv.getClosestCollision(trajectory);

        //if the ball does not collide with anything
        if (info == null) {
            point = velocity.applyToPoint(point);
            return;
        }

        //else, update the location and velocity
        velocity = info.collisionObject().hit(this, info.collisionPoint(), velocity);

        Point collisonPoint = info.collisionPoint();
        Rectangle collisonRect = info.collisionObject().getCollisionRectangle();

        double newX = collisonPoint.getX(), newY = collisonPoint.getY();

        //if the colision is on the edge of the rectangle
        if (newX == collisonRect.getUpperLeft().getX() && newY == collisonRect.getUpperLeft().getY()) {
            point = velocity.applyToPoint(point);
            return; //the change of velocity is enough
        }

        //if the collision is on the top of the rectangle
        if (collisonPoint.getY() == collisonRect.getUpperLeft().getY()) {
            newY -= size;
        }

        //if the collision is on the bottom of the rectangle
        if (collisonPoint.getY()  == collisonRect.getUpperLeft().getY() + collisonRect.getHeight()) {
            newY += size;
        }

        //if the collision is on the left side of the rectangle
        if (collisonPoint.getX() == collisonRect.getUpperLeft().getX()) {
            newX -= size;
        }

        //if the collision is on the right side of the rectangle
        if (collisonPoint.getX() == collisonRect.getUpperLeft().getX() + collisonRect.getWidth()) {
            newX += size;
        }

        point = new Point(newX, newY);
    }

    /**
     * notify the Sprites.Ball that time has passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add the ball into a given game as a sprite.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball from a given game as a sprite.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
