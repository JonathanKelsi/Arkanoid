package listeners;

import gameObjects.Ball;
import gameObjects.Block;

/**
 * The HitListener interface performs an action when it is notified by a HitNotifier.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Sprites.Ball that's doing the hitting.
     * @param beingHit the block being it.
     * @param hitter the ball that hits it.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
