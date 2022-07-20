package listeners;

import gameObjects.Ball;
import gameObjects.Block;

/**
 * A class that tests the hitListener interface.
 */
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Sprites.Block was hit.");
    }
}
