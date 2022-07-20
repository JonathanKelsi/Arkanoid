package levels;

import animations.AnimationRunner;
import animations.screens.GameOverAnimation;
import animations.screens.KeyPressStoppableAnimation;
import animations.screens.YouWinAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import other.Counter;
import java.util.List;

/**
 * This GameFlow class is in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private Counter scoreCounter;
    private GUI gui;
    private KeyboardSensor keyboard;
    private AnimationRunner animationRunner;


    /**
     * Constructor.
     * @param ar animation runner.
     * @param ks keyboard sensor.
     * @param gui the gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboard = ks;
        this.gui = gui;
        this.scoreCounter = new Counter();
    }

    /**
     * creating the different levels, and moving from one level to the next.
     * @param levels the array of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean didWin = true;

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.gui, this.keyboard, this.animationRunner, scoreCounter);
            level.initialize();
            level.run();

            if (level.getNumberOfBalls() == 0) {
                didWin = false;
                break;
            }

            scoreCounter.increase(100);
        }

        if (didWin) {
            animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new YouWinAnimation(this.scoreCounter)));
        } else {
            animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new GameOverAnimation(this.scoreCounter)));
        }

        gui.close();
    }
}
