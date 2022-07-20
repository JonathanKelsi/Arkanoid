package animations;

import gameObjects.SpriteCollection;
import biuoop.DrawSurface;
import levels.GameLevel;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds,
 * and on top of them it will show a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int count;
    private int framesPerSecond;
    private int framesPassed = 0;
    private SpriteCollection gamescreen;

    /**
     * Constructor.
     * @param numOfSeconds the number of seconds the animation should last
     * @param countFrom where should it count from
     * @param gameScreen the game's screen
     * @param framesPerSecond number of frames the game shows every second.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, int framesPerSecond) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.count = countFrom;
        this.gamescreen = gameScreen;
        this.framesPerSecond = framesPerSecond;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw the game's screen
        this.gamescreen.drawAllOn(d);

        //draw the countdown
        d.setColor(new Color(106, 211, 152));
        if (count == 0) {
            d.drawText(2 * GameLevel.BORDER_SIZE, d.getHeight() / 2, "GO", 200);
        } else {
            d.drawText(2 * GameLevel.BORDER_SIZE, d.getHeight() / 2, this.count + "...", 200);
        }

        //show the number only for the wanted amount of time
        if (framesPassed == (int) (framesPerSecond * numOfSeconds / countFrom)) {
            this.count--;
            framesPassed = 0;
        }

        this.framesPassed++;
    }

    @Override
    public boolean shouldStop() {
        return this.count == -1;
    }
}
