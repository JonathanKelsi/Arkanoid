package animations.screens;

import animations.Animation;
import biuoop.DrawSurface;
import other.Counter;
import java.awt.Color;

/**
 * The YouWinAnimation class is in charge of letting the player know he losts.
 */
public class GameOverAnimation implements Animation {
    private Counter score;

    /**
     * Constructor.
     * @param score the game's score.
     */
    public GameOverAnimation(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(1, 1, d.getWidth(), d.getHeight());

        d.setColor(new Color(136, 8, 8));
        d.drawText(d.getWidth() / 2 - 165, d.getHeight() / 3, "Game Over", 68);

        int n = ((int) (Math.log10(score.getValue() + 1) + 1) + 7) * 7;
        d.drawText(d.getWidth() / 2 - 50 - n, 5 * d.getHeight() / 12, "Your score is: " + score.getValue(), 32);

        d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2, "press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
