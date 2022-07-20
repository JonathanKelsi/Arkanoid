package listeners;

import gameObjects.Ball;
import gameObjects.Block;
import levels.GameLevel;
import other.Counter;

/**
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game a game
     * @param removedBlocks the counter of the number of blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Removes the block from the game, and the listener from the block.
     * @param beingHit the block being it.
     * @param hitter the ball that hits it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the hit listener from the block
        beingHit.removeHitListener(this);

        //remove the block from the game
        beingHit.removeFromGame(game);

        //update number of blocks
        remainingBlocks.decrease(1);
    }
}
