package gameObjects;

import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import listeners.HitNotifier;
import listeners.HitListener;
import geometry.Velocity;
import levels.GameLevel;
import java.util.ArrayList;

/**
 * The Sprites.Ball class represents a block in the game - an element
 * that can be collided with, and by definition it is a sprite.
 * The class provides a construction function, and it implements the functions
 * of the Sprites.Collidable and Sprites.Sprite Interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private ArrayList<HitListener> hitListeners;
    private boolean drawBorder;

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
     * @param rectangle the collision rectangle.
     * @param color the color of the rect
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = new Rectangle(rectangle);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.drawBorder = true;
    }

    /**
     * Constructor.
     * @param rectangle the collision rectangle.
     * @param color the color of the rect
     * @param shouldDraw should the border of the block be drawn.
     */
    public Block(Rectangle rectangle, Color color, boolean shouldDraw) {
        this.rectangle = new Rectangle(rectangle);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.drawBorder = shouldDraw;
    }

   @Override
   public Rectangle getCollisionRectangle() {
        return this.rectangle;
   }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //find the side of the rectangle the collision occurred at, and
        //return the correct velocity.

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        boolean didHitLeft = doubleEqual(collisionPoint.getX(), rectangle.getUpperLeft().getX());
        boolean didHitRight = doubleEqual(collisionPoint.getX(),
                rectangle.getUpperLeft().getX() + rectangle.getWidth());

        if (didHitLeft || didHitRight) {
            dx = -1 * currentVelocity.getDx();
        }

        boolean didHitUp = doubleEqual(collisionPoint.getY(), rectangle.getUpperLeft().getY());
        boolean didHitDown = doubleEqual(collisionPoint.getY(),
                rectangle.getUpperLeft().getY() + rectangle.getHeight());

        if (didHitUp || didHitDown) {
            dy = -1 * currentVelocity.getDy();
        }

        this.notifyHit(hitter);

        return new Velocity(dx, dy);
    }

   @Override
    public void drawOn(DrawSurface d) {
        //draw the frame of the block
        if (drawBorder) {
            d.setColor(Color.darkGray);
            d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }

        //draw the block itself
        d.setColor(color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        //does nothing
    }

    /**
     * Add the block to a given game both as a sprite and a collidable.
     * @param g a game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove the block from a given game both as a sprite and a collidable.
     * @param game a game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

   @Override
   public void addHitListener(HitListener hl) {
        if (hl != null) {
            hitListeners.add(hl);
        }
   }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * This method is called whenever a hit() occurs, and will notify all the
     * registered Listeners.HitListener objects by calling their hitEvent method.
     * @param hitter the ball that hit the block;
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
