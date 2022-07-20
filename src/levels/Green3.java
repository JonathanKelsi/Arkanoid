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
 * The Green3 game level.
 */
public class Green3 implements LevelInformation {
    private static final int BLOCK_WIDTH = GameLevel.WIDTH / 16;
    private static final int BLOCK_HEIGHT = GameLevel.HEIGHT / 24;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(-45, 6));
        velocities.add(Velocity.fromAngleAndSpeed(45, 6));

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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                //draw the green background
                d.setColor(new Color(42, 130, 21));
                d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

                //drawing the building
                int screenWidth = (GameLevel.WIDTH - 2 * GameLevel.BORDER_SIZE);

                int buildingX = GameLevel.BORDER_SIZE + screenWidth / 15;
                int buildingY = 2 * GameLevel.HEIGHT / 3;

                int windowWidth = screenWidth / 56 % 2 == 1 ? screenWidth / 56 + 1 : screenWidth / 56;
                int windowHeight = GameLevel.HEIGHT / 18;

                int buildingWidth = windowWidth * 8;
                int buildingHeight = GameLevel.HEIGHT / 3;

                d.setColor(Color.BLACK);
                d.fillRectangle(buildingX, buildingY, buildingWidth, buildingHeight);

                d.setColor(new Color(225, 244, 246));

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(buildingX + (windowWidth / 2) * (j + 1) + windowWidth * j,
                                buildingY + (windowHeight / 4) * (i + 1) + windowHeight * i,
                                windowWidth, windowHeight);
                    }
                }

                d.setColor(new Color(62, 58, 57));
                int subBuildingWidth = buildingWidth / 3;
                int subBuildingHeight = buildingHeight / 4;
                int subBuildingX = buildingX + subBuildingWidth;
                int subBuildingY = buildingY - subBuildingHeight;
                d.fillRectangle(subBuildingX, subBuildingY, subBuildingWidth, subBuildingHeight);

                d.setColor(new Color(78, 74, 73));
                int pollWidth = subBuildingWidth / 3;
                int pollHeight = buildingHeight;
                int pollX = subBuildingX + pollWidth;
                int pollY = subBuildingY - pollHeight;
                d.fillRectangle(pollX, pollY, pollWidth, pollHeight);

                int centerX = pollX + pollWidth / 2;
                int centerY = pollY;
                int radius = pollWidth;

                d.setColor(new Color(216, 172, 102));
                d.fillCircle(centerX, centerY, radius);

                d.setColor(new Color(246, 77, 54));
                d.fillCircle(centerX, centerY, 3 * radius / 5);

                d.setColor(Color.WHITE);
                d.fillCircle(centerX, centerY, radius / 5);
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

        Color[] colors = {Color.WHITE, Color.BLUE, Color.YELLOW, Color.RED, Color.GRAY};

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < j + 6; i++) {
                Block block = new Block(new Rectangle(new Point(
                        (GameLevel.WIDTH - GameLevel.BORDER_SIZE - BLOCK_WIDTH - 1) - BLOCK_WIDTH * i,
                2 * GameLevel.HEIGHT / 5 - BLOCK_HEIGHT * j), BLOCK_WIDTH, BLOCK_HEIGHT), colors[j]);
                blocks.add(block);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
