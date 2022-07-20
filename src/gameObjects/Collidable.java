package gameObjects;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * The Sprites.Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /** Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param  collisionPoint the point of collision
     * @param currentVelocity the velocity of the collision
     * @param hitter the ball that hits the collidable
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
