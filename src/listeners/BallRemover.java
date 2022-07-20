package listeners;

import gameObjects.Ball;
import gameObjects.Block;
import levels.GameLevel;
import other.Counter;

/**
 * A BallRemover removes the ball when it is notified by a block to do so.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game a game
     * @param removedBalls the counter of the number of balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Removes the block from the game, and the listener from the block.
     * @param beingHit the block being it.
     * @param hitter the ball that hits it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the block from the game
        hitter.removeFromGame(game);

        //update number of blocks
        remainingBalls.decrease(1);
    }
}
