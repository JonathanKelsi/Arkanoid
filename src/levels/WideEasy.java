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
 * The WideEasy game level.
 */
public class WideEasy implements LevelInformation {
    private static final int BLOCK_WIDTH = (GameLevel.WIDTH - 2 * GameLevel.BORDER_SIZE) / 15;
    private static final int BLOCK_HEIGHT =  GameLevel.HEIGHT / 24;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int angle = -10;
            velocities.add(Velocity.fromAngleAndSpeed(angle - i * 10, 6));
        }

        for (int i = 0; i < 5; i++) {
            int angle = 10;
            velocities.add(Velocity.fromAngleAndSpeed(angle + i * 10, 6));
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 6 * GameLevel.WIDTH / 8;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                //draw the white background
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

                //draw the sun and the light rays
                int x = (GameLevel.WIDTH - 2 * GameLevel.BORDER_SIZE) / 5;
                int y = GameLevel.HEIGHT / 6;
                int radius = GameLevel.HEIGHT / 11;

                d.setColor(new Color(239, 231, 176));

                d.drawLine(x, y, GameLevel.BORDER_SIZE, 2 * GameLevel.HEIGHT / 5 - 15);
                d.drawLine(x, y, GameLevel.BORDER_SIZE, 2 * GameLevel.HEIGHT / 5 - 10);
                d.drawLine(x, y, GameLevel.BORDER_SIZE, 2 * GameLevel.HEIGHT / 5 - 5);

                for (int i = GameLevel.BORDER_SIZE; i < GameLevel.WIDTH - BLOCK_WIDTH * 1.5; i += 5) {
                    d.drawLine(x, y, i, 2 * GameLevel.HEIGHT / 5);
                }

                d.fillCircle(x, y, (int) (radius * 1.4));

                d.setColor(new Color(236, 215, 73));
                d.fillCircle(x, y, (int) (radius * 1.2));

                d.setColor(new Color(255, 225, 24));
                d.fillCircle(x, y, radius);
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

        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};

        //align the blocks at the center of the screen
        int x = 1 + GameLevel.BORDER_SIZE;
        while (GameLevel.WIDTH - x - BLOCK_WIDTH * 15 > x) {
            x++;
        }

        for (int i = 0; i < 15; i++) {
            Block block = new Block(new Rectangle(new Point(x +  BLOCK_WIDTH * i,
                    2 * GameLevel.HEIGHT / 5), BLOCK_WIDTH, BLOCK_HEIGHT), colors[i]);

            blocks.add(block);
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
