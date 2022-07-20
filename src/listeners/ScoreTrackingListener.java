package listeners;

import gameObjects.Ball;
import gameObjects.Block;
import other.Counter;

/**
 * A ScoreTrackingListener is in charge of increasing the score when a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the hit listener from the block
        beingHit.removeHitListener(this);

        //update the score
        currentScore.increase(5);
    }
}
