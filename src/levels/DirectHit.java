package levels;

import biuoop.DrawSurface;
import gameObjects.Block;
import gameObjects.Sprite;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The DirectHit game level.
 */
public class DirectHit implements LevelInformation {
    private static final int BLOCK_SIZE = GameLevel.WIDTH / 32;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -6));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return GameLevel.WIDTH / 8;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                //draw the black background
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

                //draw the circles around it
                int radius = GameLevel.WIDTH / 6;
                int x = GameLevel.WIDTH / 2 - BLOCK_SIZE / 2 + BLOCK_SIZE / 2;
                int y = GameLevel.HEIGHT / 2 + BLOCK_SIZE / 2;

                d.setColor(Color.BLUE);
                d.drawCircle(x, y, radius);
                d.drawCircle(x, y, radius / 2);
                d.drawCircle(x, y, 3 * radius / 4);

                //draw the lines
                d.drawLine(x + BLOCK_SIZE / 2 + 10, y, x + BLOCK_SIZE / 2 + 10 + radius, y);
                d.drawLine(x - BLOCK_SIZE / 2 - 10, y, x - BLOCK_SIZE / 2 - 10 - radius, y);
                d.drawLine(x, y  + BLOCK_SIZE / 2 + 10, x, y  + BLOCK_SIZE / 2 + 10 + radius);
                d.drawLine(x, y  - BLOCK_SIZE / 2 - 10, x, y  - BLOCK_SIZE / 2 - 10 - radius);
            }

            @Override
            public void timePassed() {
                //does nothing
            }
        };
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(GameLevel.WIDTH / 2 - BLOCK_SIZE / 2,
                GameLevel.HEIGHT / 2), BLOCK_SIZE, BLOCK_SIZE), Color.RED));

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
