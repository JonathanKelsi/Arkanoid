import animations.AnimationRunner;
import biuoop.GUI;
import levels.GameLevel;
import levels.GameFlow;
import levels.DirectHit;
import levels.WideEasy;
import levels.Green3;
import levels.FinalFour;
import levels.LevelInformation;
import java.util.List;
import java.util.ArrayList;

/**
 * This class activates the game.
 */
public class Game {
    /**
     * run the game.
     * @param args a string of arguments.
     */
    public static void main(String[] args) {
        //create the essential elements for the game
        GUI gui = new GUI("Arkanoid Game", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui, GameLevel.FPS);
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor(), gui);

        //create the levels
        List<LevelInformation> levels = new ArrayList<>();

        for (String arg: args) {
            int level = 0;

            try {
                level = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                //do nothing
            }

            if (level == 1) {
                levels.add(new DirectHit());
            } else if (level == 2) {
                levels.add(new WideEasy());
            } else if (level == 3) {
                levels.add(new Green3());
            } else if (level == 4) {
                levels.add(new FinalFour());
            }
        }

        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }

        //run the game
        gameFlow.runLevels(levels);
    }
}
