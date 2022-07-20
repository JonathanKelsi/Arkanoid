package animations;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor.
     * @param gui the GUI.
     * @param framesPerSecond number of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Run the game -- start the animation loop.
     * @param animation
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
