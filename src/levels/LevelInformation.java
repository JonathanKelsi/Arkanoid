package levels;

import gameObjects.Block;
import gameObjects.Sprite;
import geometry.Velocity;
import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle;
     */
    int paddleWidth();

    /**
     * @return the level's name.
     */
    String levelName();

    /**
     * @return A sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * @return The number of blocks that should be removed in the level.
     */
    int numberOfBlocksToRemove();
}
