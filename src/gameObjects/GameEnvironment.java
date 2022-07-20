package gameObjects;

import geometry.Line;
import geometry.Point;

import java.util.ArrayList;

/**
 * The GameObjects.GameEnvironment class is a collection of things the ball can interact with.
 *  Given game environment, The ball will know and use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<>();

    /**
     * Add the given collidable to the environment.
     * @param c a Sprites.Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove the given collidable from the environment.
     * @param c a Sprites.Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * @param trajectory the route of the object which is going to collide.
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = null;
        Collidable collisionObject = null;
        double minLen = Double.MAX_VALUE;

        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null && trajectory.start().distance(p) < minLen) {
                collisionObject = c;
                collisionPoint = p;
                minLen = trajectory.start().distance(p);
            }
        }

        if (collisionObject == null) {
            return null;
        }

        return new CollisionInfo(collisionPoint, collisionObject);
    }
}
