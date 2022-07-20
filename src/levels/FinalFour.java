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
 * The FinalFour game level.
 */
public class FinalFour implements LevelInformation {
    private static final int BLOCK_WIDTH = (GameLevel.WIDTH - 2 * GameLevel.BORDER_SIZE) / 15;
    private static final int BLOCK_HEIGHT =  GameLevel.HEIGHT / 24;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(-45, 6));
        velocities.add(Velocity.fromAngleAndSpeed(45, 6));
        velocities.add(Velocity.fromAngleAndSpeed(0, 7));

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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                //draw the sky
                d.setColor(new Color(23, 136, 208));
                d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

                //draw the clouds
                int radius = 2 * BLOCK_WIDTH / 5;

                //cloud #1
                int x = GameLevel.BORDER_SIZE + 3 * BLOCK_WIDTH / 2;
                int factor = x / 10;
                int y = 2 * GameLevel.HEIGHT / 3;

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(x, y, x - 2 * factor, GameLevel.HEIGHT);
                    x += factor;
                }

                d.setColor(new Color(204, 204, 204));
                d.fillCircle(GameLevel.BORDER_SIZE + 3 * BLOCK_WIDTH / 2, 2 * GameLevel.HEIGHT / 3, radius);
                d.fillCircle(GameLevel.BORDER_SIZE + 2 * BLOCK_WIDTH, 7 * GameLevel.HEIGHT / 10, 12 * radius / 10);
                d.setColor(new Color(187, 187, 187));
                d.fillCircle(GameLevel.BORDER_SIZE + 21 * BLOCK_WIDTH / 10, 39 * GameLevel.HEIGHT / 60,
                        12 * radius / 10);
                d.setColor(new Color(170, 170, 170));
                d.fillCircle(GameLevel.BORDER_SIZE + 5 * BLOCK_WIDTH / 2, 7 * GameLevel.HEIGHT / 10, radius);
                d.fillCircle(GameLevel.BORDER_SIZE + 14 * BLOCK_WIDTH / 5, 2 * GameLevel.HEIGHT / 3, 13 * radius / 10);

                //cloud #2
                x = 4 * GameLevel.WIDTH / 5;
                y = 4 * GameLevel.HEIGHT / 5;
                factor = x / 70;

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(x, y, x - 5 * factor, GameLevel.HEIGHT);
                    x += factor;
                }

                d.setColor(new Color(204, 204, 204));
                d.fillCircle(4 * GameLevel.WIDTH / 5, 4 * GameLevel.HEIGHT / 5, radius);
                d.fillCircle(25 * GameLevel.WIDTH / 30, 21 * GameLevel.HEIGHT / 25, 12 * radius / 10);
                d.setColor(new Color(187, 187, 187));
                d.fillCircle(85 * GameLevel.WIDTH / 100, 4 * GameLevel.HEIGHT / 5, 12 * radius / 10);
                d.setColor(new Color(170, 170, 170));
                d.fillCircle(86 * GameLevel.WIDTH / 100, 42 * GameLevel.HEIGHT / 50, radius);
                d.fillCircle(88 * GameLevel.WIDTH / 100, 41 * GameLevel.HEIGHT / 50, 13 * radius / 10);
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

        Color[] colors = {Color.CYAN, Color.PINK, Color.WHITE, Color.GREEN, Color.YELLOW, Color.RED, Color.GRAY};

        //align the blocks at the center of the screen
        int x = 1 + GameLevel.BORDER_SIZE;
        while (GameLevel.WIDTH - x - BLOCK_WIDTH * 15 > x) {
            x++;
        }

        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 15; i++) {
                Block block = new Block(new Rectangle(new Point(x + BLOCK_WIDTH * i,
                        2 * GameLevel.HEIGHT / 5 - BLOCK_HEIGHT * j), BLOCK_WIDTH, BLOCK_HEIGHT), colors[j]);

                blocks.add(block);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
