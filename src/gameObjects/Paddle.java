package gameObjects;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import levels.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses. It implements the Sprite and the Collidable interfaces.
 */
public class Paddle implements Collidable, Sprite {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private int width;
    private int height;
    private int leftBorder;
    private int rightBorder;
    private int speed;

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
     * Constructor.
     * @param p the paddle's triangle's upper left point
     * @param width the width of the paddle
     * @param height the height of the paddle
     * @param leftBorder the left bound of the paddle
     * @param rightBorder the right bound of the paddle
     * @param speed the paddle's speed
     * @param ks a keyboard sensor
     */
    public Paddle(Point p, int width, int height, int leftBorder, int rightBorder, int speed, KeyboardSensor ks) {
        this.paddle = new Rectangle(p, width, height);
        this.color = Color.ORANGE;
        this.width = width;
        this.height = height;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.speed = speed;
        this.keyboard = ks;
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        Point current = this.paddle.getUpperLeft();

        //change the x value
        if (current.getX() > leftBorder) {
            Point newPoint = new Point(current.getX() - speed, current.getY());
            this.paddle.setUpperLeft(newPoint);
        }
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        Point current = this.paddle.getUpperLeft();

        //change the x value
        if (current.getX() < rightBorder - width) {
            Point newPoint = new Point(current.getX() + speed, current.getY());
            this.paddle.setUpperLeft(newPoint);
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
            return;
        }

        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }

   @Override
    public void drawOn(DrawSurface d) {
        //draw the frame of the paddle
        d.setColor(Color.BLACK);
        d.drawRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(),
                (int) paddle.getWidth(), (int) paddle.getHeight());

        //draw the paddle itself
        d.setColor(color);
        d.fillRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(),
                (int) paddle.getWidth(), (int) paddle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean didHitUp = doubleEqual(collisionPoint.getY(), paddle.getUpperLeft().getY());
        boolean didHitLeft = doubleEqual(collisionPoint.getX(), paddle.getUpperLeft().getX());
        boolean didHitRight = doubleEqual(collisionPoint.getX(), paddle.getUpperLeft().getX() + paddle.getWidth());

        if (didHitUp) {
            double start = this.paddle.getUpperLeft().getX();
            double len = this.paddle.getWidth();
            double x = collisionPoint.getX();

            double size = currentVelocity.getSize();

            // region 1
            if (start <= x && x <= start + len / 5) {
                return Velocity.fromAngleAndSpeed(300, size);
            }

            // region 2
            if (start + len / 5 < x && x <= start + 2 * (len / 5)) {
                return Velocity.fromAngleAndSpeed(330, size);
            }

            // region 3
            if (start + 2 * (len / 5) < x && x <= start + 3 * (len / 5)) {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }

            // region 4
            if (start + 3 * (len / 5) < x && x <= start + 4 * (len / 5)) {
                return Velocity.fromAngleAndSpeed(30, size);
            }

            // region 5
            if (start + 4 * (len / 5) < x && x <= start + len) {
                return Velocity.fromAngleAndSpeed(60, size);
            }
        }

        if (didHitRight || didHitLeft) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Add the ball into a given game as both a sprite and a collidable.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
